
public abstract class Trip {
//	Instance variables
	private double duration;
	protected String origin;
	protected String destination;
//	Default constructor
	public Trip() {
		duration = 0;
		origin = "IIT";
		destination = "IIT";
	}
//	Non-default constructor
	public Trip(double duration, String origin, String destination) {
		setDuration(duration);
		setOrigin(origin);
		setDestination(destination);
	}
//	Mutator methods
	public void setDuration(double duration) {
		if(duration >= 0)
			this.duration = duration;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
//	Accessor methods
	public String getDestination() {
		return destination;
	}
	public String getOrigin() {
		return origin;
	}
	public double getDuration() {
		return duration;
	}

	public boolean equals(Trip t) {
		if(!destination.equalsIgnoreCase(t.getDestination()))
			return false;
		if(!origin.equalsIgnoreCase(t.getOrigin()))
			return false;
		if(duration != t.getDuration())
			return false;
		
		return true;
	}
	
	public String toString() {
		return origin + "," + destination + "," + duration;
	}
	public abstract String travel();
}
