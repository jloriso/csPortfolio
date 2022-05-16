package exams.second;

import java.util.Scanner;

public class BinarySearch {
	public static int binarySearch(int[] array, int start, int end, int search) {
		if(start >= end) {
			return -1;
		}
		int middle = (start+end)/2;
		
		if(array[middle] > search)
			return binarySearch(array, start, middle, search);
		else if(array[middle] < search)
			return binarySearch(array, middle+1, end, search);
		else
			return middle;
	}
	
	public static void main(String[] args) {
		int[] nums = {8, 13, 18, 24, 31, 64, 65, 71, 73, 87};
		Scanner in = new Scanner(System.in);
		
		
		System.out.print("What number would you like search for: ");
		int search = in.nextInt();
		
		int foundIndex = binarySearch(nums, 0,nums.length-1,search);
		
		if(foundIndex > 0)
			System.out.println("Found '" + nums[foundIndex] + "' at: " + foundIndex);
		else
			System.out.println("Error finding: '" + search + "'");
		in.close();
	}
}
