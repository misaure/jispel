package com.msaure.jispel.builtin.math;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.BuiltinValue;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;
import com.msaure.jispel.memory.type.IntegerHandle;

public class MultiplyCommand extends BuiltinValue {

    @Override
    public Handle execute(Context ctx, Environment env, Handle[] args) throws TypeException, RecoverableException {
        if (args.length == 1) {
            return args[0];
        }

        // 1. determine type to use for result
        Handle.NodeType resultType = determineResultType(ctx, env, args);

        // 2. perform addition specific to result type
        switch(resultType) {
            case INTEGER:
                return multiplyIntegers(ctx, env, args);
            case DOUBLE:
                return multiplyDoubles(ctx, env, args);
            case AVECTOR:
                return multiplyArithmeticVectors(ctx, env, args);
            default:
                throw new TypeException("cannot perform addition on supplied arguments");
        }
    }

    private Handle multiplyIntegers(Context ctx, Environment env, Handle[] args) throws TypeException {
        int result = 1;

        for (Handle h: args) {
            result *= h.integerValue();
        }

        return IntegerHandle.valueOf(result);
    }

    private Handle multiplyDoubles(Context ctx, Environment env, Handle[] args) throws TypeException {
        return null;
    }

    private Handle multiplyArithmeticVectors(Context ctx, Environment env, Handle[] args) throws TypeException {
        return null;
    }

    private Handle.NodeType determineResultType(Context ctx, Environment env, Handle[] args) {
        return Handle.NodeType.INTEGER;
    }
}
