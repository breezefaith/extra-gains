package banking.database.view;

import banking.database.dao.MainDao;

import java.util.Scanner;

public class MainView extends AbstractView {
    protected CustomerView customerView;
    protected AdministratorView administratorView;
    protected ReportingView reportingView;

    protected MainDao mainDao;

    public MainView() {
        mainDao = new MainDao();

        customerView = new CustomerView();
        administratorView = new AdministratorView();
        reportingView = new ReportingView();
    }

    @Override
    public void launch() {
        while (true) {
            showMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextByte();
            if (choice == 1) {
                customerView.launch();
            } else if (choice == 2) {
                administratorView.launch();
            } else if (choice == 3) {
                reportingView.launch();
            } else if (choice == 4) {
                mainDao.closeConnection();
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
