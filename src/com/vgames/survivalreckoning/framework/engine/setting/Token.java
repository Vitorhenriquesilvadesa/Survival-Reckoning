package com.vgames.survivalreckoning.framework.engine.setting;

public class Token {

    private final TokenType tokenType;
    private final String lexeme;
    private final int line;
    private final int column;

    public Token(TokenType tokenType, String lexeme, int line, int column) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Token { " + tokenType.name() + ": " + lexeme + " }";
    }
}
