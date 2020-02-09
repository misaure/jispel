package com.msaure.jispel.builtin.primitive;

import com.msaure.jispel.InterpreterFixture;
import com.msaure.jispel.builtin.math.MultiplyCommand;
import com.msaure.jispel.core.Environment;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.type.IntegerHandle;
import com.msaure.jispel.memory.type.SymbolHandle;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

@Ignore("pending feature")
public class DefineCommandTest {

    DefineCommand cmd;
    Context ctx;
    Environment env;

    @Before
    public void setUp() {
        ctx = InterpreterFixture.simpleIterativeContext();

        env = new Environment();

        cmd = new DefineCommand();
    }

    @Test
    public void test1() throws Exception {
        Handle[] args = {
                SymbolHandle.valueOf("the-answer"),
                IntegerHandle.valueOf(42)
        };

        //Handle result = cmd.execute(ctx, env, args);


    }
}