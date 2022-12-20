package prob05;

import java.util.Random;
import java.util.Scanner;

public class Prob05 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );

		while( true ) {
			
			/* 게임 작성 */

			// 정답 랜덤하게 만들기
			Random random = new Random();
			int correctNumber = random.nextInt( 100 ) + 1; // 맞춰야할 수
			System.out.println("수를 결정하였습니다. 맞추어 보세요");
			
			int num = 1;
			int start = 1;
			int end = 100;
			while(true) {
				System.out.println(start + "-" + end);
				System.out.print(num + ">>");
				int answer = scanner.nextInt();
				
				if (answer == correctNumber) {
					System.out.println("맞았습니다.");
					break;
				} else if (answer > correctNumber) {
					end = answer;
					System.out.println("더 낮게");
				} else {
					start = answer;
					System.out.println("더 높게");
				}
			}
			
			//새 게임 여부 확인하기
			System.out.print( "다시 하겠습니까(y/n)>>" );
			String answer = scanner.next();
			if( "y".equals( answer ) == false ) {
				break;
			}
		}
		
		scanner.close();
	}

}