package scenario.dao;

import scenario.jdbc.JDBCTool;

import java.sql.Connection;

public class StudentDao {
    private Connection connection = JDBCTool.getConnection();
}
