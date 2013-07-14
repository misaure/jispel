package com.msaure.jispel.vm;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.memory.Handle;

public class Registers {

    private Handle acc;
    private Handle pc;
    private Environment ce;
    private Handle[] val;
    private int stackBase;
    private int stackOffset;

}
