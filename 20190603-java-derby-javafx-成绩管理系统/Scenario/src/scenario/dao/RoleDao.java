package scenario.dao;

import scenario.entity.Role;
import scenario.jdbc.JDBCTool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {
    private Connection connection = JDBCTool.getConnection();

    public List<Role> findAll() throws SQLException {
        List<Role> list = new ArrayList<>();
        String sql = "select role.ID,role.ROLE_NAME from SARAH.ROLE";
        System.out.println(sql);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Role role = new Role();
            role.setId(resultSet.getString(1));
            role.setRoleName(resultSet.getString(2));

            list.add(role);
        }
        resultSet.close();
        statement.close();
        return list;
    }
}
