package banking.database.dao;

import banking.database.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorDao extends AbstractDao {

    public boolean insertCustomer(Customer customer) throws SQLException {
        String sql = "insert into CUSTOMERS(\"CUSTOMER_NUM\", \"name\", \"address\", \"phone\") values (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, customer.getNum());
        ps.setString(2, customer.getName());
        ps.setString(3, customer.getAddress());
        ps.setString(4, customer.getPhone());

        return ps.executeUpdate() == 1;
    }

    public boolean deleteCustomer(Customer customer) throws SQLException {
        String sql = "delete from CUSTOMERS where \"CUSTOMER_NUM\" = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, customer.getNum());

        return ps.executeUpdate() == 1;
    }

    public boolean insertBranch(Branch branch) throws SQLException {
        String sql = "insert into BRANCHES(\"BRANCH_NUM\", \"branch_name\", \"address\") values (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, branch.getNum());
        ps.setString(2, branch.getName());
        ps.setString(3, branch.getAddress());

        return ps.executeUpdate() == 1;
    }

    public Branch findBranchByName(String name) throws SQLException {
        String sql = "select \"BRANCH_NUM\", \"branch_name\", \"address\" from BRANCHES where \"branch_name\" = ? limit 1";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);

        ResultSet resultSet = ps.executeQuery();
        Branch branch = null;
        while (resultSet.next()) {
            branch = new Branch();
            branch.setNum(resultSet.getString(1));
            branch.setName(resultSet.getString(2));
            branch.setAddress(resultSet.getString(3));
        }
        return branch;
    }

    public Customer findCustomerByNum(String num) throws SQLException {
        String sql = "select \"CUSTOMER_NUM\", \"name\", \"address\", \"phone\" from CUSTOMERS where \"CUSTOMER_NUM\" = ? limit 1";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, num);

        ResultSet resultSet = ps.executeQuery();
        Customer customer = null;
        while (resultSet.next()) {
            customer = new Customer();
            customer.setNum(resultSet.getString(1));
            customer.setName(resultSet.getString(2));
            customer.setAddress(resultSet.getString(3));
            customer.setPhone(resultSet.getString(4));
        }
        return customer;
    }

    public boolean insertCheckingAccount(CheckingAccount account) throws SQLException {
        String sql = "insert into CHECKING_ACCOUNTS(\"ACCOUNT_NUM\", \"branch_num\", \"date_opened\", \"balance\", \"overdraft_amount\", \"check_limit\") values (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getAccountNum());
        ps.setString(2, account.getBranchNum());
        ps.setDate(3, account.getDateOpened());
        ps.setDouble(4, account.getBalance());
        ps.setDouble(5, account.getOverdraftAmount());
        ps.setDouble(6, account.getCheckLimit());

        return ps.executeUpdate() == 1;
    }

    public boolean insertSavingsAccount(SavingsAccount account) throws SQLException {
        String sql = "insert into SAVINGS_ACCOUNTS(\"ACCOUNT_NUM\", \"branch_num\", \"date_opened\", \"balance\", \"interest_rate\") values (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getAccountNum());
        ps.setString(2, account.getBranchNum());
        ps.setDate(3, account.getDateOpened());
        ps.setDouble(4, account.getBalance());
        ps.setDouble(5, account.getInterestRate());

        return ps.executeUpdate() == 1;
    }

    public boolean insertLoanAccount(LoanAccount account) throws SQLException {
        String sql = "insert into LOAN_ACCOUNTS(\"ACCOUNT_NUM\", \"branch_num\", \"date_opened\", \"loan_type\", \"interest_rate\") values (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getAccountNum());
        ps.setString(2, account.getBranchNum());
        ps.setDate(3, account.getDateOpened());
        ps.setInt(4, account.getLoanType().ordinal());
        ps.setDouble(5, account.getInterestRate());

        return ps.executeUpdate() == 1;
    }

    public boolean insertHasAccount(HasAccount hasAccount) throws SQLException {
        String sql = "insert into HAS_ACCOUNT(\"CUSTOMER_NUM\", \"ACCOUNT_TYPE\", \"ACCOUNT_NUM\") values (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, hasAccount.getCustomer().getNum());
        ps.setInt(2, hasAccount.getAccountType().ordinal());
        ps.setString(3, hasAccount.getAccount().getAccountNum());

        return ps.executeUpdate() == 1;
    }

    public boolean deleteHasAccount(HasAccount hasAccount) throws SQLException {
        String sql = "delete from HAS_ACCOUNT where \"ACCOUNT_NUM\" = ? and \"ACCOUNT_TYPE\" = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, hasAccount.getAccount().getAccountNum());
        ps.setInt(2, hasAccount.getAccountType().ordinal());

        return ps.executeUpdate() >= 1;
    }

    public boolean deleteCheckingAccount(CheckingAccount account) throws SQLException {
        String sql = "delete from CHECKING_ACCOUNTS where \"ACCOUNT_NUM\" = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getAccountNum());

        return ps.executeUpdate() == 1;
    }

    public boolean deleteSavingsAccount(SavingsAccount account) throws SQLException {
        String sql = "delete from SAVINGS_ACCOUNTS where \"ACCOUNT_NUM\" = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getAccountNum());

        return ps.executeUpdate() == 1;
    }

    public boolean deleteLoanAccount(LoanAccount account) throws SQLException {
        String sql = "delete from LOAN_ACCOUNTS where \"ACCOUNT_NUM\" = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getAccountNum());

        return ps.executeUpdate() == 1;
    }

    public CheckingAccount findCheckingAccountByNum(String accountNum) throws SQLException {
        String sql = "select \"ACCOUNT_NUM\", \"branch_num\", \"date_opened\", \"balance\", \"overdraft_amount\", \"check_limit\" from CHECKING_ACCOUNTS where \"ACCOUNT_NUM\" = ? limit 1";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, accountNum);

        ResultSet resultSet = ps.executeQuery();
        CheckingAccount account = null;
        while (resultSet.next()) {
            account = new CheckingAccount();
            account.setAccountNum(resultSet.getString(1));
            account.setBranchNum(resultSet.getString(2));
            account.setDateOpened(resultSet.getDate(3));
            account.setBalance(resultSet.getDouble(4));
            account.setOverdraftAmount(resultSet.getDouble(5));
            account.setCheckLimit(resultSet.getDouble(6));
        }

        return account;
    }

    public boolean updateCheckingAccountByNum(CheckingAccount account) throws SQLException {
        String sql = "update CHECKING_ACCOUNTS set \"branch_num\" = ?, \"date_opened\" = ?, \"balance\" = ?, \"overdraft_amount\" = ?, \"check_limit\" = ? where \"ACCOUNT_NUM\" = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getBranchNum());
        ps.setDate(2, account.getDateOpened());
        ps.setDouble(3, account.getBalance());
        ps.setDouble(4, account.getOverdraftAmount());
        ps.setDouble(5, account.getCheckLimit());
        ps.setString(6, account.getAccountNum());

        return ps.executeLargeUpdate() == 1;
    }
}
