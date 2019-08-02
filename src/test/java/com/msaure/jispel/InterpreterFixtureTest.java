package com.msaure.jispel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.msaure.jispel.interp.ComponentFactory;
import com.msaure.jispel.interp.Context;

public class InterpreterFixtureTest {

	@Test
	public void thatItReturnsANonNullMockInterpreter() {
		Interpreter interp = InterpreterFixture.mockInterpreter();

		assertThat(interp).isNotNull();
	}

	@Test
	public void thatItReturnsANonNullComponentFactory() {
		ComponentFactory factory = InterpreterFixture.defaultComponentFactory();

		assertThat(factory).isNotNull();
	}

	@Test
	public void thatItReturnsANonNullSimpleContext() {
		Interpreter interp = InterpreterFixture.mockInterpreter();
		Context context = InterpreterFixture.simpleIterativeContext();

		assertThat(context).isNotNull();
	}
}