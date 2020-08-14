package banking.database.view;

import banking.database.dao.BankDao;

import java.util.Scanner;

public abstract class AbstractView implements IView {
    protected Scanner scanner;
    protected BankDao bankDao = new BankDao();

    public AbstractView() {
    }

    public AbstractView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
