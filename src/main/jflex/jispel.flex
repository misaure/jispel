package com.msaure.jispel.parser;

%%

%public
%class JFlexLexer
%standalone

%unicode

%line
%column

%{
  StringBuilder sb = new StringBuilder();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

%%

<YYINITIAL> {
  "("
  ")"
  "#f"
  "#t"
  "#("
  [0-9][0-9]*                    { return m_SF.newSymbol("Integer", sym.INTEGER, new Integer(yytext())); }
  [0-9][0-9]*\.?[0-9]*           { return m_SF.newSymbol("Double", sym.DOUBLE, new Double(yytext())); }
  -[0-9][0-9]*\.?[0-9]*          { return m_SF.newSymbol("Double", sym.DOUBLE, new Double(yytext())); }
}