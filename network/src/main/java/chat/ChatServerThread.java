package chat;

import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.List;

public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;
	private List<Writer> listWriters = null;
	private PrintWriter printWriter = null;
	
	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters  = listWriters;
	}
	
	@Override
	public void run() {
		// 1. remote Host Information
		
		// 2. 스트림 얻기
		
		// 3. 요청 처리
		
		
	}
	
	// doJoin은 한 사용자가 채팅방에 참여 했을 때, 다른 사용자들에게 "~님이 입장하셨습니다."
	// 라는 메세지를 브로드캐스팅해야한다.
	private void doJoin(String nickName, Writer writer) {
		this.nickname = nickName;
		
		String data = nickName + "님이 참여하였습니다.";
		broadcast(data);
		
		// writer pool에 저장
		addWriter(writer);
	
		// ack
		printWriter.println("join:ok");
	}
	
	// List인 Writer Pool에 파라미터로 받은 Writer를 추가한다.
	// synchronized 키워드는 여러 스레드가 하나의 공유 객체에 접근할 때, 동기화를 보장해준다.
	private void addWriter(Writer writer) {
		synchronized(listWriters) {
			listWriters.add(writer);
		}
	}
	
	// 서버에 연결된 모든 client에 메시지를 보내는 메소드
	private void broadcast(String data) {
		synchronized(listWriters) {
			for (Writer writer : listWriters) {
				printWriter = (PrintWriter) writer;
				printWriter.println(data);
			}
		}
	}
	
	// message 프로토콜 구현
	private void doMessage(String message) {
		/* */
	}
	
	// ~님이 퇴장하였습니다. 메시지가 브로드캐스팅 되어야 한다.
	// 현재 스레드의 writer를 Writer Pool에서 제거한 후, 브로드캐스팅한다.
	private void doQuit(Writer writer) {
		removeWriter(writer);
		
//		String data = nickName + "님이 퇴장하였습니다.";
//		broadcast(data);
	}

	private void removeWriter(Writer writer) {
		
	}

}


