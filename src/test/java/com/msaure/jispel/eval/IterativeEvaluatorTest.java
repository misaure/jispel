package com.msaure.jispel.eval;

import com.msaure.jispel.builtin.math.AddCommand;
import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.memory.TypeException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.msaure.jispel.InterpreterFixture;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.SimpleNodeFactory;
import com.msaure.jispel.memory.type.DoubleHandle;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

public class IterativeEvaluatorTest {

	IterativeEvaluator evaluator;
	Context ctx;
	SimpleNodeFactory factory;

	@Before
	public void setUp() {
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
	public void thatNilEvaluatesToSelf() throws Exception {
		Handle result = evaluator.eval(factory.makeNil());

		assertNotNull(result);
		assertEquals(factory.makeNil(), result);
	}

	@Test
	public void thatIntegersEvaluateToSelf() throws RecoverableException, TypeException {
		Handle result = evaluator.eval(factory.makeInteger(1));

		assertNotNull(result);
		assertTrue(result.hasType(Handle.NodeType.INTEGER));
		assertThat(result.integerValue()).isEqualTo(1);
	}

	@Test
	public void thatFalseEvaluatesToSelf() throws RecoverableException, TypeException {
		Handle result = evaluator.eval(factory.makeBoolean(false));

		assertThat(result).isNotNull();
		assertTrue(result.hasType(Handle.NodeType.BOOLEAN));
		assertThat(result.booleanValue()).isEqualTo(false);
	}

	@Test
	public void thatTrueEvaluatesToSelf() throws RecoverableException, TypeException {
		Handle result = evaluator.eval(factory.makeBoolean(true));

		assertThat(result).isNotNull();
		assertTrue(result.hasType(Handle.NodeType.BOOLEAN));
		assertThat(result.booleanValue()).isEqualTo(true);
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

	@Test
	@Ignore("pending feature")
	public void test() throws Exception {
		Environment globals = new Environment();
		// globals.put("+", new AddCommand());

		//evaluator.evalExpression(expr);
	}

	@Test(expected = RecoverableException.class)
	public void test1() throws RecoverableException, TypeException {
		evaluator.eval(null);
	}
}
