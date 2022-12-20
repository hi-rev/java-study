package prob3;

import java.util.Scanner;

public class Prob3 {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 입력하세요: ");
		int n = sc.nextInt();
		int res = 0;
		/* 코드 작성 */
		if (n % 2 == 0) { // 짝수일 때
			for (int i=2; i<=n; i+=2) {
				res += i;
			}
			System.out.println("결과 값 : " + res);
		} else { // 홀수일 때
			for (int i=1; i<=n; i+=2) {
				res += i;
			}
			System.out.println("결과 값 : " + res);
		}
		
		sc.close();
	}
}
