package prob5;

public class MyStack3<T> { // 제너릭 파라미터
	private int top;
	private T[] buffer;

	@SuppressWarnings("unchecked")
	public MyStack3(int capacity) {
		top = -1;
		buffer = (T[]) new Object[capacity];
	}

	public void push(T t) {
		if (top == buffer.length - 1) {
			resize();
		}

		buffer[++top] = t;		
	}

	public T pop() throws MyStackException {
		if (isEmpty()) {
			throw new MyStackException("stack is empty");
		}

		T result = buffer[top];
		buffer[top--] = null;

		return result;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	private void resize() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[buffer.length * 2];
		for (int i = 0; i <= top; i++) {
			temp[i] = buffer[i];
		}

		buffer = temp;
	}
}