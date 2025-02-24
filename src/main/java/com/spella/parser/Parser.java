package com.spella.parser;

import com.spella.lexer.Token;
import com.spella.lexer.TokenType;
import com.spella.parser.ast.Binary;
import com.spella.parser.ast.Expression;
import com.spella.parser.ast.Number;
import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int cursor = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token getNextToken() {
        return cursor < tokens.size() ? tokens.get(cursor++) : null;
    }

    private void goBack() {
        cursor = Math.max(0, cursor - 1);
    }

    private Expression factor() {
        Token token = getNextToken();
        if (token == null) throw new RuntimeException("Unexpected end of input");

        if (token.getType().equals(TokenType.NUMBER)) {
            return new Number((double) token.getLiteral());
        } else if (token.getType().equals(TokenType.LPAREN)) {
            var node = expr();
            Token closingParen = getNextToken();
            if (closingParen == null || !closingParen.getType().equals(TokenType.RPAREN)) {
                throw new RuntimeException("Expected closing parenthesis");
            }
            return node;
        } else {
            throw new RuntimeException("Unexpected token: " + token);
        }
    }

    private Expression term() {
        var left = factor();
        while (cursor < tokens.size()) {
            Token token = getNextToken();
            if (token == null) break;

            if (token.getType().equals(TokenType.TIMES) || token.getType().equals(TokenType.DIVIDED_BY)) {
                var right = factor();
                left = new Binary(left, token.getLexeme(), right);
            } else {
                goBack();
                break;
            }
        }
        return left;
    }

    private Expression expr() {
        var left = term();
        while (cursor < tokens.size()) {
            var token = getNextToken();
            if (token == null) break;

            if (token.getType().equals(TokenType.PLUS) || token.getType().equals(TokenType.MINUS)) {
                Expression right = term();
                left = new Binary(left, token.getLexeme(), right);
            } else {
                goBack();
                break;
            }
        }
        return left;
    }

    public Expression parse() {
        return expr();
    }

}
