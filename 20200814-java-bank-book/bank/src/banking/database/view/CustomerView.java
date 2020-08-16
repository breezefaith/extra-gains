package banking.database.view;

import banking.database.dao.CustomerDao;
import banking.database.entity.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerView extends AbstractView {
    protected CustomerDao customerDao = new CustomerDao();

    public CustomerView() {
    }

    public CustomerView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void launch() {
        while (true) {
            showMenu();
            int choice = scanner.nextByte();
            if (choice == 1) {
                deposit();
            } else if (choice == 2) {
                withdraw();
            } else if (choice == 3) {
                transfer();
            } else if (choice == 4) {
                loanPayment();
            } else if (choice == 5) {
                return;
            } else {
                System.out.println("Invalid choice! Please reenter");
            }
        }
    }

    private void deposit() {
        try {
            customerDao.setAutoTransaction(false);
            System.out.println("Savings Account Num:");
            String accountNum = scanner.nextLine();
            SavingsAccount account = customerDao.findSavingsAccountByNum(accountNum);

            if (account == null) {
                System.out.println("The account doesn't exist.");
                customerDao.rollback();
                return;
            }

            System.out.println("Amount:");
            Double amount = scanner.nextDouble();
            scanner.next();

            account.setBalance(account.getBalance() + amount);

            if (customerDao.updateSavingsAccountByNum(account)) {
                Transaction transaction = new Transaction();
                transaction.setAccount(account);
                transaction.setAccountType(AccountType.Savings);
                transaction.setTransDate(new Date(System.currentTimeMillis()));
                transaction.setTransAmt(amount);
                transaction.setTransType(TransType.Deposit);

                if (customerDao.insertTransaction(transaction)) {
                    customerDao.commit();
                    System.out.println("Deposited successfully.");
                } else {
                    customerDao.rollback();
                    System.out.println("Failed to deposit.");
                }
            } else {
                System.out.println("Failed to deposit.");
                customerDao.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                customerDao.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                customerDao.setAutoTransaction(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void withdraw() {
        try {
            customerDao.setAutoTransaction(false);
            System.out.println("Savings Account Num:");
            String accountNum = scanner.nextLine();
            SavingsAccount account = customerDao.findSavingsAccountByNum(accountNum);

            if (account == null) {
                System.out.println("The account doesn't exist.");
                customerDao.rollback();
                return;
            }

            System.out.println("Amount:");
            Double amount = scanner.nextDouble();
            scanner.next();

            Double diff = account.getBalance() - amount;
            if (diff < 0) {
                System.out.println("Lack of balance.");
                customerDao.rollback();
                return;
            }
            account.setBalance(diff);

            if (customerDao.updateSavingsAccountByNum(account)) {
                Transaction transaction = new Transaction();
                transaction.setAccount(account);
                transaction.setAccountType(AccountType.Savings);
                transaction.setTransDate(new Date(System.currentTimeMillis()));
                transaction.setTransAmt(amount);
                transaction.setTransType(TransType.Withdraw);

                if (customerDao.insertTransaction(transaction)) {
                    customerDao.commit();
                    System.out.println("Withdrawn successfully.");
                } else {
                    customerDao.rollback();
                    System.out.println("Failed to withdraw.");
                }
            } else {
                System.out.println("Failed to withdraw.");
                customerDao.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                customerDao.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                customerDao.setAutoTransaction(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void transfer() {
        try {
            customerDao.setAutoTransaction(false);
            System.out.println("Savings Account Num:");
            String accountNum = scanner.nextLine();
            SavingsAccount sourceAccount = customerDao.findSavingsAccountByNum(accountNum);

            if (sourceAccount == null) {
                System.out.println("The account doesn't exist.");
                customerDao.rollback();
                return;
            }

            System.out.println("Target Savings Account Num:");
            String targetAccountNum = scanner.nextLine();
            SavingsAccount targetAccount = customerDao.findSavingsAccountByNum(targetAccountNum);

            if (targetAccount == null) {
                System.out.println("The target account doesn't exist.");
                customerDao.rollback();
                return;
            }

            System.out.println("Amount:");
            Double amount = scanner.nextDouble();
            scanner.next();

            Double diff = sourceAccount.getBalance() - amount;
            if (diff < 0) {
                System.out.println("Lack of balance.");
                customerDao.rollback();
                return;
            }
            sourceAccount.setBalance(diff);

            targetAccount.setBalance(targetAccount.getBalance() + amount);

            if (!(customerDao.updateSavingsAccountByNum(sourceAccount) && customerDao.updateSavingsAccountByNum(targetAccount))) {
                System.out.println("Failed to transfer.");
                customerDao.rollback();
                return;
            }

            Transaction sourceTrans = new Transaction();
            sourceTrans.setAccount(sourceAccount);
            sourceTrans.setAccountType(AccountType.Savings);
            sourceTrans.setTransAmt(amount);
            sourceTrans.setTransDate(new Date(System.currentTimeMillis()));
            sourceTrans.setTransType(TransType.TurnOut);
            sourceTrans.setTransComments(String.format("%s -> %s", sourceAccount.getAccountNum(), targetAccount.getAccountNum()));

            Transaction targetTrans = new Transaction();
            targetTrans.setAccount(targetAccount);
            targetTrans.setAccountType(AccountType.Savings);
            targetTrans.setTransAmt(amount);
            targetTrans.setTransDate(new Date(System.currentTimeMillis()));
            targetTrans.setTransType(TransType.TurnIn);
            targetTrans.setTransComments(String.format("%s <- %s", targetAccount.getAccountNum(), sourceAccount.getAccountNum()));

            if (customerDao.insertTransaction(sourceTrans) && customerDao.insertTransaction(targetTrans)) {
                customerDao.commit();
                System.out.println("Transferred successfully.");
            } else {
                System.out.println("Failed to transfer.");
                customerDao.rollback();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                customerDao.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                customerDao.setAutoTransaction(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loanPayment() {
        try {
            customerDao.setAutoTransaction(false);

            System.out.println("Loan Account Num:");
            String accountNum = scanner.nextLine();
            LoanAccount account = customerDao.findLoanAccountByNum(accountNum);

            if (account == null) {
                System.out.println("The account doesn't exist.");
                customerDao.rollback();
                return;
            }

            System.out.println("Amount:");
            Double amount = scanner.nextDouble();
            scanner.next();

            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setAccountType(AccountType.Loan);
            transaction.setTransDate(new Date(System.currentTimeMillis()));
            transaction.setTransAmt(amount);
            transaction.setTransType(TransType.Loan);

            if (customerDao.insertTransaction(transaction)) {
                customerDao.commit();
                System.out.println("Loan payment succeeded.");
            } else {
                customerDao.rollback();
                System.out.println("Failed to loan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                customerDao.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                customerDao.setAutoTransaction(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void showMenu() {
        System.out.println("      CUSTOMER FUNCTIONS MENU");
        System.out.println("(1) Deposit");
        System.out.println("(2) Withdraw");
        System.out.println("(3) Transfer");
        System.out.println("(4) Loan payment");
        System.out.println("(5) Quit");
    }
}
