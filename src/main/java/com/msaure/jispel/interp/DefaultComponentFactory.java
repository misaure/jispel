package com.msaure.jispel.interp;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.eval.Evaluator;
import com.msaure.jispel.gc.GarbageCollector;
import com.msaure.jispel.memory.NodeFactory;

/**
 * This is the ComponentFactory used by default when no other factory is
 * explicitly specified upon creation of a new interpreter instance.
 * @version 0.1
 */
public class DefaultComponentFactory implements ComponentFactory {

    @Override
    public Environment createToplevelEnvironment() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public NodeFactory getGlobalNodeFactory(Context ctx, int size) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Evaluator createEvaluator(Context ctx) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public GarbageCollector createGC(Context ctx) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
