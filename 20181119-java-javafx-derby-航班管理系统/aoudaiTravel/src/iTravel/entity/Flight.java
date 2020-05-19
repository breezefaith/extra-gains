package iTravel.entity;

public class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int businessSeats;
    private int executiveSeats;
    private double businessFare;
    private double executiveFare;
    private String aircraftType;

    public Flight() {

    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getBusinessSeats() {
        return businessSeats;
    }

    public void setBusinessSeats(int businessSeats) {
        this.businessSeats = businessSeats;
    }

    public int getExecutiveSeats() {
        return executiveSeats;
    }

    public void setExecutiveSeats(int executiveSeats) {
        this.executiveSeats = executiveSeats;
    }

    public double getBusinessFare() {
        return businessFare;
    }

    public void setBusinessFare(double businessFare) {
        this.businessFare = businessFare;
    }

    public double getExecutiveFare() {
        return executiveFare;
    }

    public void setExecutiveFare(double executiveFare) {
        this.executiveFare = executiveFare;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", businessSeats=" + businessSeats +
                ", executiveSeats=" + executiveSeats +
                ", businessFare=" + businessFare +
                ", executiveFare=" + executiveFare +
                ", aircraftType='" + aircraftType + '\'' +
                '}';
    }
}
