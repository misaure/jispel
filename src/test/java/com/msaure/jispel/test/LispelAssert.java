package com.msaure.jispel.test;

import com.msaure.jispel.memory.Handle;
import static org.junit.Assert.assertTrue;

public class LispelAssert {

    public static void assertType(Handle.NodeType lispelType, Handle h) {
        assertTrue("expected handle of type " + lispelType.name(), h.hasType(lispelType));
    }
}
