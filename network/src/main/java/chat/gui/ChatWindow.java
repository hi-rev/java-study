package chat.gui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw = null;

	public ChatWindow(String name, Socket socket) {
		this.socket = socket;
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {
		try {
			// Button
			buttonSend.setBackground(Color.GRAY);
			buttonSend.setForeground(Color.WHITE);
			// 버튼을 누를 때 발생하는 이벤트
			buttonSend.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					sendMessage();
				}
				
			});
			
			// 버튼 누를 때 발생하는 이벤트 code2
	//		buttonSend.addActionListener((e) -> {
	//		});
	
			// Textfield
			textField.setColumns(80);
			textField.addKeyListener(new KeyAdapter() {
				// Enter 누르면 메세지 전송
				@Override
				public void keyPressed(KeyEvent e) {
					char keyCode = e.getKeyChar();
					if (keyCode == KeyEvent.VK_ENTER) {
						sendMessage();
					}
				}
			});
	
			// Pannel
			pannel.setBackground(Color.LIGHT_GRAY);
			pannel.add(textField);
			pannel.add(buttonSend);
			frame.add(BorderLayout.SOUTH, pannel);
	
			// TextArea
			textArea.setEditable(false);
			frame.add(BorderLayout.CENTER, textArea);
	
			// Frame
			frame.addWindowListener(new WindowAdapter() {
				// frame 닫는거 구현
				@Override
				public void windowClosing(WindowEvent e) {
					finish();
				}
			});
			frame.setVisible(true);
			frame.pack();
			
			// IOStream 받아오기
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"),true);
			
			// ChatClientThread run!
			new ChatClientThread().start();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				if(br != null) {
//					br.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
// -> finish()에서 br.close()
	}
	
	private void sendMessage() {
		String message = textField.getText();
		
		// -> chat.ChatClinet에서 구현하였던 것과 동일하게 protocol 처리
		if (message.equals("quit")) {
			// quit 프로토콜 처리
			pw.println("quit");
			finish();
		} else {
			// 메시지 처리
			pw.println("message:" + message);
		}
		
		textField.setText(""); // 메세지창 비우기
		textField.requestFocus(); // 메세지창 포커스
		
		// ChatClientThread 에서 서버로부터 받은 메세지가 있다 치고 
	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	private class ChatClientThread extends Thread {
		@Override
		public void run() {
			 try {
				 // chat.ChatClientThread와 동일하게 구현해주면 됨
				 while (true) {
						String data = br.readLine();
						System.out.println(data);
						if (data == null) {
							System.out.println("[client] closed");
							break;
						} 
						// 메세지 보내기
						updateTextArea(data);
					}
			 } catch (SocketException e) {
					finish();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// frame 종료하기
	private void finish() {
		try {
			// quit protocol 구현 (명시적으로 프로토콜로 종료해주는 것이 좋음)
			pw.println("quit");
			
			// clean-up
			if (socket != null & !socket.isClosed()) {
				socket.close();
			}
			if(br != null) {
				br.close();
			}
			
			// exit java(Application)
			System.exit(0);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}