package chat.gui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import chat.ChatServer;

public class ChatClientApp {

	public static void main(String[] args) {
		String name = null;
		Scanner sc = new Scanner(System.in);
		Socket socket = null;

		while( true ) {
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = sc.nextLine();
			
			if (!name.isEmpty()) {
				break;
			}
			
			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}
		
		sc.close();
		
		try {
			// 1. create socket
			socket = new Socket();
			
			// 2. connect to server
			// -> chat.ChatServer 사용
			socket.connect(new InetSocketAddress("0.0.0.0", ChatServer.PORT));
			
			// 3. get iostream
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			// 4. join protocol 진행
			pw.println("join:" + name);
			String line = br.readLine();
			if ("join:ok".equals(line)) {
				new ChatWindow(name, socket).show();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				if(!socket.isClosed() && socket != null) {
					socket.close();					
				}
			} catch (IOException es) {
				es.printStackTrace();
			}
		} 
	}
}

