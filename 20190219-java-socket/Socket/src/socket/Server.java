package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket=new ServerSocket(8888);
		System.out.println("server is running,waiting connection from client...");
		Socket socket=serverSocket.accept();

		InputStream inputStream=socket.getInputStream();
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
		String line=null;
		String receptText="";
		while ((line=bufferedReader.readLine())!=null) {
			receptText=receptText+line;
		}

		System.out.println("server's received data: "+receptText);

		OutputStream outputStream=socket.getOutputStream();

		PrintWriter printWriter=new PrintWriter(outputStream);

		System.out.println("data sent by server: "+receptText.toUpperCase());
		printWriter.write(receptText.toUpperCase());

		printWriter.close();
		outputStream.close();
		bufferedReader.close();
		inputStream.close();

		socket.close();
	}
}
