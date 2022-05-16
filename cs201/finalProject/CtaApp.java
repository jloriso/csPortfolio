package project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CtaApp {
	public static void saveData(ArrayList<Station> allStations) throws IOException {
		try {
			FileWriter out = new FileWriter("src/project/CTAStops.csv");
			//Writing the first line to the file
			String firstLine = "Name,Latitude,Longitude,Description,Wheelchair,Red:33,Green:28,Blue:33,Brown:27,Purple:27,Pink:22,Orange:16";
			out.write(firstLine + "\n");
			out.flush();
			//Writing the stations to file
			for (int i = 0; i < allStations.size(); i++) {
				out.write(allStations.get(i).toFile() + "\n");
				out.flush();
			}
			out.close();
		} catch (Exception e) {
			System.out.println("Error saving data");
		}
	}
	//Reading in the initial file
	public static ArrayList<Station> readExistingFile(){
		
		ArrayList<Station> stations = new ArrayList<Station>();
		
		try {
			File stops = new File("src/project/CTAStops.csv");
			Scanner input = new Scanner(stops);
			while(input.hasNextLine()) {
				try {
					String line = input.nextLine();
					Station e = null;
					//Seperate the line into the individual components
					String[] wholeLine = line.split(",");
					//Checking if it is the first line of the file
					if(!wholeLine[0].equalsIgnoreCase("name")) {
						e = new Station(wholeLine[0],
								Double.parseDouble(wholeLine[1]),Double.parseDouble(wholeLine[2]),
								wholeLine[3],Boolean.parseBoolean(wholeLine[4]),Integer.parseInt(wholeLine[5]),
								Integer.parseInt(wholeLine[6]), Integer.parseInt(wholeLine[7]),
								Integer.parseInt(wholeLine[8]),Integer.parseInt(wholeLine[9]),
								Integer.parseInt(wholeLine[10]),Integer.parseInt(wholeLine[11]));
					}
					if(e != null)
						stations.add(e);
				} catch(Exception e) {
					System.out.println("Error while reading file");
				}
			}
			input.close();
		} catch(Exception e){
			System.out.println("Error");
		}
		
		return stations;
	}
	//Sorting algorithm for the arrayList of ALL station - sorted in alphabetical order
	public static ArrayList<Station> sortStation(ArrayList<Station> allStations){
		for(int i = 0; i < allStations.size(); i++) {
			int j = i;
			while(j > 0 && allStations.get(j-1).getName().compareTo(allStations.get(j).getName()) > 0) {
				//Swapping the values if the second one is smaller
				Station temp = allStations.get(j);
				allStations.set(j, allStations.get(j-1));
				allStations.set(j-1, temp);
				j--;
			}
		}
		return allStations;
	}
	//Algorithm to place the stations on the lines they are supposed to be on
	public static ArrayList<Line> makeLines(ArrayList<Station> allStations){
		ArrayList<Line> allLines = new ArrayList<Line>();
		//Going through every station in the allStations arrayList
		//First checks they first element of allStations that has the order of the colors so that it iterates the number of lines there are
		//then sets the name for that line
		//then for every line will loop through ALL stations
		//retrieves the lines that the station is on
		//loops through those values and if not -1 then it is added to the line - to be sorted later
		for(int j = 0; j < allStations.get(0).getColors().length; j++) {
			Line e = new Line();
			allLines.add(e);
			allLines.get(j).setColor(allStations.get(0).getColorName(j));
			for(int i = 0; i < allStations.size(); i++) {
				// gets the lines that the station is on
				int[] stationColors = allStations.get(i).getLines();
				// if the station is on the lines then add it ie number greater than 0
				if(stationColors[j] >= 0) {
					allLines.get(j).setIndividualStop(allStations.get(i));
				}
			}
		}
		return allLines;
	}
	//Uses haversines formula to find the nearest station to the users lat and log
	//Will ask the user for their lat and log with proper try catch in place
	//then uses the calcDifference method from the point class to calculate
	public static void findNearestStation(ArrayList<Station> allStations, Scanner in) {
		boolean done = false;
		double lat = 0, log = 0;
		do {
			try {
				System.out.print("Enter your latitude: ");
				String latStr = in.nextLine();
				lat = Double.parseDouble(latStr);
				System.out.print("Enter your longitude: ");
				String logStr = in.nextLine();
				log = Double.parseDouble(logStr);
				if((log >= -180 && log <= 180) && (lat >= -90 && lat <= 90)) {
					done = true;
				} else {
					System.out.println("Your latitude and/or longitude were invalid.");
				}
			} catch(Exception e) {
				System.out.println("Invalid input");
			}
		}while(!done);
		
		double difference = 0;
		int closestStation = -1;
		for(int i = 0; i < allStations.size(); i++) {
			double newDifference = allStations.get(i).calcDifference(lat, log);
			if(i == 0 || newDifference < difference) {
				difference = newDifference;
				closestStation = i;
			}
		}
		difference = difference * 10000;
		difference = Math.round(difference);
		difference = difference / 10000;
		
		System.out.println();
		System.out.println("The closest station is: " + allStations.get(closestStation).getName() + " at " + difference + " km(s) away.");
		System.out.println();
	}
//	method to search for stations
//	has String search as an input incase the user already has entered the station they are looking for
//	if that string is empty the program will ask them the station
//	then the program will increment through the allStations arrayList and add any found matches to an arrayList
//	if that arrayList only has a size of 1 then that station will return
//	if the arrayList has size 0 then the user is told the station couldn't be found
//	if the arrayList has size greater than 1 then another method is called for clarification
	public static int searchStation(ArrayList<Station> allStations, Scanner in, String search) {
		boolean done = false;
		String find = "";
		do {
			try {
				//For methods that already asked what the user was looking for
				if(!(search.length() > 0)) {
					System.out.print("Enter the station you are looking for: ");
					find = in.nextLine();
				}
				else {
					find = search;
				}
				done = true;
			} catch(Exception e) {
				System.out.println("Please try again.");
			}
		} while(!done);
		
		//Go through all and add the ones that are the searched word
		ArrayList<Station> found = new ArrayList<Station>();
		ArrayList<Integer> index = new ArrayList<Integer>();
		
		for(int i = 0; i < allStations.size(); i++) {
			if(find.equalsIgnoreCase(allStations.get(i).getName())) {
					found.add(allStations.get(i));
					index.add(i);
			}
		}
		
		if(found.size() == 1) {
			System.out.println("\nFound - " + find.toUpperCase() + "\n" + allStations.get(index.get(0)).toString() + "\n");
			return index.get(0);
		} 
		else if(found.size() > 1) {
			int clarify = specifySearch(found, in);
			System.out.println("\nFound - " + find.toUpperCase() + "\n" + allStations.get(index.get(clarify)).toString() + "\n");
			return index.get(clarify);
		} 
		else {
				System.out.println("Unable to find station");
				return -1;
			}
		
		
	}
//	Method to clarify the search if a station was asked for and has multiple iterations
//	Will print the found stations with a number and ask for the user to enter a number
//	has proper try catch to ensure the user enters an actual number
//	returns the value they entered
	public static int specifySearch(ArrayList<Station> found, Scanner in) {
		boolean done = false;
		int index = -1;
		do {
			try {
				System.out.println("Please clarify the station you were looking for: \n");
				for(int i = 0; i < found.size(); i++) {
					System.out.println(i+1 + ") " + found.get(i).toString());
				}
				
				String input = in.nextLine();
				//Subtracts one so it is the proper index
				index = Integer.parseInt(input)-1;
				
				done = true;
			} catch(Exception e) {
				System.out.println("Try again");
			}
		} while(!done);
		
		return index;
	}
//	Method to search with 5 different requirements that the user can pick
//	Ensures the user picked an actual requirement and an actual station and then will increment through the arrayList
//	if 1 instance is found it is shown to the user
//	if 0 instances are found then the user is notified it could not be found
//	if more than 1 instances are found then the specifySearch method is utilized in order to get specification
	public static int searchWithRequirements(ArrayList<Station> allStations, Scanner in) {
		System.out.println("What requirement do you want: "
						+ "\n\t1) Handicap accessible"
						+ "\n\t2) Not handicap accessible"
						+ "\n\t3) Station is elevated"
						+ "\n\t4) Station is underground"
						+ "\n\t5) Station is at ground level");
		boolean done = false;
		int req = 0;
		do {
			try {
				String input = in.nextLine();
				req = Integer.parseInt(input);
				if(req <= 5 && req >=1)
					done = true;
				else
					System.out.println("Try again");
			} catch(Exception e) {
				System.out.println("Try again");
			}
		} while(!done);
		
		done = false;
		String find = "";
		do {
			try {
				System.out.print("Enter the station you are looking for: ");
				find = in.nextLine();
				done = true;
			} catch(Exception e) {
				System.out.println("Please try again.");
			}
		} while(!done);
		
		//Go through all and add the ones that are the searched word
		ArrayList<Station> found = new ArrayList<Station>();
		int index = -1;
		
		for(int i = 0; i < allStations.size(); i++) {
			if(find.equalsIgnoreCase(allStations.get(i).getName())) {
				if(req == 1 && allStations.get(i).getHandicap()) {
					found.add(allStations.get(i));
					index = i;
				}
				else if(req == 2 && !(allStations.get(i).getHandicap())) {
					found.add(allStations.get(i));
					index = i;
				}
				else if(req == 3 && (allStations.get(i).getDescription().toLowerCase().indexOf("elevated") != -1)) {
					found.add(allStations.get(i));
					index = i;
				}
				else if(req == 4 && (allStations.get(i).getDescription().toLowerCase().indexOf("subway") != -1)) {
					found.add(allStations.get(i));
					index = i;
				}
				else if(req == 5 && (allStations.get(i).getDescription().toLowerCase().indexOf("surface") != -1)) {
					found.add(allStations.get(i));
					index = i;
				}
			}
		}
		
		if(found.size() == 1) {
			System.out.println("\nFound - " + find.toUpperCase() + "\n" + allStations.get(index).toString() + "\n");
			return index;
		} else if(found.size() > 1) {
			index = specifySearch(found, in);
			if(index != -1) {
				System.out.println("\nFound - " + find.toUpperCase() + "\n" + found.get(index).toString() + "\n");
				return index;
			} else {
				System.out.println("Unable to find station");
				return -1;
			}
		} else {
			System.out.println("Unable to find station");
			return -1;
		}
		
		
	}
//	asks the user what attribute they want to change within the station
//	the program was already asked what station they want to edit and searched for it
//	once the user has entered an acceptable input the editAttribute method is called in order to actually edit the attribute
	public static Station editStation(ArrayList<Station> allStations, ArrayList<Line> allLines, int index, Scanner in){
		System.out.println("What do you want to change within the station:"
						+ "\n\t1) Name"
						+ "\n\t2) Latitude"
						+ "\n\t3) Longitude"
						+ "\n\t4) Description"
						+ "\n\t5) Handicap accessibility"
						+ "\n\t6) Red line number"
						+ "\n\t7) Green line number"
						+ "\n\t8) Blue line number"
						+ "\n\t9) Brown line number"
						+ "\n\t10) Purple line number"
						+ "\n\t11) Pink line number"
						+ "\n\t12) Orange line number");
		boolean done = false;
		int change = -1;
		do {
			try {
				String input = in.nextLine();
				change = Integer.parseInt(input);
				if(change <= 12 && change >= 1)
					done = true;
				else
					System.out.println("Try again");
			} catch(Exception e) {
				System.out.println("Try again");
			}
		} while(!done);
		
		Station edit = allStations.get(index);
		
		edit = editAttribute(edit, allLines, change, in);
		
		System.out.println("\tCHANGED STATION: \n" + edit);
		
		return edit;
	}
//	Uses switch case method to edit the attribute the user wanted
//	will call another method for most cases and then get the station returned and send that value back to the initial algorithm
	public static Station editAttribute(Station edit, ArrayList<Line> allLines, int change, Scanner in) {
		switch(change) {
			case 1: //Name
				System.out.print("Enter the station's new name: ");
				String name = in.nextLine();
				edit.setName(name);
				break;
			case 2: //Latitude
				edit = changeLat(edit, in);
				break;
			case 3: //Longitude
				edit = changeLog(edit, in);
				break;
			case 4: //Description
				System.out.print("Enter the station's new description: ");
				String description = in.nextLine();
				edit.setDescription(description);
				break;
			case 5: // Handicap
				edit = changeHandi(edit, in);
				break;
			case 6: //Red
				edit = changeLineNum(edit, in, 0, allLines);
				break;
			case 7: //Green
				edit = changeLineNum(edit, in, 1, allLines);
				break;
			case 8: //Blue
				edit = changeLineNum(edit, in, 2, allLines);
				break;
			case 9: //Brown
				edit = changeLineNum(edit, in, 3, allLines);
				break;
			case 10: //Purple
				edit = changeLineNum(edit, in, 4, allLines);
				break;
			case 11: //Pink
				edit = changeLineNum(edit, in, 5, allLines);
				break;
			case 12: //Orange
				edit = changeLineNum(edit, in, 6, allLines);
				break;
		}
		return edit;
	}
//	Changes the latitude of the station
	public static Station changeLat(Station edit, Scanner in) {
		boolean done = false;
		do {
			try {
				System.out.print("Enter the station's new latitude: ");
				String input = in.nextLine();
				double lat = Double.parseDouble(input);
				edit.setLat(lat);
				//Check if the value they provided was a valid value
				if(edit.getLat() == lat) {
					done = true;
				}
				else {
					System.out.println("Try again");
				}
			} catch(Exception e) {
				System.out.println("Try again");
			}
		} while(!done);
		return edit;
	}
//	Changes the longitude of a station
	public static Station changeLog(Station edit, Scanner in) {
		boolean done = false;
		do {
			try {
				System.out.print("Enter the station's new longitude: ");
				String input = in.nextLine();
				double log = Double.parseDouble(input);
				edit.setLog(log);
				//Check if the value they provided was a valid value
				if(edit.getLog() == log) {
					done = true;
				}
				else {
					System.out.println("Try again");
				}
			} catch(Exception e) {
				System.out.println("Try again");
			}
		} while(!done);
		
		return edit;
	}
//	Asks the user for a 1 or a 0 for whether or not the station is handicap accessible and changes it
	public static Station changeHandi(Station edit, Scanner in) {
		System.out.println("Enter a number: \n\t1) Handicap accessible \n\t2) Not handicap accessible");
		boolean done = false;
		do {
			try {
				String input = in.nextLine();
				int han = Integer.parseInt(input);
				if(han == 1) {
					edit.setHandicap(true);
					done = true;
				}
				else if(han == 2) {
					edit.setHandicap(false);
					done = true;
				}
				else {
					System.out.println("Try again");
				}
			} catch(Exception e) {
				System.out.println("Try again");
			}
		} while(!done);
		
		return edit;
	}
	public static Station changeLineNum(Station edit, Scanner in, int colorIndex, ArrayList<Line> allLines) {
		System.out.print("What do you want the new value of the " + edit.getColorName(colorIndex).toUpperCase() 
					+ " LINE to be for " + edit.getName() + " station: ");
		boolean done = false;
		do {
			try {
				String input = in.nextLine();
				int stationNum = Integer.parseInt(input);
				//Validating stationNum
				if(stationNum == -1 || (stationNum >= 1 && stationNum <= allLines.get(colorIndex).getStops().size())){
					//For removing the station from the line
					if(stationNum == -1) {
						ArrayList<Station> indvLine = allLines.get(colorIndex).getStops();
						for(int i = edit.getLineColor(colorIndex); i < indvLine.size()-1; i++) {
							indvLine.set(i, indvLine.get(i+1));
							indvLine.get(i+1).setLineColor(colorIndex, indvLine.get(i+1).getLineColor(colorIndex)-1);
						}
						edit.setLineColor(colorIndex, stationNum);
						//Removing the last item that was moved up one
						indvLine.remove(indvLine.size()-1);
						done = true;
					}
//					For a station that wasn't previously on the line
					else if(stationNum > 0 && edit.getLineColor(colorIndex) == -1) {
						//Converting to index numbers
						stationNum--;
						
						ArrayList<Station> updated = incrementUp(allLines.get(colorIndex).getStops(), edit, stationNum, colorIndex);
						edit.setLineColor(colorIndex, stationNum);
						allLines.get(colorIndex).setStops(updated);
						done = true;
					}
					//For moving the station within the line
					else if(stationNum > 0 && stationNum <= allLines.get(colorIndex).getStops().size()){
						//Convert to index
						stationNum--;
						//If the station is moving up
						if(stationNum < edit.getLineColor(colorIndex)) {
							ArrayList<Station> indvLine = allLines.get(colorIndex).getStops();
							for(int i = edit.getLineColor(colorIndex)-1; i >= stationNum; i--) {
								indvLine.set(i+1, indvLine.get(i));
								indvLine.get(i+1).setLineColor(colorIndex, indvLine.get(i+1).getLineColor(colorIndex)+1);
							}
							edit.setLineColor(colorIndex, stationNum);
							indvLine.set(stationNum, edit);
							allLines.get(colorIndex).setStops(indvLine);
							done = true;
						}
						
						//If the station is moving down
						else if(stationNum > edit.getLineColor(colorIndex)) {
							ArrayList<Station> indvLine = allLines.get(colorIndex).getStops();
							ArrayList<Station> updated = incrementUp(indvLine, edit, stationNum, colorIndex);
							edit.setLineColor(colorIndex, stationNum);
							allLines.get(colorIndex).setStops(updated);
							done = true;
						}
						
						done = true;
					}
					else {
						System.out.println("Error your value must be -1 or between 1-" + allLines.get(colorIndex).getStops().size() + ".");
					}
				}
				else {
					System.out.println("Error your value must be -1 or between 1-" + allLines.get(colorIndex).getStops().size() + ".");
				}
			} catch(Exception e) {
				System.out.println("Error your value must be -1 or between 1-" + allLines.get(colorIndex).getStops().size() + ".");
			}
		} while(!done);
		
		return edit;
	}
	
//	Guides the user through entering the various parts of a station
//	Name, lat, log, description, handicap, and various line stops
//	then creates the new station using a constructor and returns that value for it to be added to the correct lines and allStations arrayLists
	public static Station createStation(ArrayList<Line> allLines, Scanner in) {
		//Set name
		System.out.print("Enter the name of the new station: ");
		String name = in.nextLine();
		//Set latitude
		boolean done = false;
		double lat = -1, log = -1;
		System.out.print("Enter the latitude of the new station: ");
		do {
			try {
				String input = in.nextLine();
				lat = Double.parseDouble(input);
				if(lat >= -90 && lat <= 90) {
					done = true;
				} else {
					System.out.println("Try again");
				}
			}catch(Exception e) {
				System.out.println("Try again");
			}
		}while(!done);
		//Set longitude
		done = false;
		System.out.print("Enter the longitude of the new station: ");
		do {
			try {
				String input = in.nextLine();
				log = Double.parseDouble(input);
				if(log >= -180 && log <= 180) {
					done = true;
				} else {
					System.out.println("Try again");
				}
			}catch(Exception e) {
				System.out.println("Try again");
			}
		}while(!done);
		//Set description
		System.out.print("Enter the station's description: ");
		String description = in.nextLine();
		//Set handicap
		System.out.println("Enter a number: \n\t1) Handicap accessible \n\t2) Not handicap accessible");
		done = false;
		boolean handi = false;
		do {
			try {
				String input = in.nextLine();
				int han = Integer.parseInt(input);
				if(han == 1) {
					handi = true;
					done = true;
				}
				else if(han == 2) {
					handi = false;
					done = true;
				}
				else {
					System.out.println("Try again");
				}
			} catch(Exception e) {
				System.out.println("Try again");
			}
		} while(!done);
		//Line colors
		ArrayList<Integer> lineColors = new ArrayList<Integer>();
		for(int i = 0; i < allLines.size(); i++) {
			System.out.print("What stop number is this station on the " + allLines.get(i).getColor().toUpperCase() + " line\n\tor enter -1 if it is not on the " + allLines.get(i).getColor().toUpperCase() + " LINE: ");
			done = false;
			do {
				try {
					String input = in.nextLine();
					int index = Integer.parseInt(input)-1;
					if(index == -2) {
						lineColors.add(-1);
						done = true;
					}
					else if(index >= 0 && index <= allLines.get(i).getStops().size()) {
						lineColors.add(index);
						done = true;
					}
					else {
						System.out.println("Number must be -1 or between 1-" + allLines.get(i).getStops().size());
					}
				} catch(Exception e) {
					System.out.println("Try again");
				}
			}while(!done);
		}
		
		Station s = new Station(name, lat, log, description, handi, lineColors);
		
		return s;
	}
	public static ArrayList<Line> addStation(ArrayList<Line> allLines, Station s){
		int[] lines = s.getLines();
		for(int i = 0; i < lines.length; i++) {
			allLines.get(i).setStops(incrementUp(allLines.get(i).getStops(), s, lines[i], i));
			if(s.getLineColor(i) != -1)
				allLines.get(i).getStops().set(s.getLineColor(i), s);
		}
		
		return allLines;
	}
	public static ArrayList<Station> incrementUp(ArrayList<Station> stops, Station s, int index, int lineColor){
//		If they add it to the end of the line
		if(index == stops.size()) {
			stops.add(s);
			return stops;
		}
		//If it not on the line
		else if(index == -1) {
			return stops;
		}
//		If it is anywhere else in the line
		else {
			for(int i = stops.size()-1; i >= index; i--) {
				if(i != stops.size()-1) {
					stops.set(i+1, stops.get(i));
					stops.get(i+1).setLineColor(lineColor, stops.get(i+1).getLineColor(lineColor)+1);
				}
				else {
					stops.add(stops.get(i));
					stops.get(i+1).setLineColor(lineColor, stops.get(i+1).getLineColor(lineColor)+1);
				}
	
			}
			stops.set(index, s);
			
			return stops;
		}
	}
	public static int removeStations(ArrayList<Station> allStations, Scanner in){
		int removeInt = searchStation(allStations, in, "");
		return removeInt;
	}
	public static ArrayList<Line> incrementDown(ArrayList<Line> allLines, Station remove){
		int[] stopsRemove = remove.getLines();
		for(int i = 0; i < allLines.size(); i++) {
			ArrayList<Station> lineStops = allLines.get(i).getStops();
			if(stopsRemove[i] >= 0) {
				lineStops.remove(remove);
				for(int j = remove.getLineColor(i); j < lineStops.size(); j++) {
					lineStops.get(j).setLineColor(i, lineStops.get(j).getLineColor(i)-1);
				}
				allLines.get(i).setStops(lineStops);
			}
		}
		
		return allLines;
	}
	
	public static String startRoute(ArrayList<Station> allStations, ArrayList<Line> allLines, Scanner in) {
		System.out.print("At what station are you starting: ");
		String startStr = in.nextLine();
		int startInt = searchStation(allStations, in, startStr);
		Station start = new Station();
		if(startInt > 0)
			start = allStations.get(startInt);
		else {
			return "";
		}
		System.out.print("What station do you want to get off at: ");
		String endStr = in.nextLine();
		int endInt = searchStation(allStations, in, endStr);
		Station end = new Station();
		if(endInt > 0)
			end = allStations.get(endInt);
		else
			return "";
		
		return createRoute(start, end, allLines) + "\n";
	}
	public static String createRoute(Station start, Station end, ArrayList<Line> allLines) {
		ArrayList<String> startLines = start.getLineColors();
		ArrayList<String> endLines = end.getLineColors();
		String route = "";
		
//		Checks if the stops are on the same line - a direct route
		String common = compare(startLines, endLines);
		if(!(common.equals("none"))) {
			route = "Take the " + common.toUpperCase() + " LINE from: " + start.getName() + " to " + end.getName()+ "\n\nStations along the way: \n";
			route += printBetween(start, end, common, allLines);
			return route;
		}
//		If they weren't on the same line
		else {
			//route += transferRoute(start, end, allLines);
			return route;
		}
	}
	public static String transferRoute(Station start, Station end, ArrayList<Line> allLines) {
		String route = "";
		String[] colors = start.getColors();
		int[] startLines = start.getLines();
		int[] endLines = end.getLines();
		
		
		
		
		
		return route;
	}
	private static String printBetween(Station start, Station end, String line, ArrayList<Line> allLines) {
		String text = "";
		//Find the index of the line to print
		int index = allLines.get(0).getIndex(line);
		ArrayList<Station> lineStation = allLines.get(index).getStops();
//		Checks the position of the stations on the respective line
		int startPos = getPos(start, line);
		int endPos = getPos(end, line);
//		Makes sure value is valid
		if(startPos != -1 && endPos != -1) {
			if(startPos < endPos) {
//				Increasing on stop numbers
				text += "\n\t" + start.getName() + ",";
				for(int i = startPos+1; i <= endPos; i++) {
					text += "\n\t" + lineStation.get(i).getName() + ", ";
				}
			}
			else if(startPos > endPos) {
//				Decreasing on stop numbers
				for(int i = startPos; i >= endPos; i--) {
					text += "\n\t" + lineStation.get(i).getName() + ", ";
				}
			}
		}
		
		
		
		return text;
	}
	private static int getPos(Station s, String line) {
		switch(line.toLowerCase()) {
			case "red":
				return s.getRed();
			case "green":
				return s.getGreen();
			case "blue":
				return s.getBlue();
			case "orange":
				return s.getOrange();
			case "pink":
				return s.getPink();
			case "brown":
				return s.getBrown();
			case "purple":
				return s.getPurple();
		}
		return -1;
	}
	private static String compare(ArrayList<String> list1, ArrayList<String> list2) {
		String found = "none";
		
		for(int i = 0; i < list1.size(); i++) {
			for(int j = 0; j < list2.size(); j++){
				if(list1.get(i).equals(list2.get(j))) {
					return list1.get(i);
				}
			}
		}
		
		return found;
	}
	
	
	public static void main(String[] args) throws IOException {
		//ArrayList with all stations in it
		ArrayList<Station> allStations = new ArrayList<Station>();
		//Reads the file and adds it to all stations
		allStations = readExistingFile();
		// Sort stations by alphabetical order
		allStations = sortStation(allStations);
		//ArrayList of all lines with their proper stations
		ArrayList<Line> allLines = new ArrayList<Line>();
		//Sorts all stations from the csv file
		allLines = makeLines(allStations);
		//Sorting all the lines into their respective order
		for(int i = 0; i < allLines.size(); i++) {
			allLines.get(i).sortStops(i);
		}
		Scanner in = new Scanner(System.in);
		boolean done = false;
		
		do {
			boolean accepted = false;
			
			System.out.println("Select an option:");
			System.out.println("\t1) Add Station"
							+ "\n\t2) Remove Station"
							+ "\n\t3) Edit Station"
							+ "\n\t4) Search for Station"
							+ "\n\t5) Search with requirements"
							+ "\n\t6) Nearest Station"
							+ "\n\t7) Generate Path"
							+ "\n\t8) Save"
							+ "\n\t9) Save and Exit");
			int userSelec = -1;
			do {
				try {
					String input = in.nextLine();
					userSelec = Integer.parseInt(input);
					if(userSelec >=1 && userSelec <= 9)
						accepted = true;
					else {
						System.out.println("Try again");
					}
				} catch(Exception e) {
					System.out.println("Try again");
				}
			} while(!accepted);
			
			switch(userSelec) {
				case 1: //Add station
					Station s = createStation(allLines, in);
					allStations.add(s);
					allStations = sortStation(allStations);
					//Add to lines
					allLines = addStation(allLines, s);
					System.out.println("\n" + s + "\n");
					break;
				case 2: //Remove Station
					int removeInt = removeStations(allStations, in);
					if(removeInt > 0) {
						System.out.println("REMOVED");
						Station remove = allStations.get(removeInt);
						allStations.remove(removeInt);
						//Deincrementing all the lines
						allLines = incrementDown(allLines, remove);
					}
					break;
				case 3: // Edit Station
					//Find the station they want to change
					int index = searchStation(allStations, in, "");
					if(index == -1) {
						break;
					}
					//Station old = allStations.get(index);
					Station edit = editStation(allStations, allLines, index, in);
					break;
				case 4: // Search for station
					searchStation(allStations, in, "");
					break;
				case 5: // Search with requirements
					searchWithRequirements(allStations, in);
					break;
				case 6: // Nearest Station
					findNearestStation(allStations, in);
					break;
				case 7: // Generate path
					String route = startRoute(allStations, allLines, in);
					System.out.println(route);
					break;
				case 8: // Save
					saveData(allStations);
					System.out.println("Saved!");
					break;
				case 9: // Save and Exit
					saveData(allStations);
					System.out.println("Saved!");
					done = true;
					break;
				default:
					System.out.println("Try again");
					break;
			}
		} while(!done);
		
		
		
		
		in.close();
	}

}
