package test03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import test02.EchoServer;

public class ChatClient {
	
	private static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {
		Scanner sc = null;
		
		// 1. 소켓 생성
		Socket socket = new Socket();
		
		try {
			// 2. 서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP, EchoServer.PORT));
			System.out.println("[client] connect");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			sc = new Scanner(System.in);
			System.out.print("닉네임 >> ");
			String nickName = sc.nextLine();
			pw.println("join:" + nickName);
			
			// 3. thread create
			new ChatClientThread(nickName, br).start();
			
			while (true) {
				System.out.print(">> ");
				String input = sc.nextLine();
				
				if ("quit".equals(input)) {
					pw.println("quit:");
				} else {
					pw.println("message:" + input);
				}
			}
			
			
			
		} catch (IOException e) {
			System.out.println("error: " + e);
		} finally {
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
