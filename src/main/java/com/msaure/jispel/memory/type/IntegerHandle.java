package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;

public class IntegerHandle extends Handle {

    private int intValue;

    public IntegerHandle() {
        super(NodeType.INTEGER);
    }

    @Override
    public int integerValue() {
        return this.intValue;
    }
}
