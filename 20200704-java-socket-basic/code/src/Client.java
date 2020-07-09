import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String line = null;

        try {
            socket = new Socket("127.0.0.1", 5000);

            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.println("Hello world!");
            printWriter.flush();

            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            line = bufferedReader.readLine();
            System.out.println(line);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
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
