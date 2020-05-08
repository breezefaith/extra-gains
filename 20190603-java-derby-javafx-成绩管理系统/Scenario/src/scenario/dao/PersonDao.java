package scenario.dao;

import scenario.entity.Person;
import scenario.entity.Role;
import scenario.jdbc.JDBCTool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    private Connection connection = JDBCTool.getConnection();

    public List<Person> findAll() throws SQLException {
        ArrayList<Person> list = new ArrayList<>();
        String sql = "select role.id, role.role_name, sarah.id, sarah.name, sarah.sex, sarah.age, sarah.location from sarah.sarah, sarah.role where sarah.rid = role.id";
        System.out.println(sql);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Role role = new Role();
            role.setId(resultSet.getString(1));
            role.setRoleName(resultSet.getString(2));
            Person person = new Person();
            person.setId(resultSet.getString(3));
            person.setName(resultSet.getString(4));
            person.setSex(resultSet.getString(5));
            person.setRole(role);
            person.setAge(resultSet.getInt(6));
            person.setLocation(resultSet.getString(7));

            list.add(person);
        }
        resultSet.close();
        statement.close();
        return list;
    }

    public int delete(Person person) throws SQLException {
        String sql = "delete from SARAH.SARAH where ID='" + person.getId()+"'";
        System.out.println(sql);
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }

    public int add(Person person) throws SQLException {
        String sql = "insert into SARAH.SARAH(ID,NAME,SEX,AGE,RID,LOCATION) values ('"+ person.getId() + "','" + person.getName() + "', '" + person.getSex() + "', " + person.getAge() + ", '" + person.getRole().getId() + "', '" + person.getLocation() + "')";
        Statement statement = connection.createStatement();
        System.out.println(sql);
        return statement.executeUpdate(sql);
    }
}
