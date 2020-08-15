package banking.database.view;

import java.util.Scanner;

public class CustomerView extends AbstractView {
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

    }

    private void withdraw() {

    }

    private void transfer() {

    }

    private void loanPayment() {

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
