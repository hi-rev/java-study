package thread;

public class AlphabetThread extends Thread {

	@Override
	public void run() {
		for (char i = 'a'; i <= 'z'; i++) {
			System.out.print(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
