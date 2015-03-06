package com.msaure.jispel.builtin.primitive;

import com.msaure.jispel.core.ArgumentCountException;
import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.BuiltinValue;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;

public class CarCommand extends BuiltinValue  {

    @Override
    public Handle execute(Context ctx, Environment env, Handle[] args) throws TypeException, RecoverableException {
        if (1 != args.length) {
            throw new ArgumentCountException();
        }
        
        return args[0].car();
    }
}
