package prob5;

public class MainApp2 {

	public static void main(String[] args) {
		try {
			MyStack2 stack = new MyStack2(3);
			stack.push("Hello");
			stack.push("World");
			stack.push("!!!");
			stack.push(1);
			stack.push(".");

			while (stack.isEmpty() == false) { // 모든 데이터 pop
				String s = (String) stack.pop();
				System.out.println( s );
			}

			System.out.println("======================================");

			stack = new MyStack2(3);
			stack.push("Hello");

			System.out.println(stack.pop());
			System.out.println(stack.pop());
			
		} catch (MyStackException ex) {
			System.out.println( ex );
		}
	}
}
