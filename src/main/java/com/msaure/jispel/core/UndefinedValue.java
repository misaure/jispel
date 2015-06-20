package com.msaure.jispel.core;

public class UndefinedValue extends RecoverableException {

	private static final long serialVersionUID = -4800514121813127908L;
    
	public UndefinedValue() { }
	
	public UndefinedValue(Throwable t) {
		super(t);
	}
}
