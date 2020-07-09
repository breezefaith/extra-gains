import java.util.Calendar;
import java.util.Date;

/**
 * (A) Design a class called Policeman
 */
public class Policeman {
    /**
     * (1) Three private property: police number, name and salary.
     */
    private String policeNumber;
    private String name;
    private double salary;
    /**
     * (2) A private property which is date type, and represents the date of work.
     */
    private Date workDate;

    /**
     * (3) The constructor to create the object according to the specified number, name and salary (the time when the object is created is the date of starting work).
     * @param policeNumber
     * @param name
     * @param salary
     */
    public Policeman(String policeNumber, String name, double salary) {
        this.policeNumber = policeNumber;
        this.name = name;
        this.salary = salary;
        this.workDate = new Date();
    }

    /**
     * (4) Accessors for all properties.
     * @return
     */
    public String getPoliceNumber() {
        return policeNumber;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Date getWorkDate() {
        return workDate;
    }

    /**
     * (5) Modifier of salary property.
     * @param salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * (6) Modifier for date of work property.
     * @param workDate
     */
    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    /**
     * (7) Override the inherited toString() method and output "Number + name + years of service + salary".
     * @return
     */
    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(policeNumber + " + ");
        stringBuffer.append(name + " + ");

        Calendar calendar = Calendar.getInstance();
        int y1 = calendar.get(Calendar.YEAR);
        calendar.setTime(workDate);
        int y2 = calendar.get(Calendar.YEAR);
        stringBuffer.append((y1 - y2) + " + ");

        stringBuffer.append(salary);

        return stringBuffer.toString();
    }
}
