package com.msaure.jispel.eval;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.memory.TypeException;
import org.junit.Before;
import org.junit.Test;

import com.msaure.jispel.InterpreterFixture;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.SimpleNodeFactory;
import com.msaure.jispel.memory.type.DoubleHandle;

import static org.junit.Assert.*;

public class IterativeEvaluatorTest {

	IterativeEvaluator evaluator;
	Context ctx;
	SimpleNodeFactory factory;

	@Before
	public void setUp() {
		// final Interpreter interp = InterpreterFixture.mockInterpreter();
		this.ctx = InterpreterFixture.simpleIterativeContext();
		this.evaluator = new IterativeEvaluator(ctx);
		this.factory = new SimpleNodeFactory();
	}

	@Test
	public void testVariableEvaluation() throws Exception {
		ctx.toplevel.put("pi", DoubleHandle.valueOf(Math.PI));
		Handle pi = evaluator.evalVariable(factory.makeSymbol("pi"));
		assertNotNull(pi);
	}

	@Test
	public void thatNilEvaluatesToSelf() throws RecoverableException, TypeException {
		Handle result = evaluator.eval(factory.makeNil());

		assertNotNull(result);
		assertEquals(factory.makeNil(), result);
	}

	@Test
	public void thatIntegersEvaluateToSelf() throws RecoverableException, TypeException {
		Handle result = evaluator.eval(factory.makeInteger(1));

		assertNotNull(result);
		assertTrue(result.hasType(Handle.NodeType.INTEGER));
		assertEquals(1, result.integerValue());
	}

	@Test
	public void thatItResolvesSymbolsUsingActiveEnvironment() throws RecoverableException, TypeException {
		Environment globals = new Environment();
		globals.put("test", factory.makeInteger(42));

		evaluator.pushEnvironment(globals);

		Handle result = evaluator.eval(factory.makeSymbol("test"));

		assertNotNull(result);
		assertTrue(result.hasType(Handle.NodeType.INTEGER));
		assertEquals(42, result.integerValue());
	}
}
