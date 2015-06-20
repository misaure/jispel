package com.msaure.jispel.parser;

import java.io.IOException;

public interface Lexer {
    
    Token nextToken() throws IOException;
}
