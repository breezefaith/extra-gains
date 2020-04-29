import java.io.*;
import java.net.*;
public class MultiEchoServer
{
   //declare the ServerSocket variable and the port number for the server (constant)
	private static ServerSocket serverSock;
	private static final int PORT = 1234;
	public static void main(String[] args) throws IOException
	{
      //create the serverscoket
		try{
			serverSock=new ServerSocket(PORT);
		}
      //if the connection is unsuccessful it will goes to here
		catch (IOException e)
		{
			System.out.println("Can't listen on " + PORT);
			System.exit(1);
		}
		do
		{
         //if connection is successful it will wait for a client to connect to our server
			Socket client = null;
			System.out.println("Listening for connection...");
         //if client sends a new request, we use try catch block to accept the connection
			try{
				client = serverSock.accept();
				System.out.println("New client accepted");
            //create new ClientHander for new client
				ClientHandler handler = new ClientHandler(client);
				handler.start();
			}
			catch (IOException e)
			{
				System.out.println("Accept failed");
				System.exit(1);
			}
         //if the connection is successful we display messages to show we accept input 
			System.out.println("Connection successful");
			System.out.println("Listening for input ...");
		}while(true);
	}
}
//This is the support class that extends Thread, runs the client thread and sends and receives messages.
class ClientHandler extends Thread
{
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	public ClientHandler(Socket socket)
	{
      
		client = socket;
		try
		{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(),true);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void run()
	{
		try
		{
			String received;
			do
			{
				received = in.readLine();
				out.println("ECHO: " + received);
				System.out.println(received);
 
			}while (!received.equals("BYE"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(client!=null)
				{
					System.out.println("Closing down connection...");
					client.close();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}