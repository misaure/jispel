package com.msaure.jispel.interp;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.eval.Evaluator;
import com.msaure.jispel.gc.GarbageCollector;
import com.msaure.jispel.memory.NodeFactory;

/**
 * This class defines the interface to factory objects which are used by
 * the Interpreter to create it's sub components. The ComponentFactory can
 * be viewed as a kind of meta factory object, as most object created by
 * this factory are factory objects by themselves.
 */
public interface ComponentFactory {

    /** Creates a new global binding environment. */
    Environment createToplevelEnvironment();

    /** Returns a shared instance of a node factory object (singleton pattern). */
    NodeFactory getGlobalNodeFactory(Context ctx, int size);

    Evaluator createEvaluator(Context ctx);

    GarbageCollector createGC(Context ctx);
}
