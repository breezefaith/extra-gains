package problem1;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGameSubject implements ISubject {
    protected List<Player> players = new ArrayList<>();
    protected List<Enemy> enemies = new ArrayList<>();

    /**
     * register an observer.
     *
     * @param observer
     */
    public void attach(AbstractObserver observer) {
        if (observer instanceof Player) {
            this.players.add((Player) observer);
        } else if (observer instanceof Enemy) {
            this.enemies.add((Enemy) observer);
        }
    }

    /**
     * remove an observer.
     *
     * @param observer
     */
    public void detach(AbstractObserver observer) {
        if (observer instanceof Player) {
            this.players.remove(observer);
        } else if (observer instanceof Enemy) {
            this.enemies.remove(observer);
        }
    }

    /**
     * Event: collision
     *
     * @param player the player who was hit.
     * @param enemy  the enemy who hit the player.
     */
    public abstract void collision(Player player, Enemy enemy);

    /**
     * Event: playerLevelUp.
     *
     * @param player the player who levels up.
     */
    public abstract void playerLevelUp(Player player);

    /**
     * Event: playerArrived
     *
     * @param player the player who reaches the end.
     */
    public abstract void playerArrived(Player player);
}
