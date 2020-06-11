package t1;

public class Animal {
    private String name;
    private int legs;

    public Animal(String name, int legs) {
        this.name = name;
        this.legs = legs;
    }

    public Animal() {
        this.name = "AAA";
        this.legs = 4;
    }

    public void move() {
        System.out.println(this.name + " Moving!!");
    }

    public void move(int n) {
        for (int i = 0; i < n; i++) {
            move();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }
}
