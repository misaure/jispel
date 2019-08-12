package com.msaure.jispel.builtin.primitive;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.BuiltinValue;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;
import org.jetbrains.annotations.NotNull;

public class ConsCommand extends BuiltinValue {

    @Override
    public @NotNull Handle execute(Context ctx, Environment env, Handle[] args) throws TypeException, RecoverableException {
        if (args.length < 2) {
            throw new RecoverableException("call to 'cons' has too few arguments (0; must be 2)");
        }

        if (args.length > 2) {
            throw new RecoverableException("call to 'cons' has too many arguments (3; must be 2)");
        }

        return null;
    }
}
