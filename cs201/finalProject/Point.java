package project;

public class Point {
	private double lat;
	private double log;
//	Default constructor
	public Point(){
		setLat(0);
		setLog(0);
	}
//	Non default
	public Point(double lat, double log){
		setLat(lat);
		setLog(log);
	}
//	Getters
	public double getLat() {
		return lat;
	}
	public double getLog() {
		return log;
	}
//	Setters
	public void setLat(double lat){
		//Checks if the point is on the globe
		if(lat >= -90 && lat <= 90)
			this.lat = lat;
	}
	public void setLog(double log) {
		//Checks if the point is on the globe
		if(log >= -180 && log <= 180)
			this.log = log;
	}
//	TOSTRING
	public String toString() {
		String latitude, longitude;
		
		//Checking if the lat and log are positive and therefore if they are N or S and E or W
		if(getLat() >= 0)
			latitude = "N";
		else
			latitude = "S";
		if(getLog() >= 0)
			longitude = "E";
		else
			longitude = "W";
		
		return Math.abs(lat) + " degrees " + latitude + ", "+ Math.abs(log) 
			+ " degrees " + longitude + ".";
	}
//	alternate toString, returns in a shorter easier to read fashion using []
	public String coordinate() {
		return "[" + getLat() + ", " + getLog() + "]";
	}
//	EQUALS method
	public boolean equals(Point p) {
		//Checking if the lat and log of the given point
		//equal the instance's lat and log
		if(p.getLat() == getLat()) {
			if(p.getLog() == getLog()) {
				return true;
			}
		}
		return false;
	}
//	Two different ways to calculate the difference between two points using the haversins formula
//	one using the doubles the other using another point then calling the other method with the doubles
	public double calcDifference (Point p) {
		return calcDifference(p.getLat(), p.getLog());
	}
	public double calcDifference (double lat2, double log2) {
		//Converting the objects lat and log to radians
		double dLog = Math.toRadians(log2 - getLog());
		double dLat = Math.toRadians(lat2 - getLat());
		
		double latRad1 = Math.toRadians(getLat());
		double latRad2 = Math.toRadians(lat2);

//		Haversine formula
		double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLog / 2), 2) *
                Math.cos(latRad1) *
                Math.cos(latRad2);
		//Radius of earth in km
		double rad = 6371.07103;
		double c = 2 * Math.asin(Math.sqrt(a));

		return (c*rad);
	}
}
