package com.spella.parser;

final public class Literal implements Expression {

    private final double value;

    public Literal(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static void main(String[] args) {

        var expr = new Binary(
                new Literal(2),
                "PLUS",
                new Literal(3)
        );

    }

}
