
public class Course implements Comparable<Course> {
    private String courseCode;
    private String courseTitle;
    private int numStudents;
    private String[] professorList;

    public Course(String courseCode, String courseTitle, int numStudents, String[] professorList) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.numStudents = numStudents;
        this.professorList = professorList;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public String[] getProfessors() {
        return professorList;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    public void setProfessors(String[] professorList) {
        this.professorList = professorList;
    }

    public int compareTo(Course other) {
        if (other.getCourseCode().compareTo(this.getCourseCode()) > 0) {
            return -1;
        } else if (other.getCourseCode().compareTo(this.getCourseCode()) < 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        String s = "";
        s = s + "Course Code:   " + courseCode;
        s = s + "\nCourse Title: " + courseTitle;
        s = s + "\nNum Students:    " + numStudents;
        s = s + "\nProfessor List: " + professorList;
        return s;
    }

}
