package library;

import library.service.AdminService;
import library.service.MainService;
import library.service.PatronService;

import java.util.Scanner;

public class Main {
    private MainService mainService = new MainService();
    private AdminService adminService = new AdminService();
    private PatronService patronService = new PatronService();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        while (true) {
            showMainMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextByte();
            if (choice == 1) {
                runPatron();
            } else if (choice == 2) {
                runAdmin();
            } else if (choice == 3) {
                mainService.closeConnection();
                return;
            } else {
                System.out.println("Unknown choice!");
            }
        }
    }

    private void showMainMenu() {
        System.out.println("     MAIN MENU");
        System.out.println("(1) Patron functions");
        System.out.println("(2) Administrative functions");
        System.out.println("(3) Quit");
    }

    private void runPatron() {
        patronService.login();
        while (true) {
            if (!patronService.isLogged()) {
                System.out.println("No borrower logged in.");
                return;
            }
            patronService.showMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextByte();
            if (choice == 1) {
                patronService.checkoutBook();
            } else if (choice == 2) {
                patronService.returnBook();
            } else if (choice == 3) {
                patronService.payFine();
            } else if (choice == 4) {
                patronService.printLoanedBooks();
            } else if (choice == 5) {
                return;
            } else {
                System.out.println("Unknown choice!");
            }
        }
    }

    private void runAdmin() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            adminService.showMenu();
            int choice = scanner.nextByte();
            if (choice == 1) {
                adminService.addBook();
            } else if (choice == 2) {
                adminService.updateBookHoldings();
            } else if (choice == 3) {
                adminService.searchBook();
            } else if (choice == 4) {
                adminService.addPatron();
            } else if (choice == 5) {
                adminService.printBranchInfo();
            } else if (choice == 6) {
                adminService.printTop10Books();
            } else if (choice == 7) {
                return;
            } else {
                System.out.println("Unknown choice!");
            }
        }
    }
}
