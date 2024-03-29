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

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeURLListFactory implements URLListFactory {
    private List<URLListFactory> children;

    public CompositeURLListFactory() {
        this(new ArrayList<URLListFactory>());
    }

    public CompositeURLListFactory(List<URLListFactory> children) {
        this.children = children;
    }

    public CompositeURLListFactory(URLListFactory... urlListFactories) {
        this(Arrays.asList(urlListFactories));
    }

    public List<URLListFactory> getChildren() {
        return children;
    }

    public void setChildren(List<URLListFactory> children) {
        this.children = children;
    }

    public List<URL> create() throws Exception {
        List<URL> result = new ArrayList<URL>();
        for (URLListFactory child : children) {
            result.addAll(child.create());
        }
        return result;
    }
}
