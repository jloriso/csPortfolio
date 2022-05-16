package exams.first;

public class Circle {
	private double radius;
	private final double pi = 3.14;
	public Circle() {
		radius = 1.0;
	}
	public Circle(double r) {
		setRadius(r);
	}
	public void setRadius(double r) {
		if(r>0)
			radius = r;
	}
	public double getRadius() {
		return radius;
	}
	public String toString() {
		return "The radius of your circle is " + radius;
	}
	public boolean equals(Circle c) {
		return c.getRadius() == getRadius();
	}
	public double area() {
		return pi * (getRadius() * getRadius());
	}
	public double circumference() {
		return 2 * pi * getRadius();
	}
}
