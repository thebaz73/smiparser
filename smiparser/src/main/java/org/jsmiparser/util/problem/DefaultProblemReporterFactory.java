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

import java.lang.reflect.Proxy;

public class DefaultProblemReporterFactory implements ProblemReporterFactory {

    private final ClassLoader classLoader;
    private final ProblemEventHandler problemEventHandler;

    public DefaultProblemReporterFactory(ProblemEventHandler ph) {
        this.classLoader = Thread.currentThread().getContextClassLoader();
        problemEventHandler = ph;
    }

    public DefaultProblemReporterFactory(ClassLoader classLoader,
                                         ProblemEventHandler ph) {
        this.classLoader = classLoader;
        problemEventHandler = ph;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public ProblemEventHandler getProblemEventHandler() {
        return problemEventHandler;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> cl) {
        Class[] classArray = {cl};
        return (T) Proxy.newProxyInstance(classLoader, classArray,
                new ProblemInvocationHandler(cl, problemEventHandler));
    }
}
