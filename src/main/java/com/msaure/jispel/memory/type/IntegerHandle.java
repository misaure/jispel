package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;
import org.jetbrains.annotations.NotNull;

public class IntegerHandle extends Handle {

    private final int intValue;

    protected IntegerHandle(IntegerBuilder b) {
        super(b);
        this.intValue = b.intValue;
    }

    @Override
    public int integerValue() {
        return this.intValue;
    }

    @NotNull public static IntegerHandle valueOf(int intValue) {
        return IntegerHandle.builder()
                .withIntegerValue(intValue)
                .build();
    }

    @NotNull public static IntegerBuilder builder() {
        return new IntegerBuilder();
    }

    public static class IntegerBuilder extends HandleBuilder<IntegerHandle> {
        private int intValue;

        public IntegerBuilder() {
            withNodeType(NodeType.INTEGER);
        }

        public IntegerBuilder withIntegerValue(int intValue) {
            this.intValue = intValue;
            return this;
        }

        @Override
        @NotNull public IntegerHandle build() {
            return new IntegerHandle(this);
        }
    }
}
