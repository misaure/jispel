package com.msaure.jispel.eval;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.core.RecoverableException;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;
import java.util.Stack;

public class IterativeEvaluator implements Evaluator {
    
    private final Context ctx;
    private final Stack<Environment> envStack;
    
    public IterativeEvaluator(Context ctx) {
        this.ctx = ctx;
        this.envStack = new Stack<>();
    }
    
    /**
     * Depending on the type of the node passed to it, this method will either
     * dispatch to one of the other eval* methods or it will return it's argument
     * unchanged, thus implementing self-evaluating node types.
     * 
     * @param node The node which is to be evaluated.
     */
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
     * @param node A CONS node which is to be evaluated as an s-expression.
     */
    public Handle evalExpression( Handle node) throws RecoverableException {
        throw new UnsupportedOperationException("not implemented");
//  MCAssertValidInstance();
//  assert( 0 != node);
//
//#if defined( DEBUG) && DEBUG > 2
//  std::cerr << "SimpleEvaluator::evalExpression: ";
//  printList( node, std::cerr);
//  std::cerr << std::endl;
//#endif
//
//  // The car cell of the first node either contains a lambda
//  // expression or a symbol which refers to some executable node type.
//  // In both cases the first node needs to be evaluated before application.
//  Handle_ptr expr = eval( node->car());
//  if (0 == expr)
//    throw "SimpleEvaluator::evalExpression: eval returned (null)";
//
//  if (expr->checkFlag( Handle::SPECIALFLAG)) {
//    // Handle specials (don't evaluate arguments before call)
//
//    if (expr->hasType( Handle::ntCFUNC)) {
//      // --- apply builtin special form
//      SpecialValue *cmd = dynamic_cast<SpecialValue*>( expr->typeImpl());
//      assert( 0 != cmd);
//      return cmd->execute( m_ctx, m_envStack.top(), node);
//    }
//
//  } else {
//    // Handle standard s-expression: evaluate arguments before calling
//
//    // step 1: collect arguments
//    std::vector<Handle_ptr> args;
//    Handle_ptr arg;
//    if (eq( node->cdr(), m_ctx.NIL))
//      std::cerr << "nil arglist" << std::endl;
//    for (arg = node->cdr(); arg != m_ctx.NIL; arg = arg->cdr()) {
//#if defined( DEBUG) && DEBUG > 3
//      std::cerr << "eval arg: " << *(node->car()) << std::endl;
//#endif /*DEBUG*/
//      args.push_back( eval( arg->car()));
//    }
//#if defined( DEBUG) && DEBUG > 2
//    std::cerr << "collected " << args.size() << " arguments" << std::endl;
//#endif
//
//    // step 2: execute function with the arguments collected before
//    if (expr->hasType( Handle::ntCFUNC)) {
//      // --- apply builtin
//      BuiltinValue *cmd = dynamic_cast<BuiltinValue*>( expr->typeImpl());
//      assert( 0 != cmd);
//      return cmd->execute( m_ctx, m_envStack.top(), args);
//
//    } else if (expr->hasType( Handle::ntCLOSURE)) {
//      // --- apply closure
//      // a: push new binding environment and add arguments to it
//      pushEnvironment( expr->bindArguments( args));
//#if defined( DEBUG) && DEBUG > 2
//      std::cerr << "closure body: ";
//      printList( expr->body(), std::cerr);
//      std::cerr << std::endl;
//#endif
//      // b: call eval on the closure's body
//      Handle_ptr retval = 0;
//      Handle_ptr pos;
//      for (pos=expr->body(); !eq( pos, m_ctx.NIL); pos=pos->cdr())
//         retval = eval( pos->car());
//      popEnvironment();
//      MCAssert( 0 != retval, "invalid lambda application");
//      return retval;
//
//    } else if (expr->hasType( Handle::ntOBJECT)) {
//      // --- message sending
//      //MISSING: object implementation, need to create a good design first
//      return m_ctx.NIL;
//
//    } else
//      throw RecoverableException( "invalid expression type", __FILE__, __LINE__);
//  }
//
//  MCAssertNotReached( 0);

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

        Handle valueNode = envStack.peek().lookup(node.stringValue());

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
