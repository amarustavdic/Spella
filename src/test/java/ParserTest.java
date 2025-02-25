import com.spella.lexer.Token;
import com.spella.lexer.TokenType;
import com.spella.parser.Parser;
import com.spella.parser.ast.Expr;
import com.spella.parser.visitors.Evaluator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private static Evaluator evaluator;

    @BeforeAll
    static void setUp() {
        evaluator = new Evaluator();
    }

    @Test
    void testSimpleAddition() {
        List<Token> tokens = new ArrayList<>();

        // 1 plus 2
        tokens.add(new Token(TokenType.NUMBER, "1", 1, 1, 1));
        tokens.add(new Token(TokenType.PLUS, "plus", null, 1, 3));
        tokens.add(new Token(TokenType.NUMBER, "2", 2, 1, 8));

        var parser = new Parser(tokens);
        var ast = parser.parse();

        assertEquals(3, ast.accept(evaluator));
    }

    @Test
    void testSimpleSubtraction() {
        List<Token> tokens = new ArrayList<>();

        // 5 minus 3
        tokens.add(new Token(TokenType.NUMBER, "5", 5, 1, 1));
        tokens.add(new Token(TokenType.MINUS, "minus", null, 1, 3));
        tokens.add(new Token(TokenType.NUMBER, "3", 3, 1, 5));

        var parser = new Parser(tokens);
        Expr ast = parser.parse();

        assertEquals(2, ast.accept(evaluator));
    }

    @Test
    void testSimpleMultiplication() {
        List<Token> tokens = new ArrayList<>();

        // 4 times 2
        tokens.add(new Token(TokenType.NUMBER, "4", 4, 1, 1));
        tokens.add(new Token(TokenType.TIMES, "times", null, 1, 3));
        tokens.add(new Token(TokenType.NUMBER, "2", 2, 1, 5));

        var parser = new Parser(tokens);
        Expr ast = parser.parse();

        assertEquals(8, ast.accept(evaluator));
    }

    @Test
    void testSimpleDivision() {
        List<Token> tokens = new ArrayList<>();

        // 8 divided by 2
        tokens.add(new Token(TokenType.NUMBER, "8", 8, 1, 1));
        tokens.add(new Token(TokenType.DIVIDED_BY, "divided by", null, 1, 3));
        tokens.add(new Token(TokenType.NUMBER, "2", 2, 1, 5));

        var parser = new Parser(tokens);
        Expr ast = parser.parse();

        assertEquals(4, ast.accept(evaluator));
    }

    @Test
    void testOperatorPrecedence() {
        List<Token> tokens = new ArrayList<>();

        // 2 plus 3 times 4 (Multiplication should be evaluated first)
        tokens.add(new Token(TokenType.NUMBER, "2", 2, 1, 1));
        tokens.add(new Token(TokenType.PLUS, "plus", null, 1, 3));
        tokens.add(new Token(TokenType.NUMBER, "3", 3, 1, 5));
        tokens.add(new Token(TokenType.TIMES, "times", null, 1, 7));
        tokens.add(new Token(TokenType.NUMBER, "4", 4, 1, 9));

        var parser = new Parser(tokens);
        Expr ast = parser.parse();

        assertEquals(14, ast.accept(evaluator));
    }

    @Test
    void testParenthesesPrecedence() {
        List<Token> tokens = new ArrayList<>();

        // (2 plus 3) times 4 (Parentheses should be evaluated first)
        tokens.add(new Token(TokenType.LPAREN, "(", null, 1, 1));
        tokens.add(new Token(TokenType.NUMBER, "2", 2, 1, 2));
        tokens.add(new Token(TokenType.PLUS, "plus", null, 1, 4));
        tokens.add(new Token(TokenType.NUMBER, "3", 3, 1, 6));
        tokens.add(new Token(TokenType.RPAREN, ")", null, 1, 7));
        tokens.add(new Token(TokenType.TIMES, "times", null, 1, 9));
        tokens.add(new Token(TokenType.NUMBER, "4", 4, 1, 11));

        var parser = new Parser(tokens);
        Expr ast = parser.parse();

        assertEquals(20, ast.accept(evaluator));
    }

    @Test
    void testNegativeNumbers() {
        List<Token> tokens = new ArrayList<>();

        // minus 5 + 3 (Unary minus should apply to 5)
        tokens.add(new Token(TokenType.MINUS, "minus", null, 1, 1));
        tokens.add(new Token(TokenType.NUMBER, "5", 5, 1, 2));
        tokens.add(new Token(TokenType.PLUS, "plus", null, 1, 4));
        tokens.add(new Token(TokenType.NUMBER, "3", 3, 1, 6));

        var parser = new Parser(tokens);
        Expr ast = parser.parse();

        assertEquals(-2, ast.accept(evaluator)); // (-5) + 3 = -2
    }

    @Test
    void testComplexExpression() {
        List<Token> tokens = new ArrayList<>();

        // (10 minus 4) times (3 plus 2) divided by 2
        tokens.add(new Token(TokenType.LPAREN, "(", null, 1, 1));
        tokens.add(new Token(TokenType.NUMBER, "10", 10, 1, 2));
        tokens.add(new Token(TokenType.MINUS, "minus", null, 1, 4));
        tokens.add(new Token(TokenType.NUMBER, "4", 4, 1, 6));
        tokens.add(new Token(TokenType.RPAREN, ")", null, 1, 7));
        tokens.add(new Token(TokenType.TIMES, "times", null, 1, 9));
        tokens.add(new Token(TokenType.LPAREN, "(", null, 1, 11));
        tokens.add(new Token(TokenType.NUMBER, "3", 3, 1, 12));
        tokens.add(new Token(TokenType.PLUS, "plus", null, 1, 14));
        tokens.add(new Token(TokenType.NUMBER, "2", 2, 1, 16));
        tokens.add(new Token(TokenType.RPAREN, ")", null, 1, 17));
        tokens.add(new Token(TokenType.DIVIDED_BY, "divided by", null, 1, 19));
        tokens.add(new Token(TokenType.NUMBER, "2", 2, 1, 21));

        var parser = new Parser(tokens);
        Expr ast = parser.parse();

        assertEquals(15, ast.accept(evaluator)); // ((10 - 4) * (3 + 2)) / 2 = 15
    }

}

