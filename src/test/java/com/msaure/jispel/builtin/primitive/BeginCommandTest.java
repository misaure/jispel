package com.msaure.jispel.builtin.primitive;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.SimpleNodeFactory;

public class BeginCommandTest {

	BeginCommand cmd;

	SimpleNodeFactory nodeFactory;

	Context ctx;
	Environment env;
	Handle[] args;

	@Before
	public void setUp() {
		cmd = new BeginCommand();

		nodeFactory = new SimpleNodeFactory();
	}

	@Test
	public void test1() {
		Handle result = cmd.execute(ctx, env, args);

		assertNotNull(result);
	}
}