/**
 * Distance.java
 * <p>
 * Copyright (c) University of Sheffield, 2021
 *
 * @version 1.3 11/01/2021
 * @author Maria-Cruz Villa-Uriol
 * @author Ben Clegg
 */

package uk.ac.sheffield.com1003.problemsheet1;

public class Distance {

    // Enum of distance units
    public enum DistanceUnit {
        KILOMETRES,
        MILES
    }

    // Constants
    private static final double KMS_PER_MILE = 1.609;
    private static final double MILES_PER_KM = 0.621;

    // Instance variables
    private double value;
    private DistanceUnit distanceUnit;

    // Constructors
    public Distance(double value) {
        this.value = value;
        // Default unit is km
        this.distanceUnit = DistanceUnit.KILOMETRES;
    }

    public Distance(double value, DistanceUnit distanceUnit) {
        this.value = value;
        this.distanceUnit = distanceUnit;
    }

    // Methods: You should remove the comments and complete the method bodies for all the methods below
    //          Think that all these methods are in the class for a reason, so you should use them all
    //          DO NOT MODIFY the signature of these methods!

    public double getValue() {
        // TODO Add your own code here to get the value of this Distance
        // return the value directly
        return this.value;
    }

    public double getValue(DistanceUnit unit) {
        // TODO Add your own code here to indicate in which units you request the class to return the distance
        // return the value directly when the unit is same with the distanceUnit
        if (unit.equals(this.distanceUnit)) {
            return this.value;
        }
        // convert the value into correct unit and return.
        if (unit.equals(DistanceUnit.KILOMETRES)) {
            return convertToKilometres(this.value);
        } else {
            return convertToMiles(this.value);
        }
    }

    public void setValue(double newValue) {
        // TODO Add your own code here to set a new value (the DistanceUnit should *not* change, however)
        this.value = newValue;
    }

    public DistanceUnit getDistanceUnit() {
        // TODO Add your own code here to return the unit of this Distance
        return this.distanceUnit;
    }

    public void changeDistanceUnit(DistanceUnit newDistanceUnit) {
        // TODO Add your own code here to set the unit of this Distance, and change the distance value accordingly.
        if (this.distanceUnit.equals(newDistanceUnit)) {
            return;
        }
        if (newDistanceUnit.equals(DistanceUnit.KILOMETRES)) {
            this.value = this.value * KMS_PER_MILE;
        } else {
            this.value = this.value * MILES_PER_KM;
        }
        this.distanceUnit = newDistanceUnit;
    }

    public static double convertToMiles(double kilometres) {
        // TODO Add your own code here to convert kilometres into miles. Use one of the constants provided.
        return kilometres * MILES_PER_KM;
    }

    public static double convertToKilometres(double miles) {
        // TODO Add your own code here to convert miles into kilometres. Use one of the constants provided.
        return miles * KMS_PER_MILE;
    }

    public String toString() {
        // TODO Add your own code here to display the contents of this Distance object
        if (this.distanceUnit.equals(DistanceUnit.KILOMETRES)) {
            return String.format("%f km", this.value);
        } else {
            return String.format("%f mile", this.value);
        }
    }


}
