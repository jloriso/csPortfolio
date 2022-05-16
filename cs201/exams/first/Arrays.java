package exams.first;

import java.util.Scanner;

public class Arrays {
	public static void main(String[] args) {
		String[] words = new String[5];
		Scanner in = new Scanner(System.in);
		
		for(int i = 0; i < 5; i++) {
			System.out.print("Input a word: ");
			words[i] = in.next();
		}
		
		String last = words[0];
		for(int i = 0; i < 4; i++) {
//			Checks if the first letter is the less than if it is thats the new last word
			if(last.toLowerCase().charAt(0) < words[i].toLowerCase().charAt(0)) {
				last = words[i];
			}
//			If the first letter was the same
			int j = 1;
			if(last.toLowerCase().charAt(j) == words[i].toLowerCase().charAt(j)) {
//				Gets the length so there isn't an out of bounds exception
				int len = last.length();
				if(len > words[i].length())
					len = words[i].length();
				
				boolean done = false;
//				Goes through every letter after the first one to see if where there is a difference
//				and to find the one that has a lesser value first which is last.
				for(int n = 0; n < len; n++) {
					if(j < len && !done) {	
						if(last.toLowerCase().charAt(j) < words[i].toLowerCase().charAt(j)) {
							last = words[i];
							done = true;
						} else
							j++;
					}
				}
			}
		}
		
		System.out.println("The last word alphabetically that you provided is: " + last);
		
		in.close();
	}
}