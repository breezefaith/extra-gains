package banking.database.entity;

public class Branch {
    protected String num;
    protected String name;
    protected String address;

    public Branch() {
    }

    public Branch(String num, String name, String address) {
        this.num = num;
        this.name = name;
        this.address = address;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
