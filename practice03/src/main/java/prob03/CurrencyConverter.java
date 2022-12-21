package prob03;

public class CurrencyConverter {
	private static double rate; // 환율

	public static double getRate() {
		return rate;
	}

	public static void setRate(double rate) {
		CurrencyConverter.rate = rate;
	}

	public static double toDollar(double d) {
		return d / CurrencyConverter.getRate();
	}

	public static double toKRW(double d) {
		return CurrencyConverter.getRate() * d;
	}
}
