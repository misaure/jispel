package com.msaure.jispel.interp;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.eval.Evaluator;
import com.msaure.jispel.eval.IterativeEvaluator;
import com.msaure.jispel.gc.GarbageCollector;
import com.msaure.jispel.gc.MarkSweepGarbageCollector;
import com.msaure.jispel.memory.NodeFactory;
import com.msaure.jispel.memory.SimpleNodeFactory;

/**
 * This is the ComponentFactory used by default when no other factory is
 * explicitly specified upon creation of a new interpreter instance.
 */
public class DefaultComponentFactory implements ComponentFactory {

    private final NodeFactory nodeFactory = new SimpleNodeFactory();
    
    @Override
    public Environment createToplevelEnvironment() {
        return new Environment();
    }

    @Override
    public NodeFactory getGlobalNodeFactory(Context ctx, int size) {
        return this.nodeFactory;
    }

    @Override
    public Evaluator createEvaluator(Context ctx) {
        return new IterativeEvaluator(ctx);
    }

    @Override
    public GarbageCollector createGC(Context ctx) {
        return new MarkSweepGarbageCollector();
    }
}
