package com.msaure.jispel.builtin.primitive;

import com.msaure.jispel.core.ArgumentCountException;
import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Constants;
import com.msaure.jispel.memory.Handle;
import static com.msaure.jispel.memory.NodeUtils.eq;
import static com.msaure.jispel.memory.NodeUtils.listlength;
import com.msaure.jispel.memory.SpecialValue;
import com.msaure.jispel.memory.TypeException;

public class AndCommand extends SpecialValue {
    @Override
    public Handle execute(Context ctx, Environment env, Handle args) throws RecoverableException, TypeException {
        int argc = listlength(args);
        
        if (0 == argc) {
            throw new ArgumentCountException();
        }
        
        Handle evaluated = null;
        for (int i=0; i<argc; ++i, args=args.cdr()) {
            evaluated = ctx.eval.eval(args.car());
            
            if (eq(evaluated, Constants.FALSE.asHandle())) {
                return Constants.FALSE.asHandle();
            }
        }
        
        assert null != evaluated;
        
        return null;
    }
}
