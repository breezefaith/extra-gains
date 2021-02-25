/**
 * Steps.java
 * <p>
 * Copyright (c) University of Sheffield, 2021
 *
 * @version 1.3 11/01/2021
 * @author Maria-Cruz Villa-Uriol
 * @author Ben Clegg
 */

package uk.ac.sheffield.com1003.problemsheet1;

public class Steps {

    // Instance variables
    private int value;

    // Constructor
    public Steps(int value) {
        this.value = value;
    }

    // Methods; you should remove the comments and complete the method bodies

    public int getValue() {
        // TODO Add your own code here to get the value of this object (the number of steps)
        return this.value;
    }

    public void setValue(int newValue) {
        // TODO Add your own code here to set the the value for this object (the number of steps)
        this.value = newValue;
    }

    public String toString() {
        // TODO Add your own code here to display the contents of this Steps object
        return String.format("%d steps", this.value);
    }

}
