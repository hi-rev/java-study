package chapter04;

public class ObjectTest02 {

	public static void main(String[] args) {
		Point p1 = new Point(10, 20); // p1 저장값 ex) 레퍼런스 1000
		Point p2 = new Point(10, 20); // p2 저장값 ex) 레퍼런스 2000
		Point p3 = p2;
		
		// == : 두 객체의 동일성 비교
		System.out.println(p1 == p2); // flase
		System.out.println(p2 == p3); // true
		
		// equals: 두 객체의 동질성(내용) 비교
		//			 부모 클래스 Object의 기본 구현은 동일성(==) 비교와 같다.
		System.out.println(p1.equals(p2)); // flase
		System.out.println(p2.equals(p3)); // true
	}
	
}
