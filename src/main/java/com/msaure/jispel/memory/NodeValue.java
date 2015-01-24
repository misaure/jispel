package com.msaure.jispel.memory;

import java.io.PrintStream;

/**
 * This is the base class for actually storing the data contained in a tagged
 * memory cell. The base class makes provisions for reference counting as a
 * generalization of boolean garbage collection tags.
 */
public class NodeValue {

    /**
     * This method will be called by the garbage collector when the memory cell
     * referencing this data item will be disposed of. After calling this method
     * the destructor of the memory cell <i>might</i> be called. Whether this
     * happens or not depends on the actual gc implementation.
     */
    public void finalizeNode() {

    }

    /**
     This method implements the low-level part of the evaluation mechanism.
     The default implementation simply returns the value object's address,
     and thus implements self-evaluating objects.
     */
    public NodeValue eval() {
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     Increments the reference count by one. This method can either be used to
     set a GC tag or for implementation automatic memory management based on
     reference counting.
     */
    public void incRef() {

    }

    /**
     Decrements the reference of deletes a GC tag, resp.
     */
    public void decRef() {

    }

    /**
     Checks if there is only one reference to this element. If this method
     returns true then the class (or code) keeping a reference on this element
     can assume itself the owner of the NodeValue instance.
     */
    public boolean isShared() {
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     Print a standard representation of the current value to a given stream.
     @return The stream given as argument after the value has been printed.
     */
    public PrintStream printToStream(PrintStream out) {
        throw new UnsupportedOperationException("not implemented");
    }
}
