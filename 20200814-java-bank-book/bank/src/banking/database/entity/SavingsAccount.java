package banking.database.entity;

import java.sql.Date;

public class SavingsAccount extends AbstractAccount {
    protected Double balance;
    protected Double interestRate;

    public SavingsAccount() {
    }

    public SavingsAccount(Integer accountNum, Integer branchNum, Date dateOpened, Double balance, Double interestRate) {
        super(accountNum, branchNum, dateOpened);
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
}
