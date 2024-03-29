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

import org.jsmiparser.util.token.IdToken;

public class SmiTextualConvention extends SmiType {
    private String displayHint;
    private StatusV2 statusV2;
    private String description;
    private String reference;

    public SmiTextualConvention(IdToken idToken, SmiModule module,
                                String displayHint, StatusV2 statusV2, String description,
                                String reference) {
        super(idToken, module);
        this.displayHint = displayHint;
        this.statusV2 = statusV2;
        this.description = description;
        this.reference = reference;
    }

    public String getDisplayHint() {
        return displayHint;
    }

    public void setDisplayHint(String displayHint) {
        this.displayHint = displayHint;
    }

    public StatusV2 getStatusV2() {
        return statusV2;
    }

    public void setStatusV2(StatusV2 statusV2) {
        this.statusV2 = statusV2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
