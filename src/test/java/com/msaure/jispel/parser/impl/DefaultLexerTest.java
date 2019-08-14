package com.msaure.jispel.parser.impl;

import com.msaure.jispel.parser.Token;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import static org.assertj.core.api.Assertions.*;

import static org.junit.Assert.*;

public class DefaultLexerTest {

    @Test
    public void thatItScansSimpleInteger() throws IOException {
        DefaultLexer lexer = lexer("12");

        Token t = lexer.nextToken();

        assertNotNull(t);
        assertEquals(Token.TokenType.INT, t.getTokennum());
    }

    @Test
    public void thatItScansSingleCharacterIdentifiers() throws IOException {
        DefaultLexer lexer = lexer("a");

        Token t = lexer.nextToken();

        assertNotNull(t);
        assertEquals(Token.TokenType.ID, t.getTokennum());
    }

    @Test
    public void thatItScansAlphanumericIdentifier() throws IOException {
        DefaultLexer lexer = lexer("abcde123");

        Token t = lexer.nextToken();

        assertNotNull(t);
        assertEquals(Token.TokenType.ID, t.getTokennum());
        assertThat(t.getLexval()).isEqualTo("abcde123");
    }

    @Test
    public void thatItScansIdentifierContainingHyphens() throws IOException {
        DefaultLexer lexer = lexer("abc-def");

        Token t = lexer.nextToken();

        assertNotNull(t);
        assertThat(t.getTokennum()).isEqualTo(Token.TokenType.ID);
        assertThat(t.getLexval()).isEqualTo("abc-def");
    }

    @Test
    public void thatItScansLeftParenthesis() throws IOException {
        DefaultLexer lexer = lexer("(");

        Token t = lexer.nextToken();

        assertNotNull(t);
        assertEquals(Token.TokenType.LPAREN, t.getTokennum());
    }

    @Test
    public void thatItScansRightParenthesis() throws IOException {
        DefaultLexer lexer = lexer(")");

        Token t = lexer.nextToken();

        assertNotNull(t);
        assertEquals(Token.TokenType.RPAREN, t.getTokennum());
    }

    @Test
    public void thatItScansFalseLiteral() throws IOException {
        DefaultLexer lexer = lexer("#f");

        Token t = lexer.nextToken();

        assertNotNull(t);
        assertThat(t.getTokennum()).isEqualTo(Token.TokenType.FALSE);
        assertThat(t.getLexval()).isEqualTo("#f");
    }

    @Test
    public void thatItScansTrueLiteral() throws IOException {
        DefaultLexer lexer = lexer("#t");

        Token t = lexer.nextToken();

        assertThat(t).isNotNull();
        assertThat(t.getTokennum()).isEqualTo(Token.TokenType.TRUE);
        assertThat(t.getLexval()).isEqualTo("#t");
    }

    @Test
    @Ignore("work in progress")
    public void thatItScansSimpleStrings() throws IOException {
        DefaultLexer lexer = lexer("\"abc\"");

        Token t = lexer.nextToken();

        assertThat(t).isNotNull();
        assertThat(t.getTokennum()).isEqualTo(Token.TokenType.STRING);
        assertThat(t.getLexval()).isEqualTo("\"abc\"");
    }

    private static DefaultLexer lexer(String input) throws IOException {
        final StringReader r = new StringReader(input);
        final DefaultLexer lexer = new DefaultLexer(r);

        return lexer;
    }
}