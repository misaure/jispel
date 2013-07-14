package com.msaure.jispel.memory;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.interp.Context;

import java.io.Writer;

public abstract class BuiltinValue extends CommandImpl {
    /**
     Execute the function in the given environment with the given arguments.
     The arguments will be evaluated, so this class can't be used to
     implement special forms.
     */
    public abstract Handle execute(Context ctx, Environment env, Handle[] args);

    public void printToStream(Writer os) {

    }

}
