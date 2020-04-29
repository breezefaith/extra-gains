import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Random;

public class BroadcastChatServer {
    //Hash table for Client Names and corresponding PrintWriter objects
    //Hash table for Client IDs and corresponding message received
    private static Hashtable<String, PrintWriter> writers = new Hashtable<>();
    private static Hashtable<Integer, String> clientNames = new Hashtable<>();
    private static Hashtable<String, Integer> keys = new Hashtable<>();

    //TO-DO: Declare other static variables here (ServerSocket and PORT)
    private static ServerSocket serverSock;
    private static final int PORT = 1234;

    private static Random random;

    //as in the basic multi-client/server program
    //main method
    public static void main(String[] args) throws IOException {
        random = new Random();
        //TO-DO: code similar to basic multi-client/server program
        //create the serverscoket
        try {
            serverSock = new ServerSocket(PORT);
        }
        //if the connection is unsuccessful it will goes to here
        catch (IOException e) {
            System.out.println("Can't listen on " + PORT);
            System.exit(1);
        }
        do {
            //if connection is successful it will wait for a client to connect to our server
            Socket client = null;
            System.out.println("Listening for connection...");
            //if client sends a new request, we use try catch block to accept the connection
            try {
                client = serverSock.accept();
                System.out.println("New client accepted");
                //create new ClientHander for new client
                ClientHandler handler = new ClientHandler(client);
                handler.start();
            } catch (IOException e) {
                System.out.println("Accept failed");
                System.exit(1);
            }
            //if the connection is successful we display messages to show we accept input
            System.out.println("Connection successful");
            System.out.println("Listening for input ...");
        } while (true);
    }

    //Make the ClientHandler a class inside the main class as below
    private static class ClientHandler extends Thread {
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            //TO-DO: code similar to basic multi-client/server program
            client = socket;
            try {
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                String received;
                String plain=null;
                int currentKey = 0;
                int message = 1;
                do {
                    int index = 0;
                    received = in.readLine();
                    int clientNum = 0;
                    if (message == 1) {
                        String clientName = getName().substring(getName().length() - 1);
                        clientNum = Integer.parseInt(clientName);
                        //add client ID and message received to the clientnames hash table
                        clientNames.put(clientNum, received);
                        System.out.println(clientNames.get(clientNum) + " has joined");
                        //add client name and corresponding PrintWriter to the writers hash table
                        writers.put(clientNames.get(clientNum), out);
                        //loop through the writers hash table and broadcast to all clients
                        //that a new client has joined
                        int key = random.nextInt(25) + 1;
                        keys.put(received, key);
                        out.println(key);

                        for (String name : writers.keySet()) {
                            writers.get(name).println(CeaserUtil.encode(clientNames.get(clientNum) + " has joined", keys.get(name)));
                        }
                        message++;
                    } else {
                        //TO-DO: Get the clientName and clientNum as before
                        //Loop through the writers keySet, that is, (for String client:writers.keySet())
                        //broadcast to all clients except this one
                        String currentName = getName().substring(getName().length() - 1);
                        int currentNum = Integer.parseInt(currentName);
                        currentKey = keys.get(clientNames.get(currentNum));
                        plain = CeaserUtil.decode(received, currentKey);
                        for (String name : writers.keySet()) {
                            if (name.equals(clientNames.get(currentNum)) == false) {
                                writers.get(name).println(CeaserUtil.encode("Message from " + clientNames.get(currentNum) + ": " + plain, keys.get(name)));
                            }
                        }
                        System.out.println("Message from " + clientNames.get(currentNum) + ": " + received);
                    }
                } while (!"BYE".equals(plain));
            } catch (IOException e) {
            }
        }
    }
}