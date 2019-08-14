package com.msaure.jispel.parser.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.msaure.jispel.InterpreterFixture;
import com.msaure.jispel.memory.SimpleNodeFactory;
import com.msaure.jispel.memory.TypeException;
import com.msaure.jispel.parser.Lexer;
import com.msaure.jispel.parser.Token;
import org.junit.Before;
import org.junit.Test;

import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.NodeFactory;

public class SimpleReaderTest {
    
    SimpleReader reader;
    Lexer lexerMock;
    
    @Before
    public void setUp() {
        Context ctx = InterpreterFixture.simpleIterativeContext();
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

    @Test
    public void thatIdentifierIsParsedCorrectly() throws IOException, TypeException {
        when(lexerMock.nextToken())
                .thenReturn(new Token(Token.TokenType.ID, "abc"));

        Handle h = reader.read(lexerMock);

        assertThat(h).isNotNull();
        assertThat(h.hasType(Handle.NodeType.SYMBOL)).isTrue();
        assertThat(h.stringValue()).isEqualTo("abc");
    }

}
