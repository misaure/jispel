package com.msaure.jispel.gc;

/**
 * User: msaure
 * Date: 13.07.13
 * Time: 13:20
 */
public class MarkSweepGarbageCollector extends GarbageCollector {

    @Override
    public int incrementalGC(int amount) {
        return 0;
    }

    @Override
    public int fullGC() {
        return 0;
    }
}
