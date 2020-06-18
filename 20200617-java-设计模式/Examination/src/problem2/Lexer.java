package problem2;

import java.util.Scanner;

public class Lexer {
    private String filename;

    private Scanner scanner;

    public String nextToken() {
        while (scanner.hasNext()) {
            return scanner.next();
        }
        return null;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
