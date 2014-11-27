package com.msaure.jispel.memory;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HandleTest {

    @Test
    public void thatDefaultConstructorWorksAsExpected() {
        final Handle h = new Handle();
        assertTrue(h.hasType(Handle.NodeType.EMPTY));
        assertEquals(0, h.getFlags());
        assertFalse(h.isNilRep());
        assertFalse(h.isNumber());
    }
}
