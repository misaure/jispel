package com.msaure.jispel.builtin.primitive;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Constants;
import com.msaure.jispel.memory.Handle;
import static com.msaure.jispel.memory.NodeUtils.eq;
import com.msaure.jispel.memory.SpecialValue;
import com.msaure.jispel.memory.TypeException;

public class CondCommand extends SpecialValue {
    
    @Override
    public Handle execute(Context ctx, Environment env, Handle expr) throws RecoverableException, TypeException {
        Handle args = expr.cdr();

        //Handle branch;   // iterate over all clauses/branches
        for (Handle branch = args; !eq(branch, Constants.NIL.asHandle()); branch = branch.cdr()) {
            Handle exprList = branch.car();
            Handle evaluated = exprList.car();

            if (!eq(ctx.eval.eval(evaluated), Constants.FALSE.asHandle())) {
                // expression evaluates to true, so evaluate remaining expressions in
                // the current branch and return result of last evaluation
                for (exprList = exprList.cdr(); !eq(exprList, Constants.NIL.asHandle()); exprList = exprList.cdr()) {
                    evaluated = ctx.eval.eval(exprList.car());
                }
                
                return evaluated;
            }
        }

        return Constants.NIL.asHandle(); //this is left unspecified by R[45]RS
    }
}
