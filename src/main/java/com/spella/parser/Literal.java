package com.spella.parser;

final public class Literal implements Expression {

    private final double value;

    public Literal(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}
