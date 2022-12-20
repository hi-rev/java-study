package prob1;

import java.util.Scanner;

public class Prob1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/* 코드 작성 */
		System.out.print("수를 입력하시오 : ");
		int n = sc.nextInt();
		
		if (n % 3 == 0) {
			System.out.println("3의 배수입니다.");
		}
		sc.close();
	}
}
