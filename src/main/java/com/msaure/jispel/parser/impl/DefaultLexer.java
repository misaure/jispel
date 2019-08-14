package com.msaure.jispel.parser.impl;

import java.io.IOException;
import java.io.Reader;

import com.msaure.jispel.parser.LexBuffer;
import com.msaure.jispel.parser.Lexer;
import com.msaure.jispel.parser.Token;
import com.msaure.jispel.parser.TokenFactory;

public class DefaultLexer implements Lexer {

    public static final long CHARTYPE_ILLEGAL = 0L;
    public static final long CHARTYPE_IDBEGIN = (1L << 2);
    public static final long CHARTYPE_ID      = (1L << 3);
    public static final long CHARTYPE_DECIMAL = (1L << 4);
    public static final long CHARTYPE_LSTART  = (1L << 5);
    public static final long CHARTYPE_LEND    = (1L << 6);
    public static final long CHARTYPE_QUOTE   = (1L << 7);
    public static final long CHARTYPE_WS      = (1L << 8);
    private static final long CHARTYPE_COMMENT = (1L << 9);
    private static final long CHARTYPE_NONID   = (1L << 10);
    public static final long CHARTYPE_STRQUOT = (1L << 11);
    private static final long CHARTYPE_OPCHAR  = (1L << 12);
    private static final long CHARTYPE_RELOP   = (1L << 13);
    
    public static final char[] NON_ID_CHARACTERS = "()'\\\";".toCharArray();
    public static final char[] OPERATOR_CHARACTES = "+-*/".toCharArray();
    public static final char[] RELOP_CHARACTERS = "<>=".toCharArray();
    
    private static final int EOF = -2;
    private static final int EMPTY = -1;
    
    private static final int LEXBUFFERSIZE = 1024;
    
    public enum LexerState {
        INITIAL, SKIPWS, SPECIAL, ID, NUMBER, STRING, FLOAT, KEYWORD;
    }
    
    private final Reader is;
    private final TokenFactory tokenFactory;

    private int putback = -1;
    private int lookahead = EMPTY;
    
    public DefaultLexer(Reader is) throws IOException {
        this(is, new DefaultTokenFactory());
    }

    public DefaultLexer(Reader is, TokenFactory tokenFactory) {
        this.is = is;
        this.tokenFactory = tokenFactory;
    }
    
