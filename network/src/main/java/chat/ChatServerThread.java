package chat;

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
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ChatServerThread extends Thread {
	private String nickName;
	private Socket socket;
	private List<Writer> listWriters;
	private PrintWriter pw = null;
	
	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters  = listWriters;
	}

	@Override
	public void run() {
		try {
			// 1. remote Host Information
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remoteport = inetRemoteSocketAddress.getPort();
			System.out.println("connected by client[ " + remoteHostAddress + " : " + remoteport + " ]");
			
			// 2. 스트림 얻기(입출력)
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
			
			// 3. 요청 처리
			while (true) {
				String data = br.readLine();
				
				if (data == null) {
					System.out.println("[server] closed by client");
					doQuit(pw);
					break;
				}
				
				// 4. 프로토콜 분석
				String[] tokens = data.split(":");
				if ("join".equals(tokens[0])) {
					doJoin(tokens[1], pw);
				} else if ("message".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if ("quit".equals(tokens[0])) {
					doQuit(pw);
					break;
				} else {
					System.out.println("[server] 에러: 알 수 없는 요청 (" + tokens[0] + ")");
				}
			}
		} catch (SocketException e) {
			System.out.println("[server] closed by client");
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
	
	// doJoin은 한 사용자가 채팅방에 참여 했을 때, 다른 사용자들에게 "~님이 입장하셨습니다."
	// 라는 메세지를 브로드캐스팅해야한다.
	private void doJoin(String nickName, Writer writer) {
		this.nickName = nickName;
		
		String data = nickName + "님이 참여하였습니다.";
		broadcast(data);
		
		// writer pool에 저장
		addWriter(writer);
		
		pw.println("join:ok");
	}
	
	// List인 listWriters에 파라미터로 받은 Writer를 추가한다.
	// synchronized 키워드는 여러 스레드가 하나의 공유 객체에 접근할 때, 동기화를 보장해준다.
	private void addWriter(Writer writer) {
		synchronized(listWriters) { // 동기화
			listWriters.add(writer);
		}
	}
	
	// 서버에 연결된 모든 client에 메시지를 보내는 메소드
	private void broadcast(String data) {
		synchronized(listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter pw = (PrintWriter) writer;
				pw.println(data);
			}
		}
	}
	
	// message 프로토콜 구현
	private void doMessage(String message) {
		broadcast(nickName + ": " + message);
		System.out.println(nickName + ": " + message);
	}
	
	// ~님이 퇴장하였습니다. 메시지가 브로드캐스팅 되어야 한다.
	// 현재 스레드의 writer를 Writer Pool에서 제거한 후, 브로드캐스팅한다.
	private void doQuit(Writer writer) {
		removeWriter(writer);
		broadcast(nickName + "님이 퇴장하였습니다.");
	}

	private void removeWriter(Writer writer) {
		synchronized(listWriters) { // 동기화
			listWriters.remove(writer);
		}
	}
}
