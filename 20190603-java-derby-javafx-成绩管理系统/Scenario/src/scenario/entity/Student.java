package scenario.entity;

import java.util.List;

public class Student extends Person {
    protected List<Grade> grades;

    public Student() {
    }

    public Student(String id, String name, String sex, int age, Role role, String location, List<Grade> grades) {
        super(id, name, sex, age, role, location);
        this.grades = grades;
    }

    public Student(Person person) {
        super(person.getId(), person.getName(), person.getSex(), person.getAge(), person.getRole(), person.getLocation());
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Student{" +
                "grades=" + grades +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", role=" + role +
                ", location='" + location + '\'' +
                '}';
    }
}
