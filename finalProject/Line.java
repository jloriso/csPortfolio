import java.util.ArrayList;

public class Line {
	private ArrayList<Station> stops;
	private String color;
	final String[] colors = {"red","green","blue","brown","purple","pink","orange"};
	
	//Default constructor
	public Line() {
		stops = new ArrayList<Station>(0);
		color = "Unknown";
	}
	//non default constructor
	public Line(ArrayList<Station> stops, String color) {
		setStops(stops);
		setColor(color);
		
	}
	
//	GETTERS
	public ArrayList<Station> getStops() {
		return stops;
	}
	public String getColor() {
		return color;
	}
	
//	SETTERS
	public void setStops(ArrayList<Station> stops) {
		this.stops = stops;
	}
	public void setIndividualStop(Station stop) {
		this.stops.add(stop);
	}
	public void setColor(String color) {
		this.color = color.toLowerCase();
	}
//	TOSTRING
	public String toString() {
		String text = color + ": ";
		
		for(Station stop: stops) {
			text+= stop.toString() + "\n\n";
		}
		
		return text;
	}
//	EQUALS
	public boolean equals(Line l) {
//		if a value in this arrayList is false then they aren't equal
		ArrayList<Boolean> stopsBool = new ArrayList<Boolean>();
		if(getColor().equalsIgnoreCase(l.getColor())) {
//			If they aren't the same size they cant be the same
			if(l.getStops().size() == getStops().size()) {
				for(int i = 0; i < getStops().size(); i++) {
					stopsBool.add(l.getStops().get(i) == getStops().get(i));
				}
			}
		}
//		if not even the colors are the same
		else {
			return false;
		}
		for(boolean b : stopsBool) {
			if(!b)
				return false;
		}
		return true;
	}

//	Alternate toString method
	public String text() {
		String value = color + ":\n\t";
		
		for(int i = 0; i < stops.size(); i++) {
			value += stops.get(i).getGreen() + " - " + stops.get(i).getName() + ", \n\t";
		}
		
		return value;
	}
//	Sorting method
	public void sortStops(int index){
		boolean done = false;
		do {
			done = true;
//			continue going through the array and making neccessary swaps
//			after being able to go through without making a swap done will stay true 
//			meaning the array is sorted the algorithm finishes
			for(int j = 0; j< stops.size()-1; j++) {
				if(stops.get(j+1).getLineColor(index) < stops.get(j).getLineColor(index)) {
					swap(j, j+1);
					done = false;
				}
			}
		} while(!done);
	}
//	Private swapping method to make the sorting algorithm cleaner
//	changes the order o f 2 elements by using a temp variable
	private void swap(int first, int second) {
		Station temp = stops.get(second);
		stops.set(second, stops.get(first));
		stops.set(first, temp);
	}
//	Returns the integer value of the color based on the array of the order of the colors - if not found returns -1
	public int getIndex(String color) {
		for(int i = 0; i < colors.length; i++) {
			if(colors[i].equals(color)) {
				return i;
			}
		}
		return -1;
	}
}
