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

import java.io.PrintStream;

import org.jsmiparser.util.location.Location;

public class DefaultProblemEventHandler extends AbstractProblemEventHandler {
    private PrintStream out;
    private PrintStream err;

    public DefaultProblemEventHandler() {
        super();
        out = System.out;
        err = System.err;
    }

    public DefaultProblemEventHandler(PrintStream out, PrintStream err) {
        this.out = out;
        this.err = err;
    }

    public void handle(ProblemEvent event) {
        super.handle(event);
        switch (event.getSeverity()) {
            case ERROR:
                error(event.getLocation(), event.getLocalizedMessage());
                break;
            case FATAL:
                error(event.getLocation(), event.getLocalizedMessage());
                break;
            case WARNING:
                warning(event.getLocation(), event.getLocalizedMessage());
                break;
        }
    }

    private void error(Location location, String localizedMessage) {
        print(err, "Error", location, localizedMessage);
    }

    private void warning(Location location, String localizedMessage) {
        print(out, "Warning", location, localizedMessage);
    }

    private void print(PrintStream stream, String sev, Location location,
                       String localizedMessage) {
        String loc = location != null ? location.toString() : null;
        stream.println(sev + ": file://" + loc + " :" + localizedMessage);
    }
}
