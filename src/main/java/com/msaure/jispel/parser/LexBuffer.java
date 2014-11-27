package com.msaure.jispel.parser;

public class LexBuffer {
    public static final int DEFAULT_BUFFER_SIZE = 1024;

    private int pos;
    private char[] buffer;

    public LexBuffer() {
        this(DEFAULT_BUFFER_SIZE);
    }

    public LexBuffer(int size) {
        this.pos = 0;
        this.buffer = new char[size];
    }

    public int size() {
        return this.pos;
    }

    public int getMaxSize() {
        return this.buffer.length;
    }

    public void append(char c) {
        buffer[pos++] = c;
    }

    public void ungetc() {
        if (pos > 0) {
            --pos;
            buffer[pos] = '\0';
        }
    }

    public void reset() {
        this.pos = 0;
    }

    public String toString() {
        return new String(this.buffer, 0, pos);
    }
}
