package prob2;

public class PhoneApp {
	// 상속. super 문제.

	public static void main(String[] args) {
		Phone phone = new SmartPhone();

		phone.execute("음악");
		phone.execute("통화");
		phone.execute("앱");
	}
}