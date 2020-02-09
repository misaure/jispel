package com.msaure.jispel.builtin.math;

import com.msaure.jispel.InterpreterFixture;
import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;
import com.msaure.jispel.memory.type.DoubleHandle;
import com.msaure.jispel.memory.type.IntegerHandle;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import static org.junit.Assert.*;

public class AddCommandTest {

    AddCommand cmd;
    Context ctx;
    Environment env;

    @Before
    public void setUp() {
        ctx = InterpreterFixture.simpleIterativeContext();

        env = new Environment();

        cmd = new AddCommand();
    }

    @Test
    public void thatItDoesNotReturnNull() throws RecoverableException, TypeException {
        Handle[] args = {
                IntegerHandle.valueOf(2),
                IntegerHandle.valueOf(3)
        };

        Handle result = cmd.execute(ctx, env, args);

        assertNotNull(result);
    }

    @Test
    public void thatItAddsTwoIntegersAndReturnsTheResultAsAnInteger() throws RecoverableException, TypeException {
        Handle[] args = {
                IntegerHandle.valueOf(2),
                IntegerHandle.valueOf(3)
        };

        Handle result = cmd.execute(ctx, env, args);

        assertTrue(result.hasType(Handle.NodeType.INTEGER));
        assertEquals(5, result.integerValue());
    }

    @Test
    public void thatItHandlesSingleArgumentCorrectly() throws TypeException, RecoverableException {
        Handle[] args = {
                IntegerHandle.valueOf(7)
        };

        Handle result = cmd.execute(ctx, env, args);

        assertTrue(result.hasType(Handle.NodeType.INTEGER));
        assertEquals(7, result.integerValue());
    }

    @Test
    public void thatItAddsTwoDoubleValuesAndReturnsTheResultAsDouble() throws Exception {
        Handle[] args = {
                DoubleHandle.valueOf(4.0),
                DoubleHandle.valueOf(2.0)
        };

        Handle result = cmd.execute(ctx, env, args);

        assertThat(result.hasType(Handle.NodeType.DOUBLE))
                .isTrue();
        assertThat(result.doubleValue())
                .isBetween(5.9999999, 6.00000001);
    }
}