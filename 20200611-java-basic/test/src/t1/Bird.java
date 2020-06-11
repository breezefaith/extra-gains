package t1;

public class Bird extends Animal {
    public Bird(String name) {
        setName(name);
        setLegs(2);
    }

    @Override
    public void move() {
        System.out.println(this.getName() + " Flying!!");
    }
}
