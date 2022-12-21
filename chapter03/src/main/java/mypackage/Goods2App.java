package mypackage;

import chapter03.Goods2;

public class Goods2App {

	public static void main(String[] args) {
		Goods2 g = new Goods2();
		
		// public은 접근 제한 X
		g.name = "camera";
		
		// protected는 같은 패키지에서 접근 가능
		// 더 중요한 것은 자식에서 접근 가능
		// g.price = 10000;
		
		// 디폴트는 같은 패키지에서 접근 가능
		// g.countStock = 10;
		
		// private 는 같은 패키지에서 접근 가능
		// g.countSold = 10;
		
		System.out.println(g.name);

	}

}
