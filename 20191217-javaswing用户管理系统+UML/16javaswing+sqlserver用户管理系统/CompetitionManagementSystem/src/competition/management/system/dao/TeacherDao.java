package competition.management.system.dao;

import competition.management.system.entity.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao extends AbstractDao<Teacher> {
    @Override
    public Teacher findById(String id) throws SQLException {
        Teacher teacher = null;
        String sql = "select id, code, name, phone, email, title, department, school from teacher where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            teacher = new Teacher();
            teacher.setId(rs.getString(1));
            teacher.setCode(rs.getString(2));
            teacher.setName(rs.getString(3));
            teacher.setPhone(rs.getString(4));
            teacher.setEmail(rs.getString(5));
            teacher.setTitle(rs.getString(6));
            teacher.setDepartment(rs.getString(7));
            teacher.setSchool(rs.getString(8));
        }

        return teacher;
    }

    @Override
    public List<Teacher> findAll() throws SQLException {
        ArrayList<Teacher> teachers = new ArrayList<>();
        Teacher teacher = null;
        String sql = "select id, code, name, phone, email, title, department, school from teacher";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            teacher = new Teacher();
            teacher.setId(rs.getString(1));
            teacher.setCode(rs.getString(2));
            teacher.setName(rs.getString(3));
            teacher.setPhone(rs.getString(4));
            teacher.setEmail(rs.getString(5));
            teacher.setTitle(rs.getString(6));
            teacher.setDepartment(rs.getString(7));
            teacher.setSchool(rs.getString(8));

            teachers.add(teacher);
        }

        return teachers;
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "delete from teacher where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean insert(Teacher entity) throws SQLException {
        String sql = "insert into teacher(id, code, name, phone, email, title, department, school) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getId());
        ps.setString(2, entity.getCode());
        ps.setString(3, entity.getName());
        ps.setString(4, entity.getPhone());
        ps.setString(5, entity.getEmail());
        ps.setString(6, entity.getTitle());
        ps.setString(7, entity.getDepartment());
        ps.setString(8, entity.getSchool());
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean update(Teacher entity) throws SQLException {
        String sql = "update teacher set code=?, name=?, phone=?, email=?, title=?, department=?, school=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getCode());
        ps.setString(2, entity.getName());
        ps.setString(3, entity.getPhone());
        ps.setString(4, entity.getEmail());
        ps.setString(5, entity.getTitle());
        ps.setString(6, entity.getDepartment());
        ps.setString(7, entity.getSchool());
        ps.setString(8, entity.getId());
        return ps.executeUpdate() == 1;
    }
}
