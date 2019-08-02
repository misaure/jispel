package com.msaure.jispel.core;

import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;
import com.msaure.jispel.memory.type.IntegerHandle;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnvironmentTest {

    @Test
    public void thatUniqueEnvironmentIsTheTopLevelEnvironment() {
        final Environment env1 = new Environment();

        assertTrue(env1.isToplevelEnvironment());
    }

    @Test
    public void testIsToplevelEnvironment() {
        final Environment env1 = new Environment();
        final Environment env2 = new Environment(env1);

        assertTrue(env1.isToplevelEnvironment());
        assertFalse(env2.isToplevelEnvironment());
    }

    @Test
    public void testMakeChildEnvironment() {
        final Environment env1 = new Environment();
        final Environment env2 = env1.makeChildEnvironment();

        assertNotNull(env2);
        assertNull(env1.getParent());
        assertNotNull(env2.getParent());
        assertTrue(env1.isToplevelEnvironment());
        assertFalse(env2.isToplevelEnvironment());
    }

    @Test
    public void testDirectLookup() {
        final Environment env = new Environment();
        env.put("testval", IntegerHandle.valueOf(42));
        assertTrue(env.exists("testval"));
        Handle found = env.lookup("testval");
        assertNotNull(found);
    }
    
    @Test
    public void testParentLookup() throws TypeException {
        final Environment parent = new Environment();
        final Environment child = new Environment(parent);
        
        parent.put("parentOnly", IntegerHandle.valueOf(1));
        child.put("childOnly", IntegerHandle.valueOf(2));
        parent.put("both", IntegerHandle.valueOf(7));
        child.put("both", IntegerHandle.valueOf(8));
        
        assertEquals(1, parent.lookup("parentOnly").integerValue());
        assertEquals(2, child.lookup("childOnly").integerValue());
        assertEquals(7, parent.lookup("both").integerValue());
        assertEquals(8, child.lookup("both").integerValue());
        
        assertNull(parent.lookup("childOnly"));
        assertNull(child.lookup("parentOnly"));
    }
}

