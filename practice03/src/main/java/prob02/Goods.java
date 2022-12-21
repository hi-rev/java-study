package prob02;

public class Goods {
	private String name;
	private String price;
	private String cnt;
	
	public Goods(String name, String price, String cnt) {
		this.name = name;
		this.price = price;
		this.cnt = cnt;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	
}
