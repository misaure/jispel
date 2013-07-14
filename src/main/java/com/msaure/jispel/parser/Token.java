package com.msaure.jispel.parser;

/**
   <b>FIXME:</b> add pos in source code
   General token and scanner class.
   @version 0.2
*/
public class Token {
    
    private TokenType tokennum;
    private String lexval;
    
    public static enum TokenType {
        ttEOF, ttLPAREN, ttRPAREN, ttID, ttKEYWORD,
        ttINT, ttDOUBLE, ttSTRING, ttVECSTART,
        ttOPERATOR, ttERROR, ttFALSE, ttTRUE, ttCHARACTER, 
        ttQUOTECHAR, ttMAX;
    }
    
    /**
     * Create a token instance which is marked invalid (token number -1).
     */
    public Token() {
    }

    /**
     * Initializes a token instance to a certain type.
     * 
     * @param tokennum A numeric tag specifying the token's type.
     * @param lexval The input read by the scanner.
     */
    public Token( TokenType tokennum, String lexval) {
    }
    
    public TokenType tokennum() {
        return null;
    }
    
    public boolean hasType(TokenType t) {
        return false;
    }
    
    public String lexval() {
        return null;
    }
    
      /**
    This method is a hook for manipulating a symbol's lexical value before
    after it has been read and before it is passed back to the caller. 
    Possible applications of this method include building an atom table to
    save memory space and speed up symbol comparisons or simply convert all
    symbol to lower case to build a case insensitive interpreter.<p>
    The current implementation is a no-op.
    @param s Symbol's lexical value
    @return Converted value, which in the current implementation is the 
    identical to the argument's value.
   */
    public String convertSymbol(String s) {
        return null;
    }
    
    
    @Override
    public String toString() {
        return null;
    }
}
