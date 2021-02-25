package uk.ac.sheffield.com1003.problemsheet1.test;

import static org.junit.Assert.*;
import org.junit.Test;


import uk.ac.sheffield.com1003.problemsheet1.Distance;
import uk.ac.sheffield.com1003.problemsheet1.Distance.DistanceUnit;

public class TestDistance {


	// Constants
	private static final double MILES_PER_KM = 0.621;
	private static final double DELTA = 0.001;
	
	@Test
	public void testGetValue() {
		Distance a = new Distance(70.3);
		assertEquals(70.3, a.getValue(), DELTA);
		assertEquals(a.getDistanceUnit(), DistanceUnit.KILOMETRES);
	}
	
	@Test
	public void testSetValue() {
		Distance a = new Distance(70.3);
		a.setValue(604.9);
		assertEquals(604.9, a.getValue(), DELTA);
		assertEquals(a.getDistanceUnit(), DistanceUnit.KILOMETRES);
	}
	
	@Test
	public void testConvertToMiles() {
		assertEquals(3 * MILES_PER_KM, Distance.convertToMiles(3), DELTA);
	}
	

	@Test
	public void testToString() {
		Distance a = new Distance(17);
		assertTrue(a.toString().contains(String.valueOf(17)));
	}
	
	@Test
	public void testGetValueWithUnitMiles() {
		Distance a = new Distance(10, DistanceUnit.KILOMETRES);
		assertEquals(10 * MILES_PER_KM, a.getValue(DistanceUnit.MILES), DELTA);
		
		Distance b = new Distance(10, DistanceUnit.MILES);
		assertEquals(10, b.getValue(DistanceUnit.MILES), DELTA);
	}
}
