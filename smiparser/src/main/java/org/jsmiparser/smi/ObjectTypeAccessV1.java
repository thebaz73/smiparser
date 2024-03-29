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

public enum ObjectTypeAccessV1 implements AccessPermissions {
    READ_ONLY(AccessAll.READ_ONLY),
    READ_WRITE(AccessAll.READ_WRITE),
    WRITE_ONLY(AccessAll.WRITE_ONLY),
    NOT_ACCESSIBLE(AccessAll.NOT_ACCESSIBLE);

    private AccessAll accessAll;

    ObjectTypeAccessV1(AccessAll accessAll) {
        this.accessAll = accessAll;
    }

    public AccessAll getAccessAll() {
        return accessAll;
    }

    public String toString() {
        return accessAll.toString();
    }

    public static ObjectTypeAccessV1 find(String keyword, boolean mandatory) {
        return Util.find(ObjectTypeAccessV1.class, keyword, mandatory);
    }

    public boolean isCreateWritable() {
        return isWritable();
    }

    public boolean isReadable() {
        return this == READ_ONLY || this == READ_WRITE;
    }

    public boolean isWritable() {
        return this == WRITE_ONLY || this == READ_WRITE;
    }
}
