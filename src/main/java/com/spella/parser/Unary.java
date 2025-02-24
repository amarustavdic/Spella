package com.spella.parser;

final public class Unary implements Expression {

    private final String operator;
    private final Expression operand;

    public Unary(String operator, Expression operand) {
        this.operator = operator;
        this.operand = operand;
    }

    public String getOperator() {
        return operator;
    }

    public Expression getOperand() {
        return operand;
    }

}
