package exams.first;

public class CircleClient {

	public static void main(String[] args) {
		Circle c1 = new Circle();
		
		System.out.println(c1.getRadius());
		
		c1.setRadius(-12);
		System.out.println(c1.getRadius());
		
		c1.setRadius(3);
		System.out.println(c1.getRadius());
		
		System.out.println(c1.area());
		System.out.println(c1.circumference());
		System.out.println(c1);
		System.out.println(c1.toString());
		
		Circle c2 = new Circle(13);
		System.out.println(c1.equals(c2));
	}
}
