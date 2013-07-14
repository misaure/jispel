package com.msaure.jispel.gc;

import com.msaure.jispel.interp.Context;
import com.msaure.jispel.memory.NodeFactory;

/**
 * User: msaure
 * Date: 13.07.13
 * Time: 13:18
 */
public abstract class GarbageCollector {

    private NodeFactory factory;
    private Context context;

    public abstract int incrementalGC(int amount);

    public abstract int fullGC();

}
