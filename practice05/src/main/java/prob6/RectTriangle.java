package prob6;
// 직각삼각형 둘레
public class RectTriangle extends Shape  {
	private double w;
	private double h;
	
	public RectTriangle(double w, double h) {
		this.w = w;
		this.h = h;
	}
	
	@Override
	public Double getPerimeter() { // 둘레
		return (w+h) + Math.sqrt((w*w + h*h));
	}
	
	@Override
	public Double getArea() { // 넓이
		return  w * h * 0.5 ;
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
