package com.msaure.jispel.parser;

import java.io.IOException;
import java.io.Reader;

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
    
    public static final int LEXBUFFERSIZE = 1024;
    
    public static enum LexerState {
        INITIAL, SKIPWS, SPECIAL, ID, NUMBER, STRING, FLOAT, KEYWORD;
    }
    
    private final Reader is;
    private int putback = -1;
    
    public Lexer(Reader is) throws IOException {
        this.is = is;
        //c = is.read();
    }
    
    public Token nextToken() throws IOException {
        Token t = null; //new Token();
        LexBuffer buffer = new LexBuffer(LEXBUFFERSIZE);

        LexerState state = LexerState.INITIAL;
        for (;;) {
            int c = nextChar();
            
            //FIXME: many unget() can be saved when this is done only once outside
            // the loop, but I don't want to break the scanner again, right now...
            switch (state) {
                case INITIAL:
                    if (eof(c)) {
                        return new Token(Token.TokenType.EOF, "");

                    } else if (is_chartype(c, CHARTYPE_COMMENT)) {
                        skiptoeol(is);
                        break;

                    } else if (isspace(c)) {
                        state = LexerState.SKIPWS;
                        break;

                    } else if ('(' == c) {
                        return Token.LPAREN;

                    } else if (')' == c) {
                        return Token.RPAREN;

                    } else if ('#' == c) {
                        state = LexerState.SPECIAL;
                        break;

                    } else if (isalpha(c)) {
                        state = LexerState.ID;
                        break;

                    } else if (':' == c) {
                        state = LexerState.KEYWORD;	// consume leading ':'
                        break;

                    } else if (isdigit(c) || '-' == c || '+' == c) {
                        state = LexerState.NUMBER;
                        break;

                    } else if ('\'' == c) {
                        return Token.QUOTE;

                    } else if ('"' == c) {
                        state = LexerState.STRING;		// read string
                        break;

                    } else if (is_chartype(c, CHARTYPE_OPCHAR)) {
                        return new Token(Token.TokenType.OPERATOR, buffer.toString());

                    } else if (is_chartype(c, CHARTYPE_RELOP)) {
                        return new Token(Token.TokenType.ID, buffer.toString());

                    } else {
                        return new Token(Token.TokenType.ERROR, "illegal character");
                    }

                case SKIPWS:
                    while (!eof(c) && isspace(c)) {
                        c = is.read(); //is.get();
                    }
                    if (-1 == c) {
                        return Token.EOF;
                    }
                    //is.unget(), buffer.reset();
                    state = LexerState.INITIAL;
                    break;

                case SPECIAL: // read special introduced by '#'
                    if (eof(c)) {
                        return new Token(Token.TokenType.ERROR, "end-of-file after '#'");
                    }
                    if ('(' == c) {
                        return new Token(Token.TokenType.VECSTART, buffer.toString());

                    } else if ('f' == c) {
                        return new Token(Token.TokenType.FALSE, buffer.toString());

                    } else if ('t' == c) {
                        return new Token(Token.TokenType.TRUE, buffer.toString());

                    } else if ('!' == c) {
                        skiptoeol(is);
                        state = LexerState.INITIAL;
                        break;

                    } else if ('\\' == c) {
                        c = is.read(); //is.get();
                        for (;;) {
                            c = is.read(); //is.get();
                            if (isspace(c) || is_chartype(c, CHARTYPE_NONID)) {
                                break;
                            }
                            buffer.append((char) c);
                        }
                        //is.unget();

                        return new Token(Token.TokenType.CHARACTER, buffer.toString());

                    } else {
                        return new Token(Token.TokenType.ERROR, buffer.toString());
                    }

                case ID:
                    for (;;) {
                        if (is_chartype(c, CHARTYPE_NONID) || isspace(c) || eof(c)) {
                            //is.unget();
                            //--buffer;

                            return new Token(Token.TokenType.ID, buffer.toString());
                        }
                        //buffer.append(c = is.read());
                    }
                    //break;

                case NUMBER:
                    //is.get();
                    if ('+' == c || '-' == c) {
                        //buffer.append(c);
                        //c = is.get();
                    }
                    if (!isdigit(c)) {
                        return new Token(Token.TokenType.OPERATOR, buffer.toString());
                    }
                    while (isdigit(c)) {
                        //buffer.append(c);
                        //c = is.get();
                    }
                    if ('.' == c) {
                        state = LexerState.FLOAT;
                        break;
                    }
                    //is.unget();

                    return new Token(Token.TokenType.INT, buffer.toString());

                case STRING:
                    //c = is.read(); //is.get();				//skip leading quote character
                    while ('"' != c) {
                        if (eof(c)) {			// signal error on EOF
                            return new Token(Token.TokenType.ERROR, "end-of-file inside string");
                        }
                        if ('\\' == c) {		// save characters, performing
                            c = is.read(); //is.get();		// backslash substitution
                            switch (c) {
                                case 'n':
                                    c = '\n';
                                    break;
                                case 'r':
                                    c = '\r';
                                    break;
                                case 'b':
                                    c = '\b';
                                    break;
                                case 't':
                                    c = '\t';
                                    break;
	   // TODO case 'a': c = '\a'; break;
                                // TODO case 'v': c = '\v'; break;
                                case 'f':
                                    c = '\f';
                                    break;
                                case '"':
                                    c = '"';
                                    break;
                            }
                        }
                        //buffer.append(c);
                        c = is.read(); //is.get();
                    }

                    return new Token(Token.TokenType.STRING, buffer.toString());

                case FLOAT:
                    if (eof(c) || !isdigit(c)) {
                        //is.unget();
                        //buffer.unget();

                        return new Token(Token.TokenType.DOUBLE, buffer.toString());
                    }
                    break;

                case KEYWORD:
                    c = is.read(); //is.get();
                    while (isalnum(c)) {
                        //buffer.append(c);
                        c = is.read(); //is.get();
                    }
                    //is.unget();

                    return new Token(Token.TokenType.KEYWORD, buffer.toString());

                default:
                    return new Token(Token.TokenType.ERROR, "illegal scanner state");
            }
        }
    }
    
    protected void ungetc(char c) {
        if (-1 != this.putback) {
            throw new RuntimeException("putback buffer not empty");
        }
        this.putback = (int) c;
    }
    
    protected int nextChar() throws IOException {
        if (-1 != this.putback) {
            int t = this.putback;
            this.putback = -1;
            
            return t;
        }
        
        return is.read();
    }
    
    protected boolean eof(int c) {
        return -1 == c;
    }
    
    protected void skiptoeol(Reader is) {
        
    }
    
    protected boolean isalpha(int c) {
        return Character.isLetter((char) c);
    }
    
    protected boolean isdigit(int c) {
        return Character.isDigit((char) c);
    }
    
    protected boolean isspace(int c) {
        return Character.isSpaceChar((char) c);
    }
    
    protected boolean isalnum(int c) {
        throw new UnsupportedOperationException("not implemented");
    }
    
    protected boolean is_chartype(int c, long CHARTYPE_COMMENT) {
        return false; // FIXME: implement is_chartype
    }

}
