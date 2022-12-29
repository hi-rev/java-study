package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		// 서버 소켓 생성
		try {
			// 1. Server Socket 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩(binding)
			// 	Socket에 InetSocketaddress(IP Address + Port)를 바인딩 한다.
			//	IPAddress: 0.0.0.0 - 특정 호스트 IP에 바인딩 하지 않는다.
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5000), 10);
			
			// 3. accept
			Socket socket = serverSocket.accept(); // blocking
			
			try {
				InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
				String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				int remoteport = inetRemoteSocketAddress.getPort();
				System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remoteport + "]");
				System.out.println("connected");
				
				
				// 4. IO Stream 받아오기
				OutputStream os = socket.getOutputStream();
				InputStream is = socket.getInputStream();
				
				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); // blocking
					if (readByteCount == -1) {
						// 클라이언트가 정상적으로 종료(close() 호출)
						System.out.println("[server] closed by client");
						break;
					}
					
					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] received: " + data);
					
					// 6. 데이터 쓰기
					os.write(data.getBytes("utf-8"));
				}
				// 소켓을 2개 가지고 있기 때문에 각각 IOException을 따로 처리
			} catch(SocketException ex) { // 데이터 통신 소켓
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
