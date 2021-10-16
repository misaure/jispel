package com.msaure.jispel.builtin.math;

import static org.assertj.core.api.Assertions.assertThat;
import com.msaure.jispel.InterpreterFixture;
import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;
import com.msaure.jispel.memory.type.DoubleHandle;
import com.msaure.jispel.memory.type.IntegerHandle;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SubtractCommandTest {

    SubtractCommand cmd;
    Context ctx;
    Environment env;

    @Before
    public void setUp() {
        ctx = InterpreterFixture.simpleIterativeContext();

        env = new Environment();

        cmd = new SubtractCommand();
    }

    @Test
    public void thatItDoesNotReturnNull() throws RecoverableException, TypeException {
        Handle[] args = {
                IntegerHandle.valueOf(2),
                IntegerHandle.valueOf(3)
        };

        Handle result = cmd.execute(ctx, env, args);

        assertThat(result).isNotNull();
    }

    @Test
    public void thatItInvertsSingleInteger() throws TypeException, RecoverableException {
        Handle[] args = {
                IntegerHandle.valueOf(4)
        };

        Handle result = cmd.execute(ctx, env, args);

        assertThat(result.hasType(Handle.NodeType.INTEGER)).isTrue();
        assertThat(-4).isEqualTo(result.integerValue());
    }

    @Test
    public void thatItSubstractsTwoIntegers() throws RecoverableException, TypeException {
        Handle[] args = {
                IntegerHandle.valueOf(7),
                IntegerHandle.valueOf(9)
        };

        Handle result = cmd.execute(ctx, env, args);

        assertThat(result.hasType(Handle.NodeType.INTEGER)).isTrue();
        assertThat(-2).isEqualTo(result.integerValue());
    }

    @Test
    public void thatItSubtractsMoreThanTwoIntegers() throws RecoverableException, TypeException {
        Handle[] args = {
                IntegerHandle.valueOf(7),
                IntegerHandle.valueOf(2),
                IntegerHandle.valueOf(3)
        };

        Handle result = cmd.execute(ctx, env, args);

        assertThat(result.hasType(Handle.NodeType.INTEGER)).isTrue();
        assertThat(2).isEqualTo(result.integerValue());
    }

    @Test
    @Ignore("Pending Feature")
    public void thatItSubtractsTwoDoubles() throws TypeException, RecoverableException {
        Handle[] args = {
                DoubleHandle.valueOf(3.4),
                DoubleHandle.valueOf(1.4)
        };

        Handle result = cmd.execute(ctx, env, args);

        assertThat(result.hasType(Handle.NodeType.DOUBLE));
        assertThat(2.0).isEqualTo(result.doubleValue());
    }
}