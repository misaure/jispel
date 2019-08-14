package com.msaure.jispel.parser.impl;

import static org.assertj.core.api.Assertions.*;
import com.msaure.jispel.parser.Token;
import com.msaure.jispel.parser.TokenFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultTokenFactoryTest {

    DefaultTokenFactory tokenFactory;

    @Before
    public void setUp() {
        tokenFactory = new DefaultTokenFactory();
    }

    @Test
    public void thatItCreatesRightParenthesisTokens() {
        Token t = tokenFactory.rightParenthesis();

        assertThat(t.getTokennum()).isEqualTo(Token.TokenType.RPAREN);
    }

    @Test
    public void thatItCreatesLeftParenthesisTokens() {
        Token t = tokenFactory.leftParenthesis();

        assertThat(t.getTokennum()).isEqualTo(Token.TokenType.LPAREN);
    }

    @Test
    public void thatItCreatesFalseTokens() {
        Token t = tokenFactory.falseToken();

        assertThat(t.getTokennum()).isEqualTo(Token.TokenType.FALSE);
    }

    @Test
    public void thatItCreatesTrueTokens() {
        Token t = tokenFactory.trueToken();

        assertThat(t.getTokennum()).isEqualTo(Token.TokenType.TRUE);
    }

    @Test
    public void thatItCreatesIdentifierTokens() {
        Token t = tokenFactory.identifierToken("my-identifier");

        assertThat(t.getTokennum()).isEqualTo(Token.TokenType.ID);
        assertThat(t.getLexval()).isEqualTo("my-identifier");
    }
}