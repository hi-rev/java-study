package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

import echo.EchoServer;

public class ChatClientThread extends Thread {
	
	private Socket socket;
	private BufferedReader br;
	
	public ChatClientThread(Socket socket) {
			this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// reader를 통해 읽은 데이터 콘솔에 출력하기 (message 처리)
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			while(true) {
				String data = br.readLine();
				System.out.println(data);
				if(data == null) {
					System.out.println("[client] closed by server");
					break;
				}
			}
		} catch (SocketException e) {
			System.out.println("[client] closed by server");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(br != null )
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
}
