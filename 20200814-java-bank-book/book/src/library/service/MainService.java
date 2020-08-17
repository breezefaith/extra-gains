package library.service;

import library.tools.Tool;

import java.sql.Connection;
import java.sql.SQLException;

public class MainService {
    Connection con = Tool.getConnection();

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
