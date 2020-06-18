package problem2;

public class LexerFactory {
    public Lexer createLexer(String filename) {
        Lexer lexer = new Lexer();
        lexer.setFilename(filename);
        return lexer;
    }
}
