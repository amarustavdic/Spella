package com.spella.evaluator;

import com.spella.parser.*;

public class Evaluator {

    public double evaluate(Expression expr) {
        if (expr instanceof Literal) {
            // Base case: Literal value
            return ((Literal) expr).getValue();
        }

        if (expr instanceof Unary) {
            // Unary expression (e.g., -5)
            Unary unary = (Unary) expr;
            double operand = evaluate(unary.getOperand()); // Evaluate the operand
            return applyUnaryOperator(unary.getOperator(), operand);
        }

        if (expr instanceof Binary) {
            // Binary expression (e.g., 3 + 4)
            Binary binary = (Binary) expr;
            double left = evaluate(binary.getLeft()); // Evaluate the left operand
            double right = evaluate(binary.getRight()); // Evaluate the right operand
            return applyBinaryOperator(binary.getOperator(), left, right);
        }

        if (expr instanceof Grouping) {
            // Grouping expression (parentheses) - just evaluate the inner expression
            Grouping grouping = (Grouping) expr;
            return evaluate(grouping.getExpression());
        }

        throw new RuntimeException("Unknown expression type: " + expr.getClass().getName());
    }

    private double applyUnaryOperator(String operator, double operand) {
        switch (operator) {
            case "-":
                return -operand;
            default:
                throw new RuntimeException("Unknown unary operator: " + operator);
        }
    }

    private double applyBinaryOperator(String operator, double left, double right) {
        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                if (right == 0) {
                    throw new RuntimeException("Division by zero!");
                }
                return left / right;
            default:
                throw new RuntimeException("Unknown binary operator: " + operator);
        }
    }

}
