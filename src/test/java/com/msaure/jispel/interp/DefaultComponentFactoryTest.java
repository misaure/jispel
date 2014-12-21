package com.msaure.jispel.interp;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class DefaultComponentFactoryTest {
    
    private DefaultComponentFactory factory;
    
    @Before
    public void setUp() {
        this.factory = new DefaultComponentFactory();
    }
    
    @Test
    public void test() {
        assertNotNull(factory.createToplevelEnvironment());
    }
}
