package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;

public class DoubleHandle extends Handle {
    
    private double dblValue;
    
    public DoubleHandle() {
        super(NodeType.DOUBLE);
    }
    
    @Override
    public double doubleValue() {
        return this.dblValue;
    }
}
