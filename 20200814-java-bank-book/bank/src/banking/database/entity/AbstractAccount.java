package banking.database.entity;

import java.sql.Date;

public abstract class AbstractAccount {
    protected String accountNum;
    protected String branchNum;
    protected Date dateOpened;

    public AbstractAccount() {
    }

    public AbstractAccount(String accountNum, String branchNum, Date dateOpened) {
        this.accountNum = accountNum;
        this.branchNum = branchNum;
        this.dateOpened = dateOpened;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(String branchNum) {
        this.branchNum = branchNum;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }
}
