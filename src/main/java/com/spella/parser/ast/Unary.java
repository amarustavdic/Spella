package com.spella.parser.ast;

public class Unary implements Expression {

    private final String unaryOperator;
    private final Expression right;

    public Unary(String unaryOperator, Expression right) {
        this.unaryOperator = unaryOperator;
        this.right = right;
    }

    @Override
    public double evaluate() {
        // TODO: implement, waiting on Ivan
        return 0;
    }
}
