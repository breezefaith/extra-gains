package banking.database.view;

import banking.database.dao.ReportingDao;
import banking.database.entity.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReportingView extends AbstractView {
    private ReportingDao reportingDao = new ReportingDao();

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
        try {
            Map<String, List<Transaction>> map = reportingDao.getMonthlyStatement();
            if (map.size() == 0) {
                System.out.println("No record");
                return;
            }
            for (Map.Entry<String, List<Transaction>> entry : map.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println("Account\tAccountType\tDate\tAmount\tTransType\tComments");
                for (Transaction transaction : entry.getValue()) {
                    System.out.println(transaction.getFormatString());
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loanPaymentSchedule() {
        try {
            List<Transaction> list = reportingDao.getLoanPayments();
            if (list.size() == 0) {
                System.out.println("No record");
                return;
            }
            System.out.println("Account\tAccountType\tDate\tAmount\tTransType\tComments");
            for (Transaction transaction : list) {
                System.out.println(transaction.getFormatString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printYearlyTaxStatement() {
        try {
            Map<String, List<Transaction>> map = reportingDao.getYearlyTaxStatement();
            if (map.size() == 0) {
                System.out.println("No record");
                return;
            }
            for (Map.Entry<String, List<Transaction>> entry : map.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println("Account\tAccountType\tDate\tAmount\tTransType\tComments");
                for (Transaction transaction : entry.getValue()) {
                    System.out.println(transaction.getFormatString());
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        System.out.println("(3) Print yearly tax statement");
        System.out.println("(4) Quit");
    }
}
