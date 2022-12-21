package chapter03;

public class DiscountGoods2 extends Goods2 {
	// 그냥 0.5라는 실수로 쓰면 double형으로 인식된다.
	// 따라서 float형으로 사용할 때는 f를 붙여줘야 함
	private float discountrate = 0.5f;
	
	private int i = 10;
	private Long l = 2100000000L;
	public float getDiscountPrice() {
		// protected는 자식에서 접근할 수 있다.
		System.out.println(i + l);
		return discountrate*price;
	}
}
