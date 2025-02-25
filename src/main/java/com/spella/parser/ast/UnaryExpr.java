package com.spella.parser.ast;

import com.spella.lexer.Token;
import com.spella.parser.visitors.Visitor;

public class UnaryExpr implements Expr {

    private final Token operator;
    private final Expr right;

    public UnaryExpr(Token operator, Expr right) {
        this.operator = operator;
        this.right = right;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitUnaryExpr(this);
    }

    public Token getOperator() {
        return operator;
    }

    public Expr getRight() {
        return right;
    }

}
