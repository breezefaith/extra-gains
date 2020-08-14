package banking.database;

import banking.database.view.MainView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainView mainView = new MainView(scanner);
        mainView.launch();
    }
}
