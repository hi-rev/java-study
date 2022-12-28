package prob01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GugudanApp {
	public static void main(String[] args) {
		// 랜덤한 구구단 객체 생성
		Gugudan[] gugudans = randomizeGugudan(9);
		
		int loc = randomize(0, 9);
		int resultNumber = gugudans[loc].getlValue() * gugudans[loc].getrValue();
		System.out.println(gugudans[loc].getlValue() + " x " + gugudans[loc].getrValue() + " = ?");
		
		int len = gugudans.length;
		for (int i=0; i<len; i++) {
			System.out.println(i % 3 == 0 ? "\n" : "\t");
			System.out.println(gugudans[i].getlValue() * gugudans[i].getrValue());
		}
		
		System.out.print("\n\n");
		System.out.println("answer: ");
		
		Scanner sc = new Scanner(System.in);
		int answer = sc.nextInt();
		sc.close();
		
		System.out.println((answer == resultNumber) ? "정답" : "오답");
			
	}
	
	private static int randomize(int lNum, int rNum) {
		// Math.random(): double형으로 0.0이상 1.0 미만의 값 반환
		// random: 1부터 9까지 랜덤값
		int random = (int) (Math.random() * rNum) + lNum;
		return random;
	}	
		
	private static Gugudan[] randomizeGugudan(int count) {
		// 중복 제거를 위한 구구단 객체 HashSet
		Set<Gugudan> result = new HashSet<>();
		
		// result set의 길이가 9가 될 때까지 반복
		while (result.size() != count) {
			// 1부터 9까지의 랜덤값 2개를 가진 구구단 객체 생성
			result.add(new Gugudan(randomize(1, 9), randomize(1, 9)));
		}
		
		return result.toArray(new Gugudan[result.size()]);
	}	
}		
