package com.spella.parser.visitors;

import com.spella.parser.ast.BinaryExpr;
import com.spella.parser.ast.NumberExpr;
import com.spella.parser.ast.UnaryExpr;

public class CodeGenerator implements Visitor<StringBuilder>{

    private StringBuilder asm = new StringBuilder();

    public CodeGenerator() {
        // Initialize stack (move stack pointer all the way to the bottom)
        asm.append("MOV SP, 0x0FFF").append('\n');
    }

    @Override
    public StringBuilder visitBinaryExpr(BinaryExpr binaryExpr) {
        // Generate code for left operand
        binaryExpr.getLeft().accept(this);
        asm.append("PUSH A").append('\n'); // Save left operand

        // Generate code for right operand
        binaryExpr.getRight().accept(this);
        asm.append("POP B").append('\n'); // Restore left operand

        switch (binaryExpr.getOperator().getType()) {
            case PLUS -> asm.append("ADD A, B").append('\n');
            case MINUS -> asm.append("SUB A, B").append('\n');
            case TIMES -> asm.append("MUL B").append('\n');
            case DIVIDED_BY -> asm.append("DIV B").append('\n');
            default -> throw new RuntimeException("Unknown operator: " + binaryExpr.getOperator());
        };

        return asm;
    }

    @Override
    public StringBuilder visitUnaryExpr(UnaryExpr unaryExpr) {
        // TODO: Have to figure out how to handle this in 16-bit Assembly Simulator
        return asm;
    }

    @Override
    public StringBuilder visitNumberExpr(NumberExpr numberExpr) {
        asm.append("MOV A, ").append(numberExpr.getValue()).append('\n');
        return asm;
    }

}
