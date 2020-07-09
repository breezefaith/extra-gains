package t3;

public class TestMonster {
    public static void main(String[] args) {
        Monster fm = new FireMonster();
        Monster wm = new WaterMonster();
        Monster sm = new StoneMonster();

        System.out.println(fm.attack());
        System.out.println(wm.attack());
        System.out.println(sm.attack());
    }
}
