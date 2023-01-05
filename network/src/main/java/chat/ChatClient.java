package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {
		Scanner sc = null;
		Socket socket = null;
		try {
			// 1. 키보드 연결
			sc = new Scanner(System.in);
			
			// 2. 소켓 생성
			socket = new Socket();
			
			// 3. 서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));
			System.out.println("[client] connect");
			
			// 4. reader/writer 생성
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			// 5. join 프로토콜
			System.out.print("닉네임 >> ");
			String nickName = sc.nextLine();
			pw.println("join: " + nickName);
			
			// 6. ChatClientReceiveThread 시작
			new ChatClientThread(socket).start();
			
			// 7. 키보드 입력 처리
			while (true) {
				System.out.print(" >> ");
				String input = sc.nextLine();
				
				if ("quit".equals(input)) {
					// 8. quit 프로토콜 처리
					pw.println("quit:");
					break;
				} else {
					// 9. 메시지 처리
					pw.println("message:" + input);
				}
			}
		} catch (IOException e) {
			System.out.println("[client] error " + e);
		} finally {
			// 10. 자원 정리
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
				if (sc != null) {
					sc.close();
				}
				} catch (IOException e) {
					e.printStackTrace();
			}
		}
	}
}

