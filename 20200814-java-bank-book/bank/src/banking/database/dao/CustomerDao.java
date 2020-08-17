package banking.database.dao;

import banking.database.entity.LoanAccount;
import banking.database.entity.LoanType;
import banking.database.entity.SavingsAccount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao extends AbstractDao {
    public SavingsAccount findSavingsAccountByNum(String accountNum) throws SQLException {
        String sql = "select \"ACCOUNT_NUM\", \"branch_num\", \"date_opened\", \"balance\", \"interest_rate\" from SAVINGS_ACCOUNTS where \"ACCOUNT_NUM\" = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, accountNum);

        ResultSet resultSet = ps.executeQuery();
        SavingsAccount account = null;
        while (resultSet.next()) {
            account = new SavingsAccount();
            account.setAccountNum(resultSet.getString(1));
            account.setBranchNum(resultSet.getString(2));
            account.setDateOpened(resultSet.getDate(3));
            account.setBalance(resultSet.getDouble(4));
            account.setInterestRate(resultSet.getDouble(5));
        }

        return account;
    }

    public boolean updateSavingsAccountByNum(SavingsAccount account) throws SQLException {
        String sql = "update SAVINGS_ACCOUNTS set \"branch_num\" = ?, \"date_opened\" = ?, \"balance\" = ?, \"interest_rate\" = ? where \"ACCOUNT_NUM\" = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getBranchNum());
        ps.setDate(2, account.getDateOpened());
        ps.setDouble(3, account.getBalance());
        ps.setDouble(4, account.getInterestRate());
        ps.setString(5, account.getAccountNum());

        return ps.executeLargeUpdate() == 1;
    }

    public LoanAccount findLoanAccountByNum(String accountNum) throws SQLException {
        String sql = "select \"ACCOUNT_NUM\", \"branch_num\", \"date_opened\", \"loan_type\", \"interest_rate\" from LOAN_ACCOUNTS where \"ACCOUNT_NUM\" = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, accountNum);

        ResultSet resultSet = ps.executeQuery();
        LoanAccount account = null;
        while (resultSet.next()) {
            account = new LoanAccount();
            account.setAccountNum(resultSet.getString(1));
            account.setBranchNum(resultSet.getString(2));
            account.setDateOpened(resultSet.getDate(3));
            account.setLoanType(LoanType.values()[resultSet.getInt(4)]);
            account.setInterestRate(resultSet.getDouble(5));
        }

        return account;
    }

    public boolean updateLoanAccountByNum(LoanAccount account) throws SQLException {
        String sql = "update LOAN_ACCOUNTS set \"branch_num\" = ?, \"date_opened\" = ?, \"loan_type\" = ?, \"interest_rate\" = ? where \"ACCOUNT_NUM\" = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getBranchNum());
        ps.setDate(2, account.getDateOpened());
        ps.setInt(3, account.getLoanType().ordinal());
        ps.setDouble(4, account.getInterestRate());
        ps.setString(5, account.getAccountNum());

        return ps.executeLargeUpdate() == 1;
    }
}
