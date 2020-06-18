package problem2;

public class GameLoaderFactory {
    public GameLoader createGameLoader(Parser parser) {
        GameLoader loader = new GameLoader();
        loader.setParser(parser);
        return loader;
    }
}
