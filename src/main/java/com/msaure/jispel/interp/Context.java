package com.msaure.jispel.interp;

import com.msaure.jispel.Interpreter;
import com.msaure.jispel.core.Environment;
import com.msaure.jispel.eval.Evaluator;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.NodeFactory;

public class Context {
	
    public final NodeFactory factory;
    public final Environment toplevel;
    public Environment currentEnv;
    public final Evaluator eval;
    public final Interpreter interp;
    public final Handle NIL;
    public final Handle TRUE;
    public final Handle FALSE;
    
    public Context(Interpreter interp, ComponentFactory factory) {
        this.toplevel = factory.createToplevelEnvironment();
        this.factory = factory.getGlobalNodeFactory( this, 65535);
        this.eval = factory.createEvaluator( this);
        this.interp = interp;
        
        this.NIL = this.factory.makeNil();
        this.TRUE = this.factory.makeBoolean( true);
        this.FALSE = this.factory.makeBoolean( false);
        
        this.NIL.setFlag( Handle.GCSAFEFLAG);
        this.TRUE.setFlag( Handle.GCSAFEFLAG);
        this.FALSE.setFlag( Handle.GCSAFEFLAG);
    }
}
