package com.msaure.jispel.vm;

import com.msaure.jispel.memory.Handle;

public class Thread {
    private Registers regs;
    private Handle argumentStack;
    private int maxStackSize;
    private int stackPos;
}
