package banking.database.entity;

import java.sql.Date;

public class CheckingAccount extends AbstractAccount {
    protected Double balance;
    protected Double overdraftAmount;
    protected Double checkLimit;

    public CheckingAccount() {
        super();
    }

    public CheckingAccount(String accountNum, String branchNum, Date dateOpened, Double balance, Double overdraftAmount, Double checkLimit) {
        super(accountNum, branchNum, dateOpened);
        this.balance = balance;
        this.overdraftAmount = overdraftAmount;
        this.checkLimit = checkLimit;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getOverdraftAmount() {
        return overdraftAmount;
    }

    public void setOverdraftAmount(Double overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }

    public Double getCheckLimit() {
        return checkLimit;
    }

    public void setCheckLimit(Double checkLimit) {
        this.checkLimit = checkLimit;
    }
}
