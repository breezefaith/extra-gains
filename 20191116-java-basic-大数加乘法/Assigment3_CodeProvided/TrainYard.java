import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This is the entry point for Assignment 3, CS1027A, 2019. 
 * 
 * This program will take as a command line argument the name of a file containing the cars in a train.
 * This program will parse the train file and add the train cars into a queue. 
 * Students need to complete the 'buildTrain()' method, which adds TrainCars into the train in the 
 * correct way.
 * 
 * @author CS1027A
 *
 */
public class TrainYard {
	
	/**
	 * 'inputQueue' a queue containing TrainCars read from the train file.
	 */
	private ArrayQueue<DoubleNode<TrainCar>> inputQueue;
	
	/**
	 * 'backupQueue' a queue containing TrainCars that can't be placed yet.
	 */
	private ArrayQueue<DoubleNode<TrainCar>> backupQueue;
	
	/**
	 * 'train' a doubly linked list storing DoubleNodes.
	 */
	private Train train;

	/**
	 * This constructor reads the TrainCars from an input file.
	 * @param filename a train file
	 */
	public TrainYard (String filename) {
		
		System.out.println("Welcome to the train yard");
		System.out.println("******************************************************************************");
		System.out.println();
		
		// Build a queue of train cars from an input file
		inputQueue = new ArrayQueue<DoubleNode<TrainCar>>();
		backupQueue = new ArrayQueue<DoubleNode<TrainCar>>();
		readTrainFile(filename);
		train = new Train();
		
	} //end TrainYard
	
	/**
	 * This method attempts build a train using the inputQueue and the backupQueue.
	 * Students need to complete this method.
	 */
	public void buildTrain() {
		
		// Student code goes here.
		
	} //end buildTrain
	
	/**
	 * This method reads the train cars from the input file and adds the cars to a queue.
	 * @param trainFile the input file
	 * @return a queue containing the train cars
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void readTrainFile(String trainFile) {
		
		// 'lineParts' will hold the tokens from a parsed line
		// 'line' will hold a line from the train file
		// 'color' will hold the color token from the train file
		String[] lineParts;
		String line = "";
		String color;
		
		// 'number' will hold the weight token from the train file
		int number;
		
		// 'in' will hold a file reader to attempt to read the train file
		BufferedReader in = null;
		
		// Try to read the train file
		try {
			
			// Load up the train file
			in = new BufferedReader(new FileReader(trainFile));
			line = in.readLine();
			
			// Loop through reading each line until EOF.
			while ((line = in.readLine()) != null) {
							
				// Convert Weight to integer and leave Color as String.
				// lineParts[0]: Number
				// lineParts[1]: Color
				lineParts = line.split(",");
				number = Integer.parseInt(lineParts[0]);
				color = lineParts[1];
				
				// Add train car to a queue to be added to actual train link.
				inputQueue.enqueue(new DoubleNode<TrainCar>(new TrainCar(number, color)));
				
			} //end while
			
		} catch (FileNotFoundException e) {
			
			System.out.println("The file was not found: " + e);
			
		} catch (IOException e) {
			
			System.out.println("There was an Input/Output problem: " + e);
			
		} finally {
			
			// Try to close the file. 
			if (in != null) {
				
				try {
					
	                in.close();
	                
	            } catch (IOException e) {
	                
	            	System.out.println("There was an Input/Output problem: " + e);
	            	
	            } //end catch
				
			} //end if
			
		} //end finally
		
	} //end readTrainFile
	
	/**
	 * This method prints out the contents of the Train.
	 */
	private void printQueue() {
		
		System.out.println();
		System.out.println("Left in the queue:");
		System.out.println("======================");
		System.out.println(backupQueue.toString());
		
	} //end printQueue	
	
	/**
	 * This method returns the train.
	 * @return the train
	 */
	public Train getTrain() {
		
		return train;
		
	} //end getTrain

	/**
	 * This method checks if the appropriate command line arguments were given, and attempts to read a train file.
	 * @param args the command line arguments (hopefully a train file)
	 */
	public static void main (String[] args) {
		
		// Ensure that the program was run with exactly 1 input argument
		if (args.length == 1) {
			
			TrainYard trainYard = new TrainYard(args[0]);
			trainYard.buildTrain();
			trainYard.getTrain().printTrain();
			trainYard.printQueue();
			
		} //end if
		
		else {
			
			System.out.println("You must run the program with an argument containing the filename to load.");
			
		} //end else
		
	} //end main
	
} //end TrainYard (class)