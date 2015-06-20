package com.msaure.jispel.core;

import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;
import com.msaure.jispel.memory.type.IntegerHandle;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnvironmentTest {

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
        env.put("testval", new IntegerHandle());
        assertTrue(env.exists("testval"));
        Handle found = env.lookup("testval");
        assertNotNull(found);
    }
    
    @Test
    public void testParentLookup() throws TypeException {
        final Environment parent = new Environment();
        final Environment child = new Environment(parent);
        
        parent.put("parentOnly", new IntegerHandle(1));
        child.put("childOnly", new IntegerHandle(2));
        parent.put("both", new IntegerHandle(7));
        child.put("both", new IntegerHandle(8));
        
        assertEquals(1, parent.lookup("parentOnly").integerValue());
        assertEquals(2, child.lookup("childOnly").integerValue());
        assertEquals(7, parent.lookup("both").integerValue());
        assertEquals(8, child.lookup("both").integerValue());
        
        assertNull(parent.lookup("childOnly"));
        assertNull(child.lookup("parentOnly"));
    }
}

