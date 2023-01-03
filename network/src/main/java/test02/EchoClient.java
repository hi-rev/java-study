package test02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	
	private static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {
		Scanner sc = null;
		
		// 1. 소켓 생성
		Socket socket = new Socket();
		
		try {
			// 2. 서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP, EchoServer.PORT));
			System.out.println("client connect");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			sc = new Scanner(System.in);
			while (true) {
				System.out.print("> ");
				String line = sc.nextLine();
				
				if ("exit".equals(line)) {
					break;
				}
				pw.println("line이 뭐냐 " + line);
				////
				String data = br.readLine(); // line이 뭐냐 hello
				if (data == null) {
					System.out.println("closed by server");
					break;
				}
				System.out.println("나경: " + data);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
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


