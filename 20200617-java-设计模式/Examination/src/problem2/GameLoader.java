package problem2;

import java.util.ArrayList;

public class GameLoader {
    private Parser parser;

    public Game load() throws GameLoadException {
        Game game = new Game();
        for (Element element : parser.parse()) {
            if (element instanceof Brick) {
                game.addBrick((Brick) element);
            } else if (element instanceof Enemy) {
                game.addEnemy((Enemy) element);
            } else if (element instanceof Character) {
                game.addCharacter((Character) element);
            }
        }
        return game;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }
}
