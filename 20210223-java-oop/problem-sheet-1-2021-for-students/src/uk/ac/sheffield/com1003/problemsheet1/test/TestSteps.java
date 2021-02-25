package uk.ac.sheffield.com1003.problemsheet1.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.sheffield.com1003.problemsheet1.Steps;

public class TestSteps {

	@Test
	public void testGetValue() {
		Steps s = new Steps(20);
		assertEquals(20, s.getValue());
		//assertEquals(20, s.getValue());
	}

	@Test
	public void testToString() {
		Steps s = new Steps(17);
		assertTrue(s.toString().contains(String.valueOf(17)));
	}

}
