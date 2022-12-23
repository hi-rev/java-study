package prob4;

public class Cat implements Soundable {
	// implements: 부모 객체는 선언만 하여 정의는 자식에서 오버라이딩(재정의)해서 사용해야 함

	@Override
	public String sound() {
		return "\"야옹\"";
	}

}
