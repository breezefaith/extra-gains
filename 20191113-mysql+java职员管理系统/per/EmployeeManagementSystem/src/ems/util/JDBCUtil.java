package ems.util;

import ems.dao.EmployeeDao;
import ems.entity.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCUtil {
    private static Connection connection = null;
    private static String url;
    private static String driver;
    private static String username;
    private static String password;

    private final static EmployeeDao employeeDao = new EmployeeDao();

    private JDBCUtil() {

    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                ResourceBundle resource = ResourceBundle.getBundle("jdbc");
                driver = resource.getString("driver");
                url = resource.getString("url");
                username = resource.getString("username");
                password = resource.getString("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
            }
        }
        return connection;
    }

    public static EmployeeDao getEmployeeDao(){
        return employeeDao;
    }
}

