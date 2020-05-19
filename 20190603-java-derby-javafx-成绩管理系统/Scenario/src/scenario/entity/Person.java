package scenario.entity;

public class Person {
    protected String id;
    protected String name;
    protected String sex;
    protected int age;
    protected Role role;
    protected String location;

    public Person() {
    }

    public Person(String id, String name, String sex, int age, Role role, String location) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.role = role;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", role=" + role +
                ", location='" + location + '\'' +
                '}';
    }
}
