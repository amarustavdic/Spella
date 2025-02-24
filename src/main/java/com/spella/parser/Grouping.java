package com.spella.parser;

final public class Grouping implements Expression {

    private final Expression expression;

    public Grouping(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

}
