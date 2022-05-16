package exams.first;
import java.util.Scanner;
public class Repetition {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean done = false;
		int count = 0;
		int total = 0;
		
		do {
			System.out.print("Enter an even number or enter '-1' to exit: ");
			int temp = in.nextInt();
			if(temp > 0) {	
				total += temp;
				count++;
			} else
				done = true;
		}while(!done);
		
		double average = (double)total / count;
		
		System.out.println("The average of your " + count + " number(s) is: " + average);
		
		in.close();
	}
}