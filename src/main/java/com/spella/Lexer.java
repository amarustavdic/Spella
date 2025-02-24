package com.spella;

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

            if (input.charAt(cursor) == ' ') {
                cursor++; column++;
            }
            if (input.charAt(cursor) == '\n') {
                cursor++; line++;
            }
            if (input.charAt(cursor) == '\t') {
                cursor++; column++;
            }
            if (input.charAt(cursor) == '\r') {
                cursor++; column++;
            }

            if (input.charAt(cursor) == '(') {
                cursor++;
                column++;
                tokens.add(new Token(TokenType.LPAREN, "(", null, line, column));
            }

            if (input.charAt(cursor) == ')') {
                cursor++;
                column++;
                tokens.add(new Token(TokenType.RPAREN, ")", null, line, column));
            }

        }

        return tokens;
    }

}
