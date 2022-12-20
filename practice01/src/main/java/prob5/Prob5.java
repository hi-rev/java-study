package prob5;

public class Prob5 {

	public static void main(String[] args) {
		
		for (int i=1; i<100; i++) {
			String str = Integer.toString(i);
			int cnt = 0;
			
			if (i < 10) {
				if (i == 3 || i == 6 || i == 9) {
					System.out.println(i + " 짝");
				}
			} else { // 2자리일 때
				char a = str.charAt(0);
				char b = str.charAt(1);
				
				if (a == '3' || a == '6' || a == '9') {
					cnt++;
				}
				
				if (b == '3' || b == '6' || b == '9') {
					cnt++;
				}
				
				if (cnt == 1) {
					System.out.println(i + " 짝");
					
				} else if (cnt == 2) {
					System.out.println(i + " 짝짝");
				}
			}
		}
	}
}
