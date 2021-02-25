package uk.ac.sheffield.com1003.problemsheet1.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.sheffield.com1003.problemsheet1.HeartRate;

public class TestHeartRate {

	@Test
	public void testSetValue() {
		HeartRate h = new HeartRate(17.4);
		h.setValue(486.2);
		assertEquals(486.2, h.getValue(), 0.001);
	}

	@Test
	public void testToString() {
		HeartRate h = new HeartRate(15);
		assertTrue(h.toString().contains(String.valueOf(15)));
	}

}
