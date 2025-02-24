package com.spella.parser.ast;

public class Number implements Expression{

    private final double value;

    public Number(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public double evaluate() {
        return value;
    }

}
