package prob03;

public class Money {
	private int amount;
	
	/* 코드 작성 */
	public Money(int amount) {
		this.setAmount(amount);
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Money add(Money three) {
		return new Money(amount + three.getAmount());
	}

	public Money minus(Money two) {
		return new Money(amount - two.getAmount());
	}

	public Money multiply(Money two) {
		return new Money(amount * two.getAmount());
	}

	public Money devide(Money five) {
		return new Money(amount / five.getAmount());
	}

	public boolean equals(Money m) {
		return this.amount == m.amount;
	}
}
