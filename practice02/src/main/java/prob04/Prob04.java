package prob04;
public class Prob04 {

	public static void main(String[] args) {
		char[] c1 = reverse("Hello World");
		printCharArray( c1 );
		
		char[] c2 = reverse("Java Programming!");
		printCharArray( c2 );
	}
	
	public static char[] reverse(String str) {
		/* 코드를 완성합니다 */
		int idx = str.length() - 1;
		char[] result = new char[str.length()];
		
		for (int i=0; i<result.length; i++) {
			result[i] = str.charAt(idx);
			idx--;
		}
		
		return result;
	}

	public static void printCharArray(char[] array) {
		/* 코드를 완성합니다 */
		for (int i=0; i<array.length; i++) {
			System.out.print(array[i]);
		}
		System.out.println();
	}
}