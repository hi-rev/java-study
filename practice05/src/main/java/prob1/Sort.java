package prob1;

public class Sort {
	
	public static void main(String[] arg) {
		// 버블정렬 문제
	
		int arr[] = { 5, 9, 3, 8, 60, 20, 1 };
		int cnt =  arr.length;
		
		System.out.println( "Before sort." );
		
		for (int i = 0; i < cnt; i++) {
			System.out.print( arr[ i ] + " " );
		}
		
		//
		// 정렬 알고리즘이 적용된 코드를 여기에 작성합니다.
		//
		
		for (int i=0; i<cnt-1; i++) {
			for (int j=0; j<cnt-1-i; j++) {
				if (arr[j] < arr[j+1]) {
						int temp = arr[j];
						arr[j] = arr[j+1];
						arr[j+1] = temp;
				}
			}
		}

		// 결과 출력
		System.out.println( "\nAfter Sort." );
		
		for (int i = 0; i < cnt; i++) {
			System.out.print(arr[i] + " ");
		}		
	}
}