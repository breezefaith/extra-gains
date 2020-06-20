package problem1;

public class Main {
    public static void main(String[] args) {
        // create the subject
        GameWorldSubject subject = new GameWorldSubject();

        //create a player object and register it as an observer
        Player player = new Player(0, subject);

        //create 10 enemy objects and register them as observers
        Enemy[] enemies = new Enemy[10];
        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy(i, subject);
        }

        //fire three kinds of events
        //playerLevelUp event
        System.out.println("============================================================");
        player.levelUp();
        System.out.println("============================================================\n");

        //collision event
        System.out.println("============================================================");
        enemies[0].hit(player);
        enemies[4].hit(player);
        enemies[3].hit(player);
        System.out.println("============================================================\n");

        //playerArrived event
        System.out.println("============================================================");
        player.arrive();
        System.out.println("============================================================\n");
    }
}
