package com.msaure.jispel.builtin.math;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.BuiltinValue;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;

public class AddCommand extends BuiltinValue {

    @Override
    public Handle execute(Context ctx, Environment env, Handle[] args) throws TypeException, RecoverableException {
        return null;
    }
}
