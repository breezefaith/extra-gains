import java.util.Calendar;

/**
 * (E) Compile the test program and create a police officer with the police number 10034,
 * the name is "Jackie chan", the salary is 3000, the army is the 7th Brigade of the
 * Armed Police, the rank is the third Police supervisor. Set the working time as April 1,
 * 2004.Assume a raise every two years, modify his current salary according to the current
 * system time, and output his personal information.
 */
public class Test {
    public static void main(String[] args) {
        ArmedPolice armedPolice = new ArmedPolice("10034", "Jackie chan", 3000, "the 7th Brigade of the Armed Police", "the third Police supervisor");
        Calendar workCalendar = Calendar.getInstance();
        workCalendar.set(2004, 3, 1);
        armedPolice.setWorkDate(workCalendar.getTime());

        workCalendar.setTime(armedPolice.getWorkDate());

        Calendar current = Calendar.getInstance();

        for (int i = 0; i < current.get(Calendar.YEAR) - workCalendar.get(Calendar.YEAR); i += 2) {
            armedPolice.Raise();
        }

        System.out.println(armedPolice.toString());
    }
}
