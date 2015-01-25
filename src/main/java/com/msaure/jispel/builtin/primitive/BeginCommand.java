package com.msaure.jispel.builtin.primitive;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.BuiltinValue;
import com.msaure.jispel.memory.Handle;

/**
 * Implements the R4RS 'begin' function which provides clean code blocks.
 * 
 * It is assumed clean because begin behaves like a normal function as it
 * returns the result of the last expression computed. This behaviour is
 * equivalent to performing the expressions within nested lambda expressions.
 */
public class BeginCommand extends BuiltinValue {
    @Override
    public Handle execute(Context ctx, Environment env, Handle[] args) {
        throw new UnsupportedOperationException("not implemented");
    }
}
