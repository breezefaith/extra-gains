import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReadFile {

	private String filename;

	public ReadFile(String filename) {

		this.filename = filename;

	} // end ReadFile (constructor)

	public BinarySearchTree<Course> buildTree() throws FileNotFoundException, IOException, DuplicatedKeyException {

		BinarySearchTree<Course> bst = new BinarySearchTree<>();

		// Set up the file reader and skip the first line.
		BufferedReader in;
		String line = "";
		in = new BufferedReader(new FileReader(filename));
		line = in.readLine(); // Ignore first line
		StringTokenizer lineTokens = null;

		Course newCourse;

		line = in.readLine(); // Read first actual line

		while (line != null) {

			lineTokens = new StringTokenizer(line, ",");

			// Break up the line using the tokenizer and split to get the individual parts.
			String courseCode = lineTokens.nextToken();
			String courseTitle = lineTokens.nextToken();
			String profList = lineTokens.nextToken();
			profList = profList.substring(1,profList.length()-1); // Remove brackets.
			String[] profs = profList.split(";");
			int numStudents = Integer.parseInt(lineTokens.nextToken());

			// Create Course object with this line's info.
			newCourse = new Course(courseCode, courseTitle, numStudents, profs);
			bst.add(bst.getRoot(), newCourse);

			// Read the next line in from file.
			line = in.readLine();

		} // end while

		// close the file
		in.close();

		return bst;

	} // end buildTree

} // end ReadFile (class)