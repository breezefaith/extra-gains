package competition.management.system.dao;

import competition.management.system.entity.HosterAdmin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HosterAdminDao extends AbstractDao<HosterAdmin> {
    @Override
    public HosterAdmin findById(String id) throws SQLException {
        HosterAdmin hosterAdmin = null;
        String sql = "select id, code, name, school, password from hoster_admin where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            hosterAdmin = new HosterAdmin();
            hosterAdmin.setId(rs.getString(1));
            hosterAdmin.setCode(rs.getString(2));
            hosterAdmin.setName(rs.getString(3));
            hosterAdmin.setSchool(rs.getString(4));
            hosterAdmin.setPassword(rs.getString(5));
        }
        return hosterAdmin;
    }

    @Override
    public List<HosterAdmin> findAll() throws SQLException {
        ArrayList<HosterAdmin> hosterAdmins = new ArrayList<>();
        HosterAdmin hosterAdmin = null;
        String sql = "select id, code, name, school, password from hoster_admin";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            hosterAdmin = new HosterAdmin();
            hosterAdmin.setId(rs.getString(1));
            hosterAdmin.setCode(rs.getString(2));
            hosterAdmin.setName(rs.getString(3));
            hosterAdmin.setSchool(rs.getString(4));
            hosterAdmin.setPassword(rs.getString(5));

            hosterAdmins.add(hosterAdmin);
        }
        return hosterAdmins;
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "delete from hoster_admin where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean insert(HosterAdmin entity) throws SQLException {
        String sql = "insert into hoster_admin(id, code, name, school, password) values(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getId());
        ps.setString(2, entity.getCode());
        ps.setString(3, entity.getName());
        ps.setString(4, entity.getSchool());
        ps.setString(5, entity.getPassword());
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean update(HosterAdmin entity) throws SQLException {
        String sql = "update hoster_admin set code=?, name=?, school=?, password=? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getCode());
        ps.setString(2, entity.getName());
        ps.setString(3, entity.getSchool());
        ps.setString(4, entity.getPassword());
        ps.setString(5, entity.getId());
        return ps.executeUpdate() == 1;
    }

    public HosterAdmin findByUsernameAndPassword(String username, String password) throws SQLException {
        HosterAdmin hosterAdmin = null;
        String sql = "select id, code, name, school, password from hoster_admin where code = ? and password = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            hosterAdmin = new HosterAdmin();
            hosterAdmin.setId(rs.getString(1));
            hosterAdmin.setCode(rs.getString(2));
            hosterAdmin.setName(rs.getString(3));
            hosterAdmin.setSchool(rs.getString(4));
            hosterAdmin.setPassword(rs.getString(5));
        }
        return hosterAdmin;
    }
}
