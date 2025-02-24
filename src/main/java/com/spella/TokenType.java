package com.spella;

public enum TokenType {
    // Basic Tokens
    NUMBER,        // Represents a numeric value (int or float)
    PLUS,          // "+" operator (addition)
    MINUS,         // "-" operator (subtraction)
    TIMES,         // "*" operator (multiplication)
    DIVIDED_BY,    // "/" operator (division)

    // Delimiters and Grouping
    LPAREN,        // "("
    RPAREN,        // ")"

    EOF,           // End of file/input
}
