package t1;

public class Zoo {
    public static void main(String[] args) {
        Animal a1 = new Animal();
        System.out.println(a1.getName());
        System.out.println(a1.getLegs());
        a1.move();
        a1.move(3);
        a1.setName("new");
        a1.setLegs(8);
        System.out.println(a1.getName());
        System.out.println(a1.getLegs());
        System.out.println();

        Animal a2 = new Animal("snake", 0);
        System.out.println(a2.getName());
        System.out.println(a2.getLegs());
        a2.move();
        a2.move(2);
        a2.setName("new");
        a2.setLegs(4);
        System.out.println(a2.getName());
        System.out.println(a2.getLegs());
        System.out.println();

        Fish f1 = new Fish("fish");
        System.out.println(f1.getName());
        System.out.println(f1.getLegs());
        f1.move();
        f1.move(2);
        f1.setName("new");
        f1.setLegs(0);
        System.out.println(f1.getName());
        System.out.println(f1.getLegs());
        System.out.println();

        Bird b1 = new Bird("bird");
        System.out.println(b1.getName());
        System.out.println(b1.getLegs());
        b1.move();
        b1.move(2);
        b1.setName("new");
        b1.setLegs(1);
        System.out.println(b1.getName());
        System.out.println(b1.getLegs());
        System.out.println();
    }
}
