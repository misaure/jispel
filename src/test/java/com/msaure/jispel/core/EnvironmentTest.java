package com.msaure.jispel.core;

import com.msaure.jispel.memory.Handle;
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

    public void testDirectLookup() {
        final Environment env = new Environment();
        env.put("testval", new IntegerHandle());
        assertTrue(env.exists("testval"));
        Handle found = env.lookup("testval");
        assertNotNull(found);
    }
}

