package com.spella.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final String input;
    private final List<Token> tokens = new ArrayList<>();
    private int cursor = 0;
    private int line = 0;
    private int column = 0;

    public Lexer(String input) {
        this.input = input;
    }

    /*
        Takes stream of characters as input and turns them into
        meaningful tokens, that are then processed further by parser
     */
    public List<Token> tokenize() {

        while (cursor < input.length()) {
            char c = input.charAt(cursor);
            if (Character.isWhitespace(c)) {
                if (c == '\n') {
                    line++;
                    column = 0;
                } else column++;
                cursor++;
                continue;
            }
            if (c == '(') {
                cursor++;
                column++;
                tokens.add(new Token(TokenType.LPAREN, "(", null, line, column));
            } else if (c == ')') {
                cursor++;
                column++;
                tokens.add(new Token(TokenType.RPAREN, ")", null, line, column));
            } else if (Character.isDigit(c)) {
                int startColumn = column;
                StringBuilder number = new StringBuilder();
                boolean isDouble = false;
                int inputLength = input.length();
                while (cursor < inputLength) {
                    char ch = input.charAt(cursor);
                    if (Character.isDigit(ch)) number.append(ch);
                    else if (ch == '.') {
                        if (isDouble)
                            throw new RuntimeException("Unexpected character '" + ch + "' at line " + (line + 1) + ", column " + (column + 1));
                        isDouble = true;
                        number.append('.');
                    } else break;
                    cursor++;
                    column++;
                }
                tokens.add(new Token(TokenType.NUMBER, number.toString(), Double.parseDouble(number.toString()), line, startColumn));
            } else if (input.startsWith("plus", cursor)) {
                cursor += 4;
                column += 4;
                tokens.add(new Token(TokenType.PLUS, "plus", null, line, column));
            } else if (input.startsWith("minus", cursor)) {
                cursor += 5;
                column += 5;
                tokens.add(new Token(TokenType.MINUS, "minus", null, line, column));
            } else if (input.startsWith("times", cursor)) {
                cursor += 5;
                column += 5;
                tokens.add(new Token(TokenType.TIMES, "times", null, line, column));
            } else if (input.startsWith("divided by", cursor)) {
                cursor += 10;
                column += 10;
                tokens.add(new Token(TokenType.DIVIDED_BY, "divided by", null, line, column));
            } else
                throw new RuntimeException("Unexpected character '" + c + "' at line " + (line + 1) + ", column " + (column + 1));
        }

        // It is not that important here but bro how could have you forgotten about EOF :)
        tokens.add(new Token(TokenType.EOF, "", null, line, column));

        return tokens;
    }
}
