package com.msaure.jispel.eval;

import com.msaure.jispel.Interpreter;
import com.msaure.jispel.InterpreterFixture;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.SimpleNodeFactory;
import com.msaure.jispel.memory.type.DoubleHandle;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class IterativeEvaluatorTest {
    
    IterativeEvaluator evaluator;
    Context ctx;
    SimpleNodeFactory factory;
    
    @Before
    public void setUp() {
        final Interpreter interp = InterpreterFixture.mockInterpreter();
        this.ctx = InterpreterFixture.simpleIterativeContext(interp);
        this.evaluator = new IterativeEvaluator(ctx);
        this.factory = new SimpleNodeFactory();
    }
    
    @Test
    public void testVariableEvaluation() throws Exception {
        ctx.toplevel.put("pi", new DoubleHandle(Math.PI));
        Handle pi = evaluator.evalVariable(factory.makeSymbol("pi"));
        assertNotNull(pi);
        
    }
}
