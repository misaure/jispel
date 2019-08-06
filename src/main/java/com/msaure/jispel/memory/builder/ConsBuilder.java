package com.msaure.jispel.memory.builder;

import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;
import com.msaure.jispel.memory.type.ConsHandle;

/**
 * @depcated Use {@link HandleBuilder#consCell()} instead.
 */
public interface ConsBuilder {

    ConsBuilder car(Handle car);
    ConsBuilder cdr(Handle cdr);
    ConsBuilder nil();
    ConsHandle build() throws TypeException;
    
}
