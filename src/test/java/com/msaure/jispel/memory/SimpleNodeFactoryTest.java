package com.msaure.jispel.memory;

import com.msaure.jispel.memory.type.ConsHandle;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class SimpleNodeFactoryTest {

    SimpleNodeFactory factory;
    
    @Before
    public void setUp() {
        this.factory = new SimpleNodeFactory();
    }
    
    @Test
    public void thatMakeNilReturnsEmptyConsHandle() {
        Handle h = factory.makeNil();
        assertNotNull(h);
        assertTrue(h instanceof ConsHandle);
    }
}
