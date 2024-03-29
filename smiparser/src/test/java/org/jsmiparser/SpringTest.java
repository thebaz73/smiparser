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
package org.jsmiparser;

import org.jsmiparser.smi.SmiMib;
import org.junit.Test;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.util.StopWatch;

public class SpringTest extends AbstractDependencyInjectionSpringContextTests {

    private SmiMib m_mib;

    @Override
    protected String getConfigPath() {
        return "/org/jsmiparser/SpringTest.xml";
    }

    public SmiMib getMib() {
        return m_mib;
    }

    public void setMib(SmiMib mib) {
        m_mib = mib;
    }

    @Test
    public void test() {
        // assertEquals(44, getMib().getScalars().size());
        // assertEquals(57, getMib().getColumns().size());
        System.err.println(getMib().getScalars().size());
        System.err.println(getMib().getColumns().size());

    }

    @Test
    public void testPperformance() {
        int times = 10000000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("query for " + times + " times");

        for (int i = 0; i < times; i++) {
            getMib().getOidValues().find("snmpOutPkts");
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
