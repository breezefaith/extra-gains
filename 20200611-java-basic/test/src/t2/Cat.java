package t2;

public class Cat {
    private int id;
    private String name;
    private static int sid = 0;

    public Cat(String name) {
        this.name = name;
        this.id = ++sid;
    }

    public String info() {
        return String.format("My name is %s, No. %d", name, id);
    }

    public static void main(String[] args) {
        Cat c1 = new Cat("c1");
        Cat c2 = new Cat("c2");

        System.out.println(c1.info());
        System.out.println(c2.info());
    }
}
