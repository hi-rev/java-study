package exception;

public class ExceptionTest {

	public static void main(String[] args) {
		int a = 0;
		int b = 10 - a;
		
		try {
			System.err.println("code1");
			System.err.println("code2: file open");
			int result = (1 + 2 + 3) / b;
			System.err.println("code3");
			System.err.println("code4");
		} catch(ArithmeticException e) {
			/* 예외 처리 */
			// 1. 로깅
			System.err.println("error: " + e);
			
			// 2. 오류 알림
			System.out.println("gg");
			
			// 3. 정상 종료
//			return;
			System.exit(0);
		} finally {
			System.out.println("file close");
		}
		
	}
	
}
