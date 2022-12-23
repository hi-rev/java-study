package chapter04;

public class Object03Test {

	public static void main(String[] args) {
		// String 객체
		String s1 = new String("hello");
		String s2 = new String("hello");
		
		System.out.println(s1 == s2); // false
		System.out.println(s1.equals(s2)); // true
		// hashCode는 같다 (내용 기반 hashCode)
		System.out.println(s1.hashCode() + " " + s2.hashCode());
		// hashCode 다르다 (주소 기반 hashCode)
		System.out.println(System.identityHashCode(s1) + " " + System.identityHashCode(s2));
		
		System.out.println("========================");
		String s3 = "hello";
		String s4 = "hello";
		
		System.out.println(s3 == s4); // true
		System.out.println(s3.equals(s4)); // true
		// hashCode는 같다 (내용 기반 hashCode)
		System.out.println(s3.hashCode() + " " + s4.hashCode());
		// hashCode는 같다 (주소 기반 hashCode)
		System.out.println(System.identityHashCode(s3) + " " + System.identityHashCode(s4));
		
		// JVM 내에는 상수풀이라는 것이 있음 (String용)
		// new String 하면 각각 다른 주소의 객체가 생성되는데,
		// 그냥 리터럴로 사용하면 하나의 String을 같이 가리키게 된다. 
		
	}

}
