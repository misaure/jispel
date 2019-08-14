package com.msaure.jispel.parser;

import com.msaure.jispel.memory.Handle;

public interface LispelReader {
    
    class ReadException extends RuntimeException {
		private static final long serialVersionUID = -3115344325832221093L;

		public ReadException(String msg) {
            super(msg);
        }

        public ReadException(String message, Throwable t) {
		    super(message, t);
        }
    }
    
    Handle read(Lexer stream);
    Handle readList(Lexer stream);
    Handle readVector(Lexer stream);
    Handle readAtom(Lexer stream);
}
