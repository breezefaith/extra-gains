package banking.database.view;

import java.util.Scanner;

public class MainView extends AbstractView {
    protected CustomerView customerView;
    protected AdministratorView administratorView;
    protected ReportingView reportingView;

    public MainView() {
    }

    public MainView(Scanner scanner) {
        super(scanner);
        customerView = new CustomerView(scanner);
        administratorView = new AdministratorView(scanner);
        reportingView = new ReportingView(scanner);
    }

    @Override
    public void launch() {
        while (true) {
            showMenu();
            int choice = scanner.nextByte();
            if (choice == 1) {
                customerView.launch();
            } else if (choice == 2) {
                administratorView.launch();
            } else if (choice == 3) {
                reportingView.launch();
            } else if (choice == 4) {
                return;
            } else {
                System.out.println("Invalid choice! Please reenter");
            }
        }
    }

    @Override
    public void showMenu() {
        System.out.println("      MAIN MENU");
        System.out.println("(1) Customer functions");
        System.out.println("(2) Administrative functions");
        System.out.println("(3) Reporting functions");
        System.out.println("(4) Quit");
    }
}
