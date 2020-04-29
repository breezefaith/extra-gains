/**
 * This file represents a Train, which is composed of one or more train cars.
 * Note that the Train described in this assignment is simply a doubly linked list.
 * 
 * @author CS1027A
 *
 */
public class Train {
	
	// Student code goes here.
	
	/**
	 * This method prints out the contents of the Train in more detail.
	 */
	public void printDetailedTrain() {
		
		DoubleNode<TrainCar> current = locomotive;
		
		System.out.println("*******************************");
		System.out.println("Locomotive: " + locomotive.getElement());
		System.out.println("Caboose: " + caboose.getElement());
		System.out.println("*=*=*=*=*=*=*=*=*");
		
		// Traverse through the Train, e.g. traverse through the doubly linked list
		while (current != null) {
		
			String str;
			
			// Print previous element if it exists.
			if (current.getPrevious() != null) {
				
				str = current.getPrevious() + " >> ";
				
			} //end if
			
			// Print something informative if it does not exist
			else {
				
				str = "N/A >> ";
				
			} //end else			
			
			// Print actual element value.
			str += "'" + current.getElement() + "' >> ";
			
			// Print next element if it exists.
			if (current.getNext() != null) {
				
				str += current.getNext();
				
			} //end if 
			
			// Print something informative if it does not exist
			else {
				
				str += "N/A";
				
			} //end else
		
			System.out.println(str);
			current = current.getNext();
			
		} //end while
		
		System.out.println("*******************************");
		
	} //end printDetailedTrain
	
	/**
	 * This method prints out the contents of the Train.
	 */
	public void printTrain() {
		
		DoubleNode<TrainCar> current = locomotive;

		String mainStr = "";
		int count = 0;
		
		// Traverse through the Train, e.g. traverse through the doubly linked list
		while (current != null) {

			if (count > 0) {
				
				mainStr += ",  ";
				
			} //end if
			
			mainStr += current.toString();
			current = current.getNext();
			count++;
			
		} //end while		
		
		System.out.println("Main train cars:");
		System.out.println("======================");
		System.out.println(mainStr);
		
	} //end printTrain
	
} //end Train (class)