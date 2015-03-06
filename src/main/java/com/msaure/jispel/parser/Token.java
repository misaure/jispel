package com.msaure.jispel.parser;

/**
   <b>FIXME:</b> add pos in source code
   General token and scanner class.
   @version 0.2
*/
public class Token {
    
    public static final Token EOF = new Token(TokenType.EOF, "");
    public static final Token LPAREN = new Token(Token.TokenType.LPAREN, "(");
    public static final Token RPAREN = new Token(Token.TokenType.RPAREN, ")");
    public static final Token QUOTE = new Token(Token.TokenType.QUOTECHAR, "'");
    
    private TokenType tokennum;
    private String lexval;
    
    public static enum TokenType {
        EOF, LPAREN, RPAREN, ID, KEYWORD,
        INT, DOUBLE, STRING, VECSTART,
        OPERATOR, ERROR, FALSE, TRUE, CHARACTER, 
        QUOTECHAR, MAX;
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
        this.tokennum = tokennum;
        this.lexval = lexval;
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

    public TokenType getTokennum() {
        return tokennum;
    }

    public void setTokennum(TokenType tokennum) {
        this.tokennum = tokennum;
    }

    public String getLexval() {
        return lexval;
    }

    public void setLexval(String lexval) {
        this.lexval = lexval;
    }
    
    
    @Override
    public String toString() {
        return null;
    }

}
