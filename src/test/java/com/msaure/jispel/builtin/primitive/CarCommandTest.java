package com.msaure.jispel.builtin.primitive;

import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.SimpleNodeFactory;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class CarCommandTest {

    SimpleNodeFactory nodeFactory;
    CarCommand cmd;
    
    @Before
    public void setUp() {
        this.cmd = new CarCommand();
        this.nodeFactory = new SimpleNodeFactory();
    }
    
    @Test
    public void testHappyPath() throws Exception {
        final Handle cons = nodeFactory.makeCons(
                nodeFactory.makeInteger(1), nodeFactory.makeInteger(2));
        
        final Handle actual = cmd.execute(null, null, new Handle[] { cons });
        assertNotNull(actual);
    }
}
