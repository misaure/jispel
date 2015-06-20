package com.msaure.jispel.core;

public class RecoverableException extends Exception {
    
    private static final long serialVersionUID = 643103044630055891L;
    
    public RecoverableException() {
        
    }
    
    public RecoverableException(String message) {
        super(message);
    }
    
    public RecoverableException(Throwable t) {
    	super(t);
    }
}
