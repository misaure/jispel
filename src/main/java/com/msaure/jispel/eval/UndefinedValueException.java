package com.msaure.jispel.eval;

import com.msaure.jispel.core.RecoverableException;

public class UndefinedValueException extends RecoverableException {
    
    private static final long serialVersionUID = -2915262656389048532L;

    public UndefinedValueException() {
    }

    public UndefinedValueException(String symbolName) {
        super("undefined value " + symbolName);
    }

}
