package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;

public class ArithmeticVectorHandle extends Handle {

    private double[] data;
    private int length;
    
    public ArithmeticVectorHandle() {
        super(NodeType.AVECTOR);
    }
    
}
