package com.spella;

import com.spella.lexer.Lexer;
import com.spella.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        var br = new BufferedReader(new InputStreamReader(System.in));
        var line = "";
        while (true) {
            System.out.print("> ");
            line = br.readLine();

            var lexer = new Lexer(line);
            var parser = new Parser(lexer.tokenize());
            System.out.println(parser.parse().evaluate());
        }
    }
}
