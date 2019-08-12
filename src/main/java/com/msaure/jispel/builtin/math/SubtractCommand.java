package com.msaure.jispel.builtin.math;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.BuiltinValue;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;
import com.msaure.jispel.memory.type.IntegerHandle;
import org.jetbrains.annotations.NotNull;

public class SubtractCommand extends BuiltinValue {

    @Override
    public @NotNull Handle execute(Context ctx, Environment env, Handle[] args) throws TypeException, RecoverableException {
        // 1. determine type to use for result
        Handle.NodeType resultType = determineResultType(ctx, env, args);
        
        switch (resultType) {
            case INTEGER:
                return subtractIntegers(ctx, env, args);
            case DOUBLE:
                return subtractDoubles(ctx, env, args);
            case AVECTOR:
                return subtractArithmeticVectors(ctx, env, args);
            default:
                throw new TypeException("cannot perform addition on supplied arguments");
        }
    }

    private Handle subtractArithmeticVectors(Context ctx, Environment env, Handle[] args) {
        return null;
    }

    private Handle subtractDoubles(Context ctx, Environment env, Handle[] args) {
        return null;
    }

    private Handle subtractIntegers(Context ctx, Environment env, Handle[] args) throws TypeException {
        if (args.length == 1) {
            return IntegerHandle.valueOf(- args[0].integerValue());
        }

        int result = args[0].integerValue();

        for (int i=1; i<args.length; ++i) {
            result -= args[i].integerValue();
        }

        return IntegerHandle.valueOf(result);
    }

    private Handle.NodeType determineResultType(Context ctx, Environment env, Handle[] args) {
        return Handle.NodeType.INTEGER;
    }

}
