package banking.database.view;

import java.util.Scanner;

public class ReportingView extends AbstractView {
    public ReportingView() {
    }

    public ReportingView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void launch() {
        while (true) {
            showMenu();
            int choice = scanner.nextByte();
            if (choice == 1) {
                printMonthlyStatement();
            } else if (choice == 2) {
                loanPaymentSchedule();
            } else if (choice == 3) {
                printYearlyTaxStatement();
            } else if (choice == 4) {
                return;
            } else {
                System.out.println("Invalid choice! Please reenter");
            }
        }
    }

    private void printMonthlyStatement() {

    }

    private void loanPaymentSchedule() {

    }

    private void printYearlyTaxStatement() {
        sendMail();
    }

    private void sendMail() {
        System.out.println("Sent mails to all customer successfully.");
    }

    @Override
    public void showMenu() {
        System.out.println("      REPORTING FUNCTIONS MENU");
        System.out.println("(1) Print monthly statement");
        System.out.println("(2) Print loan payment schedule");
        System.out.println("(3) Print yearly tax statement (interested earned to be mailed out for each customer)");
        System.out.println("(4) Quit");
    }
}
