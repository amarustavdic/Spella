package com.spella.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    /*
        Takes stream of characters as input and turns them into
        meaningful tokens, that are then processed further by parser
     */
    public List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        int cursor = 0;
        int line = 0;
        int column = 0;

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
                while (cursor < input.length() && Character.isDigit(input.charAt(cursor))) {
                    number.append(input.charAt(cursor));
                    cursor++;
                    column++;
                }
                tokens.add(new Token(TokenType.NUMBER, number.toString(), Double.parseDouble(number.toString()), line, startColumn));
            } else if (input.startsWith("plus", cursor)) {
                cursor += 4;
                column += 4;
                tokens.add(new Token(TokenType.PLUS, "+", null, line, column));
            } else if (input.startsWith("minus", cursor)) {
                cursor += 5;
                column += 5;
                tokens.add(new Token(TokenType.MINUS, "-", null, line, column));
            } else if (input.startsWith("times", cursor)) {
                cursor += 5;
                column += 5;
                tokens.add(new Token(TokenType.TIMES, "*", null, line, column));
            } else if (input.startsWith("divided by", cursor)) {
                cursor += 10;
                column += 10;
                tokens.add(new Token(TokenType.DIVIDED_BY, "/", null, line, column));
            } else
                throw new RuntimeException("Unexpected character '" + c + "' at line " + line + ", column " + column);
        }

        return tokens;
    }
}
