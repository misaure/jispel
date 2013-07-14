package com.msaure.jispel.builtin.primitive;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.BuiltinValue;
import com.msaure.jispel.memory.Handle;

/**
 This class allows the creation of a Lispel type predicate on the fly.
 Just pass the type you want to check as an argument to the constructor and
 register that object with your interpreter.
 @version 0.1
 */
public class TypePredicate extends BuiltinValue {
    @Override
    public Handle execute(Context ctx, Environment env, Handle[] args) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
