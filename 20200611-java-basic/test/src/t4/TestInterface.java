package t4;

public class TestInterface {
    public static void main(String[] args) {
        Student s1 = new Student("s1", new HuaweiPhone());
        Student s2 = new Student("s2", new ApplePhone());

        s1.mycall();
        s2.mycall();
    }
}
