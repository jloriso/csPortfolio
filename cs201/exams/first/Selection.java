package exams.first;
import java.util.Scanner;
public class Selection {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int input = 0;
		
		System.out.print("Input an integer: ");
		input = in.nextInt();
		
		if((input % 3) == 0)
			System.out.print("foo");
		if((input % 5) == 0)
			System.out.print("bar");
		
		in.close();
	}
}
