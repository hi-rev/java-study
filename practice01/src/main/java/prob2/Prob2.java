package prob2;

public class Prob2 {
	public static void main(String[] args) {
		int start = 1;
		
		/* 코드 작성 */
		for (int i=0; i<9; i++) {
			for (int j=start; j<start+10; j++) {
				System.out.print(j + " ");
			}
			start++;
			System.out.println();
		}
	}
}
