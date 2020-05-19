package scenario.dao;

import scenario.entity.Grade;
import scenario.entity.Student;
import scenario.jdbc.JDBCTool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GradeDao {
    private Connection connection = JDBCTool.getConnection();


    public List<Grade> findByStudent(Student student) throws SQLException {
        List<Grade> list = new ArrayList<>();
        String sql = "select gid,course,term,mark from SARAH.GRADE where sid='" + student.getId() + "'";
        System.out.println(sql);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Grade grade = new Grade();
            grade.setGid(resultSet.getString(1));
            grade.setSid(student.getId());
            grade.setCourse(resultSet.getString(2));
            grade.setTerm(resultSet.getString(3));
            grade.setMark(resultSet.getInt(4));

            list.add(grade);
        }
        resultSet.close();
        statement.close();
        return list;
    }

    public int add(Grade grade) throws SQLException {
        String sql = "insert into SARAH.GRADE(GID,SID,COURSE,TERM,MARK) values ('" + grade.getGid() + "','" + grade.getSid() + "', '" + grade.getCourse() + "', '" + grade.getTerm() + "', " + grade.getMark() + ")";
        Statement statement = connection.createStatement();
        System.out.println(sql);
        return statement.executeUpdate(sql);
    }

    public int delete(Grade grade) throws SQLException {
        String sql = "delete from SARAH.GRADE where GID='" + grade.getGid() + "'";
        System.out.println(sql);
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }

    public int deleteBySid(String sid) throws SQLException {
        String sql = "delete from SARAH.GRADE where SID='" + sid + "'";
        System.out.println(sql);
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }
}
