import com.spella.lexer.Lexer;
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

            // For now just printing out recognized tokens
            for (var token : tokens) {
                System.out.println(token.getType());
            }
        }

    }
}
