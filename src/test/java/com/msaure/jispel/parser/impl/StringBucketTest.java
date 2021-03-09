package com.msaure.jispel.parser.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringBucketTest {

    StringBucket bucket;

    @Before
    public void setUp() {
        bucket = new StringBucket();
    }

    @Test
    public void thatItDoesNotReturnNull() {
        assertNotNull(bucket.internString("symbol"));
    }

    @Test
    public void thatItReturnsInternedInstance() {
        String first = bucket.internString("symbol");

        String second = bucket.internString(new String("symbol"));

        assertTrue(second == first);
    }

    @Test
    public void thatItHandlesNullArgumentsGracefully() {
        assertNull(bucket.internString(null));
    }
}