package prob02;

import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];

		// 상품 입력
		for (int i = 0; i < COUNT_GOODS; i++) {
			String info = scanner.nextLine();
			String[] infos = info.split(" ");
			
			goods[i] = new Goods(infos[0], infos[1], infos[2]);
		}

		// 상품 출력
		for (int i = 0; i < COUNT_GOODS; i++) {
			System.out.println(goods[i].getName() + "(가격:" + goods[i].getPrice() + "원)이 " + goods[i].getCnt() + "개 입고 되었습니다.");
		}
		
		// 자원정리
		scanner.close();
	}
}
