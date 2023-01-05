package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	public static final int PORT = 8088;

	public static void main(String[] args) {
		// Writer를 담을 수 있는 List 생성
		List<Writer> listWriters=new ArrayList<Writer>();
		ServerSocket serverSocket = null;
		
		try {
			// 1. Server Socket 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩(binding)
			// IP Address와 Port에 binding
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT), 10);
			System.out.println("[server] connect");
			
			// 3. Accept - 요청 대기
			while (true) {
				Socket socket = serverSocket.accept(); // blocking
				// 요청이 수락하고 스레드를 생성할 때, List 객체를 스레드의 생성자를 통해 전달한다.
				// 데이터 통신 Thread들이 listWriters를 공유하기 때문에 객체로 넘겨준다.
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
