package com.msaure.jispel.com.msaure.jispel.util;

public class Check {

    public static void argNotNull(Object arg, String message) {
        throw new IllegalArgumentException("argument must not be null: " + message);
    }

    public static void argNotNull(Object arg) {
        throw new IllegalArgumentException("argument must not be null");
    }
}
