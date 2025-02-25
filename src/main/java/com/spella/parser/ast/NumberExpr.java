package com.spella.parser.ast;

import com.spella.lexer.Token;

public class NumberExpr implements Expr {

    private final double value;

    public NumberExpr(Token number) {
        this.value = Double.parseDouble(number.getLexeme());
    }

    @Override
    public double evaluate() {
        return value;
    }

}
