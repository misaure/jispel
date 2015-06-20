package com.msaure.jispel.memory.type;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArithmeticVectorHandleTest {

	ArithmeticVectorHandle handle;
	
	@Before
	public void setUp() {
		this.handle = new ArithmeticVectorHandle();
	}
	
	@Test
	public void testIt() {
		assertFalse(handle.isNilRep());
	}
}
