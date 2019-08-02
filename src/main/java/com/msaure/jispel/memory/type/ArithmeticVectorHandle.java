package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;
import org.jetbrains.annotations.NotNull;

public class ArithmeticVectorHandle extends Handle {

    private double[] data;
    private int length;
    
    private ArithmeticVectorHandle(ArithmeticVectorBuilder b) {
        super(b);
    }

    @NotNull public static ArithmeticVectorBuilder builder() {
        return new ArithmeticVectorBuilder();
    }

    public static class ArithmeticVectorBuilder extends HandleBuilder<ArithmeticVectorHandle> {

        public ArithmeticVectorBuilder() {
            withNodeType(NodeType.AVECTOR);
        }

        @NotNull
        @Override
        public ArithmeticVectorHandle build() {
            return new ArithmeticVectorHandle(this);
        }
    }
}
