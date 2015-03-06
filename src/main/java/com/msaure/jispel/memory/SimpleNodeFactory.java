package com.msaure.jispel.memory;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.memory.builder.ConsBuilder;
import com.msaure.jispel.memory.type.BooleanHandle;
import com.msaure.jispel.memory.type.ConsHandle;
import com.msaure.jispel.memory.type.IntegerHandle;
import com.msaure.jispel.memory.type.StringHandle;
import com.msaure.jispel.memory.type.SymbolHandle;
import java.util.Collection;

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
        final BooleanHandle bh = new BooleanHandle();
        bh.setBoolValue(b);
        
        return bh;
    }

    @Override
    public Handle makeInteger(int value) {
        return new IntegerHandle(value);
    }

    @Override
    public Handle makeDouble(double value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Handle makeString(String value) {
        return new StringHandle(value);
    }

    @Override
    public Handle makeSymbol(String name) {
        return new SymbolHandle(name);
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
    public Handle makeCons() {
        return new ConsHandle();
    }

    public ConsBuilder cons() {
        return new SimpleConsBuilder();
    }
    
    @Override
    public Handle makeCons(Handle car) throws TypeException {
        final ConsHandle ch = new ConsHandle();
        ch.setCar(car);
        
        return ch;
    }

    @Override
    public Handle makeCons(Handle car, Handle cdr) throws TypeException {
        final ConsHandle ch = new ConsHandle();
        ch.setCar(car);
        ch.setCdr(cdr);
        
        return ch;
    }

    @Override
    public Handle makeClosure(Collection<Handle> args, Environment env, Handle codeBody) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Handle makeNil() {
        return new ConsHandle();
    }
    
    private static class SimpleConsBuilder implements ConsBuilder {
        Handle car;
        Handle cdr;
        
        @Override
        public ConsBuilder car(Handle car) {
            this.car = car;
            return this;
        }

        @Override
        public ConsBuilder cdr(Handle cdr) {
            this.cdr = cdr;
            return this;
        }

        @Override
        public ConsBuilder nil() {
            this.car = null;
            this.cdr = null;
            return this;
        }

        @Override
        public ConsHandle build() throws TypeException {
            final ConsHandle newCons = new ConsHandle();
            newCons.setCar(this.car);
            newCons.setCdr(this.cdr);
            
            return newCons;
        }
    }
}
