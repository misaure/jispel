package com.msaure.jispel.builtin.primitive;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.SimpleNodeFactory;

public class IfCommandTest {

	IfCommand cmd;

	SimpleNodeFactory nodeFactory;

	Context ctx;
	Environment env;
	Handle args;

	@Before
	public void setUp() {
		Handle result = cmd.execute(ctx, env, args);

		assertNotNull(result);
	}
}
