package chapter03;

import java.util.Arrays;

public class ArrayUtilTest {

	public static void main(String[] args) {
//		int[] a = {10, 20, 30, 40}
//		int[] a = new int[4];
//		a[0] = 10;
//		a[1] = 20;
//		a[2] = 30;
//		a[3] = 40;
		
		double[] d = ArrayUtil.intToDouble(new int[] {10, 20, 30, 40});
		System.out.println(Arrays.toString(d));
		
		int[] i1 = ArrayUtil.concat(new int[] {1, 2, 3}, new int[] {4, 5, 6});
//		System.out.println(Arrays.toString(i2));
		
		Goods g = new Goods();
		System.out.println(g);
		System.out.println(g.toString());
	}
}
