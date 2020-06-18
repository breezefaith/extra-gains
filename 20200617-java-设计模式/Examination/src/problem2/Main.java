package problem2;

public class Main {
    public static void main(String[] args) throws GameLoadException {
        String filename = ".\\game.txt";
        Lexer lexer = new Lexer(filename);

        ParserFactory parserFactory = new ParserFactory();
        GameLoaderFactory gameLoaderFactory = new GameLoaderFactory();

        Parser parser = parserFactory.createParser(lexer);
        GameLoader gameLoader = gameLoaderFactory.createGameLoader(parser);

        Game game = gameLoader.load();
        System.out.println(game);
    }
}
