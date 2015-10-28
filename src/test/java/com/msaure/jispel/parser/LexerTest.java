package com.msaure.jispel.parser;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

import org.junit.Test;

import com.msaure.jispel.parser.impl.DefaultLexer;

public class LexerTest {

    @Test//(timeout = 500)
    public void testReadingOfFalseLiteral() throws Exception {
        assertTokenType("#f", Token.TokenType.FALSE);
    }
    
    @Test//(timeout = 500)
    public void testReadingOfTrueLiteral() throws Exception {
        assertTokenType("#t", Token.TokenType.TRUE);
    }
    
    @Test//(timeout = 500)
    public void testReadingOfKeyword() throws Exception {
    }
    
    @Test
    public void testReadingOfIds() throws Exception {
        assertTokenType(":akey", Token.TokenType.KEYWORD);
    }
    
     @Test//(timeout = 500)
    public void testReadingOfUnsignedInteger() throws Exception {
        assertTokenType("1", Token.TokenType.INT);
        assertTokenType("123", Token.TokenType.INT);
    }
    
    protected Token assertTokenType(String input, Token.TokenType tokenType) throws IOException {
        final Lexer lexer = lexerForString(input);
        final Token token = lexer.nextToken();
        
        assertNotNull(token);
        assertEquals(tokenType, token.getTokennum());
        
        return token;
    }
    
    protected Lexer lexerForString(String input) throws IOException {
        return new DefaultLexer(new StringReader(input));
    }
}
