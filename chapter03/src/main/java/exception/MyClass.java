package exception;

import java.io.IOException;

public class MyClass {
	// IOExcepion을 던질 수도 있다는 것을 알려주는 것
	public void danger() throws IOException, MyException {
		System.out.println("some code1...");
		System.out.println("some code2...");
		
		if (3 == 3) {
			throw new MyException();
		}
		
		if (5 == 5) {
			throw new IOException();
		}
		
		System.out.println("some code3...");
		System.out.println("some code4...");
	}
}
