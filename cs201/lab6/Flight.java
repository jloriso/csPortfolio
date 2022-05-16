package labs.lab6;

public class Flight extends Trip{
//	Instance variables
	private boolean meals;
//	Default constructor
	public Flight(){
		super();
		meals = false;
	}
//	Non-default constructor
	public Flight(String origin, String destination, double duration, boolean meals){
		setDuration(duration);
		setOrigin(origin);
		setDestination(destination);
		this.meals = meals;
	}
//  Accessor method
	public boolean hasMeals() {
		return meals;
	}
//	Mutator method
	public void setMeals(boolean meals) {
		this.meals = meals;
	}
//	ToString method
	public String toString() {
		return "Flight," + super.toString() + "," + ((hasMeals())? hasMeals():"");
	}
	public String travel() {
		return "Flying from " + getOrigin() + " to " + getDestination() + " in " + getDuration() + " hours" + ((hasMeals())?" with meals":" without any meals") ;
	}
//	Equals method
	public boolean equals(Flight f) {
		if(!getDestination().equalsIgnoreCase(f.getDestination()))
			return false;
		if(!getOrigin().equalsIgnoreCase(f.getOrigin()))
			return false;
		if(getDuration() != f.getDuration())
			return false;
		if(!hasMeals() && !f.hasMeals())
			return false;
		
		return true;
	}
}
