package com.msaure.jispel.parser;

import com.msaure.jispel.memory.Handle;
import java.io.Reader;
import java.io.StringReader;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class SimpleReaderTest {
    
    private SimpleReader reader;
    
    @Before
    public void setUp() {
        this.reader = new SimpleReader();
    }
    
    @Test
    public void thatAnIntegerIsParsedCorrectly() {
        Handle h = this.reader.read(readerFromString("1234"));
        assertNotNull(h);
    }
    
    @Test
    public void thatBooleanTrueConstantIsParsedCorrectly() {
        Handle h = this.reader.read(readerFromString("#t"));
        assertNotNull(h);
    }
    
    @Test
    public void thatBooleanFalseConstantIsParsedCorrectly() {
        Handle h = this.reader.read(readerFromString("#f"));
        assertNotNull(h);
    }
    
    private Reader readerFromString(String s) {
        return new StringReader(s);
    }
}
