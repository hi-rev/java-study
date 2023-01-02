package test02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static final int PORT = 8088;

	public static void main(String[] args) {
		
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
				new EchoThread(socket).start();
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
