/**
 * (C) Design a class named ArmedPolice, inherit the police class and implement the employee interface
 */
public class ArmedPolice extends Policeman implements Employee {
    /**
     * (1) A String type property, representing the name of the army to which it belongs.
     */
    private String army;
    /**
     * (2) A String type property , representing the rank.
     */
    private String rank;

    /**
     * (3) The constructor to create the object according to the specified number, name, salary, army and rank (the time to create the object is the date to start work).
     * @param policeNumber
     * @param name
     * @param salary
     * @param army
     * @param rank
     */
    public ArmedPolice(String policeNumber, String name, double salary, String army, String rank) {
        super(policeNumber, name, salary);
        this.army = army;
        this.rank = rank;
    }

    /**
     * (4) Realize the abstract method raise to increase the salary attribute by 30%.
     */
    @Override
    public void Raise() {
        setSalary(getSalary() * 1.3);
    }

    /**
     * (5) Override the inherited toString() method and output "Number + name + years of service + salary + army + rank".
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " + " + army + " + " + rank;
    }
}
