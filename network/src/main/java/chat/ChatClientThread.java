package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import echo.EchoServer;

public class ChatClientThread extends Thread {
	private static final String SERVER_IP = "127.0.0.1";
	
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;
	
	public ChatClientThread(Socket socket) {
		try {
			this.socket = socket;
			
			socket.connect(new InetSocketAddress(SERVER_IP, EchoServer.PORT));
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		// reader를 통해 읽은 데이터 콘솔에 출력하기 (message 처리)
			try {
				while (true) {
					String message = br.readLine();
					
					if (message == null) {
						return;
					} else {
						System.out.println(message);
					}
				}
			} catch (IOException e) {
				System.out.println("[client] error " + e);
			}
		}
	
	// protocol method 구현하기
	
}
