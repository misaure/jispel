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
    
	public Handle getAcc() {
		return acc;
	}
	
	public void setAcc(Handle acc) {
		this.acc = acc;
	}
	
	public Handle getPc() {
		return pc;
	}
	
	public void setPc(Handle pc) {
		this.pc = pc;
	}
	
	public Environment getCe() {
		return ce;
	}
	
	public void setCe(Environment ce) {
		this.ce = ce;
	}
	
	public Handle[] getVal() {
		return val;
	}
	
	public void setVal(Handle[] val) {
		this.val = val;
	}
	
	public int getStackBase() {
		return stackBase;
	}
	
	public void setStackBase(int stackBase) {
		this.stackBase = stackBase;
	}
	
	public int getStackOffset() {
		return stackOffset;
	}
	
	public void setStackOffset(int stackOffset) {
		this.stackOffset = stackOffset;
	}

}
