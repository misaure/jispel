package com.msaure.jispel.parser;

import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.NodeFactory;
import java.io.Reader;

public class SimpleReader implements LispelReader {

    private NodeFactory nodeFactory;
    private Token currentToken;
    private int indent;
    private Context ctx;
    
    @Override
    public Handle read(Reader stream) {
        assert null != stream;
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Handle readList(Reader stream) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Handle readVector(Reader stream) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Handle readAtom(Reader stream) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
