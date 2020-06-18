package problem2;

public class ParserFactory {
    public Parser createParser(Lexer lexer) {
        Parser parser = new Parser();
        parser.setLexer(lexer);
        return parser;
    }
}
