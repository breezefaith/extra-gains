package t4;

public class Student {
    private String name;
    private CameraPhone myPhone;

    public Student(String name, CameraPhone myPhone) {
        this.name = name;
        this.myPhone = myPhone;
    }

    public void mycall() {
        myPhone.call();
    }
}
