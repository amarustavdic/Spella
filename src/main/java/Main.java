import com.spella.lexer.Lexer;
import com.spella.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        var lexer = new Lexer();

        var br = new BufferedReader(new InputStreamReader(System.in));
        var line = "";
        while (true) {
            line = br.readLine();
            var tokens = lexer.tokenize(line);
            var parser = new Parser(tokens);
            var expr = parser.parse();
            System.out.println("Result: " + expr.evaluate());
        }
    }
}
