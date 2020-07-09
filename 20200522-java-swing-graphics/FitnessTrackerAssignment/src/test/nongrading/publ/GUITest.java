package test.nongrading.publ;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import assignment2020.dataloading.DataLoader;

public class GUITest {

	@Test
	public void testClassIsPresent() {
		// Only tests that classes can be loaded
		
		try {
			Class.forName("assignment2020.gui.GUIPanel");
			Class.forName("assignment2020.gui.GUIPlotPanel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			fail("Required class not found, check that it is in the correct package.");
		}
	}
	

}

