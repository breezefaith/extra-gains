package banking.database.view;

import banking.database.dao.AdministratorDao;
import banking.database.entity.*;
import banking.database.util.RandomUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class AdministratorView extends AbstractView {
    protected AdministratorDao administratorDao = new AdministratorDao();

    public AdministratorView() {
    }

    @Override
    public void launch() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMenu();
            int choice = scanner.nextByte();
            if (choice == 1) {
                processCheck();
            } else if (choice == 2) {
                addOrDropCustomer();
            } else if (choice == 3) {
                openOrCloseAccount();
            } else if (choice == 4) {
                return;
            } else {
                System.out.println("Invalid choice! Please reenter");
            }
        }
    }

    private void processCheck() {
        Scanner scanner = new Scanner(System.in);
        try {
            administratorDao.setAutoTransaction(false);
            System.out.println("Checking Account Num:");
            CheckingAccount account = administratorDao.findCheckingAccountByNum(scanner.nextLine());

            if (account == null) {
                System.out.println("The account doesn't exist.");
                return;
            }

            System.out.println("Amount:");
            Double amount = scanner.nextDouble();
            Double diff = account.getBalance() - amount;
            if (diff < 0) {
                account.setBalance(0d);
                if (account.getOverdraftAmount() - diff > account.getCheckLimit()) {
                    System.out.println("Over the check limit");
                    return;
                }
                account.setOverdraftAmount(account.getOverdraftAmount() - diff);
            } else {
                account.setBalance(diff);
            }

            if (administratorDao.updateCheckingAccountByNum(account)) {
                Transaction transaction = new Transaction();
                transaction.setAccount(account);
                transaction.setAccountType(AccountType.Checking);
                transaction.setTransDate(new Date(System.currentTimeMillis()));
                transaction.setTransAmt(amount);
                transaction.setTransType(TransType.Check);
                transaction.setTransComments("Cash a cheque");

                if (administratorDao.insertTransaction(transaction)) {
                    administratorDao.commit();
                    System.out.println("Processed check successfully.");
                } else {
                    administratorDao.rollback();
                    System.out.println("Failed to process check.");
                }
            } else {
                administratorDao.rollback();
                System.out.println("Failed to process check.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                administratorDao.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                administratorDao.setAutoTransaction(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addOrDropCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please choice: (1) Add (2) Drop");
        int c = scanner.nextByte();

        if (c == 1) {
            addCustomer();
        } else if (c == 2) {
            dropCustomer();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void addCustomer() {
        Scanner scanner = new Scanner(System.in);
        try {
            administratorDao.setAutoTransaction(false);

            Customer customer = new Customer();
            customer.setNum(RandomUtil.get11RandomNumber());
            System.out.println("Please enter the name:");
            customer.setName(scanner.nextLine());
            System.out.println("Please enter the address:");
            customer.setAddress(scanner.nextLine());
            System.out.println("Please enter the phone:");
            customer.setPhone(scanner.nextLine());

            if (administratorDao.insertCustomer(customer)) {
                administratorDao.commit();
                System.out.println("Added customer successfully.");
                System.out.println("Customer Number: " + customer.getNum());
            } else {
                System.out.println("Failed to add customer.");
                administratorDao.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                administratorDao.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                administratorDao.setAutoTransaction(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void dropCustomer() {
        Scanner scanner = new Scanner(System.in);
        try {
            administratorDao.setAutoTransaction(false);
            Customer customer = new Customer();
            System.out.println("Please enter the customer's num:");
            customer.setNum(scanner.nextLine());
            boolean res = administratorDao.deleteCustomer(customer);
            if (res) {
                administratorDao.commit();
                System.out.println("Dropped customer successfully.");
            } else {
                administratorDao.rollback();
                System.out.println("Failed to drop customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                administratorDao.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                administratorDao.setAutoTransaction(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void openOrCloseAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please choice: (1) Open (2) Close");
        int c = scanner.nextByte();
        if (c == 1) {
            openAccount();
        } else if (c == 2) {
            closeAccount();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void openAccount() {
        Scanner scanner = new Scanner(System.in);

        try {
            administratorDao.setAutoTransaction(false);
            System.out.println("Please select account type: (1) Checking (2) Savings (3) Loan");
            int c = scanner.nextByte();
            if (c < 1 || c > 3) {
                System.out.println("Invalid choice!");
                return;
            }

            System.out.println("Please enter the customer num:");
            scanner = new Scanner(System.in);
            String customerNum = scanner.nextLine();
            Customer customer = administratorDao.findCustomerByNum(customerNum);
            if (customer == null) {
                System.out.println("The customer doesn't exist.");
                return;
            }

            System.out.println("Please enter the branch name:");
            String branchName = scanner.nextLine();
            Branch branch = administratorDao.findBranchByName(branchName);
            if (branch == null) {
                System.out.println("There is no such a branch, so we'll add new one into database.");
                branch = new Branch();
                branch.setNum(RandomUtil.get11RandomNumber());
                branch.setName(branchName);
                System.out.println("Please enter the branch address:");
                branch.setAddress(scanner.nextLine());
                if (administratorDao.insertBranch(branch)) {
                    System.out.println("Inserted branch successfully.");
                } else {
                    administratorDao.rollback();
                    System.out.println("Failed to inserted branch.");
                    return;
                }
            }
            HasAccount hasAccount = null;

            if (c == 1) {
                CheckingAccount account = new CheckingAccount();
                account.setAccountNum(RandomUtil.get11RandomNumber());
                account.setBranchNum(branch.getNum());
                account.setDateOpened(new Date(System.currentTimeMillis()));

                System.out.println("Balance:");
                account.setBalance(scanner.nextDouble());
                System.out.println("Overdraft Amount:");
                account.setOverdraftAmount(scanner.nextDouble());
                System.out.println("Check limit:");
                account.setCheckLimit(scanner.nextDouble());
                if (administratorDao.insertCheckingAccount(account)) {
                    System.out.println("Inserted account successfully.");
                } else {
                    administratorDao.rollback();
                    System.out.println("Failed to inserted account.");
                    return;
                }

                hasAccount = new HasAccount(customer, account, AccountType.Checking);
            } else if (c == 2) {
                SavingsAccount account = new SavingsAccount();
                account.setAccountNum(RandomUtil.get11RandomNumber());
                account.setBranchNum(branch.getNum());
                account.setDateOpened(new Date(System.currentTimeMillis()));

                System.out.println("Balance:");
                account.setBalance(scanner.nextDouble());
                System.out.println("Interest Rate:");
                account.setInterestRate(scanner.nextDouble());

                if (administratorDao.insertSavingsAccount(account)) {
                    System.out.println("Inserted account successfully.");
                } else {
                    administratorDao.rollback();
                    System.out.println("Failed to inserted account.");
                    return;
                }

                hasAccount = new HasAccount(customer, account, AccountType.Savings);
            } else if (c == 3) {
                LoanAccount account = new LoanAccount();
                account.setAccountNum(RandomUtil.get11RandomNumber());
                account.setBranchNum(branch.getNum());
                account.setDateOpened(new Date(System.currentTimeMillis()));

                System.out.println("Loan Type: (1) Short term (2) Medium or long term");
                int type = scanner.nextByte();
                if (type < 1 || type > 2) {
                    administratorDao.rollback();
                    System.out.println("Unknown loan type");
                    return;
                }
                account.setLoanType(LoanType.values()[type - 1]);
                System.out.println("Interest Rate:");
                account.setInterestRate(scanner.nextDouble());


                if (administratorDao.insertLoanAccount(account)) {
                    System.out.println("Inserted account successfully.");
                } else {
                    administratorDao.rollback();
                    System.out.println("Failed to inserted account.");
                    return;
                }

                hasAccount = new HasAccount(customer, account, AccountType.Loan);
            } else {
                System.out.println("Invalid choice!");
            }

            if (hasAccount != null) {
                if (administratorDao.insertHasAccount(hasAccount)) {
                    administratorDao.commit();
                    System.out.println("Inserted hasAccount successfully.");
                    System.out.println("Account Number: " + hasAccount.getAccount().getAccountNum());
                } else {
                    administratorDao.rollback();
                    System.out.println("Failed to inserted hasAccount.");
                    return;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                administratorDao.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                administratorDao.setAutoTransaction(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void closeAccount() {

        try {
            administratorDao.setAutoTransaction(false);
            System.out.println("Please select account type: (1) Checking (2) Savings (3) Loan");
            Scanner scanner = new Scanner(System.in);
            int c = scanner.nextByte();

            if (c == 1) {
                CheckingAccount account = new CheckingAccount();
                System.out.println("Please enter the account num:");
                scanner = new Scanner(System.in);
                account.setAccountNum(scanner.nextLine());

                HasAccount hasAccount = new HasAccount();
                hasAccount.setAccount(account);
                hasAccount.setAccountType(AccountType.Checking);

                if (administratorDao.deleteHasAccount(hasAccount)) {
                    System.out.println("Deleted hasAccount successfully.");
                } else {
                    administratorDao.rollback();
                    System.out.println("Failed to delete hasAccount.");
                    return;
                }

                if (administratorDao.deleteCheckingAccount(account)) {
                    administratorDao.commit();
                    System.out.println("Deleted account successfully.");
                } else {
                    administratorDao.rollback();
                    System.out.println("Failed to delete account.");
                    return;
                }
            } else if (c == 2) {
                SavingsAccount account = new SavingsAccount();
                System.out.println("Please enter the account num:");
                scanner = new Scanner(System.in);
                account.setAccountNum(scanner.nextLine());

                HasAccount hasAccount = new HasAccount();
                hasAccount.setAccount(account);
                hasAccount.setAccountType(AccountType.Checking);

                if (administratorDao.deleteHasAccount(hasAccount)) {
                    System.out.println("Deleted hasAccount successfully.");
                } else {
                    System.out.println("Failed to delete hasAccount.");
                    administratorDao.rollback();
                    return;
                }

                if (administratorDao.deleteSavingsAccount(account)) {
                    administratorDao.commit();
                    System.out.println("Deleted account successfully.");
                } else {
                    System.out.println("Failed to delete account.");
                    administratorDao.rollback();
                    return;
                }
            } else if (c == 3) {
                LoanAccount account = new LoanAccount();
                System.out.println("Please enter the account num:");
                scanner = new Scanner(System.in);
                account.setAccountNum(scanner.nextLine());

                HasAccount hasAccount = new HasAccount();
                hasAccount.setAccount(account);
                hasAccount.setAccountType(AccountType.Checking);

                if (administratorDao.deleteHasAccount(hasAccount)) {
                    System.out.println("Deleted hasAccount successfully.");
                } else {
                    System.out.println("Failed to delete hasAccount.");
                    administratorDao.rollback();
                    return;
                }

                if (administratorDao.deleteLoanAccount(account)) {
                    administratorDao.commit();
                    System.out.println("Deleted account successfully.");
                } else {
                    System.out.println("Failed to delete account.");
                    administratorDao.rollback();
                    return;
                }
            } else {
                System.out.println("Invalid choice!");
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                administratorDao.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                administratorDao.setAutoTransaction(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void showMenu() {
        System.out.println("      ADMINISTRATIVE FUNCTIONS MENU");
        System.out.println("(1) Process checks");
        System.out.println("(2) Add/drop customer");
        System.out.println("(3) Open/close account");
        System.out.println("(4) Quit");
    }
}
