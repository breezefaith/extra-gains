package banking.database.entity;

public class HasAccount {
    protected Customer customer;
    protected AbstractAccount account;
    protected AccountType accountType;

    public HasAccount() {
    }

    public HasAccount(Customer customer, AbstractAccount account, AccountType accountType) {
        this.customer = customer;
        this.account = account;
        this.accountType = accountType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AbstractAccount getAccount() {
        return account;
    }

    public void setAccount(AbstractAccount account) {
        this.account = account;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
