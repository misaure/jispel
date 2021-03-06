package com.msaure.jispel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import com.msaure.jispel.memory.TypeException;
import com.msaure.jispel.memory.type.IntegerHandle;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.eval.UndefinedValueException;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.NodeFactory;
import com.msaure.jispel.memory.NodeUtils;

public class InterpreterTest {


	private Interpreter interp;
	private NodeFactory nodeFactory;
	private Environment toplevel;
	private Environment current;

	@Before
	public void setUp() {
		this.interp = new Interpreter(InterpreterFixture.defaultComponentFactory());
		this.toplevel = new Environment();
		this.current = new Environment();
		this.nodeFactory = this.interp.getNodeFactory();
	}

	@Test
	public void testInitialConfiguration() {
		assertNotNull(interp.getContext());
	}

	@Test
	public void testEnvironmentHandling() throws TypeException {
		assertThat(interp.getToplevel()).isNotNull();

		interp.toplevelDefine("my-variable", IntegerHandle.valueOf(1));
		Handle h = interp.toplevelLookup("my-variable");

		assertThat(h.integerValue()).isEqualTo(1);
	}

	@Test
	@Ignore("not implemented yet")
	public void thatHelloWorldIsExecutedWithoutException() throws Exception {
		// Create an s-expression manually and try to execute it
		Handle argCons = nodeFactory.makeCons(nodeFactory.makeString("hello, world"));
		Handle exprCons = nodeFactory.makeCons(nodeFactory.makeSymbol("display"), argCons);

		NodeUtils.printList(exprCons);

		// So far, the following structure has been built:
		//   +---------+          +-----------------+
		//   | display |          | "hello, world!" |
		//   +---------+          +-----------------+
		//      ^                    ^
		//   +--|--+---+          +--|--+---+
		//   |  *  | *----------> |  *  | *----------> NIL
		//   +-----+---+          +-----+---+
		interp.getEvaluator().eval(exprCons);
	}

}
