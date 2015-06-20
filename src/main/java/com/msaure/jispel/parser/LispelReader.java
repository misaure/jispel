package com.msaure.jispel.parser;

import com.msaure.jispel.memory.Handle;
import java.io.Reader;

public interface LispelReader {
    
    class ReadException extends RuntimeException {
		private static final long serialVersionUID = -3115344325832221093L;

		public ReadException(String msg) {
            super(msg);
        }
    }
    
    Handle read(Reader stream);
    Handle readList(Reader stream);
    Handle readVector(Reader stream);
    Handle readAtom(Reader stream);
}
