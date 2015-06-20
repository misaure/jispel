package com.msaure.jispel.core;

public class ArgumentCountException extends RecoverableException {

	private static final long serialVersionUID = 6709461466344132842L;
    
	public ArgumentCountException() {
		super("wrong argument count");
	}
	
	public ArgumentCountException(Throwable t) {
		super(t);
	}
	
	public ArgumentCountException(String message, Throwable t) {
		super(message, t);
	}
	
	public ArgumentCountException(String message) {
		super(message);
	}
}
