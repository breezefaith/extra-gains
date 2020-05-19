package iTravel.adapter;

import iTravel.entity.Flight;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlightAdapter {
    private Connection connection;

    public FlightAdapter(Connection connection) {
        this.connection = connection;
    }

    public boolean addFlight(Flight flight) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "insert into FLIGHT(FLIGHT_NUMBER,ORIGIN,DESTINATION,BUSINESS_SEATS,EXECUTIVE_SEATS,BUSSINESS_FARE,EXECUTIVE_FARE,AIRCRAFT_TYPE) " +
                "values ('" + flight.getFlightNumber() + "','" + flight.getOrigin() + "','" + flight.getDestination() + "'," + flight.getBusinessSeats() + "," + flight.getExecutiveSeats() +
                "," + flight.getBusinessFare() + "," + flight.getExecutiveFare() + ",'" + flight.getAircraftType() + "')";
        return st.execute(sql);
    }

    public boolean removeFlight(Flight flight) throws SQLException{
        Statement st = connection.createStatement();
        String sql = "delete from FLIGHT where FLIGHT_NUMBER='"+flight.getFlightNumber()+"'";
        return st.execute(sql);
    }

    public List<Flight> findAll() throws SQLException{
        ArrayList<Flight> list=new ArrayList<>();
        Statement st=connection.createStatement();
        String sql="select * from FLIGHT";
        ResultSet rs=st.executeQuery(sql);
        while (rs.next()){
            Flight flight=new Flight();
            flight.setFlightNumber(rs.getString(1));
            flight.setOrigin(rs.getString(2));
            flight.setDestination(rs.getString(3));
            flight.setBusinessSeats(rs.getInt(4));
            flight.setExecutiveSeats(rs.getInt(5));
            flight.setBusinessFare(rs.getDouble(6));
            flight.setExecutiveFare(rs.getDouble(7));
            flight.setAircraftType(rs.getString(8));
        }
        return list;
    }
}
