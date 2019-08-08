package com.msaure.jispel.builtin.primitive;

import static org.junit.Assert.assertNotNull;

import com.msaure.jispel.memory.Constants;
import com.msaure.jispel.memory.type.ConsHandle;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.msaure.jispel.InterpreterFixture;
import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.SimpleNodeFactory;
import com.msaure.jispel.memory.TypeException;

public class AndCommandTest {

	SimpleNodeFactory nodeFactory;
	AndCommand cmd;

	Context ctx;
	Environment env;
	// Handle args;

	@Before
	public void setUp() {
		nodeFactory = new SimpleNodeFactory();

		// Interpreter interp = InterpreterFixture.mockInterpreter();
		ctx = InterpreterFixture.simpleIterativeContext();
		cmd = new AndCommand();
	}

	@Test
	@Ignore("not implemented yet")
	public void test1() throws RecoverableException, TypeException {
		Handle args = ConsHandle.builder()
				.withCar(Constants.TRUE.asHandle())
				.withCdr(
						ConsHandle.builder()
						.withCar(Constants.TRUE.asHandle())
						.withCdr(Constants.NIL.asHandle())
						.build())
				.build();
		Handle result = cmd.execute(ctx, env, args);

		assertNotNull(result);
	}
}
