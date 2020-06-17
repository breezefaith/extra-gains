package problem1;

public class Enemy extends AbstractObserver {
    private int damage = 20;
    private int number;

    public Enemy(int number, Subject subject) {
        super(subject);
        this.number = number;
    }

    /**
     * An enemy hits a player and fires the event "collision".
     *
     * @param player target of the hitting
     */
    public void hit(Player player) {
        System.out.println(String.format("Enemy %d hit the player %d.", this.number, player.getNumber()));
        this.subject.collision(player, this);
    }

    /**
     * Reduce the damage to a player.
     */
    public void reduceDamage() {
        if (this.damage <= 5) {
            System.out.println(String.format("Enemy %d's damage has been minimum 5.", this.number));
            return;
        }
        int origin = this.damage;
        this.damage -= 5;
        if (this.damage < 5) {
            this.damage = 5;
        }
        System.out.println(String.format("Enemy %d's damage reduced from %d to %d.", this.number, origin, this.damage));
    }

    /**
     * An enemy dies and it should be remove from the observers' list.
     */
    public void die() {
        System.out.println(String.format("Enemy %d is dead.", this.number));
        this.subject.detach(this);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
