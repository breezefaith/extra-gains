package problem1;

public class Player extends AbstractObserver {
    private int health = 100;
    private int maxHealth = 100;
    private boolean isDead;
    private int number;

    public Player(int number, Subject subject) {
        super(subject);
        this.number = number;
    }

    /**
     * A player reaches the end and fires the event "playerArrived".
     */
    public void arrive() {
        System.out.println(String.format("Player %d reached the end.", this.number));
        this.subject.playerArrived(this);
    }

    /**
     * A player upgrades and fires the event "playerLevelUp".
     */
    public void levelUp() {
        System.out.println(String.format("Player %d leveled up.", this.number));
        this.subject.playerLevelUp(this);
    }

    /**
     * decrease the health when a player is hit, and set isHead as true when the health is less than 0.
     */
    public void beHit(int damage) {
        int origin = this.health;
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            this.isDead = true;
        }
        System.out.println(String.format("Player %d's health reduced from %d to %d.", this.number, origin, this.health));
        if (this.isDead == true) {
            System.out.println("player is dead.");
        }
    }

    /**
     * increase a player's maximum health.
     */
    public void increaseMaxHealth() {
        if (this.maxHealth >= 200) {
            System.out.println(String.format("Player %d's maximum health has been maximum 200.", this.number));
            return;
        }
        int origin = this.maxHealth;
        this.maxHealth += 10;
        if (this.maxHealth > 200) {
            this.maxHealth = 200;
        }
        System.out.println(String.format("Player %d's maximum health increased from %d to %d.", this.number, origin, this.maxHealth));
    }

    /**
     * restore a player's current health.
     */
    public void refillHealth() {
        int origin = this.health;
        this.health = this.maxHealth;
        System.out.println(String.format("Player %d's health restored from %d to %d.", this.number, origin, this.maxHealth));
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
