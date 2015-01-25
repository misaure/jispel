package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;

public class StringHandle extends Handle {

    private final String stringValue;
    
    public StringHandle(String stringValue) {
        super(NodeType.STRING);
        this.stringValue = stringValue;
    }
    
    @Override
    public String stringValue() throws TypeException {
        return this.stringValue;
    }
    
}
