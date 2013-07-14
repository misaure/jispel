package com.msaure.jispel.vm;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.memory.Handle;

/**
 * This is the basic type for the activation (or control) stack.
 *
 * Created with IntelliJ IDEA.
 * User: msaure
 * Date: 13.07.13
 * Time: 12:46
 * To change this template use File | Settings | File Templates.
 */
public class CallFrame {

    private Handle pc;
    private Handle[] val;
    private Environment ce;

    public CallFrame( Registers regs) {

    }

    public void load(Registers regs) {

    }

    public void restore(Registers regs) {

    }
}
