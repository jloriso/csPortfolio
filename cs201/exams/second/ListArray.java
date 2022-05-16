package exams.second;

import java.util.ArrayList;
import java.util.Scanner;

public class ListArray {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<String> words = new ArrayList<String>();
		
		boolean done = false;
		
		while(!done) {
			System.out.println("Enter a word or enter 'Done' to exit:");
			String word = in.nextLine();
			
			if(word.equalsIgnoreCase("done"))
				done = true;
			else
				words.add(word);
		}
		
		ArrayList<String> firstLetter = new ArrayList<String>();
		
		for(String word : words) {
			firstLetter.add(word.substring(0,1));
		}
		
		String[] letter = new String[26];
		int[] lettersCount = new int[26];
		
		letter[0] = "a";
		letter[1] = "b";
		letter[2] = "c";
		letter[3] = "d";
		letter[4] = "e";
		letter[5] = "f";
		letter[6] = "g";
		letter[7] = "h";
		letter[8] = "i";
		letter[9] = "j";
		letter[10] = "k";
		letter[11] = "l";
		letter[12] = "m";
		letter[13] = "n";
		letter[14] = "o";
		letter[15] = "p";
		letter[16] = "q";
		letter[17] = "r";
		letter[18] = "s";
		letter[19] = "t";
		letter[20] = "u";
		letter[21] = "v";
		letter[22] = "w";
		letter[23] = "x";
		letter[24] = "y";
		letter[25] = "z";
		
		for(int i = 0; i < firstLetter.size(); i++) {
			for(int j = 0; j < letter.length; j++) {
				if(firstLetter.get(i).equalsIgnoreCase(letter[j])) {
					lettersCount[j]++;
				}
			}
		}
		
		for(int i = 0; i < letter.length; i++) {
			if(lettersCount[i] != 0) {
				System.out.println(letter[i].toUpperCase() + " - " + lettersCount[i]);
			}
		}
		
		in.close();
	}
}
