package com.spella.parser;

import com.spella.lexer.Token;
import com.spella.lexer.TokenType;

import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int cursor = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Expression parse() {
        return expression();
    }

    // Handles addition and subtraction (left to right)
    private Expression expression() {
        var expr = term();

        while (match(TokenType.TIMES, TokenType.DIVIDED_BY)) {
            var operator = previous();
            var right = term();
            expr = new Binary(expr, toOperator(operator), right);
        }
        return expr;
    }

    private Expression term() {
        var expr = factor();

        while (match(TokenType.TIMES, TokenType.DIVIDED_BY)) {
            Token operator = previous();
            var right = factor();
            expr = new Binary(expr, toOperator(operator), right);
        }
        return expr;
    }

    private Expression factor() {
        if (match(TokenType.MINUS)) {
            Token operator = previous();
            var operand = factor();
            return new Unary(toOperator(operator), operand);
        }

        if (match(TokenType.NUMBER)) {
            return new Literal((double) previous().getLiteral());
        }

        if (match(TokenType.LPAREN)) {
            var expr = expression();
            consume(TokenType.RPAREN, "Expected ')' after expression.");
            return new Grouping(expr);
        }

        throw new RuntimeException("Unexpected token: " + peek().getLexeme());
    }

    // Check if current token matches one of the given types and advance if it does
    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    // Check if the current token is of a given type
    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().getType() == type;
    }

    // Advance to the next token and return the previous one
    private Token advance() {
        if (!isAtEnd()) cursor++;
        return previous();
    }

    // Get the previous token
    private Token previous() {
        return tokens.get(cursor - 1);
    }

    // Get the current token
    private Token peek() {
        return tokens.get(cursor);
    }

    // Check if we've reached the end of the token list
    private boolean isAtEnd() {
        return peek().getType() == TokenType.EOF;
    }

    // Consume a token of a specific type, or throw an error
    private Token consume(TokenType type, String errorMessage) {
        if (check(type)) return advance();
        throw new RuntimeException(errorMessage);
    }

    private String toOperator(Token token) {
        return switch (token.getType()) {
            case PLUS -> "+";
            case MINUS -> "-";
            case TIMES -> "*";
            case DIVIDED_BY -> "/";
            default -> throw new RuntimeException("Invalid operator: " + token.getLexeme());
        };
    }

}
