/**
 * Copyright 2011-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jsmiparser.smi;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SmiOidNode {

    private final SmiOidNode parentNode;
    Map<Integer, SmiOidNode> childMap = new TreeMap<Integer, SmiOidNode>();

    private List<SmiOidValue> values = new ArrayList<SmiOidValue>();
    private final int value;

    private int[] oidInArray;
    private String oidStr;

    public SmiOidNode(SmiOidNode parent, int value) {
        parentNode = parent;
        this.value = value;

        if (parentNode != null) {
            if (parentNode.childMap.get(value) != null) {
                throw new IllegalStateException();
            }
            parentNode.childMap.put(value, this);
        }
    }

    public SmiOidNode getParent() {
        return parentNode;
    }

    public Collection<? extends SmiOidNode> getChildren() {
        return childMap.values();
    }

    public List<SmiOidValue> getValues() {
        return values;
    }

    public int[] getOid() {
        return oidInArray;
    }

    public String getOidStr() {
        return oidStr;
    }

    /*
      * public int[] determineFullOid() { if (m_oid == null) { SmiOidNode parent
      * = getParent(); if (parent != null) { int[] parentOid =
      * parent.determineFullOid(); if (parentOid != null) { m_oid = new
      * int[parentOid.length + 1]; System.arraycopy(parentOid, 0, m_oid, 0,
      * parentOid.length); m_oid[m_oid.length-1] = getLastOid(); m_oidStr =
      * parent.getOidStr() + "." + getLastOid(); } else { m_oid = new int[]
      * {getLastOid()}; m_oidStr = String.valueOf(getLastOid()); } } } return
      * m_oid; }
      */

    public int getTotalChildCount() {
        int result = childMap.size();
        for (SmiOidNode child : childMap.values()) {
            result += child.getTotalChildCount();
        }
        return result;
    }

    public void dumpTree(PrintStream w, String indent) {
        w.print(indent);
        w.print(value);
        for (SmiOidValue value : values) {
            w.print(":");
            w.print(value.getId());
        }
        for (SmiOidNode child : childMap.values()) {
            child.dumpTree(w, indent + " ");
        }
    }

    public static SmiOidNode createRootNode() {
        return new SmiOidNode(null, -1);
    }

    public SmiOidNode findChild(int value) {
        return childMap.get(value);
    }

    public <T extends SmiOidValue> T getSingleValue(Class<T> clazz) {
        if (values.size() != 1) {
            throw new AssertionError("expected only a single value");
        }
        return clazz.cast(values.get(0));
    }

    public <T extends SmiOidValue> T getSingleValue(Class<T> clazz,
                                                    SmiModule module) {
        T result = null;
        for (SmiOidValue value : values) {
            if (value.getModule() == module && clazz.isInstance(value)) {
                if (result == null) {
                    result = clazz.cast(value);
                } else {
                    throw new IllegalArgumentException("more than one found");
                }
            }
        }
        return result;
    }

    public SmiOidValue getSingleValue() {
        if (values.size() != 1) {
            throw new AssertionError("expected only a single value");
        }
        return values.get(0);
    }

    /**
     * This method as such makes no sense (you can get the root node directly
     * from SmiMib), but it is used mainly for unit testing.
     *
     * @return The root of the oid tree.
     */
    public SmiOidNode getRootNode() {
        SmiOidNode parent = this;
        while (parent.getParent() != null) {
            parent = parent.getParent();
        }
        return parent;
    }

    public void dumpAncestors(PrintStream out) {
        SmiOidNode oidValue = this;
        while (oidValue != null) {
            out.print(oidValue.value);
            for (SmiOidValue value : values) {
                out.print(",");
                out.print(value.getId());
            }
            if (oidValue.getParent() != null) {
                out.print(": ");
            }
            oidValue = oidValue.getParent();
        }
        out.println();
    }

    public int[] determineFullOid() {
        if (oidInArray == null) {
            SmiOidNode parent = getParent();
            if (parent != null) {
                int[] parentOid = parent.determineFullOid();
                if (parentOid != null) {
                    oidInArray = new int[parentOid.length + 1];
                    System.arraycopy(parentOid, 0, oidInArray, 0, parentOid.length);
                    oidInArray[oidInArray.length - 1] = value;
                    oidStr = parent.getOidStr() + "." + value;
                } else {
                    oidInArray = new int[]{value};
                    oidStr = String.valueOf(value);
                }
            }
        }
        return oidInArray;
    }

    public boolean contains(SmiOidNode node) {
        return childMap.containsValue(node);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + oidStr;
    }
}
