package banking.database.entity;

public class Customer {
    protected Integer num;
    protected String name;
    protected String address;
    protected String phone;

    public Customer() {
    }

    public Customer(Integer num, String name, String address, String phone) {
        this.num = num;
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
