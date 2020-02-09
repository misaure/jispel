package com.msaure.jispel.builtin.primitive;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.SpecialValue;

public class DefineCommand extends SpecialValue {

    @Override
    public Handle execute(Context ctx, Environment env, Handle args) {
        throw new UnsupportedOperationException("not implemented");
    }
}
