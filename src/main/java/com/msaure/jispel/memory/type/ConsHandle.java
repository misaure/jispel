package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;

public class ConsHandle extends Handle {

    private Handle car;
    private Handle cdr;

    public ConsHandle() {
        super(NodeType.CONS);
    }

    @Override
    public Handle car() throws TypeException {
       return car;
    }

    @Override
    public Handle cdr() throws TypeException {
        return cdr;
    }

    @Override
    public void setCar(Handle car) throws TypeException {
        this.car = car;
    }

    @Override
    public void setCdr(Handle cdr) throws TypeException {
        this.cdr = cdr;
    }
}
