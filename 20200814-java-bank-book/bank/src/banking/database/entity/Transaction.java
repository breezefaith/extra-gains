package banking.database.entity;

import java.sql.Date;

public class Transaction {
    protected AccountType accountType;
    protected AbstractAccount account;
    protected Date transDate;
    protected Double transAmt;
    protected TransType transType;
    protected String transComments;

    public Transaction() {
    }

    public Transaction(AccountType accountType, AbstractAccount account, Date transDate, Double transAmt, TransType transType, String transComments) {
        this.accountType = accountType;
        this.account = account;
        this.transDate = transDate;
        this.transAmt = transAmt;
        this.transType = transType;
        this.transComments = transComments;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AbstractAccount getAccount() {
        return account;
    }

    public void setAccount(AbstractAccount account) {
        this.account = account;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public Double getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(Double transAmt) {
        this.transAmt = transAmt;
    }

    public TransType getTransType() {
        return transType;
    }

    public void setTransType(TransType transType) {
        this.transType = transType;
    }

    public String getTransComments() {
        return transComments;
    }

    public void setTransComments(String transComments) {
        this.transComments = transComments;
    }
}
