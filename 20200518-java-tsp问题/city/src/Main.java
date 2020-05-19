import java.util.ArrayList;

/*
    Feel free to change this file, but:
	!!!!!  ANY modification to this file WILL BE INGNORED  !!!!!
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<City> cities = TSPSolver.readFile(/*put your file path here to test with different instances*/"G:\\__Deliverable\\20200518-java-tsp问题\\city\\C110_1.TXT");
        cities = TSPSolver.solveProblem(cities);
        Double totalDistance = TSPSolver.printSolution(cities);
        System.out.printf("Distances: %f\n", totalDistance);
		// Your program should not crash after running the code above!!!
		// It should print out a correct result
    }
}
