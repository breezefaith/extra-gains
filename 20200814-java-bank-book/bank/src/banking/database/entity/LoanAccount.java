package banking.database.entity;

import java.sql.Date;

public class LoanAccount extends AbstractAccount {
    protected LoanType loanType;
    protected Double interestRate;

    public LoanAccount() {
    }

    public LoanAccount(Integer accountNum, Integer branchNum, Date dateOpened, LoanType loanType, Double interestRate) {
        super(accountNum, branchNum, dateOpened);
        this.loanType = loanType;
        this.interestRate = interestRate;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
}
