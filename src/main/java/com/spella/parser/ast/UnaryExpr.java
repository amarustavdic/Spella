package com.spella.parser.ast;

import com.spella.lexer.Token;
import com.spella.lexer.TokenType;

public class UnaryExpr implements Expr {

    private final Token operator;
    private final Expr right;

    public UnaryExpr(Token operator, Expr right) {
        this.operator = operator;
        this.right = right;
    }

    @Override
    public double evaluate() {
        double value = right.evaluate();
        if (operator.getType() == TokenType.MINUS) {
            return -value;
        }
        throw new RuntimeException("Unknown unary operator: " + operator.getLexeme());
    }
}
