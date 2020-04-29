package competition.management.system.entity;

public class SchoolAdmin extends AbstractAdmin{
    protected String password;

    public SchoolAdmin() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
