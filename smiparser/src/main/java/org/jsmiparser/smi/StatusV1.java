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

public enum StatusV1 {
    MANDATORY(StatusAll.MANDATORY),
    OPTIONAL(StatusAll.OPTIONAL),
    OBSOLETE(StatusAll.OBSOLETE),
    DEPRECATED(StatusAll.DEPRECATED);

    private StatusAll statusAll;

    private StatusV1(StatusAll statusAll) {
        this.statusAll = statusAll;
    }

    public StatusAll getStatusAll() {
        return statusAll;
    }

    public String toString() {
        return statusAll.toString();
    }

    public static StatusV1 find(String keyword, boolean mandatory) {
        return Util.find(StatusV1.class, keyword, mandatory);
    }
}
