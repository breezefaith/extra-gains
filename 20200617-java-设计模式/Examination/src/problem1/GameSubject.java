package problem1;

public class GameSubject extends AbstractGameSubject {
    /**
     * Event: collision
     *
     * @param player the player who was hit.
     * @param enemy  the enemy who hit the player.
     */
    public void collision(Player player, Enemy enemy) {
        System.out.println("The event \"collision\" was fired.");
        if (players.contains(player) == false || enemies.contains(enemy) == false) {
            return;
        }
        //remove the enemy
        enemy.die();
        //decrease player's health
        player.beHit(enemy.getDamage());
    }

    /**
     * Event: playerLevelUp.
     *
     * @param player the player who levels up.
     */
    public void playerLevelUp(Player player) {
        System.out.println("The event \"playerLevelUp\" was fired.");
        if (this.players.contains(player) == false) {
            return;
        }
        //increase player's maximum health
        player.increaseMaxHealth();
        //restore player's current health
        player.refillHealth();
        //reduce enemies' damage to the player
        for (Enemy enemy : enemies) {
            enemy.reduceDamage();
        }
    }

    /**
     * Event: playerArrived
     *
     * @param player the player who reaches the end.
     */
    public void playerArrived(Player player) {
        System.out.println("The event \"playerArrived\" was fired.");
        if (this.players.contains(player) == false) {
            return;
        }
        //kill all of the enemies
        for (int i = 0; i < this.enemies.size(); ) {
            enemies.get(i).die();
        }
    }
}
