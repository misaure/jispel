package com.msaure.jispel.builtin.primitive;

import com.msaure.jispel.com.msaure.jispel.util.Check;
import com.msaure.jispel.core.Environment;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Constants;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.SpecialValue;
import com.msaure.jispel.memory.TypeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.msaure.jispel.memory.NodeUtils.eq;
import static com.msaure.jispel.memory.NodeUtils.isList;

public class LambdaCommand extends SpecialValue {

    @Override
    public Handle execute(Context ctx, Environment env, Handle expr) throws TypeException {
        Check.argNotNull(expr, "expression handle");

        Handle args = expr.cdr();
        
        if (!isList(args.car())) {
            throw new TypeException("list");
        }
        
        List<Handle> argumentList = new ArrayList<>();
        if (!eq(args.car(), Constants.NIL.asHandle())) {
            
        }

        throw new UnsupportedOperationException("not implemented");
    }
}
