package Test02;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import Test01.Student;

public class Client {
    public static void main(String[] args) {
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);

        Socket socket = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String line = null;

        try {
            socket = new Socket("127.0.0.1", 9999);

            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.println(student.toString());
            printWriter.flush();

            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            line = bufferedReader.readLine();
            System.out.println(line);

            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.println(scanner.next());
            printWriter.flush();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
