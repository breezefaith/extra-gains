package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner scanner=new Scanner(System.in);
		Socket socket=new Socket("127.0.0.1", 8888);

		OutputStream outputStream=socket.getOutputStream();
		PrintWriter printWriter=new PrintWriter(outputStream);
		System.out.println("Please input your data from keyboard");
		printWriter.write(scanner.nextLine());
		printWriter.flush();
		socket.shutdownOutput();

		InputStream inputStream=socket.getInputStream();
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

		String receptText="";
		String temp=null;

		while ((temp=bufferedReader.readLine())!=null) {
			receptText+=temp;
		}

		System.out.println("client's received data:"+receptText);

		bufferedReader.close();
		inputStream.close();
		printWriter.close();
		outputStream.close();

		socket.close();
	}
}
