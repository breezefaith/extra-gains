package test.nongrading.publ;

import assignment2020.codeprovided.dataloading.DataParsingException;
import assignment2020.codeprovided.fitnesstracker.Participant;
import assignment2020.codeprovided.handoutquestions.AbstractFitnessQuestions;
import assignment2020.dataloading.DataLoader;
import assignment2020.handoutquestions.FitnessQuestions;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class FitnessQuestionsTest {
	
	static Collection<Participant> participants;
	static AbstractFitnessQuestions qa;
	
	@BeforeClass
	public static void setup()
	{
		List<String> inputPartA = new ArrayList<>();
		inputPartA.add("Participant: john");
		inputPartA.add("Age: 25");
		inputPartA.add("Gender: M");
		inputPartA.add("--------------------------------------------");
		inputPartA.add("Heart Rate");
		inputPartA.add("Count;GS;FT1;FT2;FT3;");
		inputPartA.add("1;91.0;92.0;90.0;90.0;"); 
		inputPartA.add("2;102.0;91.0;89.0;90.0;");
		inputPartA.add("3;109.0;93.0;91.0;93.0;");
		inputPartA.add("--------------------------------------------");
		inputPartA.add("Steps");
		inputPartA.add("Count;GS;FT1;FT2;FT3;FT4;FT5;");
		inputPartA.add("1;108.0;104.0;105.0;104.0;101.0;101.0;");
		inputPartA.add("2;212.0;210.0;210.0;211.0;207.0;207.0;");
		inputPartA.add("--------------------------------------------");
		inputPartA.add("Energy expenditure");
		inputPartA.add("Count;GS;FT1;FT2;FT3;FT4;FT5;");
		inputPartA.add("1;2.3;0.0;6.0;7.0;6.0;6.0;");
		inputPartA.add("2;7.0;0.0;14.0;14.0;14.0;13.0;");
		inputPartA.add("--------------------------------------------");
		inputPartA.add("Distance");
		inputPartA.add("Count;GS;FT1;FT2;FT3;FT4;FT5;");
		inputPartA.add("1;2887.0;3060.0;3110.0;3250.0;3330.0;3260.0;");
		
		List<String> inputPartB = new ArrayList<>();
		inputPartB.add("Participant: sally");
		inputPartB.add("Age: 28");
		inputPartB.add("Gender: F");
		inputPartB.add("--------------------------------------------");
		inputPartB.add("Heart Rate");
		inputPartB.add("Count;GS;FT1;FT2;");
		inputPartB.add("1;92.0;94.0;93.0;"); 
		inputPartB.add("2;104.0;92.0;88.0;");
		inputPartB.add("3;110.0;92.0;90.0;");
		inputPartB.add("--------------------------------------------");
		inputPartB.add("Steps");
		inputPartB.add("Count;GS;FT1;FT2;FT3;FT4;FT5;");
		inputPartB.add("1;109.0;104.0;110.0;105.0;120.0;121.0;");
		inputPartB.add("2;204.0;213.0;202.0;210.0;200.0;200.0;");
		inputPartB.add("3;201.0;212.0;202.0;210.0;200.0;200.0;");
		inputPartB.add("--------------------------------------------");
		inputPartB.add("Energy expenditure");
		inputPartB.add("Count;GS;FT1;FT2;FT3;FT4;FT5;");
		inputPartB.add("1;3.3;2.0;4.0;6.0;10.0;6.7;");
		inputPartB.add("2;5.0;1.0;14.5;14.3;14.2;13.6;");
		inputPartB.add("--------------------------------------------");
		inputPartB.add("Distance");
		inputPartB.add("Count;GS;FT1;FT2;FT3;FT4;FT5;");
		inputPartB.add("1;2847.0;3160.0;3010.0;3352.0;3320.0;3210.0;");
		
		
		// Load participants
		DataLoader d = new DataLoader();
		participants = new ArrayList<>(); 
		try {
			participants.add(d.loadDataLines(inputPartA));
			participants.add(d.loadDataLines(inputPartB));
		}catch (DataParsingException e) {
			e.printStackTrace();
			throw new IllegalStateException("Could not load the test data due to a parsing error.");
		}
		
		qa = new FitnessQuestions(participants);
	}

	@Test
	public void testClassIsPresent() {
		// Only tests that classes can be loaded
		
		try {
			Class.forName("assignment2020.handoutquestions.FitnessQuestions");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			fail("Required class (FitnessQuestions) not found, check that it is in the correct package.");
		}
	}

	@Test
	public void testQ09() {
		// Includes GS
		assertEquals(24, qa.getTotalEECount(), 0.01);
	}
	
	@Test
	public void testQ15() {
		String[] result = qa.getLowestNumberOfStepsParticipants().toArray(new String[] {});
		
		assertFalse(result.length == 0);
		assertTrue(result[0].contains("john"));
		assertTrue(result[0].contains("101"));
	}
	
	
	@Test
	public void testQ18() {
		// 94.095238095 global avg - includes GS
		System.out.println(qa.getGlobalAverageHR());
		assertEquals(94.0952, qa.getGlobalAverageHR(), 0.1);
	}
}
