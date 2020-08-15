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

    public AdministratorView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void launch() {
        while (true) {
            showMenu();
            int choice = scanner.nextByte();
            scanner.next();
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

    }

    private void addOrDropCustomer() {
        System.out.println("Please choice: (1) Add (2) Drop");
        int c = scanner.nextByte();
        scanner.next();
        if (c == 1) {
            addCustomer();
        } else if (c == 2) {
            dropCustomer();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void addCustomer() {
        try {
            Customer customer = new Customer();
            customer.setNum(RandomUtil.get11RandomNumber());
            System.out.println("Please enter the name:");
            customer.setName(scanner.nextLine());
            System.out.println("Please enter the address:");
            customer.setAddress(scanner.nextLine());
            System.out.println("Please enter the phone:");
            customer.setPhone(scanner.nextLine());

            if (administratorDao.insertCustomer(customer)) {
                System.out.println("Added customer successfully.");
            } else {
                System.out.println("Failed to add customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropCustomer() {
        try {
            Customer customer = new Customer();
            System.out.println("Please enter the customer's num:");
            customer.setNum(scanner.nextLine());
            boolean res = administratorDao.deleteCustomer(customer);
            if (res) {
                System.out.println("Dropped customer successfully.");
            } else {
                System.out.println("Failed to drop customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openOrCloseAccount() {
        System.out.println("Please choice: (1) Open (2) Close");
        int c = scanner.nextByte();
        scanner.next();
        if (c == 1) {
            openAccount();
        } else if (c == 2) {
            closeAccount();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void openAccount() {
        try {
            System.out.println("Please select account type: (1) Checking (2) Savings (3) Loan");
            int c = scanner.nextByte();
            scanner.next();

            System.out.println("Please enter the customer num:");
            Customer customer = administratorDao.findCustomerByNum(scanner.nextLine());
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
                scanner.next();

                if (administratorDao.insertCheckingAccount(account)) {
                    System.out.println("Inserted account successfully.");
                } else {
                    System.out.println("Failed to inserted account.");
                    return;
                }

                hasAccount = new HasAccount(customer, account, AccountType.Checking);

                if (administratorDao.insertHasAccount(hasAccount)) {
                    System.out.println("Inserted hasAccount successfully.");
                } else {
                    System.out.println("Failed to inserted hasAccount.");
                    return;
                }
            } else if (c == 2) {
                SavingsAccount account = new SavingsAccount();
                account.setAccountNum(RandomUtil.get11RandomNumber());
                account.setBranchNum(branch.getNum());
                account.setDateOpened(new Date(System.currentTimeMillis()));

                if (administratorDao.insertSavingsAccount(account)) {
                    System.out.println("Inserted account successfully.");
                } else {
                    System.out.println("Failed to inserted account.");
                    return;
                }

                hasAccount = new HasAccount(customer, account, AccountType.Savings);
                account.setAccountNum(RandomUtil.get11RandomNumber());

                if (administratorDao.insertHasAccount(hasAccount)) {
                    System.out.println("Inserted hasAccount successfully.");
                } else {
                    System.out.println("Failed to inserted hasAccount.");
                    return;
                }
            } else if (c == 3) {
                LoanAccount account = new LoanAccount();
                account.setBranchNum(branch.getNum());
                account.setDateOpened(new Date(System.currentTimeMillis()));


                if (administratorDao.insertLoanAccount(account)) {
                    System.out.println("Inserted account successfully.");
                } else {
                    System.out.println("Failed to inserted account.");
                    return;
                }

                hasAccount = new HasAccount(customer, account, AccountType.Loan);

                if (administratorDao.insertHasAccount(hasAccount)) {
                    System.out.println("Inserted hasAccount successfully.");
                } else {
                    System.out.println("Failed to inserted hasAccount.");
                    return;
                }
            } else {
                System.out.println("Invalid choice!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void closeAccount() {
        try {
            System.out.println("Please select account type: (1) Checking (2) Savings (3) Loan");
            int c = scanner.nextByte();
            scanner.next();
            if (c == 1) {
                CheckingAccount account = new CheckingAccount();
                System.out.println("Please enter the account num:");
                account.setAccountNum(scanner.nextLine());

                HasAccount hasAccount = new HasAccount();
                hasAccount.setAccount(account);
                hasAccount.setAccountType(AccountType.Checking);

                if (administratorDao.deleteHasAccount(hasAccount)) {
                    System.out.println("Deleted hasAccount successfully.");
                } else {
                    System.out.println("Failed to delete hasAccount.");
                    return;
                }

                if (administratorDao.deleteCheckingAccount(account)) {
                    System.out.println("Deleted account successfully.");
                } else {
                    System.out.println("Failed to delete account.");
                    return;
                }
            } else if (c == 2) {
                SavingsAccount account = new SavingsAccount();
                System.out.println("Please enter the account num:");
                account.setAccountNum(scanner.nextLine());

                HasAccount hasAccount = new HasAccount();
                hasAccount.setAccount(account);
                hasAccount.setAccountType(AccountType.Checking);

                if (administratorDao.deleteHasAccount(hasAccount)) {
                    System.out.println("Deleted hasAccount successfully.");
                } else {
                    System.out.println("Failed to delete hasAccount.");
                    return;
                }

                if (administratorDao.deleteSavingsAccount(account)) {
                    System.out.println("Deleted account successfully.");
                } else {
                    System.out.println("Failed to delete account.");
                    return;
                }
            } else if (c == 3) {
                LoanAccount account = new LoanAccount();
                System.out.println("Please enter the account num:");
                account.setAccountNum(scanner.nextLine());

                HasAccount hasAccount = new HasAccount();
                hasAccount.setAccount(account);
                hasAccount.setAccountType(AccountType.Checking);

                if (administratorDao.deleteHasAccount(hasAccount)) {
                    System.out.println("Deleted hasAccount successfully.");
                } else {
                    System.out.println("Failed to delete hasAccount.");
                    return;
                }

                if (administratorDao.deleteLoanAccount(account)) {
                    System.out.println("Deleted account successfully.");
                } else {
                    System.out.println("Failed to delete account.");
                    return;
                }
            } else {
                System.out.println("Invalid choice!");
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMenu() {
        System.out.println("      ADMINISTRATIVE FUNCTIONS MENU");
        System.out.println("(1) Process checks (assume a file containing checks received by the bank)");
        System.out.println("(2) Add/drop customer");
        System.out.println("(3) Open/close account");
        System.out.println("(4) Quit");
    }
}
