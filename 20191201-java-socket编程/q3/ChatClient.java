import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
    private static InetAddress host;
    private static final int port = 1234;
    private static Socket link;
    private static BufferedReader in;
    private static PrintWriter out;
    private static BufferedReader keyboard;
    private String userName;
    public int key;

    public static void main(String[] args) throws Exception {
        try {
            InetAddress host = InetAddress.getLocalHost();
            Socket link = new Socket(host, port);

            System.out.println("Connected to the chat server");

            ChatClient client = new ChatClient();

            new ReadThread(link, client).start();
            new WriteThread(link, client).start();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
    }
}

class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;

    public ReadThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        int times = 0;
        while (true) {
            try {
                String response = reader.readLine();
                if (times == 0) {
                    client.key = Integer.parseInt(response);
                } else {
                    System.out.println("\n" + CeaserUtil.decode(response, client.key));
                }
                times++;
            } catch (IOException ex) {
                System.out.println("Connection shut down ");
                break;
            }
        }
    }
}

class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;

    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        try {
            Console console = System.console();

            String userName = console.readLine("\nEnter name: ");
            writer.println(userName);

            String text;

            do {
                text = console.readLine();
                writer.println(CeaserUtil.encode(text, client.key));

            } while (!text.equals("BYE"));


            socket.close();
        } catch (IOException ex) {

            System.out.println("Connection shut down ");
        }
    }
}
