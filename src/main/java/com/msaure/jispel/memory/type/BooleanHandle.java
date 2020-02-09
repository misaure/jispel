package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Constants;
import com.msaure.jispel.memory.Handle;

public class BooleanHandle extends Handle {
    
    private final boolean boolValue;
    
    protected BooleanHandle(BooleanBuilder b) {
        super(b);
        this.boolValue = b.boolValue;
    }

    public static BooleanHandle valueOf(boolean value) {
        return (BooleanHandle) (value? Constants.TRUE.asHandle() : Constants.FALSE.asHandle());
    }

    public boolean toBoolean() {
        return boolValue;
    }

    @Override
    public boolean booleanValue() {
        return boolValue;
    }

    @Override
    public String toString() {
        return boolValue? "#t" : "#f";
    }

    public static BooleanBuilder builder() {
        return new BooleanBuilder();
    }

    public static class BooleanBuilder extends HandleBuilder<BooleanHandle> {

        private boolean boolValue;

        public BooleanBuilder() {
            withNodeType(NodeType.BOOLEAN);
        }

        public BooleanBuilder withBooleanValue(boolean value) {
            this.boolValue = value;
            return this;
        }

        @Override
        public BooleanHandle build() {
            return new BooleanHandle(this);
        }
    }
}
