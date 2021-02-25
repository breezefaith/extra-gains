/**
 * HeartRate.java
 * <p>
 * Copyright (c) University of Sheffield, 2021
 *
 * @version 1.3 11/01/2021
 * @author Maria-Cruz Villa-Uriol
 * @author Ben Clegg
 */

package uk.ac.sheffield.com1003.problemsheet1;

public class HeartRate {

    // Instance variables
    private double value;

    // Constructor
    public HeartRate(double value) {
        this.value = value;
    }

    // Methods; you should remove the block comments and complete the method bodies

    public double getValue() {
        // TODO Add your own code here to get the value of this object (the heart rate)
        return this.value;
    }

    public void setValue(double newValue) {
        // TODO Add your own code here to set the the value for this object (the heart rate)
        this.value = newValue;
    }

    public String toString() {
        // TODO Add your own code here to display the contents of this HeartRate object
        return String.format("%f", this.value);
    }

}
