package com.msaure.jispel.builtin.primitive;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.msaure.jispel.InterpreterFixture;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;

public class MemstatCommandTest {

	MemstatCommand command;

	@Before
	public void setUp() {
		this.command = new MemstatCommand();
	}

	@Test
	public void thatItDoesNotReturnNull() throws TypeException, RecoverableException {
		Context ctx = InterpreterFixture.simpleIterativeContext(InterpreterFixture.mockInterpreter());
		Handle result = command.execute(ctx, null, null);

		assertNotNull(result);
	}
}
