package com.douzone.paint.collection.test;

import java.util.ArrayList;
import java.util.List;

import com.douzone.paint.collection.MyStack3;
import com.douzone.paint.i.Drawable;
//import com.douzone.paint.point.Point;
import com.douzone.paint.shape.Rect;
import com.douzone.paint.shape.Shape;

public class GenericTest {

	public static void main(String[] args) {
		drawShape(new Rect());
//		drawShape(new Point());
		
		MyStack3<? extends Shape> s1 = null;
		MyStack3<? super Shape> s2 = null;
		
		List<Rect> list1 = new ArrayList<>();
		List<Rect> list2 = new ArrayList<>();
		
		MyStack3<Drawable> s = new MyStack3<>(20);
		
		drawShapeLayer(list1, null);
		drawShapeLayer(list2, null);
	}
	
	// Generic Parameter 제한01: extend
	// 1) hierachy max
	// 2) 자식들만(자신포함)
	public static <T extends Shape> void drawShape(T t) {
		t.draw();
	}
	
	// Generic Parameter 제한02: super
	// 1. hierachy min
	// 2. 부모들만(자신포함)
	// 3. <T super Type>: 오류이다.
	//	-> 함수 구현 블록(클래스 구현 블록) 안에서 T 타입이 모호하기 때문에 구현이 불가능
	//	-> Type Erasing 결과 <T super Type>는 Object 이기 때문에 문법적인 의미가 없다.
	//	-> 결론은 존재하지 않는다.
	// public static <T super Shape> void drawShape(T t) {
	// 	t.draw();
	// }
	
	// ?: Generic Parameter Wildcard
	// 1. 제네릭 타입의 클래스를 사용할 때 (메소드에서는 절대 x), 
	// 제한(extends, super와 함께)적으로 제네릭 타입을 결정해야할 때 사용한다.
	// 2. 사용할 수 없는 예
	//	-> 이 클래스를 제네릭 타입으로 정의할까 ? MyStack<?>
	//	-> 이 클래스를 Shape 자식의 제네릭 타입으로 정의할까 ? MyStack<? extends Shape>은 x <T extends Shape>만 가능
	//	-> 이 메소드의 첫 번째 파라미터를 제네릭 타입으로 할까 ? <?> void m(? t)은 x
	//	-> 이 메소드의 첫 번째 파라미터를 Shape 자식의 제네릭 타입으로 할까 ? <? extends Shape> m(? t) x
	// 3. 사용할 수 있는 예
	//	-> 제네릭 클래스 MyStack의 제네릭 파라미터를 Shape의 자식으로만 하자. MyStack3<? extends Shape> o
	// 	-> 제네릭 클래스 MyStack의 제네릭 파라미터를 Shape의 부모로만 하자. MyStack3<? super Shape> o
	
	public static void drawShapeLayer(List<? extends Shape> in,  // Producer, Lower Bounded
			MyStack3<? super Drawable> out ) { // Consumer, Upper Bounded
		// 잘못된 사용
		// MyStack3<? extends Shape> s1 = new MyStack3<>(10);
		// s1.push(new Rect());
		
		for (Shape s: in) {
			out.push(s);
		}
	}
}
