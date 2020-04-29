package competition.management.system.entity;

public class HosterAdmin extends AbstractAdmin{
    protected String password;

    public HosterAdmin() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
