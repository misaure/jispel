package com.msaure.jispel.parser;

import com.msaure.jispel.parser.Token;

%%

%public
%class JFlexLexer
%standalone

%unicode

%line
%column

%{
  private Token currentToken;

  private int token(Token.TokenType type) {
    this.currentToken = Token.create()
        .withTokenType(type)
        .build();

    return type.ordinal();
  }
  private int token(Token.TokenType type, String value) {
    this.currentToken = Token.create()
        .withTokenType(type)
        .withLexicalValue(value)
        .build();

    return type.ordinal();
  }

  public Token getCurrentToken() {
    return this.currentToken;
  }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

%%

<YYINITIAL> {
  "("                           { return token(Token.TokenType.LPAREN, yytext()); }
  ")"                           { return token(Token.TokenType.RPAREN, yytext()); }
  "#f"                          { return token(Token.TokenType.FALSE, yytext()); }
  "#t"                          { return token(Token.TokenType.TRUE, yytext()); }
  "#("
  [0-9][0-9]*                    { return token(Token.TokenType.INT, yytext()); }
  [0-9][0-9]*\.?[0-9]*           { return token(Token.TokenType.DOUBLE, yytext()); }
  -[0-9][0-9]*\.?[0-9]*          { return token(Token.TokenType.DOUBLE, yytext()); }
}