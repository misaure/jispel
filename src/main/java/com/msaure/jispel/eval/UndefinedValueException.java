package com.msaure.jispel.eval;

import com.msaure.jispel.core.RecoverableException;

public class UndefinedValueException extends RecoverableException {

    public UndefinedValueException() {        
    }
    
    public UndefinedValueException(String symbolName) {
        super("undefined value " + symbolName);
    }
    
}
