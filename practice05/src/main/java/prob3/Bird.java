package prob3;

public abstract class Bird {
	// name이 부모에 있어야 함
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract void fly();

	public abstract void sing();
	
	public abstract String toString();
}