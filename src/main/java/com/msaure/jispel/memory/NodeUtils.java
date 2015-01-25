package com.msaure.jispel.memory;

import java.io.PrintStream;

public class NodeUtils {
    
    public static String typetagToString( Handle.NodeType t) {
        return t.name();
        
        // TODO add to NodeType as additional attribute
//      switch( t) {
//      case Handle::ntEMPTY:		return "empty";
//      case Handle::ntBOOLEAN:	return "boolean";
//      case Handle::ntCHARACTER:	return "character";
//      case Handle::ntINTEGER:	return "integer";
//      case Handle::ntDOUBLE:	return "double";
//      case Handle::ntSTRING:	return "string";
//      case Handle::ntSYMBOL:	return "symbol";
//      case Handle::ntARRAY:
//      case Handle::ntAVECTOR:	return "vector";
//      case Handle::ntCONS:		return "pair";
//      case Handle::ntCFUNC:		return "builtin";
//      case Handle::ntCLOSURE:	return "closure";
//      default:
//        return "unknown type";
//      }
    }

    public static int listlength(Handle n) throws TypeException {
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
