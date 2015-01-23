package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;

public class BooleanHandle extends Handle {
    
    private boolean boolValue;
    
    public BooleanHandle() {
        super(NodeType.BOOLEAN);
    }

    public boolean isBoolValue() {
        return boolValue;
    }

    public void setBoolValue(boolean boolValue) {
        this.boolValue = boolValue;
    }

}
