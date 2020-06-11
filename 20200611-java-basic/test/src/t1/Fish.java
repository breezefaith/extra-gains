package t1;

public class Fish extends Animal {
    public Fish(String name) {
        setName(name);
        setLegs(0);
    }

    @Override
    public void move() {
        System.out.println(this.getName() + " Swimming!!");
    }
}
