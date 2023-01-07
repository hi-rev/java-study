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

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	private String name;

	public ChatWindow(String name) {
		this.name = name;
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		new ChatClientThread().start();;
	}

	public void show() {
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
	}
	
	private void sendMessage() {
		String message = textField.getText();
		
		if (message == "quit") {
			finish();
		}
		
		textField.setText(""); // 메세지창 비우기
		textField.requestFocus(); // 메세지창 포커스
		
		// ChatClientThread 에서 서버로부터 받은 메세지가 있다 치고 
		updateTextArea(name + ":" + message);
	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	private class ChatClientThread extends Thread {
		@Override
		public void run() {
			// String message = br.readLine();
			updateTextArea("즐거운 채팅 되세요!");
		}
	}
	
	// frame 종료하기
	private void finish() {
		// exit java(Application)
		System.exit(0);
	}
}