package com.msaure.jispel.memory.type;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.msaure.jispel.memory.TypeException;

public class ConsHandleTest {

	private ConsHandle cons;

	@Before
	public void setUp() {
		// cons = new ConsHandle();
		cons = ConsHandle.builder()
				.withCar(IntegerHandle.valueOf(1))
				.withCdr(IntegerHandle.valueOf(2))
				.build();
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
