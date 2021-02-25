/**
 * MeasurementGenerator.java
 * <p>
 * Copyright (c) University of Sheffield, 2021
 *
 * @version 1.3 11/01/2021
 * @author Maria-Cruz Villa-Uriol
 * @author Ben Clegg
 */

package uk.ac.sheffield.com1003.problemsheet1;

public class MeasurementGenerator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // req 1
        Steps steps1 = new Steps(6000);
        Steps steps2 = new Steps(15000);

        HeartRate heartRate1 = new HeartRate(75.8);
        HeartRate heartRate2 = new HeartRate(87.1);

        Distance distance1 = new Distance(10, Distance.DistanceUnit.KILOMETRES);
        Distance distance2 = new Distance(3, Distance.DistanceUnit.KILOMETRES);
        Distance distance3 = new Distance(7, Distance.DistanceUnit.MILES);

        // req 2
        System.out.println("steps1: " + steps1);
        System.out.println("steps2: " + steps2);

        // req 3
        System.out.println("heartRate1: " + heartRate1);
        System.out.println("heartRate2: " + heartRate2);

        // req 4
        System.out.println("distance1: " + distance1);
        System.out.println("distance2: " + distance2);
        System.out.println("distance3: " + distance3);

        // req 5
        distance1.changeDistanceUnit(Distance.DistanceUnit.MILES);
        System.out.println("distance1: " + distance1);

        distance3.changeDistanceUnit(Distance.DistanceUnit.KILOMETRES);
        System.out.println("distance3: " + distance3);
    }

}
