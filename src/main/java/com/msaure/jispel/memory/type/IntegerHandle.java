package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;

public class IntegerHandle extends Handle {

    private int intValue;

    public IntegerHandle() {
        super(NodeType.INTEGER);
    }
    
    public IntegerHandle(int intValue) {
        super(NodeType.INTEGER);
        this.intValue = intValue;
    }

    @Override
    public int integerValue() {
        return this.intValue;
    }
}
