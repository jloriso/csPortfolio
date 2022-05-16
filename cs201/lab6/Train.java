package labs.lab6;

import java.util.ArrayList;

public class Train extends Trip{
private ArrayList<String> stops = new ArrayList<String>();
	
	public Train() {
		super();
	}
	public Train(String origin, String destination, double duration, ArrayList<String> stops){
		super();
		setDuration(duration);
		setOrigin(origin);
		setDestination(destination);
		setStops(stops);
	}
	public ArrayList<String> getStops() {
		return stops;
	}
	public void setStops(ArrayList<String> stops) {
		for(String stop : stops) {
			this.stops.add(stop);
		}
	}
	public boolean equals(Train t) {
		if(t.getDuration() != getDuration())
			return false;
		if(!t.getOrigin().equalsIgnoreCase(getOrigin()))
			return false;
		if(!t.getDestination().equalsIgnoreCase(getDestination()))
			return false;
		
		if(t.getStops() == getStops())
			return false;
		
		return true;
	}
	public String toString() {
		String stopString = "";
		for(String s : stops) {
			if(s != null)
				stopString += s + ",";
		}
		return "Train," + super.toString() + stopString;
	}
	public String travel() {
		String stopString = "";
		for(String s : stops) {
			if(s != null) {
				stopString += s;
				stopString += ",";
			}
		}
		return "Train from " + getOrigin() + " to " + getDestination() + " in " + getDuration() + " hours, stopping at: " + stopString;
	}
}
