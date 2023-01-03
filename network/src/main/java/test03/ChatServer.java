package test03;

import java.io.IOException;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import chat.ChatServerThread;

public class ChatServer {
	
	public static final int PORT = 8088;

	public static void main(String[] args) {
		List<Writer> listWriters=new ArrayList<Writer>();
		ServerSocket serverSocket = null;
		
		// 서버 구현하기
		
		try {
			// 1. Server Socket 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩하기
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT), 10);
			System.out.println("[server] connect");
			
			// 3. accept - 요청대기
			while (true) {
				Socket socket = serverSocket.accept();
				// thread 생성
				new ChatServerThread(socket, listWriters).start();
			}
			
		} catch (IOException e) {
			System.out.println("[server] error: " + e);
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
