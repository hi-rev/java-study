package prob02;

import java.util.Scanner;

public class CalcApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while( true ) {
			System.out.print( ">> " );
			String expression = scanner.nextLine();
			
			if( "quit".equals( expression ) ) {
				break;
			}
			
			String[] tokens = expression.split( " " );
			
			if( tokens.length != 3 ) {
				System.out.println( ">> 알 수 없는 식입니다.");
				continue;
			}
			
			int lValue = Integer.parseInt( tokens[ 0 ] );
			int rValue = Integer.parseInt( tokens[ 1 ] );
			
			/* 코드 작성 */
			Arithmetic add = new Add();
			Arithmetic sub = new Sub();
			Arithmetic mul = new Mul();
			Arithmetic div = new Div();
			
			int result = 0;
			
			if ("+".equals(tokens[2])) {
				result = add.calculate(lValue, rValue);
			} else if ("-".equals(tokens[2])) {
				result = sub.calculate(lValue, rValue);
			} else if ("*".equals(tokens[2])) {
				result = mul.calculate(lValue, rValue);
			} else if ("/".equals(tokens[2])) {
				result = div.calculate(lValue, rValue);
			}
			
			System.out.println( ">> " + result );
		}
		
		scanner.close();
	}
}