package problem2;

public class Main {
    public static void main(String[] args) {
        LexerFactory lexerFactory = new LexerFactory();
        ParserFactory parserFactory = new ParserFactory();
        GameLoaderFactory gameLoaderFactory = new GameLoaderFactory();

        Lexer lexer = lexerFactory.createLexer("");
        Parser parser = parserFactory.createParser(lexer);
        GameLoader gameLoader = gameLoaderFactory.createGameLoader(parser);

        Game game = gameLoader.load();
    }
}
