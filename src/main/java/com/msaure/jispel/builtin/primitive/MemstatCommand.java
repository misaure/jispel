package com.msaure.jispel.builtin.primitive;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.BuiltinValue;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;

/**
 The 'memstat' command returns the current heap size and the number of
 unused memory cells as a two element consed list. The command doesn't
 require any arguments.
 */
public class MemstatCommand extends BuiltinValue {
    @Override
    public Handle execute(Context ctx, Environment env, Handle[] args) throws TypeException, RecoverableException {
        throw new UnsupportedOperationException("not implemented");
    }
}
