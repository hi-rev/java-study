package prob6;
// 직사각형 둘레
public class Rectangle extends Shape implements Resizable  {
	private double w;
	private double h;
	
	public Rectangle(double w, double h) { // 생성자
		this.w = w;
		this.h = h;
	}
	
	@Override
	public Double getPerimeter() {
		return (w+h) * 2.0;
	}
	
	@Override
	public Double getArea() {
		return (double) (w * h);
	}
	
	@Override
	public void resize(double s) {
		w *= s;
		h *= s;
	}
	
	public double getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public double getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
}
