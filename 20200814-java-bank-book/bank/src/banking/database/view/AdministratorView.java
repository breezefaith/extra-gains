package banking.database.view;

import banking.database.entity.Customer;

import java.sql.SQLException;
import java.util.Scanner;

public class AdministratorView extends AbstractView {
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
            System.out.println("Please enter the name:");
            customer.setName(scanner.nextLine());
            System.out.println("Please enter the address:");
            customer.setAddress(scanner.nextLine());
            System.out.println("Please enter the phone:");
            customer.setPhone(scanner.nextLine());

            boolean res = bankDao.insertCustomer(customer);
            if (res) {
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
            customer.setNum(scanner.nextInt());
            boolean res = bankDao.deleteCustomer(customer);
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
        if (c == 1) {
            openAccount();
        } else if (c == 2) {
            closeAccount();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void openAccount() {
        System.out.println("Please select account type: (1) Checking (2) Savings (3) Loan");
        int c = scanner.nextByte();
        if (c == 1) {

        } else if (c == 2) {

        } else if (c == 3) {

        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void closeAccount() {

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
