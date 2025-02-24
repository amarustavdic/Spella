package com.spella.parser.ast;

public class Number implements Expression{

    private double value;

    public Number(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public double evaluate() {
        return 0;
    }
}
