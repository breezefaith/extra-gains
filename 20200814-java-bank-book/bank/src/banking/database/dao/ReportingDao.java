package banking.database.dao;

import banking.database.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportingDao extends AbstractDao {
    public Map<String, List<Transaction>> getMonthlyStatement() throws SQLException {
        Map<String, List<Transaction>> map = new HashMap<>();

        String sql = "select \"ACCOUNT_TYPE\", \"ACCOUNT_NUM\", \"TRANS_DATE\", \"TRANS_AMT\", \"TRANS_TYPE\", \"trans_comments\" from TRANSACTIONS order by \"TRANS_DATE\" desc ";
        PreparedStatement ps = connection.prepareStatement(sql);

        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Transaction transaction = new Transaction();
            transaction.setTransType(TransType.values()[resultSet.getInt(1)]);
            transaction.setTransDate(resultSet.getDate(3));
            transaction.setTransAmt(resultSet.getDouble(4));
            transaction.setAccountType(AccountType.values()[resultSet.getInt(5)]);
            transaction.setTransComments(resultSet.getString(6));

            if (transaction.getAccountType().equals(AccountType.Checking)) {
                transaction.setAccount(findCheckingAccountByNum(resultSet.getString(2)));
            } else if (transaction.getAccountType().equals(AccountType.Loan)) {
                transaction.setAccount(findLoanAccountByNum(resultSet.getString(2)));
            } else if (transaction.getAccountType().equals(AccountType.Savings)) {
                transaction.setAccount(findSavingsAccountByNum(resultSet.getString(2)));
            }

            String key = String.format("%d-%d", transaction.getTransDate().getYear(), transaction.getTransDate().getMonth());
            List<Transaction> transactions = map.get(key);
            if (transactions == null) {
                transactions = new ArrayList<>();
                map.put(key, transactions);
            }
            transactions.add(transaction);
        }

        return map;
    }

    public List<Transaction> getLoanPayments() throws SQLException {
        List<Transaction> list = new ArrayList<>();

        String sql = "select \"ACCOUNT_TYPE\", \"ACCOUNT_NUM\", \"TRANS_DATE\", \"TRANS_AMT\", \"TRANS_TYPE\", \"trans_comments\" from TRANSACTIONS where \"TRANS_TYPE\"=? order by \"TRANS_DATE\" desc ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, TransType.Loan.ordinal());

        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Transaction transaction = new Transaction();
            transaction.setTransType(TransType.values()[resultSet.getInt(1)]);
            transaction.setTransDate(resultSet.getDate(3));
            transaction.setTransAmt(resultSet.getDouble(4));
            transaction.setAccountType(AccountType.values()[resultSet.getInt(5)]);
            transaction.setTransComments(resultSet.getString(6));

            if (transaction.getAccountType().equals(AccountType.Checking)) {
                transaction.setAccount(findCheckingAccountByNum(resultSet.getString(2)));
            } else if (transaction.getAccountType().equals(AccountType.Loan)) {
                transaction.setAccount(findLoanAccountByNum(resultSet.getString(2)));
            } else if (transaction.getAccountType().equals(AccountType.Savings)) {
                transaction.setAccount(findSavingsAccountByNum(resultSet.getString(2)));
            }

            list.add(transaction);
        }

        return list;
    }

    public Map<String, List<Transaction>> getYearlyTaxStatement() throws SQLException {
        Map<String, List<Transaction>> map = new HashMap<>();

        String sql = "select \"ACCOUNT_TYPE\", \"ACCOUNT_NUM\", \"TRANS_DATE\", \"TRANS_AMT\", \"TRANS_TYPE\", \"trans_comments\" from TRANSACTIONS where \"ACCOUNT_TYPE\" = ? or \"ACCOUNT_TYPE\" = ? order by \"TRANS_DATE\" desc ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, AccountType.Savings.ordinal());
        ps.setInt(2, AccountType.Loan.ordinal());

        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Transaction transaction = new Transaction();
            transaction.setTransType(TransType.values()[resultSet.getInt(1)]);
            transaction.setTransDate(resultSet.getDate(3));
            transaction.setTransAmt(resultSet.getDouble(4));
            transaction.setAccountType(AccountType.values()[resultSet.getInt(5)]);
            transaction.setTransComments(resultSet.getString(6));

            if (transaction.getAccountType().equals(AccountType.Checking)) {
                transaction.setAccount(findCheckingAccountByNum(resultSet.getString(2)));
            } else if (transaction.getAccountType().equals(AccountType.Loan)) {
                transaction.setAccount(findLoanAccountByNum(resultSet.getString(2)));
            } else if (transaction.getAccountType().equals(AccountType.Savings)) {
                transaction.setAccount(findSavingsAccountByNum(resultSet.getString(2)));
            }

            String key = String.format("%d", transaction.getTransDate().getYear());
            List<Transaction> transactions = map.get(key);
            if (transactions == null) {
                transactions = new ArrayList<>();
                map.put(key, transactions);
            }
            transactions.add(transaction);
        }

        return map;
    }

    private CheckingAccount findCheckingAccountByNum(String accountNum) throws SQLException {
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

    private LoanAccount findLoanAccountByNum(String accountNum) throws SQLException {
        String sql = "select \"ACCOUNT_NUM\", \"branch_num\", \"date_opened\", \"loan_type\", \"interest_rate\" from LOAN_ACCOUNTS where \"ACCOUNT_NUM\" = ? limit 1";
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

    private SavingsAccount findSavingsAccountByNum(String accountNum) throws SQLException {
        String sql = "select \"ACCOUNT_NUM\", \"branch_num\", \"date_opened\", \"balance\", \"interest_rate\" from SAVINGS_ACCOUNTS where \"ACCOUNT_NUM\" = ? limit 1";
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
}
