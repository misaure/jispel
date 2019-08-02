package com.msaure.jispel.memory;

import java.util.Collection;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.memory.type.ConsHandle;
import com.msaure.jispel.memory.type.IntegerHandle;
import com.msaure.jispel.memory.type.StringHandle;
import com.msaure.jispel.memory.type.SymbolHandle;

public class SimpleNodeFactory implements NodeFactory {

	@Override
	public Handle makeValue(Handle.NodeType type, NodeValue val) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Handle makeCharacter(char c) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Handle makeBoolean(boolean b) {
		return b? Constants.TRUE.asHandle() : Constants.FALSE.asHandle();
	}

	@Override
	public Handle makeInteger(int value) {
		return IntegerHandle.builder()
				.withIntegerValue(value)
				.build();
	}

	@Override
	public Handle makeDouble(double value) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Handle makeString(String value) {
		return StringHandle.valueOf(value);
	}

	@Override
	public Handle makeSymbol(String name) {
		return SymbolHandle.builder()
				.withSymbolName(name)
				.build();
	}

	@Override
	public Handle makeKeyword(String name) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Handle makeVector(Collection<Handle> items) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Handle makePort(PortValue v) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Handle makeHashtable() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Handle makeSet() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ConsHandle makeCons(Handle car) throws TypeException {
		return ConsHandle.builder()
				.withCar(car)
				.build();
	}

	@Override
	public ConsHandle makeCons(Handle car, Handle cdr) throws TypeException {
		return ConsHandle.builder()
				.withCar(car)
				.withCdr(cdr)
				.build();
	}

	@Override
	public Handle makeClosure(Collection<Handle> args, Environment env, Handle codeBody) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Handle makeNil() {
		return Constants.NIL.asHandle();
	}

}
