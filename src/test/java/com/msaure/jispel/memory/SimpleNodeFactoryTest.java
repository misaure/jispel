package com.msaure.jispel.memory;

import com.msaure.jispel.memory.type.ConsHandle;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.*;

import com.msaure.jispel.memory.type.IntegerHandle;
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
    
    @Test
    public void thatMakeSymbolReturnsValidSymbol() throws TypeException {
        Handle h = factory.makeSymbol("mysym");
        assertNotNull(h);
        assertTrue(h.hasType(Handle.NodeType.SYMBOL));
        assertEquals("mysym", h.stringValue());
    }

    @Test
    public void thatConsWithCarOnlyReturnsValidSymbol() throws TypeException {
        ConsHandle h = factory.makeCons(IntegerHandle.valueOf(1));

        assertThat(h).isNotNull();
        assertThat(h.car()).isEqualTo(IntegerHandle.valueOf(1));
        assertThat(h.cdr()).isEqualTo(Constants.NIL);
    }
}
