package com.msaure.jispel.parser;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class LexBufferTest {

    private static final int BUFFER_SIZE = 64;
    
    private LexBuffer buffer;
    
    @Before
    public void setUp() {
        this.buffer = new LexBuffer(BUFFER_SIZE);
    }
    
    @Test
    public void thatANewInstanceBehavesCorrectly() {
        assertEquals("", buffer.toString());
        assertEquals(0, buffer.size());
        assertEquals(BUFFER_SIZE, buffer.getMaxSize());
        assertEquals("", buffer.toString());
    }
    
    @Test
    public void thatAppendingASingleCharacterWorks() {
        
    }

}
