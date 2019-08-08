package com.msaure.jispel.parser.impl;

import com.msaure.jispel.parser.Token;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class DefaultLexerTest {

    @Test
    public void test1() throws IOException {
        DefaultLexer lexer = lexer("12");

        Token t = lexer.nextToken();

        assertNotNull(t);
        assertEquals(Token.TokenType.INT, t.getTokennum());
    }

    @Test
    public void test2() throws IOException {
        DefaultLexer lexer = lexer("a");

        Token t = lexer.nextToken();

        assertNotNull(t);
        assertEquals(Token.TokenType.ID, t.getTokennum());
    }

    @Test
    public void test3() throws IOException {
        DefaultLexer lexer = lexer("abcde123");

        Token t = lexer.nextToken();

        assertNotNull(t);
        assertEquals(Token.TokenType.ID, t.getTokennum());
    }

    @Test
    public void test4() throws IOException {
        DefaultLexer lexer = lexer("(");

        Token t = lexer.nextToken();

        assertNotNull(t);
        assertEquals(Token.TokenType.LPAREN, t.getTokennum());
    }

    @Test
    public void test5() throws IOException {
        DefaultLexer lexer = lexer(")");

        Token t = lexer.nextToken();

        assertNotNull(t);
        assertEquals(Token.TokenType.RPAREN, t.getTokennum());
    }

    private static DefaultLexer lexer(String input) throws IOException {
        final StringReader r = new StringReader(input);
        final DefaultLexer lexer = new DefaultLexer(r);

        return lexer;
    }
}