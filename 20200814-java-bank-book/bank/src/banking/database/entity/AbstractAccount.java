package banking.database.entity;

import java.sql.Date;

public abstract class AbstractAccount {
    protected Integer accountNum;
    protected Integer branchNum;
    protected Date dateOpened;

    public AbstractAccount() {
    }

    public AbstractAccount(Integer accountNum, Integer branchNum, Date dateOpened) {
        this.accountNum = accountNum;
        this.branchNum = branchNum;
        this.dateOpened = dateOpened;
    }

    public Integer getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(Integer accountNum) {
        this.accountNum = accountNum;
    }

    public Integer getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(Integer branchNum) {
        this.branchNum = branchNum;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }
}
