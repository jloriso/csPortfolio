package labs.lab6;

public class Drive extends Trip{
	private double gallonsOfGas;
//	Default constructor
	public Drive() {
		super();
		gallonsOfGas = 0;
	}
//	Non-default
	public Drive(String origin, String destination, double duration, double gallonsOfGas) {
		super();
		setOrigin(origin);
		setDestination(destination);
		setDuration(duration);
		setGallonsOfGas(gallonsOfGas);
	}
	public double getGallonsOfGas() {
		return gallonsOfGas;
	}
	public void setGallonsOfGas(double gas) {
		if(gas >= 0)
			gallonsOfGas = gas;
	}
	
	public boolean equals(Drive d) {
		if(!getOrigin().equalsIgnoreCase(d.getDestination()))
			return false;
		if(!d.getDestination().equalsIgnoreCase(getDestination()))
			return false;
		if(d.getDuration() != getDuration())
			return false;
		if(d.getGallonsOfGas() != getGallonsOfGas())
			return false;
		return true;
	}
	public String toString() {
		return "Drive," + super.toString() + "," + getGallonsOfGas();
	}
	public String travel() {
		return "Driving from " + getOrigin() + " to " + getDestination() 
			+ " in " + getDuration() + " hours taking "
			+ getGallonsOfGas() + " gallon(s) of gas.";
	}

}
