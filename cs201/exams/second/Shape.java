package exams.second;

public abstract class Shape {
	protected String name;
	
	public Shape() {
		setName("N/A");
	}
	public Shape(String name) {
		setName(name);
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public boolean equals(Shape s) {
		if(s.getName().equals(getName()))
			return true;
		return false;
	}
	public String toString() {
		return "This shape is named: " + getName() + ".";
	}
	public abstract double area();
	public abstract double perimeter();
}
