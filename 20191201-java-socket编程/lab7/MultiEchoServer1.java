import java.io.*;
import java.net.*;
public class MultiEchoServer1
{
	private static ServerSocket serverSock;
	private static final int PORT = 1234;
	public static void main(String[] args) throws IOException
	{
		try{
			serverSock=new ServerSocket(PORT);
		}
		catch (IOException e)
		{
			System.out.println("Can't listen on " + PORT);
			System.exit(1);
		}
		do
		{
			Socket client = null;
			System.out.println("Listening for connection...");
			try{
				client = serverSock.accept();
				System.out.println("New client accepted");
				ClientHandler handler = new ClientHandler(client);
				handler.start();
			}
			catch (IOException e)
			{
				System.out.println("Accept failed");
				System.exit(1);
			}
			System.out.println("Connection successful");
			System.out.println("Listening for input ...");
		}while(true);
	}
}

class ClientHandler extends Thread
{
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
   private static final int numClients = 4;
   private String[] clientNames = new String[numClients];
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
         int b=0;
         int clientNum=0;
   	   do
			{
            if(b==0){
           received = in.readLine();
           String clientName=getName().substring(getName().length()-1);
           clientNum = Integer.parseInt(clientName);
           clientNames[clientNum]=received;
           b=b+1;
         }
         else{

			   	received = in.readLine();
			   	out.println("ECHO: " + received);
			   	System.out.println("Message from " + clientNames[clientNum] + ": " + received);
         }
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