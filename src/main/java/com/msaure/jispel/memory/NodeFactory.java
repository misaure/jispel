package com.msaure.jispel.memory;

import com.msaure.jispel.core.Environment;

import java.util.Collection;

/**
 * This class should be used to create any memory cell which should be visible
 * inside the interpreter. In a classical lisp implementation it would simply
 * serve as a facade to the heap and the garbage collector. The default
 * implementation provides a very basic heap implementation completely inside
 * one class.
 */
public interface NodeFactory {

    Handle makeValue(Handle.NodeType type, NodeValue val);
    Handle makeCharacter( char c);
    Handle makeBoolean( boolean b);
    Handle makeInteger( int value);
    Handle makeDouble(double value);
    Handle makeString(String value);
    Handle makeSymbol(String name);
    Handle makeKeyword(String name);
    Handle makeVector(Collection<Handle> items);
    Handle makePort(PortValue v);
    Handle makeHashtable();
    Handle makeSet();

    Handle makeCons();
    Handle makeCons(Handle car) throws TypeException;
    Handle makeCons(Handle car, Handle cdr) throws TypeException;

    Handle makeClosure(Collection<Handle> args, Environment env, Handle codeBody);

    Handle makeNil();
    
}
