package t1;

abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

interface Sing {
    void sing();
}

class Chinese extends Person implements Sing {
    private int age;

    public Chinese(String name, int age) {
        super(name);
        this.age = age;
    }

    @Override
    public void sing() {
        System.out.println(getName() + " is singing.");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Chinese{" +
                "name=" + getName() + ", " +
                "age=" + age +
                '}';
    }
}

public class T1 {
    public static void main(String[] args) {
        Chinese chinese = new Chinese("张三", 20);
        chinese.sing();
        System.out.println(chinese);

        chinese.setName("李四");
        chinese.setAge(50);
        chinese.sing();
        System.out.println(chinese);
    }
}
