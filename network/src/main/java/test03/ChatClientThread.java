package test03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ChatClientThread extends Thread {
	private Socket socket;
	private BufferedReader br;

	public ChatClientThread(Socket socket) {
		this.socket = socket;
		
	}

	@Override
	public void run() {
		
		try {
			// message 처리하기
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			while(true) {
				String data = br.readLine();
				System.out.println(data);
				if(data == null) {
					System.out.println("closed by server");
					break;
				}
			}
		} catch (SocketException e) {
			System.out.println("closed by server");
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
	



