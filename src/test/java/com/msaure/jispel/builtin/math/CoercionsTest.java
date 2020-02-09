package com.msaure.jispel.builtin.math;

import com.msaure.jispel.InterpreterFixture;
import com.msaure.jispel.core.Environment;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.type.DoubleHandle;
import com.msaure.jispel.memory.type.IntegerHandle;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class CoercionsTest {

    Context ctx;
    Environment env;

    @Before
    public void setUp() {
        ctx = InterpreterFixture.simpleIterativeContext();

        env = new Environment();
    }

    // TODO (msa) bad name
    @Test
    public void test1() throws Exception {
        Handle[] args = {
                IntegerHandle.valueOf(1),
                IntegerHandle.valueOf(41)
        };

        assertThat(Coercions.determineResultType(ctx, env, args))
                .isSameAs(Handle.NodeType.INTEGER);
    }

    // TODO (msa) bad name
    @Test
    public void test2() throws Exception {
        Handle[] args = {
                DoubleHandle.valueOf(1.0),
                IntegerHandle.valueOf(41)
        };

        assertThat(Coercions.determineResultType(ctx, env, args))
                .isSameAs(Handle.NodeType.DOUBLE);
    }


}