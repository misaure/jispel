package com.msaure.jispel.eval;

public class InternalInconsistencyException extends RuntimeException {
    
    private static final long serialVersionUID = 1802656169459657701L;

    public InternalInconsistencyException() {
    }

    public InternalInconsistencyException(String message) {
        super(message);
    }

}
