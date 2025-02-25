package com.spella.parser;

import com.spella.lexer.Token;
import com.spella.lexer.TokenType;
import com.spella.parser.ast.BinaryExpr;
import com.spella.parser.ast.Expr;
import com.spella.parser.ast.NumberExpr;
import com.spella.parser.ast.UnaryExpr;
import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int cursor = 0;
    private Token currentToken;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentToken = tokens.get(cursor);
    }

    private void eat(TokenType type) {
        if (currentToken.getType() == type) {
            cursor++;
            if (cursor < tokens.size()) {
                currentToken = tokens.get(cursor);
            } else {
                currentToken = new Token(TokenType.EOF, "", null, -1, -1); // Mark end of input
            }
        } else {
            throw new RuntimeException("Unexpected token: " + currentToken.getLexeme());
        }
    }

    // Parses: <expression> ::= <term> ( ("plus" | "minus") <term> )*
    private Expr expression() {
        Expr left = term(); // Parse the first term

        while (currentToken.getType() == TokenType.PLUS || currentToken.getType() == TokenType.MINUS) {
            Token operator = currentToken;
            eat(operator.getType()); // consume "plus" or "minus"
            Expr right = term();
            left = new BinaryExpr(left, operator, right);
        }
        return left;
    }

    // Parse: <term> ::= <factor> ( ("times" | "divided by") <factor> )*
    private Expr term() {
        Expr left = factor(); // Parse first factor

        while (currentToken.getType() == TokenType.TIMES || currentToken.getType() == TokenType.DIVIDED_BY) {
            Token operator = currentToken;
            eat(operator.getType());
            Expr right = factor();
            left = new BinaryExpr(left, operator, right);
        }
        return left;
    }

    // Parse: <factor> ::= <unary> | <grouping> | <number>
    private Expr factor() {
        if (currentToken.getType() == TokenType.NUMBER) {
            Token numberToken = currentToken;
            eat(numberToken.getType());
            return new NumberExpr(numberToken);
        } else if (currentToken.getType() == TokenType.LPAREN) {
            eat(TokenType.LPAREN);
            Expr expr = expression();
            eat(TokenType.RPAREN);
            return expr;
        } else if (currentToken.getType() == TokenType.MINUS) {
            Token operator = currentToken;
            eat(TokenType.MINUS);
            Expr right = factor();
            return new UnaryExpr(operator, right);
        }
        throw new RuntimeException("Unexpected token: " + currentToken.getLexeme());
    }

    public Expr parse() {
        return expression();
    }

}
