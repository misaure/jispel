package com.msaure.jispel.memory;

public class TypeException extends Exception {
    
    private static final long serialVersionUID = 996171870492960567L;

    public TypeException(String typeName) {
        super(typeName);
    }
}
