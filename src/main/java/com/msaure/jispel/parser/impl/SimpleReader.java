package com.msaure.jispel.parser.impl;

import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.NodeFactory;
import com.msaure.jispel.parser.Lexer;
import com.msaure.jispel.parser.LispelReader;
import com.msaure.jispel.parser.Token;

public class SimpleReader implements LispelReader {

    private final Context ctx;
    private final NodeFactory nodeFactory;
    private Token currentToken;

    /**
     * Initialize a reader to use a certain NodeFactory instance.
     * 
     * A node factory
     * objects is a kind of memory cell dispenser, in a classical Lisp
     * implementation this would be e.g the heap. The default implementation
     * provided by this class implements a simple recursive descent parser. This
     * can, of course, be changed to some more efficient implementation by
     * subclassing the Reader class and passing the derived class to the
     * interpreter.
     * 
     * @param nodeFactory A NodeFactory instance which produces tagged memory
     * cells from tokens.
     */
    public SimpleReader(Context ctx, NodeFactory nodeFactory) {
        this.ctx = ctx;
        this.nodeFactory = nodeFactory;
    }

    @Override
    public Handle read(Lexer r) {
        return null;
    }

    @Override
    public Handle readList(Lexer r) {
        return null;
    }

    @Override
    public Handle readVector(Lexer r) {
        return null;
    }

    @Override
    public Handle readAtom(Lexer r) {
        return null;
    }
}
