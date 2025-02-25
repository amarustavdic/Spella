import com.spella.lexer.Lexer;
import com.spella.lexer.Token;
import com.spella.lexer.TokenType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest {

    @Test
    void testSimpleNumber() {
        Lexer lexer = new Lexer("42");
        List<Token> tokens = lexer.tokenize();

        assertEquals(2, tokens.size());
        assertToken(tokens.get(0), TokenType.NUMBER, "42");
        assertToken(tokens.get(1), TokenType.EOF, "");
    }

    @Test
    void testSimpleOperators() {
        Lexer lexer = new Lexer("plus minus times divided by");
        List<Token> tokens = lexer.tokenize();

        /*
            Ivan if you are wondering, maybe test is wrong, well now
            lexeme according to literature should be exact string that you have parsed
         */

        assertEquals(5, tokens.size());
        assertToken(tokens.get(0), TokenType.PLUS, "plus");
        assertToken(tokens.get(1), TokenType.MINUS, "minus");
        assertToken(tokens.get(2), TokenType.TIMES, "times");
        assertToken(tokens.get(3), TokenType.DIVIDED_BY, "divided by");
        assertToken(tokens.get(4), TokenType.EOF, "");
    }

    @Test
    void testParentheses() {
        Lexer lexer = new Lexer("( )()");
        List<Token> tokens = lexer.tokenize();

        assertEquals(5, tokens.size());
        assertToken(tokens.get(0), TokenType.LPAREN, "(");
        assertToken(tokens.get(1), TokenType.RPAREN, ")");
        assertToken(tokens.get(2), TokenType.LPAREN, "(");
        assertToken(tokens.get(3), TokenType.RPAREN, ")");
        assertToken(tokens.get(4), TokenType.EOF, "");
    }

    @Test
    void testComplexExpression() {
        Lexer lexer = new Lexer("3 plus 5 times (10 minus 2) divided by 4");
        List<Token> tokens = lexer.tokenize();

        assertEquals(12, tokens.size());
        assertToken(tokens.get(0), TokenType.NUMBER, "3");
        assertToken(tokens.get(1), TokenType.PLUS, "plus");
        assertToken(tokens.get(2), TokenType.NUMBER, "5");
        assertToken(tokens.get(3), TokenType.TIMES, "times");
        assertToken(tokens.get(4), TokenType.LPAREN, "(");
        assertToken(tokens.get(5), TokenType.NUMBER, "10");
        assertToken(tokens.get(6), TokenType.MINUS, "minus");
        assertToken(tokens.get(7), TokenType.NUMBER, "2");
        assertToken(tokens.get(8), TokenType.RPAREN, ")");
        assertToken(tokens.get(9), TokenType.DIVIDED_BY, "divided by");
        assertToken(tokens.get(10), TokenType.NUMBER, "4");
        assertToken(tokens.get(11), TokenType.EOF, "");
    }

    @Test
    void testWhitespaceHandling() {
        Lexer lexer = new Lexer("   12   plus   34  ");
        List<Token> tokens = lexer.tokenize();

        assertEquals(4, tokens.size());
        assertToken(tokens.get(0), TokenType.NUMBER, "12");
        assertToken(tokens.get(1), TokenType.PLUS, "plus");
        assertToken(tokens.get(2), TokenType.NUMBER, "34");
        assertToken(tokens.get(3), TokenType.EOF, "");
    }

    @Test
    void testFloatNumbers() {
        Lexer lexer = new Lexer("3.14 plus 2.71");
        List<Token> tokens = lexer.tokenize();

        assertEquals(4, tokens.size());
        assertToken(tokens.get(0), TokenType.NUMBER, "3.14");
        assertToken(tokens.get(1), TokenType.PLUS, "plus");
        assertToken(tokens.get(2), TokenType.NUMBER, "2.71");
        assertToken(tokens.get(3), TokenType.EOF, "");
    }

    @Test
    void testNegativeNumbers() {
        Lexer lexer = new Lexer("minus5 plus minus 3");
        List<Token> tokens = lexer.tokenize();

        assertEquals(6, tokens.size());
        assertToken(tokens.get(0), TokenType.MINUS, "minus");
        assertToken(tokens.get(1), TokenType.NUMBER, "5");
        assertToken(tokens.get(2), TokenType.PLUS, "plus");
        assertToken(tokens.get(3), TokenType.MINUS, "minus");
        assertToken(tokens.get(4), TokenType.NUMBER, "3");
        assertToken(tokens.get(5), TokenType.EOF, "");
    }

    @Test
    void testEndOfInput() {
        Lexer lexer = new Lexer("");
        List<Token> tokens = lexer.tokenize();

        assertEquals(1, tokens.size());
        assertToken(tokens.getFirst(), TokenType.EOF, "");
    }

    private void assertToken(Token token, TokenType expectedType, String expectedLexeme) {
        assertEquals(expectedType, token.getType(), "Unexpected token type");
        assertEquals(expectedLexeme, token.getLexeme(), "Unexpected lexeme");
    }

}
