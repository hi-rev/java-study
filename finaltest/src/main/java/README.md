## hashCode와 equals 재정의

### Java hash code란?

Object의 **hashCode()** 메소드는 객체의 메모리 번지를 이용해서 해시 코드를 만들어 리턴한다.
따라서 객체의 메모리 번지를 이용해서 해시코드를 만들어 리턴하기 때문에 해시코드는 객체마다 다른 값을 가지고 있다. 

Collection의 ``HashSet, HashMap, HashTable`` 은 다음과 같은 과정을 통하여 내부적으로 객체가 동등한지 비교한다.
1. **hashCode()** 메소드를 실행하서 리턴된 해시 값이 같은지 본다.
2. 해시 코드 값이 같다면 **equals()** 메소드로 다시 비교한다.

### ``prob01``

``GugudanApp.java``에서 선언한 **Set의 타입**이 **Gugudan 객체**이기 때문에, Gugudan의 hashCode와 equals 메소드 재정의를 통해 원하는 답에 도달할 수 있다. 

우리가 원하는 것은 예를 들어, Gugudan(1, 9) 객체와 Gugudan(3, 3) 객체가 Set에서 동일한 객체로 정의되어 중복에서 제거되어야 한다. (1 x 9 = 9, 3 x 3 = 9로 곱셈 값이 같기 때문)

따라서 ``Gugudan.java`` 에서 hashCode()와 equals()를 오버라이드하여 재정의 해주자.

```
@Override
	public int hashCode() {
		return Objects.hash(lValue*rValue);
	}
```
먼저, hashCode()는 생성자에서 파라미터로 받아온 IValue와 rValue를 곱한 값을 해시값으로 재정의 해준다.

```
@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gugudan other = (Gugudan) obj;
		return lValue * rValue == other.rValue * other.lValue;
	}
```
그리고, equals() 메소드의 리턴값 또한 위와 같이 정의해준다. lValue와 rValue의 곱이 같다면 true를 return 한다.

이렇게 재정의 하면, 객체에 파라미터로 들어온 두 값의 곱이 같을때 HahSet에서 중복이 제거될 수 있다.

### ``prob03``
``MoneyTest.java``는 앞서 prob01과 마찬가지로 equals 메서드 재정의를 통해 해결할 수 있다. 객체의 amount 변수의 값이 동일해야 하기 때문에, 객체의 amount 값을 비교하도록 재정의 해주었다.

```
@Override
	public int hashCode() {
		return Objects.hash(amount);
	}
```
따라서 hashCode()는 amount에 대한 해시값으로 return 하도록 재정의하고,

```
@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return amount == other.amount;
	}
```
equals() 또한 amount를 비교하도록 재정의 해주면 된다!

[Reference]

- https://jisooo.tistory.com/entry/java-hashcode%EC%99%80-equals-%EB%A9%94%EC%84%9C%EB%93%9C%EB%8A%94-%EC%96%B8%EC%A0%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B3%A0-%EC%99%9C-%EC%82%AC%EC%9A%A9%ED%95%A0%EA%B9%8C
