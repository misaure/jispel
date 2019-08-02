package com.msaure.jispel.interp;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.eval.Evaluator;
import com.msaure.jispel.memory.NodeFactory;
import org.jetbrains.annotations.NotNull;

public class Context {
	
    public final NodeFactory factory;
    public final Environment toplevel;
    public Environment currentEnv;
    public final Evaluator eval;

    public Context(@NotNull ComponentFactory factory) {
        this.toplevel = factory.createToplevelEnvironment();
        this.factory = factory.getGlobalNodeFactory( this, 65535);
        this.eval = factory.createEvaluator( this);
    }
}
