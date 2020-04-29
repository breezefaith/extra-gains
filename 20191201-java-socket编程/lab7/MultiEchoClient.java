import java.io.*;
import java.net.*;
public class MultiEchoClient
{
   //declare the InetAddress host, port number, Scoket, BufferedReader and PrintWriter for the server (constant)
	private static InetAddress host;
	private static final int PORT = 1234;
	private static Socket link;
	private static BufferedReader in;
	private static PrintWriter out;
	private static BufferedReader keyboard;
	
	public static void main(String[] args) throws Exception
	{
		try
		{
         //set the destination IP address
			InetAddress host = InetAddress.getLocalHost();
			link = new Socket(host, PORT);
		         //link = new Socket("127.0.0.1",PORT);
         //The BufferedReader object at the client side will receive messages sent by the PrintWriter
			in = new BufferedReader(new InputStreamReader(link.getInputStream()));
			out = new PrintWriter(link.getOutputStream(), true);
         //read from keybord
			keyboard = new BufferedReader(new InputStreamReader(System.in));
			//send to server
			String message, response;
			do
			{
				System.out.print("Enter message (BYE to exit): ");
				message = keyboard.readLine();
				out.println(message);
				response = in.readLine();
				System.out.println(response);
			}while(!message.equals("BYE"));
		}
		catch(UnknownHostException e)
		{
			System.out.println("Unknown host");
			System.exit(1);
		}
		catch(IOException e)
		{
			System.out.println("Cannot connect to host");
			System.exit(1);
		}
		finally
		{
         //close link
			try
			{
				if (link!=null)
				{
					System.out.println("Closing down connection ...");
					link.close();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}