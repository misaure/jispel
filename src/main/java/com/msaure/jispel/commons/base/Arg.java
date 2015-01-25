package com.msaure.jispel.commons.base;

public class Arg {

    public static void notNull(Object o, String argumentName) {
        if (null == o) {
            throw new IllegalArgumentException("argument " + argumentName + " must not be null");
        }
    }
    
    public static void notNull(Object o) {
        if (null == o) {
            throw new IllegalArgumentException("argument must not be null");
        }
    }
}
