package com.spella.parser.ast;

import com.spella.lexer.Token;
import com.spella.parser.visitors.Visitor;

public class BinaryExpr implements Expr {

    private final Expr left;
    private final Token operator;
    private final Expr right;

    public BinaryExpr(Expr left, Token token, Expr right) {
        this.left = left;
        this.operator = token;
        this.right = right;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitBinaryExpr(this);
    }

    public Expr getLeft() {
        return left;
    }

    public Token getOperator() {
        return operator;
    }

    public Expr getRight() {
        return right;
    }

}
