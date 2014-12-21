package com.msaure.jispel.parser;

import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.NodeFactory;
import java.io.Reader;

public class SimpleReader implements LispelReader {

    private final Context ctx;
    private final NodeFactory nodeFactory;
    private Token currentToken;

    /**
     Initialize a reader to use a certain NodeFactory instance. A node factory
     objects is a kind of memory cell dispenser, in a classical lisp
     implementation this would be e.g the heap. The default implementation
     provided by this class implements a simple recursive descent parser. This
     can, of course, be changed to some more efficient implementation by
     subclassing the Reader class and passing the derived class to the
     interpreter.
     @param nodeFactory A NodeFactory instance which produces tagged memory
     cells from tokens.
     */
    public SimpleReader(Context ctx, NodeFactory nodeFactory) {
        this.ctx = ctx;
        this.nodeFactory = nodeFactory;
    }

    @Override
    public Handle read(Reader r) {
        return null;
    }

    @Override
    public Handle readList(Reader r) {
        return null;
    }

    @Override
    public Handle readVector(Reader r) {
        return null;
    }

    @Override
    public Handle readAtom(Reader r) {
        return null;
    }
}
