package problem2;

public class ParserFactory {
    public Parser createParser(Lexer lexer) throws GameLoadException {
        Parser parser = new Parser();
        if(lexer == null){
            throw new GameLoadException();
        }
        parser.setLexer(lexer);
        return parser;
    }
}
