package com.spella.parser.ast;

import com.spella.parser.visitors.Visitor;

public interface Expr {
    <T> T accept(Visitor<T> visitor);
}
