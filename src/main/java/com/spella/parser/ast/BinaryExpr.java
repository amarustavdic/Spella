package com.spella.parser.ast;

import com.spella.lexer.Token;
import com.spella.lexer.TokenType;

public class BinaryExpr implements Expr {

    private final Expr left;
    private final Token operator;
    private final Expr right;

    public BinaryExpr(Expr left, Token token, Expr right) {
        this.left = left;
        this.operator = token;
        this.right = right;
    }

    @Override
    public double evaluate() {
        double leftValue = left.evaluate();
        double rightValue = right.evaluate();

        if (operator.getType() == TokenType.DIVIDED_BY && rightValue == 0.0) {
            throw new ArithmeticException("Division by zero");
        }

        return switch (operator.getType()) {
            case PLUS -> leftValue + rightValue;
            case MINUS -> leftValue - rightValue;
            case TIMES -> leftValue * rightValue;
            case DIVIDED_BY -> leftValue / rightValue;
            default -> throw new RuntimeException("Unknown operator: " + operator.getLexeme());
        };
    }

}
