package prob4;

import java.util.Scanner;

public class Prob4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("문자열을 입력하세요 : ");
		String text = sc.nextLine();
		int end = 1;
		
		for (int i=0; i<text.length(); i++) {
			for (int j=0; j<end; j++) {
				System.out.print(text.charAt(j));
			}
			System.out.println();
			end++;
		}
		
		sc.close();
	}

}
