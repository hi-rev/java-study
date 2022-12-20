package prob01;

import java.util.Scanner;

public class Prob01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner( System.in  );

		final int[] MONEYS = { 50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1 };
		System.out.print("금액: ");
		int m = sc.nextInt();
		int res = m; // 나머지
		
		System.out.println();
		/* 코드 작성 */
		for (int i=0; i<MONEYS.length; i++) {
			System.out.print(MONEYS[i] + "원 : ");
			System.out.println(res / MONEYS[i] + "개");
			
			res %= MONEYS[i];
		}
		
		sc.close();
 	}
}