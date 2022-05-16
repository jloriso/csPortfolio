package exams.second;

public class Square extends Shape {
	private double side;
	
	public Square() {
		super();
		setSide(0);
	}
	public Square(double side) {
		super();
		setSide(side);
	}
	public void setSide(double side) {
		if(side >= 0)
			this.side = side;
	}
	public double getSide() {
		return side;
	}
	
	public boolean equals(Square s) {
		if(s.getName().equals(getName())) {
			if(s.getSide() == getSide())
				return true;
		}
		return false;
	}
	public String toString() {
		return getName() + " has a side length of: " + getSide() + ".";
	}
	
	@Override
	public double area() {
		return side * side;
	}

	@Override
	public double perimeter() {
		return 4 * side;
	}

}
