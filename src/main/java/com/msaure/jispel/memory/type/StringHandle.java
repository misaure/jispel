package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;
import org.jetbrains.annotations.NotNull;

public class StringHandle extends Handle {

    private final String stringValue;
    
    private StringHandle(StringBuilder b) {
        super(b);
        this.stringValue = b.stringValue;
    }

    public static StringHandle valueOf(String stringValue) {
        return builder()
                .withStringValue(stringValue)
                .build();
    }

    @Override
    public String stringValue() throws TypeException {
        return this.stringValue;
    }

    @NotNull
    public static StringBuilder builder() {
        return new StringBuilder();
    }

    public static class StringBuilder extends HandleBuilder<StringHandle> {
        private String stringValue;

        public StringBuilder() {
            withNodeType(NodeType.STRING);
        }

        @NotNull
        public StringBuilder withStringValue(String stringValue) {
            this.stringValue = stringValue;
            return this;
        }

        @NotNull
        @Override
        public StringHandle build() {
            return new StringHandle(this);
        }
    }
}
