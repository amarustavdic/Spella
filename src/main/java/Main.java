import com.spella.Token;
import com.spella.TokenType;

public class Main {


    public static void main(String[] args) {

        var token = new Token(TokenType.LPAREN, "(", null, 1, 1);
        System.out.println(token);

    }
}
