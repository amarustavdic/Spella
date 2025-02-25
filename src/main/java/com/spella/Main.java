package com.spella;

import com.spella.lexer.Lexer;
import com.spella.parser.Parser;
import com.spella.parser.visitors.CodeGenerator;
import com.spella.parser.visitors.Evaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        // AST Visitors
        var evaluator = new Evaluator();

        // Simple REPL
        var br = new BufferedReader(new InputStreamReader(System.in));
        var line = "";
        while (true) {
            System.out.print(">>> ");
            line = br.readLine();

            var lexer = new Lexer(line);
            var parser = new Parser(lexer.tokenize());
            var ast = parser.parse();

            // var result = ast.accept(evaluator);
            // System.out.println(result);

            // Testing code generation
            var code = ast.accept(new CodeGenerator());
            System.out.println(code);
            
        }
    }
}
