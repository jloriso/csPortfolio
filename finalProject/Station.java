import java.util.ArrayList;

public class Station extends Point{
	private String name;
	private String description;
	private boolean handicap;
	int[] lineColors = new int[7];
	final public String[] colors = {"red","green","blue","brown","purple","pink","orange"};
	
	public Station() {
		super();
		setName("N/A");
		setDescription("elevated");
		setHandicap(false);
		setRed(-1);
		setGreen(-1);
		setBlue(-1);
		setBrown(-1);
		setPurple(-1);
		setPink(-1);
		setOrange(-1);
	}
	
	public Station(String name, double lat, double log, String description, boolean handicap, int red, int green, int blue, int brown, int purple, int pink, int orange) {
		super();
		setName(name);
		setLat(lat);
		setLog(log);
		setDescription(description);
		setHandicap(handicap);
		setRed(red);
		setGreen(green);
		setBlue(blue);
		setBrown(brown);
		setPurple(purple);
		setPink(pink);
		setOrange(orange);
	}
	public Station(String name, double lat, double log, String description, boolean handicap, ArrayList<Integer> lines) {
		setName(name);
		setLat(lat);
		setLog(log);
		setDescription(description);
		setHandicap(handicap);
		setLines(lines);
	}
	public String getColorName(int index) {
		return colors[index];
	}
	public String[] getColors() {
		return colors;
	}
	public void setLines(ArrayList<Integer> lines) {
		for(int i=0; i< lines.size(); i++) {
			lineColors[i] = lines.get(i);
		}
	}
	public int[] getLines() {
		return lineColors;
	}
	public int getLineColor(int index) {
		return lineColors[index];
	}
	public void setLineColor(int indexColor, int index) {
		lineColors[indexColor] = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean getHandicap() {
		return handicap;
	}
	public void setHandicap(boolean handicap) {
		this.handicap = handicap;
	}
	public int getRed() {
		return lineColors[0];
	}
	public void setRed(int red) {
		if(red >= -1)
			this.lineColors[0] = red;
	}
	public int getGreen() {
		return lineColors[1];
	}
	public void setGreen(int green) {
		if(green >= -1)
			this.lineColors[1] = green;
	}
	public int getBlue() {
		return lineColors[2];
	}
	public void setBlue(int blue) {
		if(blue >= -1)
			this.lineColors[2] = blue;
	}
	public int getBrown() {
		return lineColors[3];
	}
	public void setBrown(int brown) {
		if(brown >= -1)
			this.lineColors[3] = brown;
	}
	public int getPurple() {
		return lineColors[4];
	}
	public void setPurple(int purple) {
		if(purple >= -1)
			this.lineColors[4] = purple;
	}
	public int getPink() {
		return lineColors[5];
	}
	public void setPink(int pink) {
		if(pink >= -1)
			this.lineColors[5] = pink;
	}
	public int getOrange() {
		return lineColors[6];
	}
	public void setOrange(int orange) {
		if(orange >= -1)
			this.lineColors[6] = orange;
	}
	public String toString() {
		String text =  name + " at " + coordinate() + " is a(n) " + description + " station, is ";
		
		//Checks if the stop is handicap accessible and adds proper verbage based on it
		if(!handicap)
			text += "not ";
		text += "handicap accessible, and is";
		
		//used to check if the stop is the first found one so commas are formatted properly
		boolean first = true;
		
		//checks what lines the station is on
		//if the value is greater than 0 it has to be on the line so if greater than 0 the text is added and all colored lines are gone through
		if(getRed() >= 0) {
			if(!first)
				text += ", ";
			else
				first = false;
			text += "\n\tstop number " + (getRed()+1) + " on the red line";
		}
		if(getGreen() >=0) {
			if(!first)
				text += ", ";
			else
				first = false;
			text += "\n\tstop number " + (getGreen()+1) + " on the green line";
		}
		if(getBlue() >= 0) {
			if(!first)
				text += ", ";
			else
				first = false;
			text += "\n\tstop number " + (getBlue()+1) + " on the blue line";
		}
		if(getBrown() >= 0) {
			if(!first)
				text += ", ";
			else
				first = false;
			text += "\n\tstop number " + (getBrown()+1) + " on the brown line";
		}
		if(getPurple() >= 0) {
			if(!first)
				text += ", ";
			else
				first = false;
			text += "\n\tstop number " + (getPurple()+1) + " on the purple line";
		}
		if(getPink() >= 0) {
			if(!first)
				text += ", ";
			else
				first = false;
			text += "\n\tstop number " + (getPink()+1) + " on the pink line";
		}
		if(getOrange() >= 0) {
			if(!first)
				text += ", ";
			else
				first = false;
			text += "\n\tstop number " + (getOrange()+1) + " on the orange line";
		}
		
		text += ".";
		
		return text;
	}
	public ArrayList<String> getLineColors(){
		ArrayList<String> colors = new ArrayList<String>();
		
		if(getRed() > 0)
			colors.add("red");
		if(getGreen() > 0)
			colors.add("green");
		if(getBlue() > 0)
			colors.add("blue");
		if(getBrown() > 0)
			colors.add("brown");
		if(getPurple() > 0)
			colors.add("purple");
		if(getPink() > 0)
			colors.add("pink");
		if(getOrange() > 0)
			colors.add("Orange");
		
		return colors;
	}
	public String toFile(){
		String stringReturn = this.getName() + "," + this.getLat() + "," + this.getLog() + "," +
							this.getDescription() + "," + Boolean.toString(this.getHandicap()).toUpperCase() + ",";
		
		for (int i = 0; i < this.lineColors.length; i++) {
            stringReturn += this.lineColors[i] + ",";
        }
        return stringReturn;
	}
}
