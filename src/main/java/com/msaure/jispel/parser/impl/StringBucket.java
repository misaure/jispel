package com.msaure.jispel.parser.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Scheme handles symbols similar to Java's interned Strings.
 */
public class StringBucket {

    private final Map<String,String> strings = new HashMap<>();

    public String internString(String s) {
        if (s == null) {
            return null;
        }

        if (!this.strings.containsKey(s)) {
            this.strings.put(s, s);
        }

        return this.strings.get(s);
    }
    
    public void releaseString(String s) {
        /// no-op ///
    }
}
