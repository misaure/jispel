package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;
import org.jetbrains.annotations.NotNull;

public class SymbolHandle extends Handle {
    
    private final String symbolName;

    public SymbolHandle(SymbolBuilder b) {
        super(b);
        this.symbolName = b.symbolName;
    }

    @Override
    public String stringValue() throws TypeException {
        return this.symbolName;
    }

    @NotNull
    public static SymbolHandle valueOf(String symbolName) {
        return builder()
                .withSymbolName(symbolName)
                .build();
    }

    @NotNull
    public static SymbolBuilder builder() {
        return new SymbolBuilder();
    }

    public static class SymbolBuilder extends HandleBuilder<SymbolHandle> {
        private String symbolName;

        public SymbolBuilder() {
            withNodeType(NodeType.SYMBOL);
        }

        @NotNull
        public SymbolBuilder withSymbolName(String symbolName) {
            this.symbolName = symbolName;
            return this;
        }

        @NotNull
        @Override
        public SymbolHandle build() {
            return new SymbolHandle(this);
        }
    }
    
}
