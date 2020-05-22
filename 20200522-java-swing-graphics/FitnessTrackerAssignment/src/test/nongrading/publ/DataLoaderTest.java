package test.nongrading.publ;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import assignment2020.codeprovided.dataloading.DataParsingException;
import assignment2020.codeprovided.fitnesstracker.Participant;
import assignment2020.codeprovided.fitnesstracker.measurements.HeartRate;
import assignment2020.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2020.dataloading.DataLoader;

public class DataLoaderTest {

	@Test
	public void testClassIsPresent() {
		// Only tests that classes can be loaded
		
		try {
			Class.forName("assignment2020.dataloading.DataLoader");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			fail("Required class (DataLoader) not found, check that it is in the correct package.");
		}
	}
	
	@Test
	public void testLoadAll() {
		DataLoader d = new DataLoader();
		try {
			Collection<Participant> participants = d.loadAllParticipants(Paths.get("resources/data"));
			assertEquals(10, participants.size());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void testLoadP2() {
		DataLoader d = new DataLoader();

		try {
			Participant p = d.loadDataFile(Paths.get("resources/data/P02.txt"));		
			assertEquals(32, p.getAge());
			assertTrue(p.getTracker("FT1")
					.getMeasurementsForType(MeasurementType.HEART_RATE)
					.contains(new HeartRate(21, "82.0"))
					);
			
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
			fail();
		} catch (DataParsingException dataEx) {
			dataEx.printStackTrace();
			fail();
		}
		
	}

}
