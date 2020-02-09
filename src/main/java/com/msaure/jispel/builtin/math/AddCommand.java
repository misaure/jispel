package com.msaure.jispel.builtin.math;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.BuiltinValue;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;
import com.msaure.jispel.memory.type.DoubleHandle;
import com.msaure.jispel.memory.type.IntegerHandle;

public class AddCommand extends BuiltinValue {

    @Override
    public Handle execute(Context ctx, Environment env, Handle[] args) throws TypeException, RecoverableException {
        if (args.length == 1) {
            return args[0];
        }

        // 1. determine type to use for result
        Handle.NodeType resultType = Coercions.determineResultType(ctx, env, args);

        // 2. perform addition specific to result type
        switch(resultType) {
            case INTEGER:
                return addIntegers(ctx, env, args);
            case DOUBLE:
                return addDoubles(ctx, env, args);
            case AVECTOR:
                return addArithmeticVectors(ctx, env, args);
            default:
                throw new TypeException("cannot perform addition on supplied arguments");
        }
    }

    private Handle addIntegers(Context ctx, Environment env, Handle[] args) throws TypeException {
        int result = 0;

        for (Handle h: args) {
            result += h.integerValue();
        }

        return IntegerHandle.valueOf(result);
    }

    private Handle addDoubles(Context ctx, Environment env, Handle[] args) throws TypeException {
        double result = 0.0;

        for (Handle h: args) {
            result += h.doubleValue();
        }

        return DoubleHandle.valueOf(result);
    }

    private Handle addArithmeticVectors(Context ctx, Environment env, Handle[] args) throws TypeException {
        return null;
    }
}
