import com.spella.evaluator.Evaluator;
import com.spella.lexer.Lexer;
import com.spella.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        var lexer = new Lexer();

        // Simple REPL loop
        var br = new BufferedReader(new InputStreamReader(System.in));
        var line = "";
        while (true) {
            line = br.readLine();

            var tokens = lexer.tokenize(line);

//            for (var token : tokens) System.out.println(token.getType());

            var parser = new Parser(tokens);
            var expr = parser.parse();

            var eval = new Evaluator();

            double result = eval.evaluate(expr);

            System.out.println("Result: " + result);
        }

    }
}
