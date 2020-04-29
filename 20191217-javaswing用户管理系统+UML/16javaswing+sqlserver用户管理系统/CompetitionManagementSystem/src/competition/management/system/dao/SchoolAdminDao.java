package competition.management.system.dao;

import competition.management.system.entity.SchoolAdmin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolAdminDao extends AbstractDao<SchoolAdmin> {
    @Override
    public SchoolAdmin findById(String id) throws SQLException {
        SchoolAdmin schoolAdmin = null;
        String sql = "select id, code, name, school, password from school_admin where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            schoolAdmin = new SchoolAdmin();
            schoolAdmin.setId(rs.getString(1));
            schoolAdmin.setCode(rs.getString(2));
            schoolAdmin.setName(rs.getString(3));
            schoolAdmin.setSchool(rs.getString(4));
            schoolAdmin.setPassword(rs.getString(5));
        }

        return schoolAdmin;
    }

    @Override
    public List<SchoolAdmin> findAll() throws SQLException {
        ArrayList<SchoolAdmin> schoolAdmins = new ArrayList<>();
        SchoolAdmin schoolAdmin = null;
        String sql = "select id, code, name, school, password from school_admin";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            schoolAdmin = new SchoolAdmin();
            schoolAdmin.setId(rs.getString(1));
            schoolAdmin.setCode(rs.getString(2));
            schoolAdmin.setName(rs.getString(3));
            schoolAdmin.setSchool(rs.getString(4));
            schoolAdmin.setPassword(rs.getString(5));

            schoolAdmins.add(schoolAdmin);
        }

        return schoolAdmins;
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "delete from school_admin where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean insert(SchoolAdmin entity) throws SQLException {
        String sql = "insert into school_admin(id, code, name, school, password) values(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getId());
        ps.setString(2, entity.getCode());
        ps.setString(3, entity.getName());
        ps.setString(4, entity.getSchool());
        ps.setString(5, entity.getPassword());
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean update(SchoolAdmin entity) throws SQLException {
        String sql = "update school_admin set code=?, name=?, school=?, password=? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getCode());
        ps.setString(2, entity.getName());
        ps.setString(3, entity.getSchool());
        ps.setString(4, entity.getPassword());
        ps.setString(5, entity.getId());
        return ps.executeUpdate() == 1;
    }

    public SchoolAdmin findByUsernameAndPassword(String username, String password) throws SQLException {
        SchoolAdmin schoolAdmin = null;
        String sql = "select id, code, name, school, password from school_admin where code = ? and password = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            schoolAdmin = new SchoolAdmin();
            schoolAdmin.setId(rs.getString(1));
            schoolAdmin.setCode(rs.getString(2));
            schoolAdmin.setName(rs.getString(3));
            schoolAdmin.setSchool(rs.getString(4));
            schoolAdmin.setPassword(rs.getString(5));
        }
        return schoolAdmin;
    }

    public boolean changePassword(String id, String newPassword) throws SQLException {
        String sql = "update school_admin set password=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, newPassword);
        ps.setString(2, id);
        return ps.executeUpdate() == 1;
    }

    public List<SchoolAdmin> findBySchoolName(String schoolName) throws SQLException {
        ArrayList<SchoolAdmin> schoolAdmins = new ArrayList<>();
        SchoolAdmin schoolAdmin = null;
        String sql = "select id, code, name, school, password from school_admin where school = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, schoolName);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            schoolAdmin = new SchoolAdmin();
            schoolAdmin.setId(rs.getString(1));
            schoolAdmin.setCode(rs.getString(2));
            schoolAdmin.setName(rs.getString(3));
            schoolAdmin.setSchool(rs.getString(4));
            schoolAdmin.setPassword(rs.getString(5));

            schoolAdmins.add(schoolAdmin);
        }

        return schoolAdmins;
    }
}
