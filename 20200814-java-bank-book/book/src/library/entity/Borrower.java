package library.entity;

public class Borrower {
    private String cardNo;
    private String name;
    private String address;
    private String phone;
    private Double unpaidDues;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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

    public Double getUnpaidDues() {
        return unpaidDues;
    }

    public void setUnpaidDues(Double unpaidDues) {
        this.unpaidDues = unpaidDues;
    }
}
