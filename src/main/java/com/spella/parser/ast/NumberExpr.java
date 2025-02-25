package com.spella.parser.ast;

import com.spella.lexer.Token;
import com.spella.parser.visitors.Visitor;

public class NumberExpr implements Expr {

    private final double value;

    public NumberExpr(Token number) {
        this.value = Double.parseDouble(number.getLexeme());
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitNumberExpr(this);
    }

    public double getValue() {
        return value;
    }

}
