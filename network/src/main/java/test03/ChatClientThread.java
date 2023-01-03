package test03;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;

public class ChatClientThread extends Thread {
	private String nickName = null;
	private BufferedReader br = null;

	public ChatClientThread(String nickName, BufferedReader br) {
		this.nickName = nickName;
		this.br = br;
	}

	@Override
	public void run() {
		
		try {
			while (true) {
				String data = br.readLine();
				
				if (data == null) {
					System.out.println("closed by server");
					break;
				}
				
				if ("join:ok".equals(data)) {
					System.out.println(nickName + "님 환영합니다!");
					System.out.print(">> ");
				} else {
					System.out.println(nickName + ": " + data);
					System.out.print(">>");
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
	



