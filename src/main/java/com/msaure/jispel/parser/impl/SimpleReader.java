package com.msaure.jispel.parser.impl;

import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.NodeFactory;
import com.msaure.jispel.memory.type.IntegerHandle;
import com.msaure.jispel.parser.Lexer;
import com.msaure.jispel.parser.LispelReader;
import com.msaure.jispel.parser.Token;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

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
    public SimpleReader(@NotNull  Context ctx, @NotNull NodeFactory nodeFactory) {
        this.ctx = ctx;
        this.nodeFactory = nodeFactory;
    }

    @Override
    public Handle read(Lexer r) {
        try {
            currentToken = r.nextToken();

            if (Token.TokenType.LPAREN == currentToken.getTokennum()) {
                return readList(r);

            } else if (Token.TokenType.VECSTART == currentToken.getTokennum()) {
                return readVector(r);

            } else {
                return readAtom(r);
            }
        } catch (IOException e) {
            // FIXME handle exception properly
            e.printStackTrace();
        }

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
        Token t = nextToken(r);

        switch (t.getTokennum()) {
            case INT:
                return nodeFactory.makeInteger(Integer.valueOf(currentToken.getLexval()));
            case TRUE:
                return nodeFactory.makeBoolean(true);
            case FALSE:
                return nodeFactory.makeBoolean(false);
            case ID:
                return nodeFactory.makeSymbol(currentToken.getLexval());
        }

        // FIXME handle unprocessable token type
        return null;
    }

    private Token nextToken(Lexer r) {
        if (currentToken == null) {
            try {
                currentToken = r.nextToken();
            } catch (IOException e) {
                throw new ReadException("cannot read input", e);
            }
        }

        return currentToken;
    }
}
