import java.io.IOException;
import java.util.Iterator;

public class Registrar {

	public static void main(String[] args) {

		TreeQuery query;
		BinarySearchTree<Course> bst = null;
		int passedCount = 0;

		if (args.length != 1) {

			System.out.println("You must enter a filename in the program arguments");
			return;

		} // end if

		try {

			ReadFile rf = new ReadFile(args[0]);
			bst = rf.buildTree();

		} catch (Exception e) {

			e.printStackTrace();

		} // end try-catch

		query = new TreeQuery(bst);

		int answer1 = query.findNumberOfStudentsInCourse("BI1013");
		ListADT<String> answer2 = query.findAllCoursesTaughtBy("Khan");
		int answer3 = query.findNumberOfFirstYearStudents();
		String answer4 = query.findCourseWithMostStudents();
		int answer5 = query.computeTreeHeight();

		try {

			if (answer1 == 617) {

				System.out.println("Query 1 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Query 1 failed");

			} // end else

		} catch (Exception e) {

			System.out.println("Query 1 failed: " + e);

		} // end catch

		try {

			if (answer2.contains("EN1220") && answer2.contains("EN1501")) {

				System.out.println("Query 2 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Query 2 failed");

			} // end else

		} catch (Exception e) {

			System.out.println("Query 2 failed: " + e);

		} // end catch

		try {

			if (answer3 == 6279) {

				System.out.println("Query 3 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Query 3 failed");

			} // end else

		} catch (Exception e) {

			System.out.println("Query 3 failed: " + e);

		} // end catch

		try {

			if (answer4.equals("CH1107")) {

				System.out.println("Query 4 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Query 4 failed");

			} // end else

		} catch (Exception e) {

			System.out.println("Query 4 failed: " + e);

		} // end catch

		try {

			if (answer5 == 12) {

				System.out.println("Query 5 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Query 5 failed");

			} // end else

		} catch (Exception e) {

			System.out.println("Query 5 failed: " + e);

		} // end catch

		System.out.println("\nScore: " + passedCount + " / 5");

	} // end main

} // end Registrar class