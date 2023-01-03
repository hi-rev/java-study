package test02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoThread extends Thread {

	private Socket socket;
	
	public EchoThread(Socket socket) {
		
		this.socket = socket;
		
	}

	@Override
	public void run() {
		// 1. remote Host Information
		InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
		String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
		int remotePort = inetRemoteSocketAddress.getPort();
		System.out.println("connected by client[" + remoteHostAddress + " : " + remotePort + "]");
		
		try {
			// 2. 스트림 얻기
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			while (true) {
				String data = br.readLine(); 
				
			if (data == null) {
				System.out.println("closed by client");
				break;
			}
			
			System.out.println("data가 뭐냐 " + data);
			pw.println(data);
			
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("error: " + e);
		} finally {
			if (socket != null && !socket.isClosed()) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
