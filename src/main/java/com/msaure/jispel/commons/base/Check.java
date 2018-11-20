package com.msaure.jispel.commons.base;

public class Check {

    public static void notNull(Object o, String argumentName) {
        if (null == o) {
            throw new NullPointerException("argument " + argumentName + " must not be null");
        }
    }
    
    public static void notNull(Object o) {
        if (null == o) {
            throw new NullPointerException("argument must not be null");
        }
    }
}
