package com.spella;

public class Token {

    private final TokenType type;
    private final String lexeme;
    private final Object literal;
    private final int line;
    private final int column;

    public Token(TokenType type, String lexeme, Object literal, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return "{"
                + "\"type\": \"" + type + "\", "
                + "\"lexeme\": \"" + lexeme + "\", "
                + "\"literal\": \"" + literal + "\", "
                + "\"line\": " + line + ", "
                + "\"column\": " + column
                + "}";
    }
}
