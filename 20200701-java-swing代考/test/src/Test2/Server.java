package Test2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;

        OutputStream outputStream = null;
        InputStream inputStream = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        String line = null;
        String studentInfo = null;
        try {
            server = new ServerSocket(9999);
            socket = server.accept();

            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            line = bufferedReader.readLine();
            System.out.println(line);
            studentInfo = line;

            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.println("学生信息：" + studentInfo + "，是否保存？（输入yes执行保存操作）");
            printWriter.flush();

            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            line = bufferedReader.readLine();

            if ("yes".equals(line)) {
                saveFile(studentInfo);
            }
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
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveFile(String info) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("student.txt");
            printWriter.println(info);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
