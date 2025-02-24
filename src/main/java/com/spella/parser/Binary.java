package com.spella.parser;

final public class Binary implements Expression {

    private final Expression left;
    private final String operator;
    private final Expression right;

    public Binary(Expression left, String operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
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
