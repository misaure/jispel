package com.msaure.jispel.parser;

public interface TokenFactory {

    Token leftParenthesis();

    Token rightParenthesis();

    Token identifierToken(String name);

    Token falseToken();

    Token trueToken();
}
