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
package org.jsmiparser.util.url;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ClassPathURLListFactoryTest {

    @Test
    public void testClassPathURLListFactory() {
        ClassPathURLListFactory factory = new ClassPathURLListFactory();
        List<String> children = createChildren();
        factory.setChildren(children);
        List<URL> urls = factory.create();
        assertEquals(1, urls.size());

        factory = new ClassPathURLListFactory("");
        children = createChildren();
        factory.setChildren(children);
        urls = factory.create();
        assertEquals(1, urls.size());

        children = createChildren();
        factory = new ClassPathURLListFactory("", children);
        ClassLoader classloader = Thread.currentThread()
                .getContextClassLoader();
        factory.setClassLoader(classloader);
        urls = factory.create();
        assertEquals(1, urls.size());

    }

    private List<String> createChildren() {
        List<String> children = new ArrayList<String>();
        children.add("log4j.properties");
        return children;
    }
}
