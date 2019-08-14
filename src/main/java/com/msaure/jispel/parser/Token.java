package com.msaure.jispel.parser;

/**
   <b>FIXME:</b> add pos in source code
   General token and scanner class.
   @version 0.2
*/
public class Token {
    
    public static final Token EOF = token().withTokenType(TokenType.EOF).withLexicalValue("<EOF>").build();
    public static final Token QUOTE = token().withTokenType(TokenType.QUOTECHAR).withLexicalValue("'").build();
    public static final Token FALSE = token().withTokenType(TokenType.FALSE).withLexicalValue("#f").build();
    public static final Token TRUE = token().withTokenType(TokenType.TRUE).withLexicalValue("#t").build();
    
    private final TokenType tokennum;
    private final String lexval;
    
    public enum TokenType {
        EOF, LPAREN, RPAREN, ID, KEYWORD,
        INT, DOUBLE, STRING, VECSTART,
        OPERATOR, ERROR, FALSE, TRUE, CHARACTER, 
        QUOTECHAR, MAX;
    }

    public Token(TokenType tokenType, String lexicalValue) {
        this.tokennum = tokenType;
        this.lexval = lexicalValue;
    }

    /**
     * Initializes a token instance to a certain type.
     */
    protected Token( Builder b) {
        this.tokennum = b.tokenType;
        this.lexval = b.lexicalValue;
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
    This method is a hook for manipulating a symbol's lexical value
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

    public String getLexval() {
        return lexval;
    }
  
    public static Builder create() {
        return new Builder();
    }
    
    public static Builder token() {
        return create();
    }
    
    @Override
    public String toString() {
        return null;
    }

    public static class Builder {
        private String lexicalValue;
        private TokenType tokenType;
        
        public Builder withLexicalValue(String lexicalValue) {
            this.lexicalValue = lexicalValue;
            return this;
        }
        
        public Builder withTokenType(TokenType tokenType) {
            this.tokenType = tokenType;
            return this;
        }
        
        public Token build() {
            if (this.tokenType == null) {
                throw new IllegalStateException("cannot create token without token type definition");
            }
            
            return new Token(this);
        }
    }
}
