package problem2;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Character> characters = new ArrayList<>();
    private List<Brick> bricks = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void addBrick(Brick brick) {
        bricks.add(brick);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
}
