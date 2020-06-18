package problem2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lexer {
    private String filename;

    private Scanner scanner;

    public Lexer(String filename) {
        this.filename = filename;
        try {
            this.scanner = new Scanner(new File(this.filename));
        } catch (FileNotFoundException e) {
            this.scanner = null;
        }
    }

    public String nextToken() throws GameLoadException {
        try {
            while (scanner != null && scanner.hasNext()) {
                return scanner.next();
            }
            return null;
        } catch (Exception e) {
            throw new GameLoadException();
        }
    }
}
