package queries;

public class FamilyRecord {
    public final String name;
    public final int birthYear;
    public final String city;
    public final String state;
    public final String job;

    public FamilyRecord(String n, int y, String c, String s, String j) {
        name = n;
        birthYear = y;
        city = c;
        state = s;
        job = j;
    }

    @Override
    public String toString(){
        return "Family record(Name=" + name + ", Birth Year=" + birthYear + ", City=" + city + ", State=" + state + ", Job=" + job + ")";
    }

}
