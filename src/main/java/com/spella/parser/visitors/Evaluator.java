package com.spella.parser.visitors;

import com.spella.lexer.TokenType;
import com.spella.parser.ast.BinaryExpr;
import com.spella.parser.ast.NumberExpr;
import com.spella.parser.ast.UnaryExpr;

public class Evaluator implements Visitor<Double> {

    @Override
    public Double visitBinaryExpr(BinaryExpr binaryExpr) {
        double left = binaryExpr.getLeft().accept(this);
        double right = binaryExpr.getRight().accept(this);

        return switch (binaryExpr.getOperator().getType()) {
            case PLUS -> left + right;
            case MINUS -> left - right;
            case TIMES -> left * right;
            case DIVIDED_BY -> left / right;
            default -> throw new RuntimeException("Unknown operator: " + binaryExpr.getOperator());
        };
    }

    @Override
    public Double visitUnaryExpr(UnaryExpr unaryExpr) {
        double value = unaryExpr.getRight().accept(this);
        if (unaryExpr.getOperator().getType() == TokenType.MINUS) {
            return -value;
        }
        throw new RuntimeException("Unknown unary operator: " + unaryExpr.getOperator());
    }

    @Override
    public Double visitNumberExpr(NumberExpr numberExpr) {
        return numberExpr.getValue();
    }

}
