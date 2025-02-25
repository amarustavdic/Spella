package com.spella.parser.visitors;

import com.spella.parser.ast.BinaryExpr;
import com.spella.parser.ast.NumberExpr;
import com.spella.parser.ast.UnaryExpr;

public interface Visitor<T> {
    T visitBinaryExpr(BinaryExpr binaryExpr);
    T visitUnaryExpr(UnaryExpr unaryExpr);
    T visitNumberExpr(NumberExpr numberExpr);
}
