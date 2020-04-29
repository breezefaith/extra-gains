import java.io.*;
import java.net.*;
public class MultiEchoClient1
{
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
			InetAddress host = InetAddress.getLocalHost();
			link = new Socket(host, PORT);
		        //link = new Socket("127.0.0.1",PORT);
			in = new BufferedReader(new InputStreamReader(link.getInputStream()));
			out = new PrintWriter(link.getOutputStream(), true);
			keyboard = new BufferedReader(new InputStreamReader(System.in));
			
			String message, response;
         int a=1;
			do
			{
            if(a==1){
               System.out.print("Enter name: ");
				   message = keyboard.readLine();
               out.println(message);
               a=a+1;
            }
            else{
				   System.out.print("Enter message (BYE to exit): ");
				   message = keyboard.readLine();
				   out.println(message);
				   response = in.readLine();
				   System.out.println(response);
            }
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