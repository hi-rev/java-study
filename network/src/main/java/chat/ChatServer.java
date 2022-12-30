package chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	public static final int PORT = 8088;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			// 1. Server Socket 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩(binding)
			// 특정 IP Address와 Port에 binding
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT), 10);
			
			// 3. accpet - 요청 대기
			while (true) {
				socket = serverSocket.accept(); // blocking
//				new ChatServerThread(socket).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
