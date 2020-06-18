package problem2;

public class GameLoader {
    private Parser parser;

    public Game load() {
        Game game = new Game();
        for (Element element : parser.parse()) {
            if (element instanceof Brick) {
                game.getBricks().add((Brick) element);
            } else if (element instanceof Enemy) {
                game.getEnemies().add((Enemy) element);
            } else if (element instanceof Character) {
                game.getCharacters().add((Character) element);
            }
        }
        return game;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }
}
