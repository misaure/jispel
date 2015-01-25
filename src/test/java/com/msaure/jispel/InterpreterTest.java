package com.msaure.jispel;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.eval.UndefinedValueException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.NodeFactory;
import com.msaure.jispel.memory.NodeUtils;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class InterpreterTest {

    private Interpreter interp;
    private NodeFactory nodeFactory;
    private Environment toplevel;
    private Environment current;

    @Before
    public void setUp() {
        this.interp = new Interpreter();
        this.toplevel = new Environment();
        this.current = new Environment();
        this.nodeFactory = this.interp.getNodeFactory();
    }

    @Test
    public void testInitialConfiguration() {
        assertNotNull(interp.getContext());
    }

    @Test
    public void testEnvironmentHandling() {

    }

    @Test
    public void testThatInterpreterKnowsNil() {
        Handle nilHandle = interp.getContext().NIL;
        assertNotNull(nilHandle);
        //assertTrue(nilHandle.hasType(NodeType.))
    }
    
    @Test
    public void thatTheGlobalNilConstantIsInitializedCorrectly() {
        Context ctx = this.interp.getContext();
        Handle nil = ctx.NIL;
        assertNotNull(nil);
        assertTrue(nil.hasType(Handle.NodeType.CONS));
        assertTrue(nil.isNilRep());
    }
    
    @Test(expected = UndefinedValueException.class)
    public void thatHelloWorldIsExecutedWithoutException() throws Exception {
        // Create an s-expression manually and try to execute it
        Handle exprCons = nodeFactory.makeCons(nodeFactory.makeSymbol("display"));
        Handle argCons = nodeFactory.makeCons(nodeFactory.makeString("hello, world"));
        exprCons.setCdr(argCons);

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
