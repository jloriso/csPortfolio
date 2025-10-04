// package project.tests;

// import static org.junit.Assert.*;

// import java.util.ArrayList;

// import org.junit.Test;

// import project.Station;

// public class testStation {

// 	@Test
// 	public void testStationDefault() {
// 		Station s = new Station();
		
// 		assertTrue(s.getLat() == 0);
// 		assertTrue(s.getLog() == 0);
// 		assertTrue(s.getName().equals("N/A"));
// 		assertTrue(s.getDescription().equals("elevated"));
// 		assertFalse(s.getHandicap());
// 		assertTrue(s.getRed()==-1);
// 		assertTrue(s.getGreen()==-1);
// 		assertTrue(s.getBlue()==-1);
// 		assertTrue(s.getBrown()==-1);
// 		assertTrue(s.getPurple()==-1);
// 		assertTrue(s.getPink()==-1);
// 		assertTrue(s.getOrange()==-1);
// 	}

// 	@Test
// 	public void testStationNonDefaultIntegers() {
// 		Station s = new Station("Test Stop", 15, 34, "enbankment", true, 3,6,5,-1,45,33,2);
		
// 		assertTrue(s.getLat() == 15);
// 		assertTrue(s.getLog() == 34);
// 		assertTrue(s.getName().equals("Test Stop"));
// 		assertTrue(s.getDescription().equals("enbankment"));
// 		assertTrue(s.getHandicap());
// 		assertTrue(s.getRed()==3);
// 		assertTrue(s.getGreen()==6);
// 		assertTrue(s.getBlue()==5);
// 		assertTrue(s.getBrown()==-1);
// 		assertTrue(s.getPurple()==45);
// 		assertTrue(s.getPink()==33);
// 		assertTrue(s.getOrange()==2);
// 		// To check the format of the toString() method
// 		System.out.println(s);
// 	}

// 	@Test
// 	public void testStationNonDefaultArrayList() {
// 		ArrayList<Integer> stops = new ArrayList<Integer>();
// 		//3,6,5,-1,45,33,2
// 		stops.add(3);
// 		stops.add(6);
// 		stops.add(5);
// 		stops.add(-1);
// 		stops.add(45);
// 		stops.add(33);
// 		stops.add(2);
		
// 		Station s = new Station("Test Stop", 15, 34, "enbankment", true, stops);
		
// 		assertTrue(s.getLat() == 15);
// 		assertTrue(s.getLog() == 34);
// 		assertTrue(s.getName().equals("Test Stop"));
// 		assertTrue(s.getDescription().equals("enbankment"));
// 		assertTrue(s.getHandicap());
// 		assertTrue(s.getRed()==3);
// 		assertTrue(s.getGreen()==6);
// 		assertTrue(s.getBlue()==5);
// 		assertTrue(s.getBrown()==-1);
// 		assertTrue(s.getPurple()==45);
// 		assertTrue(s.getPink()==33);
// 		assertTrue(s.getOrange()==2);
// 	}

// 	@Test
// 	public void testSetRed() {
// 		Station s = new Station("Test Stop", 15, 34, "enbankment", true, 3,6,5,-1,45,33,2);
		
// 		s.setRed(-45);		
// 		assertFalse(-45 == s.getRed());
		
// 		s.setRed(12);
// 		assertEquals(12, s.getRed());
// 	}

// 	@Test
// 	public void testSetGreen() {
// 		Station s = new Station("Test Stop", 15, 34, "enbankment", true, 3,6,5,-1,45,33,2);
		
// 		s.setGreen(-45);		
// 		assertFalse(-45 == s.getGreen());
		
// 		s.setGreen(12);
// 		assertEquals(12, s.getGreen());
// 	}

// 	@Test
// 	public void testSetBlue() {
// 		Station s = new Station("Test Stop", 15, 34, "enbankment", true, 3,6,5,-1,45,33,2);
		
// 		s.setBlue(-45);		
// 		assertFalse(-45 == s.getBlue());
		
// 		s.setBlue(12);
// 		assertEquals(12, s.getBlue());	}

// 	@Test
// 	public void testSetBrown() {
// 		Station s = new Station("Test Stop", 15, 34, "enbankment", true, 3,6,5,-1,45,33,2);
		
// 		s.setBrown(-45);		
// 		assertFalse(-45 == s.getBrown());
		
// 		s.setBrown(12);
// 		assertEquals(12, s.getBrown());
// 	}

// 	@Test
// 	public void testSetPurple() {
// 		Station s = new Station("Test Stop", 15, 34, "enbankment", true, 3,6,5,-1,45,33,2);
		
// 		s.setPurple(-45);		
// 		assertFalse(-45 == s.getPurple());
		
// 		s.setPurple(12);
// 		assertEquals(12, s.getPurple());	}

// 	@Test
// 	public void testSetPink() {
// 		Station s = new Station("Test Stop", 15, 34, "enbankment", true, 3,6,5,-1,45,33,2);
		
// 		s.setPink(-45);		
// 		assertFalse(-45 == s.getPink());
		
// 		s.setPink(12);
// 		assertEquals(12, s.getPink());	}

// 	@Test
// 	public void testSetOrange() {
// 		Station s = new Station("Test Stop", 15, 34, "enbankment", true, 3,6,5,-1,45,33,2);
		
// 		s.setOrange(-45);		
// 		assertFalse(-45 == s.getOrange());
		
// 		s.setOrange(12);
// 		assertEquals(12, s.getOrange());
// 	}

// }
