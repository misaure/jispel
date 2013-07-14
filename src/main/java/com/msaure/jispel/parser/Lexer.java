package com.msaure.jispel.parser;

public class Lexer {

    public static final long CHARTYPE_ILLEGAL = 0L;
    public static final long CHARTYPE_IDBEGIN = (1L << 2);
    public static final long CHARTYPE_ID      = (1L << 3);
    public static final long CHARTYPE_DECIMAL = (1L << 4);
    public static final long CHARTYPE_LSTART  = (1L << 5);
    public static final long CHARTYPE_LEND    = (1L << 6);
    public static final long CHARTYPE_QUOTE   = (1L << 7);
    public static final long CHARTYPE_WS      = (1L << 8);
    public static final long CHARTYPE_COMMENT = (1L << 9);
    public static final long CHARTYPE_NONID   = (1L << 10);
    public static final long CHARTYPE_STRQUOT = (1L << 11);
    public static final long CHARTYPE_OPCHAR  = (1L << 12);
    public static final long CHARTYPE_RELOP   = (1L << 13);
    
    public static final char[] NON_ID_CHARACTERS = "()'\\\";".toCharArray();
    public static final char[] OPERATOR_CHARACTES = "+-*/".toCharArray();
    public static final char[] RELOP_CHARACTERS = "<>=".toCharArray();
    
}