    @Override
    public Token nextToken() throws IOException {
        Token t = null; //new Token();
        LexBuffer buffer = new LexBuffer(LEXBUFFERSIZE);

        LexerState state = LexerState.INITIAL;
        
        for (;;) {
        	
            if (EMPTY == lookahead) {
            	lookahead = nextChar();
            }
            
            //FIXME: many unget() can be saved when this is done only once outside
            // the loop, but I don't want to break the scanner again, right now...
            switch (state) {
                case INITIAL:
                    if (eof(lookahead)) {
                        return new Token(Token.TokenType.EOF, "");

                    } else if (is_chartype(lookahead, CHARTYPE_COMMENT)) {
                        skiptoeol(is);
                        break;

                    } else if (isspace(lookahead)) {
                        state = LexerState.SKIPWS;
                        break;

                    } else if ('(' == lookahead) {
                        return tokenFactory.leftParenthesis();

                    } else if (')' == lookahead) {
                        return tokenFactory.rightParenthesis();

                    } else if ('#' == lookahead) {
                        state = LexerState.SPECIAL;
                        consume();
                        break;

                    } else if (isalpha(lookahead)) {
                        state = LexerState.ID;
                        break;

                    } else if (':' == lookahead) {
                        state = LexerState.KEYWORD;	// consume leading ':'
                        break;

                    } else if (isdigit(lookahead) || '-' == lookahead || '+' == lookahead) {
                        state = LexerState.NUMBER;
                        consume();
                        buffer.append((char) lookahead);
                        break;

                    } else if ('\'' == lookahead) {
                        return Token.QUOTE;

                    } else if ('"' == lookahead) {
                        state = LexerState.STRING;		// read string
                        break;

                    } else if (is_chartype(lookahead, CHARTYPE_OPCHAR)) {
                        return new Token(Token.TokenType.OPERATOR, buffer.toString());

                    } else if (is_chartype(lookahead, CHARTYPE_RELOP)) {
                        return new Token(Token.TokenType.ID, buffer.toString());

                    } else {
                        return new Token(Token.TokenType.ERROR, "illegal character");
                    }

                case SKIPWS:
                    while (!eof(lookahead) && isspace(lookahead)) {
                    	lookahead = is.read(); //is.get();
                    }
                    if (-1 == lookahead) {
                        return Token.EOF;
                    }
                    //is.unget(), buffer.reset();
                    state = LexerState.INITIAL;
                    break;

                case SPECIAL: // read special introduced by '#'
                    if (eof(lookahead)) {
                        return new Token(Token.TokenType.ERROR, "end-of-file after '#'");
                    }
                    if ('(' == lookahead) {
                        return new Token(Token.TokenType.VECSTART, buffer.toString());

                    } else if ('f' == lookahead) {
                    	consume();
                        return tokenFactory.falseToken();

                    } else if ('t' == lookahead) {
                    	consume();
                        return tokenFactory.trueToken();

                    } else if ('!' == lookahead) {
                        skiptoeol(is);
                        state = LexerState.INITIAL;
                        break;

                    } else if ('\\' == lookahead) {
                    	lookahead = is.read(); //is.get();
                        for (;;) {
                        	lookahead = is.read(); //is.get();
                            if (isspace(lookahead) || is_chartype(lookahead, CHARTYPE_NONID)) {
                                break;
                            }
                            buffer.append((char) lookahead);
                        }

                        return new Token(Token.TokenType.CHARACTER, buffer.toString());

                    } else {
                        return new Token(Token.TokenType.ERROR, buffer.toString());
                    }

                case ID:
                	buffer.append((char) lookahead);
                	consume();
                	
                    for (;;) {
                    	lookahead = nextChar();
                    	
                        if (is_chartype(lookahead, CHARTYPE_NONID) || isspace(lookahead) || eof(lookahead)) {
                            //return new Token(Token.TokenType.ID, buffer.toString());
                            return tokenFactory.identifierToken(buffer.toString());
                        } else {
                        	buffer.append((char) lookahead);
                        	consume();
                        }
                    }
                    //break;

                case NUMBER:
                    if (!isdigit(lookahead) && '.' != ((char) lookahead)) {
                        return new Token(Token.TokenType.INT, buffer.toString());
                    }
                    else if (isdigit(lookahead)) {
                    	buffer.append((char) lookahead);
                    	consume();
                    }
                    else if ('.' == lookahead) {
                        state = LexerState.FLOAT;
                        buffer.append('.');
                        consume();
                    }
                    //is.unget();

                    return new Token(Token.TokenType.INT, buffer.toString());

                case STRING:
                    while ('"' != lookahead) {
                        if (eof(lookahead)) {			// signal error on EOF
                            return new Token(Token.TokenType.ERROR, "end-of-file inside string");
                        }
                        if ('\\' == lookahead) {		// save characters, performing
                        	lookahead = is.read(); //is.get();		// backslash substitution
                            switch (lookahead) {
                                case 'n':
                                	lookahead = '\n';
                                    break;
                                case 'r':
                                	lookahead = '\r';
                                    break;
                                case 'b':
                                	lookahead = '\b';
                                    break;
                                case 't':
                                	lookahead = '\t';
                                    break;
	   // TODO case 'a': c = '\a'; break;
                                // TODO case 'v': c = '\v'; break;
                                case 'f':
                                	lookahead = '\f';
                                    break;
                                case '"':
                                	lookahead = '"';
                                    break;
                            }
                        }
                        lookahead = is.read(); //is.get();
                    }

                    return new Token(Token.TokenType.STRING, buffer.toString());

                case FLOAT:
                    if (eof(lookahead) || !isdigit(lookahead)) {
                        return new Token(Token.TokenType.DOUBLE, buffer.toString());
                    }
                    break;

                case KEYWORD:
                	lookahead = is.read(); //is.get();
                    while (isalnum(lookahead)) {
                    	lookahead = is.read(); //is.get();
                    }

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
    
    private int nextChar() throws IOException {
    	if (EOF == this.lookahead) {
    		throw new IOException("attempt to read past end of file");
    	}
    	
    	if (EMPTY == this.lookahead) {
    		int c = is.read();
    		
    		if (-1 == c) {
    			this.lookahead = EOF;
    		} else {
    			this.lookahead = c;
    		}
    	}
        
        return lookahead;
    }
    
    private void consume() {
    	this.lookahead = EMPTY;
    }

    private boolean eof(int c) {
        return EOF == c;
    }
    
    protected boolean empty(int c) {
    	return EMPTY == c;
    }

    private void skiptoeol(Reader is) {
        
    }

    private boolean isalpha(int c) {
        return Character.isLetter((char) c);
    }

    private boolean isdigit(int c) {
        return Character.isDigit((char) c);
    }

    private boolean isspace(int c) {
        return Character.isSpaceChar((char) c);
    }

    private boolean isalnum(int c) {
    	return Character.isLetter(c) || Character.isDigit(c);
    }

    private boolean is_chartype(int c, long CHARTYPE_COMMENT) {
        return false; // FIXME: implement is_chartype
    }

}
