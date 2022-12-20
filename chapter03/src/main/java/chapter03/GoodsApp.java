package chapter03;

public class GoodsApp {

	public static void main(String[] args) {
		Goods camera = new Goods();
		camera.setName("nikon");
		camera.setPrice(400000);
		camera.setCountStock(30);
		camera.setCountSold(50);
		
		camera.printInfo();
	}
	
	// main문을 실행하면 어떻게 되는가?
	// GoodsApp.class가 실행됨
	// method area에 GoodsApp이 올라옴
	// method area - 필드 정보(인스턴스와 관련된 정보), 메소드 코드, 메인 메소드, static 변수 의 정보가 method area에 담긴다.
}
