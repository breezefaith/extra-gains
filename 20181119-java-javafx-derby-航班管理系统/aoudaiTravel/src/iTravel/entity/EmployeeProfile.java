package iTravel.entity;

public class EmployeeProfile {
    private String employeeNumber;
    private String employeeName;

    public EmployeeProfile() {
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    @Override
    public String toString() {
        return "EmployeeProfile{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", employeeName='" + employeeName + '\'' +
                '}';
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
