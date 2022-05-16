package exams.second;

public class Bubble {
	public static String[] bubbleSort(String[] words) {
		boolean done = false;

		do 
		{
			done = true;
			for(int i = 0; i < words.length-1; i++) 
			{
				if(words[i].compareTo(words[i+1]) > 0)
				{
					String temp = words[i+1];
					words[i+1] = words[i];
					words[i] = temp;
					
					done = false;
				}
			}
		} while(!done);
		
		return words;
	}
	
	public static void main(String[] args) {
		String[] words = {"briefly", "mugwump", "articulation", "sync", "skein", "moire", "advisability", "varmint", "mandibular", "evergreen"};
		
		words = bubbleSort(words);
		
		for(String word: words) {
			System.out.println(word);
		}
	}
}
