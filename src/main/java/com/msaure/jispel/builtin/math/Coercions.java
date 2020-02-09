package com.msaure.jispel.builtin.math;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;

public class Coercions {

    public static Handle.NodeType determineResultType(Context ctx, Environment env, Handle[] args) {
        for (Handle arg: args) {
            if (arg.hasType(Handle.NodeType.DOUBLE)) {
                return Handle.NodeType.DOUBLE;
            }
        }

        return Handle.NodeType.INTEGER;
    }

}
