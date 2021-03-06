package com.msaure.jispel.memory;

import java.io.PrintStream;
import java.util.Objects;

public class NodeUtils {
    
    public static String typetagToString( Handle.NodeType t) {
        return t.name();
    }

    public static int listlength(Handle n) throws TypeException {
        Objects.requireNonNull(n, "Handle must not be null");

        int length = 0;
        Handle iter = n;
        for (; !n.isNilRep(); ++length) {
            iter = iter.cdr();
        }
        return length;
    }

    public static void printList(Handle n, PrintStream out) throws TypeException {
        
    }
    
    public static void printList(Handle n) throws TypeException {
        printList(n, System.out);
    }

    public static boolean isList(Handle h) {
        throw new UnsupportedOperationException("not implemented");
    }
    
    public static boolean eq(Handle n1, Handle n2) {
        return n1 == n2;
    }
    
    public static boolean eqv(Handle n1, Handle n2) throws TypeException {
        if (eq(n1, n2)) {
            return true;
        }
        if (n1.type() != n2.type()) {
            return false;
        }

        if (n1.hasType(Handle.NodeType.INTEGER)) {
            return n1.integerValue() == n2.integerValue();
        }

        if (n1.hasType(Handle.NodeType.DOUBLE)) {
            return n1.doubleValue() == n2.doubleValue();
        }

        if (n1.hasType(Handle.NodeType.CHARACTER)) {
            return n1.integerValue() == n2.integerValue();
        }

        return false;
    }

    
}
