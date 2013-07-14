package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.TypeException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

public class ConsHandleTest {

    private ConsHandle cons;

    @Before
    public void setUp() {
        cons = new ConsHandle();
    }

    @Test(expected = TypeException.class)
    public void thereMustBeNoIntegerValueAvailable() throws Exception {
        cons.integerValue();
        fail("cons returned integer value");
    }

    @Test(expected = TypeException.class)
    public void thereMustBeNoBooleanValueAvailable() throws Exception {
        cons.booleanValue();
        fail("cons returned boolean value");
    }

    @Test(expected = TypeException.class)
    public void thereMustBeNoStringValueAvailable() throws Exception {
        cons.stringValue();
        fail("cons returned string value");
    }
}
