package prob3;

public class Prob2 {
	public static void main(String[] args) {
		Bird bird01 = new Duck();
		bird01.setName( "꽥꽥이" );
		bird01.fly(); // 날지 않습니다.
		bird01.sing(); // 소리내어 웁니다.
		System.out.println( bird01.toString() ); // 오리의 이름은 꽥꽥이 입니다.
		
		Bird bird02 = new Sparrow();
		bird02.setName( "짹짹이" ); 
		bird02.fly(); // 날아다닙니다.
		bird02.sing(); // 소리내어 웁니다.
		System.out.println( bird02.toString() ); // 짹짹입니다.
	}
}
