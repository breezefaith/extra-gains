package banking.database.view;

import banking.database.dao.AdministratorDao;

import java.util.Scanner;

public abstract class AbstractView implements IView {
    protected Scanner scanner;

    public AbstractView() {
    }

    public AbstractView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
