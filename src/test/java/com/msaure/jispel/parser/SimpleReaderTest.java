package com.msaure.jispel.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.msaure.jispel.memory.SimpleNodeFactory;
import com.msaure.jispel.memory.TypeException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.NodeFactory;
import com.msaure.jispel.parser.impl.SimpleReader;

public class SimpleReaderTest {
    
    SimpleReader reader;
    Lexer lexerMock;
    
    @Before
    public void setUp() {
        Context ctx = null;
        NodeFactory factory = new SimpleNodeFactory();
        this.reader = new SimpleReader(ctx, factory);
        lexerMock = mock(Lexer.class);
    }
    
    @Test
    public void thatAnIntegerIsParsedCorrectly() throws IOException, TypeException {
    	when(lexerMock.nextToken())
    		.thenReturn(new Token(Token.TokenType.INT, "1234"));
    	
        Handle h = this.reader.readAtom(lexerMock);
        
        assertNotNull(h);
        assertThat(h.integerValue()).isEqualTo(1234);
    }
    
    @Test
    public void thatBooleanTrueConstantIsParsedCorrectly() throws IOException {
    	when(lexerMock.nextToken())
    		.thenReturn(new Token(Token.TokenType.TRUE, "#t"));
    	
        Handle h = this.reader.read(lexerMock);
        
        assertNotNull(h);
    }
    
    @Test
    public void thatBooleanFalseConstantIsParsedCorrectly() throws IOException {
    	when(lexerMock.nextToken())
    		.thenReturn(new Token(Token.TokenType.FALSE, "#f"));
    	
        Handle h = this.reader.read(lexerMock);
        
        assertNotNull(h);
    }

}
