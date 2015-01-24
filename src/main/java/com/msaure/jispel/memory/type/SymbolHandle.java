package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;

public class SymbolHandle extends Handle {
    
    private String symbolName;
    
    public SymbolHandle() {
        super(NodeType.SYMBOL);
    }
    
    public SymbolHandle(String name) {
        super(NodeType.SYMBOL);
        this.symbolName = name;
    }

    public void setSymbolName(String name) {
        this.symbolName = name;
    }
    
    @Override
    public String stringValue() throws TypeException {
        return this.symbolName;
    }
    
    
}
