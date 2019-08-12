package com.msaure.jispel.memory;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;

public abstract class BuiltinValue extends CommandImpl {

    private static final Logger LOG = LoggerFactory.getLogger(BuiltinValue.class);

    /**
     Execute the function in the given environment with the given arguments.
     The arguments will be evaluated, so this class can't be used to
     implement special forms.
     */
    @NotNull
    public abstract Handle execute(Context ctx, Environment env, Handle[] args) throws TypeException, RecoverableException;

    public void printToStream(Writer os) {
        try {
            os.write("#<procedure>");
        } catch (IOException e) {
            LOG.warn("cannot print to stream", e);
        }
    }

}
