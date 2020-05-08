package scenario.entity;

public class Grade {
    protected String gid;
    protected String sid;
    protected String course;
    protected String term;
    protected int mark;

    public Grade() {
    }

    public Grade(String gid, String sid, String course, String term, int mark) {

        this.gid = gid;
        this.sid = sid;
        this.course = course;
        this.term = term;
        this.mark = mark;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gid=" + gid +
                ", sid=" + sid +
                ", course='" + course + '\'' +
                ", term='" + term + '\'' +
                ", mark=" + mark +
                '}';
    }
}
