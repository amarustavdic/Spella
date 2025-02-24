package com.spella.parser;

import com.spella.lexer.Token;
import com.spella.parser.ast.Expression;

import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int pos = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }



    public Expression parse() {
        return null;
    }

}
