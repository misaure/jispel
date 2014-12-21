package com.msaure.jispel.parser;

public class LexBuffer {

    public static final int DEFAULT_BUFFER_SIZE = 1024;
    
    private int pos;
    private int maxSize;
    private char[] buffer;

    public LexBuffer() {
        this(DEFAULT_BUFFER_SIZE);
    }
    
    /**
     * Create a read buffer capable of holding the given number of characters.
     */
    public LexBuffer(int maxSize) {
        this.maxSize = maxSize;
        this.pos = 0;
        this.buffer = new char[maxSize];
    }
    
  /**
     Append a character to the read buffer. This method also appends a zero
     character so that the buffer always holds a valid C string.
  */
    public void append(char c) {
        buffer[pos++] = c;
    }

    //public int getMaxSize() {
    //    return this.buffer.length;
    //}

    public void ungetc() {
        if (pos > 0) {
            --pos;
            buffer[pos] = '\0';
        }
    }
    
  /**
     Clears the buffer so it can be reused for storing a new value.
  */
    public void reset() {
        pos = 0;
    }
    
  /**
    Get number of characters added so far, i.e. the length of the string.
    */
    public int size() {
        return pos;
    }
    
    public int getMaxSize() {
        return this.maxSize;
    }
    
    @Override
    public String toString() {
        return new String(buffer, 0, pos);
    }

}
