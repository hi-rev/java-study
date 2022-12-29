package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	public static final int PORT = 8000;
	

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try { // ** 어느 부분을 Thread로 태워야할까?
			serverSocket = new ServerSocket();
			
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 8000), 10);
			System.out.println("[Echoserver] starts... [port: " + PORT + "]");
			Socket socket = serverSocket.accept(); // blocking
			
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remoteport = inetRemoteSocketAddress.getPort();
			log("connected by client[" + remoteHostAddress + ":" + remoteport + "]");
			
			try {
				// true: 버퍼가 다 찰때까지 기다리지 말고 즉시 비우기
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
				
				while (true) {
					// 5. 데이터 읽기
					String data = br.readLine();
					if (data == null) {
						log("closed by client");
						break;
					}
					log("received: " + data);
					pw.println(data);
				}
				// 소켓을 2개 가지고 있기 때문에 각각 IOException을 따로 처리
			} catch(SocketException ex) {
				System.out.println("[server] suddenly closed by client");
			} catch(IOException ex) { // 데이터 통신 소켓
				System.out.println("[server] error: " + ex);
			} finally {
				try {
					if (socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) { // 서버 소켓
			log("[server] error: " + e);
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
	private static void log(String message) {
		System.out.println("[EchoServer] " + message);
	}
}
