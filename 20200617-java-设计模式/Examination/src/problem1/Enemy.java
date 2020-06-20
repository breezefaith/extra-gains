package problem1;

public class Enemy extends GameWorldElement {
    private int damage = 20;
    private int number;
    private boolean isDead;

    public Enemy(int number, ISubject subject) {
        super(subject);
        this.number = number;
    }

    @Override
    protected void handleLevelUp(Event event) {
        this.reduceDamage();
    }

    @Override
    protected void handleArriving(Event event) {
        this.die();
    }

    @Override
    protected void handleCollision(Event event) {
        if (event.getSource() == this) {
            this.die();
        }
    }

    /**
     * An enemy hits a player and fires the event "collision".
     *
     * @param player target of the hitting
     */
    public void hit(Player player) {
        System.out.println(String.format("Enemy %d hit the player %d.", this.number, player.getNumber()));
        this.subject.fireEvent(new Event(EventType.Collision, this, player));
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
     * An enemy dies.
     */
    public void die() {
        if (this.isDead == true) {
            return;
        }
        System.out.println(String.format("Enemy %d is dead.", this.number));
        this.isDead = true;
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

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
