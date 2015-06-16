package com.msaure.jispel.builtin.primitive;

import org.junit.Before;
import org.junit.Test;

import com.msaure.jispel.Interpreter;
import com.msaure.jispel.InterpreterFixture;
import com.msaure.jispel.core.Environment;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.TypeException;

public class LambdaCommandTest {

	LambdaCommand cmd;
	Context ctx;
	Environment env;
	
	@Before
	public void setUp() {
		this.cmd = new LambdaCommand();
		
		Interpreter interp = InterpreterFixture.mockInterpreter();
		ctx = InterpreterFixture.simpleIterativeContext(interp);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void thatNullExpressionHandleTriggersIllegalArgumentException() throws TypeException {
		this.cmd.execute(ctx, env, null);
	}
}
