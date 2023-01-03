package test03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ChatServerThread3 extends Thread {
	
	private String nickName;
	private Socket socket;
	PrintWriter pw = null;
	private List<Writer> listWriters;
	
	public ChatServerThread3(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
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
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			while (true) {
				String data = br.readLine(); 
				
			if (data == null) {
				System.out.println("closed by client");
				break;
			}
			
			String[] tokens = data.split(":");
			if ("join".equals(tokens[0])) {
				doJoin(tokens[1], pw);
			} else if ("message".equals(tokens[0])) {
				doMessage(tokens[1]);
			} else if ("quit".equals(tokens[0])) {
				doQuit(pw);
			} else {
				System.out.println("에러: 알 수 없는 요청 (" + tokens[0] + ")");
			}
			
		}
			
		} catch (SocketException e) {
			System.out.println("클라이언트로부터 연결 끊");
			doQuit(pw);
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
	
	//대화방 참여
	private void doJoin(String nickName, PrintWriter pw) {
		this.nickName = nickName;
		
		String data = nickName + "님이 참여하였습니다.";
		broadcast(data);
		/* writer pool에 저장 */
		addWriter(pw);
		// ack
		pw.println("join:ok");
		
	}
	
	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}
	
	private void broadcast(String data) {
		synchronized (listWriters) {
			for(Writer writer: listWriters) {
				PrintWriter pw = new PrintWriter(writer,true);
				pw.println(data);
			}
		}
	}

	private void doMessage(String data) {
		broadcast(data);
	}
	
	private void doQuit(Writer writer) {
		removeWriter(writer);
		
		String data = nickName + "님이 퇴장하였습니다.";
		broadcast(data);
	}

	private void removeWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.remove(writer);
		}
	}
}
