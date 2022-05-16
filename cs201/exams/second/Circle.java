package exams.second;

public class Circle extends Shape {

	private double radius;
	
	public Circle() {
		super();
		setRadius(0);
	}
	public Circle(double radius) {
		super();
		setRadius(radius);
	}
	public void setRadius(double radius) {
		if(radius >= 0)
			this.radius = radius;
	}
	public double getRadius() {
		return this.radius;
	}
	
	public boolean equals(Circle c) {
		if(c.getName().equalsIgnoreCase(getName())) {
			if(c.getRadius() == getRadius())
				return true;
		}
		
		return false;
	}
	public String toString() {
		return getName() + " has a radius of " + getRadius() + ".";
	}
	
	@Override
	public double area() {
		return radius * radius * 3.14;
	}

	@Override
	public double perimeter() {
		return 2 * radius * 3.14;
	}

}
