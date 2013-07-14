package com.msaure.jispel.vm;

/**
 * A stack for saving activation records upon applications.
 */
public class ActivationStack {

    private CallFrame stack;
    private int pos;
    private int size;

    public ActivationStack(int initialSize) {

    }

    public void push(Registers regs) {

    }

    public void pop(Registers regs) {

    }

    public boolean resize(int newSize) {
        return false;
    }

    public int size() {
        return 0;
    }
}
