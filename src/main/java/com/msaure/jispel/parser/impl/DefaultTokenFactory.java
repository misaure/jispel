package com.msaure.jispel.parser.impl;

import com.msaure.jispel.parser.Token;
import com.msaure.jispel.parser.TokenFactory;

public class DefaultTokenFactory implements TokenFactory {

    private enum Singletons {

       LPAREN(Token.create()
                .withTokenType(Token.TokenType.LPAREN)
                .withLexicalValue("(")
                .build()),

        RPAREN(Token.create()
                .withTokenType(Token.TokenType.RPAREN)
                .withLexicalValue(")")
                .build()),

        FALSE(Token.create()
                .withTokenType(Token.TokenType.FALSE)
                .withLexicalValue("#f")
                .build()),

        TRUE(Token.create()
                .withTokenType(Token.TokenType.TRUE)
                .withLexicalValue("#t")
                .build());

       private final Token tokenValue;

       Singletons(Token t) {
           this.tokenValue = t;
       }

       public Token getTokenValue() {
           return tokenValue;
       }
    }

    @Override
    public Token leftParenthesis() {
        return Singletons.LPAREN.getTokenValue();
    }

    @Override
    public Token rightParenthesis() {
        return Singletons.RPAREN.getTokenValue();
    }

    @Override
    public Token identifierToken(String name) {
        // TODO internalize lexical value of tokens
        return Token.create()
                .withTokenType(Token.TokenType.ID)
                .withLexicalValue(name)
                .build();
    }

    @Override
    public Token falseToken() {
        return Singletons.FALSE.getTokenValue();
    }

    @Override
    public Token trueToken() {
        return Singletons.TRUE.getTokenValue();
    }
}
