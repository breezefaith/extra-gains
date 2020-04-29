package competition.management.system.entity;

public abstract class AbstractAdmin extends AbstractEntity {
    protected String school;

    public AbstractAdmin() {
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
