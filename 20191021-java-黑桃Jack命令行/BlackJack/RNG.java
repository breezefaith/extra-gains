import java.util.*;
/**
 * Write a description of class RNG here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RNG
{
    // instance variables - replace the example below with your own
    private int maximumValue;
    private int minimumValue;
    public RNG(){
      this.maximumValue = 0;
      this.minimumValue = 0;
    }
    public RNG(int maximumValue, int minimumValue){
      this.maximumValue = maximumValue;
      this.minimumValue = minimumValue;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public void setMinimumValue(int minimumValue) {
        this.minimumValue = minimumValue;
    }

    public int getMinimumValue() {
        return minimumValue;
    }

    public void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
    }



    public int roll(){
      Random ra = new Random();
      return ra.nextInt(maximumValue) + minimumValue;
    }

    
}
