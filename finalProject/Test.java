import java.util.ArrayList;

public class Test {
	public static void  main(String[] args) {
		Station harlem = new Station("Harlem",41.88706,-87.80486,"elevated",true,-1,0,-1,-1,-1,-1,-1);
		Station oakPark = new Station ("Oak Park",41.886784,-87.794324,"elevated",false,-1,1,-1,-1,-1,-1,-1);
		Station ridgeland = new Station ("Ridgeland",41.886784,-87.784628,"elevated",false,-1,2,-1,-1,-1,-1,-1);
		Station austin = new Station ("Austin",41.887293,-87.774135,"elevated",false,-1,3,-1,-1,-1,-1,-1);
		Station central = new Station ("Central",41.887389,-87.76565,"elevated",true,-1,4,-1,-1,-1,-1,-1);
		Station laramie = new Station ("Laramie",41.887163,-87.754986,"elevated",true,-1,5,-1,-1,-1,-1,-1);
		Station cicero = new Station ("Cicero",41.886519,-87.744698,"elevated",true,-1,6,-1,-1,-1,-1,-1);
		Station pulaski = new Station ("Pulaski",41.885412,-87.725404,"elevated",true,-1,7,-1,-1,-1,-1,-1);
		Station conserv = new Station ("Conservatory-Central Park Drive",41.884904,-87.716513,"elevated",true,-1,8,-1,-1,-1,-1,-1);
		Station kenzie = new Station ("Kenzie",41.884321,-87.706155,"elevated",true,-1,9,-1,-1,-1,-1,-1);
		Station cali = new Station ("California",41.88422,-87.696234,"elevated",true,-1,10,-1,-1,-1,-1,-1);
		Station ashland = new Station ("Ashland",41.885268,-87.666969,"elevated",true,-1,11,-1,-1,-1,11,-1);
		Station moran = new Station ("Morgan",41.8856,-87.6522,"elevated",true,-1,12,-1,-1,-1,12,-1);
		Station clinton = new Station ("Clinton",41.885678,-87.641782,"elevated",true,-1,13,-1,-1,-1,13,-1);
		Station clarknlake = new Station ("Clark/Lake",41.885767,-87.630885,"elevated",true,-1,14,16,26,19,14,12);
		Station statenlake = new Station ("State/Lake",41.88574,-87.627835,"elevated",false,-1,15,-1,25,20,15,13);
		Station washingtonnwabash = new Station ("Washington/Wabash",41.8829,-87.626205,"elevated",true,-1,16,-1,24,21,16,14);
		Station adamsnwabash = new Station ("Adams/Wabash",41.884431,-87.6261,"elevated",false,-1,17,-1,23,22,17,15);
		Station roosevelt = new Station ("Roosevelt",41.867368,-87.627402,"elevated/subway",true,23,18,-1,-1,-1,-1,7);
		Station cermackmccormick = new Station ("Cermak-McCormick",41.8531548,-87.626423,"elevated",true,-1,19,-1,-1,-1,-1,-1);
		Station bronzeville = new Station ("35th-Bronzeville-IIT",41.831677,-87.625826,"elevated",true,-1,20,-1,-1,-1,-1,-1);
		Station indiana = new Station ("Indiana",41.821732,-87.621371,"elevated",true,-1,21,-1,-1,-1,-1,-1);
		Station fourtyThird = new Station ("43rd",41.816462,-87.619021,"elevated",true,-1,22,-1,-1,-1,-1,-1);
		Station fourtySeven = new Station ("47th",41.8094,-87.61909,"elevated",true,-1,23,-1,-1,-1,-1,-1);
		Station fiftyone = new Station ("51st",41.8022,-87.61903,"elevated",true,-1,24,-1,-1,-1,-1,-1);
		Station garfield = new Station ("Garfield",41.79454,-87.61835,"elevated",true,-1,25,-1,-1,-1,-1,-1);
		Station halstead = new Station ("Halsted",41.77954,-87.64468,"elevated",true,-1,26,-1,-1,-1,-1,-1);
		Station ashlandnsixtythree = new Station ("Ashland/63rd",41.77943,-87.66393,"elevated",true,-1,27,-1,-1,-1,-1,-1);
		
		ArrayList<Station> stops = new ArrayList<Station>(100);
		
		
		stops.add(conserv);
		stops.add(kenzie);
		stops.add(cali);
		stops.add(clarknlake);
		stops.add(austin);
		stops.add(central);
		stops.add(moran);
		stops.add(halstead);
		stops.add(ashlandnsixtythree);
		stops.add(laramie);
		stops.add(roosevelt);
		stops.add(cermackmccormick);
		stops.add(bronzeville);
		stops.add(indiana);
		stops.add(fourtyThird);
		stops.add(fourtySeven);
		stops.add(fiftyone);
		stops.add(garfield);
		stops.add(cicero);
		stops.add(pulaski);
		stops.add(statenlake);
		stops.add(oakPark);
		stops.add(ridgeland);
		stops.add(washingtonnwabash);
		stops.add(ashland);
		stops.add(harlem);
		stops.add(clinton);
		stops.add(adamsnwabash);
		
		
		Line green = new Line( stops, "green");
		
		//System.out.println(green.text());

		
		System.out.println(green.text());
	}
}
