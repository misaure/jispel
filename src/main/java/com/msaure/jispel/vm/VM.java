package com.msaure.jispel.vm;

import com.msaure.jispel.memory.Handle;

/**
 * This class implements the basic functions of a simple heap-based virtual
 * machine suitable for executing simple Lispel programs. To avoid any
 * restrictions in the possible uses of this VM, this class isn't tied to any
 * external representations like byte codes. It simply provides one method for
 * each instruction, so that it can be used as the basis of a byte code
 * interpreter or an interpreter performing compiled functions, ...
 */
public class VM {

    private CallFrame activationStack;
    private int stackSize;
    private int stackPosition;
    private Registers regs;

    public Handle halt() {
        throw new UnsupportedOperationException("not implemented");
    }

    public void resolveBinding(Handle var) {

    }

    public void constant(Handle obj) {

    }

    public void createClosure(Handle vars, Handle body) {

    }


}
