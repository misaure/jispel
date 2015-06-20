package com.msaure.jispel.gc;

import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.NodeFactory;

public abstract class GarbageCollector {

    private NodeFactory factory;
    private Context context;

    public abstract int incrementalGC(int amount);

    public abstract int fullGC();

}
