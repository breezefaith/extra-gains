package iTravel.adapter;

import iTravel.AlertController;
import iTravel.entity.EmployeeProfile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeProfileAdapter {
    private Connection connection;

    public EmployeeProfileAdapter(Connection connection) {
        this.connection = connection;
    }

    public boolean addProfile(EmployeeProfile profile) throws SQLException {
        Statement st=connection.createStatement();
        String sql="insert into employee_profile(employee_number,employee_name) values('"+profile.getEmployeeNumber()+"','"+profile.getEmployeeName()+"')";
        return st.execute(sql);
    }

    public boolean removeProfile(EmployeeProfile profile) throws SQLException {
        Statement st=connection.createStatement();
        String sql="delete from employee_profile where employee_number='"+profile.getEmployeeNumber()+"'";
        return st.execute(sql);
    }

    public boolean modifyProfile(EmployeeProfile profile) throws SQLException{
        Statement st=connection.createStatement();
        String sql="update EMPLOYEE_PROFILE set EMPLOYEE_NAME='"+profile.getEmployeeName()+"' where employee_number='"+profile.getEmployeeNumber()+"'";
        return st.execute(sql);
    }

    public List<EmployeeProfile> findAll() throws SQLException{
        ArrayList<EmployeeProfile> list=new ArrayList<>();
        Statement st=connection.createStatement();
        String sql="select * from employee_profile";
        ResultSet rs=st.executeQuery(sql);
        while (rs.next()){
            EmployeeProfile profile=new EmployeeProfile();
            profile.setEmployeeNumber(rs.getString(1));
            profile.setEmployeeName(rs.getString(2));
            list.add(profile);
        }
        return list;
    }

    public EmployeeProfile findByEmployeeNumber(String employeeNumber)throws SQLException{
        Statement st=connection.createStatement();
        EmployeeProfile profile=null;
        String sql="select * from employee_profile where EMPLOYEE_NUMBER='"+employeeNumber+"'";
        ResultSet rs=st.executeQuery(sql);
        while (rs.next()){
            profile=new EmployeeProfile();
            profile.setEmployeeNumber(rs.getString(1));
            profile.setEmployeeName(rs.getString(2));
        }
        return profile;
    }
}
