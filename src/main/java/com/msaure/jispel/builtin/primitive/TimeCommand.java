package com.msaure.jispel.builtin.primitive;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.SpecialValue;

/**
 Implements a builtin that executes a sequence of expressions from left to
 right and returns the time that has been consumed by the evaluation
 process.
 */
public class TimeCommand extends SpecialValue {

    @Override
    public Handle execute(Context ctx, Environment env, Handle[] args) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
