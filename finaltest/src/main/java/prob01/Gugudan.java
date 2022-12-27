package prob01;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Gugudan {
	private int lValue;
	private int rValue;
	
	public Gugudan(int lValue, int rValue) {
		this.lValue = lValue;
		this.rValue = rValue;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(lValue, rValue);
	}

	// 본래 Java에서 equals 메소드는 동등성 비교를 위한 것
	// 여기에서는 구구단값의 동일성을 비교한다.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gugudan other = (Gugudan) obj;
		return lValue * other.rValue == rValue * other.lValue;
	}
	
	@Override
	public String toString() {
		return "Gugudan [lValue=" + lValue + ", rValue=" + rValue + "]";
	}
}
