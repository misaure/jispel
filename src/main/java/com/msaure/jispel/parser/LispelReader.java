package com.msaure.jispel.parser;

import com.msaure.jispel.memory.Handle;

import java.io.Reader;

public interface LispelReader {

    /**
     Try to read the next expression from the parser given as an argument.
     @param r Scanner which provides the tokens for the parser.
     */
    Handle read(Reader r);


    Handle readList(Reader r);
    Handle readVector(Reader r);
    Handle readAtom(Reader r);
}
