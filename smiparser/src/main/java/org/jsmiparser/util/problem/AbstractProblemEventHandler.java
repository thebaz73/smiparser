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
package org.jsmiparser.util.problem;

import org.jsmiparser.util.problem.annotations.ProblemSeverity;

public abstract class AbstractProblemEventHandler implements
        ProblemEventHandler {

    private int[] severityCounters = new int[ProblemSeverity.values().length];
    private int totalCounter;

    public void handle(ProblemEvent event) {
        severityCounters[event.getSeverity().ordinal()]++;
        totalCounter++;
    }

    public boolean isOk() {
        for (int i = 0; i < severityCounters.length; i++) {
            if (i >= ProblemSeverity.ERROR.ordinal()) {
                int severityCounter = severityCounters[i];
                if (severityCounter > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isNotOk() {
        return !isOk();
    }

    public int getSeverityCount(ProblemSeverity severity) {
        return severityCounters[severity.ordinal()];
    }

    public int getTotalCount() {
        return totalCounter;
    }
}
