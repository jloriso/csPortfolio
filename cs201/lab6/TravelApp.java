
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TravelApp {
	public static ArrayList<Trip> readExistingFile(){
		ArrayList<Trip> trips = new ArrayList<Trip>();
		
		try {
			File travel = new File("src/labs/lab6/travel.csv");
			Scanner input = new Scanner(travel);
			while(input.hasNextLine()) {
				try {
					String line = input.nextLine();
					Trip e = null;
					
					String[] wholeLine = line.split(",");
					if(wholeLine[0].equalsIgnoreCase("Flight")){
						e = new Flight(wholeLine[1],wholeLine[2],
								Double.parseDouble(wholeLine[3]),
								Boolean.parseBoolean(wholeLine[4]));
					}
					else if(wholeLine[0].equalsIgnoreCase("Drive")) {
						e = new Drive(wholeLine[1],wholeLine[2],
								Double.parseDouble(wholeLine[3]),
								Double.parseDouble(wholeLine[4]));
					}
					else if(wholeLine[0].equalsIgnoreCase("Train")) {
						ArrayList<String> stops = new ArrayList<String>();
						for(int i = 4; i < wholeLine.length; i++) {
							if(wholeLine[i] != null)
								stops.add(wholeLine[i]);
						}
						e = new Train(wholeLine[1],wholeLine[2],
								Double.parseDouble(wholeLine[3]),
								stops);
					}
					trips.add(e);
				}catch(Exception e) {
					System.out.println("Error while reading file");
				}
			}
			input.close();
		} catch(Exception e) {
			System.out.println("Error reading from file");
		}
		
		return trips;
	}
	
	public static ArrayList<Trip> takeTrips(ArrayList<Trip> trips) {
		for(Trip t: trips) {
			System.out.println(t.travel());
		}
		trips.clear();
		return trips;
	}
	
	
	public static ArrayList<Trip>  addFlight(ArrayList<Trip> trips, Scanner in) {
		try {
			Flight f = new Flight();
			
			System.out.println("Where is your trip starting: ");
			f.setOrigin(in.nextLine());
			
			System.out.println("Where are you going: ");
			f.setDestination(in.nextLine());
			
			System.out.println("How long is the trip: ");
			f.setDuration(in.nextDouble());
			
			boolean done2 = false;
			boolean meals = false;
			do {
				System.out.println("Did you purchase any meals (Y/N): ");
				String in3 = in.next();
				switch(in3) {
					case"y","Y","yes","YES","Yes":
						meals = true;
						done2 = true;
						break;
					case"n","N","no","No","NO":
						meals = false;
						done2 = true;
						break;
					default:
						System.out.println("Please enter a valid answer.");
						break;
				}
			} while(!done2);
			f.setMeals(meals);
			trips.add(f);
		} catch(Exception e) {
			System.out.println("Error adding flight.");
		}
		return trips;
	}
	public static ArrayList<Trip> addDrive(ArrayList<Trip> trips, Scanner in) {
		Drive d = new Drive();
		try {
			System.out.println("Where is your trip starting: ");
			d.setOrigin(in.nextLine());
			
			System.out.println("Where are you going: ");
			d.setDestination(in.nextLine());
			
			System.out.println("How long is the trip: ");
			d.setDuration(in.nextDouble());
			
			System.out.println("How many gallons of gas will the trip take: ");
			d.setGallonsOfGas(in.nextDouble());
			
			trips.add(d);
		} catch(Exception e) {
			System.out.println("Error adding drive.");
		}
		return trips;
	}
	public static ArrayList<Trip> addTrain(ArrayList<Trip> trips, Scanner in) {
		Train t = new Train();
		try {
			System.out.println("Where is your trip starting: ");
			t.setOrigin(in.nextLine());
			
			System.out.println("Where are you going: ");
			t.setDestination(in.nextLine());
			
			System.out.println("How long is the trip: ");
			t.setDuration(in.nextDouble());
			
			ArrayList<String> stops = new ArrayList<String>();
			
			boolean done = false;
			do {
				System.out.println("Enter stop name or enter -1 to stop: ");
				String enterStop = in.nextLine();
				if(!(enterStop.equals("-1"))) {
					stops.add(enterStop);
				}else {
					done = true;
				}
			}while(!done);
			
			t.setStops(stops);
			trips.add(t);
		} catch(Exception e) {
			System.out.println("Error adding train.");
		}
		return trips;
	}
	
	
	public static ArrayList<Trip> addTrip(ArrayList<Trip> trips, Scanner in) {
		boolean done = false;
		do {
			try {
				System.out.println("1. Fly"
								+ "\n2. Drive"
								+ "\n3. Train");
				String in2 = in.nextLine();
				switch(in2) {
					case"1"://Fly
						trips = addFlight(trips, in);
						break;
					case"2"://Drive
						trips = addDrive(trips,in);
						break;
					case"3": //Train
						trips = addTrain(trips, in);
						break;
				}
				
				
				done = true;
			} catch(Exception e) {
				System.out.println("Error");
			}
		}while(!done);
		return trips;
	}
	
	public static void saveData(ArrayList<Trip> trips) {
		try {
			FileWriter out = new FileWriter("src/labs/lab6/travel.csv");
			for (Trip t : trips) {
				out.write(t.toString() + "\n");
				out.flush();
			}
			out.close();
		} catch (Exception e) {
			
		}
	}
	
	public static ArrayList<Trip> removeTrip(ArrayList<Trip> trips, Scanner in) {
		int found = findTrips(trips, in);
		if(found >= 0) {
			boolean removed = trips.remove(trips.get(found));
			if(removed) {
				System.out.println("Removed successfully");
			}
			else {
				System.out.println("Error removing trip");
			}
		}
		else
			System.out.println("Trip not found");
		return trips;
	}
	public static int findTrips(ArrayList<Trip> trips,Scanner in) {
		int found = -1;
		
		boolean done = false;
		do {
			try {
				System.out.println("Enter trip type: "
						+ "\n 1. Drive"
						+ "\n 2. Fly"
						+ "\n 3. Train");
				int type = Integer.parseInt(in.nextLine());
				switch(type) {
					case 1: //Drive
						//Print
						int count = 1;
						int one = 0;
						ArrayList<Integer> values = new ArrayList<Integer>();
						for(int i = 0; i < trips.size(); i++) {
							try {
								((Drive) trips.get(i)).getGallonsOfGas();
								values.add(i);
								System.out.println(count + ". " + trips.get(i).travel());
								one = i;
								count++;
							} catch(Exception e) {}
						}
						if(count == 2) {
							return one;
						}
						//Takes the users input
						boolean finished = false;
						do {
							try {
								System.out.println("Enter the number of the drive you want to view.");
								int selection = Integer.parseInt(in.nextLine());
								if(!(selection > count)) {
									finished = true;
									return values.get(selection-1);
								}
							} catch(Exception e) {
								System.out.println("Enter a real number");
							}
						} while(!finished);
						
						done = true;
						break;
					case 2: //Fly
						//Print
						int count2 = 1;
						int two = 0;
						ArrayList<Integer> values2 = new ArrayList<Integer>();
						for(int i = 0; i < trips.size(); i++) {
							try {
								((Flight) trips.get(i)).hasMeals();
								
								values2.add(i);
								System.out.println(count2 + ". " + trips.get(i).travel());
								two = i;
								count2++;
							} catch(Exception e) {}
						}
						if(count2 == 2) {
							return two;
						}
						//Takes the users input
						boolean finished2 = false;
						do {
							try {
								System.out.println("Enter the number of the flight you want to view.");
								int selection = Integer.parseInt(in.nextLine());
								if(!(selection > count2)) {
									finished2 = true;
									return values2.get(selection-1);
								}
							} catch(Exception e) {
								System.out.println("Enter a real number");
							}
						} while(!finished2);
						
						done = true;
						break;
						
					case 3: //Train
						//Print
						int count3 = 1;
						int three = 0;
						ArrayList<Integer> values3 = new ArrayList<Integer>();
						for(int i = 0; i < trips.size(); i++) {
							try {
								((Train) trips.get(i)).getStops();
								
								values3.add(i);
								System.out.println(count3 + ". " + trips.get(i).travel());
								three = i;
								count3++;
							} catch(Exception e) {}
						}
						
						if(count3 == 2) {
							return three;
						}
						//Takes the users input
						boolean finished3 = false;
						do {
							try {
								System.out.println("Enter the number of the train ride you want to view.");
								int selection = Integer.parseInt(in.nextLine());
								if(!(selection > count3)) {
									finished3 = true;
									return values3.get(selection-1);
								}
							} catch(Exception e) {
								System.out.println("Enter a real number");
							}
						} while(!finished3);
						
						done = true;
						break;
						
					default:
						System.out.println("Try again");
				}
				
			} catch(Exception e) {
				System.out.println("Error");
			}
		} while(!done);
		
		return found;
	}
	
	public static void main(String[] args) {
		ArrayList<Trip> trips = new ArrayList<Trip>();
		trips = readExistingFile();
		
		Scanner in = new Scanner(System.in);
		boolean done = false;
		do {
			try {
				System.out.println("1. Add New Trip:"
								+ "\n2. Remove Trip"
								+ "\n3. View Trip"
								+ "\n4. Take All Trips"
								+ "\n5. Exit");
				int in1 = Integer.parseInt(in.nextLine());
				switch(in1) {
					case 1: //Add
						trips = addTrip(trips, in);
						break;
					case 2: //Remove
						trips = removeTrip(trips,in);
						break;
					case 3: //View
						int found = findTrips(trips, in);
						System.out.println(trips.get(found).travel());
						break;
					case 4: // Take
						trips = takeTrips(trips);
						break;
					case 5: //Exit
						done = true;
						saveData(trips);
						System.out.println("Goodbye");
						break;
					default:
						System.out.println("Try again.");
						break;
				}
			} catch(Exception e) {}
			System.out.println();
		}while(!done);
		
		in.close();
	}

}
