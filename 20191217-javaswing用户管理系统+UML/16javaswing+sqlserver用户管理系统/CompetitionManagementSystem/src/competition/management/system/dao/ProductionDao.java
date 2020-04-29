package competition.management.system.dao;

import competition.management.system.entity.Production;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionDao extends AbstractDao<Production> {
    @Override
    public Production findById(String id) throws SQLException {
        Production production = null;
        String sql = "select id, code, name, description, path from production where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            production = new Production();
            production.setId(rs.getString(1));
            production.setCode(rs.getString(2));
            production.setName(rs.getString(3));
            production.setDescription(rs.getString(4));
            production.setPath(rs.getString(5));
        }

        return production;
    }

    @Override
    public List<Production> findAll() throws SQLException {
        ArrayList<Production> productions = new ArrayList<>();
        Production production = null;
        String sql = "select id, code, name, description, path from production";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            production = new Production();
            production.setId(rs.getString(1));
            production.setCode(rs.getString(2));
            production.setName(rs.getString(3));
            production.setDescription(rs.getString(4));
            production.setPath(rs.getString(5));

            productions.add(production);
        }

        return productions;
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "delete from production where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean insert(Production entity) throws SQLException {
        String sql = "insert into production(id, code, name, description, path) values(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getId());
        ps.setString(2, entity.getCode());
        ps.setString(3, entity.getName());
        ps.setString(4, entity.getDescription());
        ps.setString(5, entity.getPath());
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean update(Production entity) throws SQLException {
        String sql = "update production set code=?, name=?, description=?, path=? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getCode());
        ps.setString(2, entity.getName());
        ps.setString(3, entity.getDescription());
        ps.setString(4, entity.getPath());
        ps.setString(5, entity.getId());
        return ps.executeUpdate() == 1;
    }
}
