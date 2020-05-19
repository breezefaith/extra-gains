import java.io.*;
import java.util.ArrayList;

public class TSPSolver {
    public static ArrayList<City> readFile(String filename) {
        ArrayList<City> cities = new ArrayList<>();
        /*
			Provide code here
		*/
        String line = null;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                cities.add(new City(words[0], words[1], words[2]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static ArrayList<City> solveProblem(ArrayList<City> citiesToVisit) {
        ArrayList<City> routine = new ArrayList<City>();
        /*
			provide code here. you are allowed to add your own functions outside
			But you are NOT allowed to modify method name, parameters, return type.
		*/
        City current = citiesToVisit.remove(0);
        routine.add(current);
        while (citiesToVisit.size() > 0) {
            double minDis = Integer.MAX_VALUE;
            City minCity = null;
            for (City c : citiesToVisit) {
                double dis = current.distance(c);
                if (dis < minDis) {
                    minCity = c;
                    minDis = dis;
                }
            }
            citiesToVisit.remove(minCity);
            routine.add(minCity);
            current = minCity;
        }

        return routine;
    }

    public static double printSolution(ArrayList<City> routine) {
        double totalDistance = 0.0;
        /*
			provide code here. you are NOT allowed to call your own functions.
			But you can use your own variables defined somewhere else! 
		*/
        City start = routine.get(0);
        City current = start;
        for (int i = 0; i < routine.size(); i++) {
            City c = routine.get(i);
            System.out.print(c.getId() + "->");
            totalDistance += current.distance(c);
            current = c;
        }
        System.out.println(start.getId());
        totalDistance += current.distance(start);
        return totalDistance;
    }

}
