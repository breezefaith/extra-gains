package problem2;

public class GameLoaderFactory {
    public GameLoader createGameLoader(Parser parser) throws GameLoadException {
        GameLoader loader = new GameLoader();
        if(parser == null){
            throw new GameLoadException();
        }
        loader.setParser(parser);
        return loader;
    }
}
