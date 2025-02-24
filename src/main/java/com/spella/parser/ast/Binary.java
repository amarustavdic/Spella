package com.spella.parser.ast;

final public class Binary implements Expression {

    private final Expression left;
    private final String operator;
    private final Expression right;

    public Binary(Expression left, String operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public double evaluate() {
        return switch (operator) {
            case "+" -> left.evaluate() + right.evaluate();
            case "-" -> left.evaluate() - right.evaluate();
            case "*" -> left.evaluate() * right.evaluate();
            case "/" -> left.evaluate() / right.evaluate();
            default -> throw new RuntimeException("Unknown operator: " + operator);
        };
    }

    public Expression getLeft() {
        return left;
    }

    public String getOperator() {
        return operator;
    }

    public Expression getRight() {
        return right;
    }

}
