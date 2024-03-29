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
package org.jsmiparser.util.token;

import org.jsmiparser.util.location.Location;

public class QuotedStringToken extends StringToken {

    private char quoteChar;

    public QuotedStringToken(Location location, String value, char quoteChar) {
        super(location, strip(value, quoteChar));
        this.quoteChar = quoteChar;
    }

    private static String strip(String str, char quoteChar) {
        if (str.charAt(0) == quoteChar
                && str.charAt(str.length() - 1) == quoteChar) {
            return str.substring(1, str.length() - 1);
        }
        throw new IllegalArgumentException(str + " is not a valid " + quoteChar
                + "-quoted string ");
    }

    public char getQuoteChar() {
        return quoteChar;
    }

    @Override
    public String toString() {
        return quoteChar + value + quoteChar;
    }
}
