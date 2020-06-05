import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class T3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("fin.txt"));
        String s = bufferedReader.readLine();
        PrintWriter printWriter = new PrintWriter("fou.txt");
        for (int i = 0; i < s.length(); i++) {
            printWriter.print(s.charAt(i));
            if ((i + 1) % 40 == 0) {
                printWriter.println();
            }
        }
        printWriter.println();

        printWriter.close();
        bufferedReader.close();
    }
}
