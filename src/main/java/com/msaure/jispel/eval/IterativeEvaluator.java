package com.msaure.jispel.eval;

import com.msaure.jispel.commons.base.Arg;
import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.BuiltinValue;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.SpecialValue;
import com.msaure.jispel.memory.TypeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IterativeEvaluator implements Evaluator {
    
    private static final Logger LOG = LoggerFactory.getLogger(IterativeEvaluator.class);
    
    private final Context ctx;
    private final Stack<Environment> envStack;
    
    public IterativeEvaluator(Context ctx) {
        this.ctx = ctx;
        this.envStack = new Stack<>();
        this.envStack.push(ctx.toplevel);
    }
    
    /**
     * Depending on the type of the node passed to it, this method will either
     * dispatch to one of the other eval* methods or it will return it's argument
     * unchanged, thus implementing self-evaluating node types.
     * 
     * @param node The node which is to be evaluated.
     * 
     * @return Returns the result of the evaluation of the given s-expression.
     */
    @Override
    public Handle eval( Handle node) throws RecoverableException, TypeException {
        if (node.hasType(Handle.NodeType.CONS)) {
            //FIXME: why handle application of NIL?
            return (eq(node, ctx.NIL))? ctx.NIL : evalExpression(node);
            
        } else if (node.hasType(Handle.NodeType.SYMBOL)) {
            return evalVariable(node);
        }
        
        return node; // node evaluates to itself
    }
    
    /**
     * During the evaluation phase all (unquoted) lists are treated as 
     * s-expressions, which means that they will be executed as functions.
     * 
     * This is what evalSExpression does. Before actually calling any code body,
     * all arguments will be evaluated using calls to eval().
     * 
     * @param node A cons node which is to be evaluated as an s-expression. Must not be <code>null</code>.
     * 
     * @return Returns the result of the expression evaluation as a heap cell handle.
     */
    public Handle evalExpression(Handle node) throws TypeException, RecoverableException {
        Arg.notNull(node, "node");

//#if defined( DEBUG) && DEBUG > 2
//  std::cerr << "SimpleEvaluator::evalExpression: ";
//  printList( node, std::cerr);
//  std::cerr << std::endl;
//#endif
//
        // The car cell of the first node either contains a lambda
        // expression or a symbol which refers to some executable node type.
        // In both cases the first node needs to be evaluated before application.
        Handle expr = eval(node.car());
        if (null == expr) {
            throw new RecoverableException("SimpleEvaluator::evalExpression: eval returned (null)");
        }

        if (expr.checkFlag(Handle.SPECIALFLAG)) {
            // Handle specials (don't evaluate arguments before call)

            if (expr.hasType(Handle.NodeType.CFUNC)) {
                // --- apply builtin special form
                final SpecialValue cmd = (SpecialValue) expr.typeImpl();
                assert null != cmd;
                return cmd.execute(ctx, envStack.peek(), node);
            }

        } else {
    // Handle standard s-expression: evaluate arguments before calling

            // step 1: collect arguments
            final List<Handle> argsTmp = new ArrayList<>();   //std::vector<Handle_ptr> args;

            if (eq(node.cdr(), ctx.NIL)) {
                LOG.debug("nil argument list");
            }

            for (Handle arg = node.cdr(); !eq(arg, ctx.NIL); arg = arg.cdr()) {
                argsTmp.add(eval(arg.car()));
            }

            final Handle[] args = argsTmp.toArray(new Handle[argsTmp.size()]);

            //}
//    #if defined( DEBUG) && DEBUG > 2
//        std::cerr << "collected " << args.size() << " arguments" << std::endl;
//    #endif
            LOG.debug("collected {} arguments", Integer.valueOf(argsTmp.size()));

            // step 2: execute function with the arguments collected before
            if (expr.hasType(Handle.NodeType.CFUNC)) {
                // --- apply builtin
                BuiltinValue cmd = (BuiltinValue) expr.typeImpl(); //dynamic_cast<BuiltinValue*>( expr->typeImpl());
                assert null != cmd;
                //return cmd->execute( m_ctx, m_envStack.top(), args);
                return cmd.execute(ctx, envStack.peek(), args);

            } else if (expr.hasType(Handle.NodeType.CLOSURE)) {
      // --- apply closure
                // a: push new binding environment and add arguments to it
                pushEnvironment(expr.bindArguments(args));

//#if defined( DEBUG) && DEBUG > 2
//      std::cerr << "closure body: ";
//      printList( expr->body(), std::cerr);
//      std::cerr << std::endl;
//#endif
                // b: call eval on the closure's body
                Handle retval = null;

                for (Handle pos = expr.body(); !eq(pos, ctx.NIL); pos = pos.cdr()) {
                    retval = eval(pos.car());
                }

                popEnvironment();

      //MCAssert( 0 != retval, "invalid lambda application");
                return retval;

            } else if (expr.hasType(Handle.NodeType.OBJECT)) {
      // --- message sending
                //MISSING: object implementation, need to create a good design first
                return ctx.NIL;

            } else {
                throw new RecoverableException("invalid expression type");
            }
        }

        throw new UnsupportedOperationException("undefined state in evaluator");
    }

    /**
     * Evaluation of a variable boils down to looking it up in the current
     * environment.
     */
    public Handle evalVariable(Handle node) throws RecoverableException, TypeException {
        //FIXME: better error handling!
        if (0 == envStack.size() || null == envStack.peek()) {
            throw new InternalInconsistencyException("eval stack corrupted");
        }

        final Handle valueNode = envStack.peek().lookup(node.stringValue());

        if (null == valueNode) {
            throw new UndefinedValueException(node.stringValue());
        }

        return valueNode;
    }

    /**
     * Creates a new child environment the current environment.
     * <b>NOTE:</b> This method is likely to vanish in one of the next versions.
     *
     * @return The newly allocated current environment.
     */
    public Environment pushEnvironment() {
        pushEnvironment( envStack.peek().makeChildEnvironment());
        return envStack.peek();
    }

    public void pushEnvironment(Environment env) {
        envStack.push(env);
    }

    /**
     * Removes the current environment from the environment stack. The
     * environment won't be destroyed, so this is up to the caller.
     * <b>NODE:</b> This method is likely to vanish soon.
     */
    public void popEnvironment() {
        envStack.pop();
    }

    public Environment currentEnvironment() {
        return envStack.peek();
    }

    private static boolean eq(Handle n1, Handle n2) {
        return n1==n2;
    }
}
