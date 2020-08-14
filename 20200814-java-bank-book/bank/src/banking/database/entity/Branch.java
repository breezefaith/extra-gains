package banking.database.entity;

public class Branch {
    protected Integer num;
    protected String name;
    protected String address;

    public Branch() {
    }

    public Branch(Integer num, String name, String address) {
        this.num = num;
        this.name = name;
        this.address = address;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
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
