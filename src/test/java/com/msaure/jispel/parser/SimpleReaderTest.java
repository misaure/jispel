package com.msaure.jispel.parser;

import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import java.io.Reader;
import java.io.StringReader;
import static org.junit.Assert.assertNotNull;

import com.msaure.jispel.memory.NodeFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SimpleReaderTest {
    
    private SimpleReader reader;
    
    @Before
    public void setUp() {
        Context ctx = null;
        NodeFactory factory = null;
        this.reader = new SimpleReader(ctx, factory);
    }
    
    @Test @Ignore("pending feature")
    public void thatAnIntegerIsParsedCorrectly() {
        Handle h = this.reader.read(readerFromString("1234"));
        assertNotNull(h);
    }
    
    @Test @Ignore("pending feature")
    public void thatBooleanTrueConstantIsParsedCorrectly() {
        Handle h = this.reader.read(readerFromString("#t"));
        assertNotNull(h);
    }
    
    @Test @Ignore("pending feature")
    public void thatBooleanFalseConstantIsParsedCorrectly() {
        Handle h = this.reader.read(readerFromString("#f"));
        assertNotNull(h);
    }
    
    private Reader readerFromString(String s) {
        return new StringReader(s);
    }
}
