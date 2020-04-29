package competition.management.system.dao;

import competition.management.system.entity.Grade;
import competition.management.system.entity.HosterAdmin;
import competition.management.system.entity.Member;
import competition.management.system.entity.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDao extends AbstractDao<Grade> {
    @Override
    public Grade findById(String id) throws SQLException {
        Grade grade = null;
        String sql = "select id, team_id, pgrade, lgrade, hoster_admin_id from grade where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            grade = new Grade();
            grade.setId(rs.getString(1));
            Team team = new Team();
            team.setId(rs.getString(2));
            grade.setTeam(team);
            grade.setPgrade(rs.getDouble(3));
            grade.setLgrade(rs.getDouble(4));
            HosterAdmin hosterAdmin = new HosterAdmin();
            hosterAdmin.setId(rs.getString(5));
            grade.setHosterAdmin(hosterAdmin);
        }
        return grade;
    }

    @Override
    public List<Grade> findAll() throws SQLException {
        ArrayList<Grade> grades = new ArrayList<>();
        Grade grade = null;
        String sql = "select id, team_id, pgrade, lgrade, hoster_admin_id from grade";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            grade = new Grade();
            grade.setId(rs.getString(1));
            Team team = new Team();
            team.setId(rs.getString(2));
            grade.setTeam(team);
            grade.setPgrade(rs.getDouble(3));
            grade.setLgrade(rs.getDouble(4));
            HosterAdmin hosterAdmin = new HosterAdmin();
            hosterAdmin.setId(rs.getString(5));
            grade.setHosterAdmin(hosterAdmin);

            grades.add(grade);
        }
        return grades;
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "delete from grade where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean insert(Grade entity) throws SQLException {
        String sql = "insert into grade(id, team_id, pgrade, lgrade, hoster_admin_id) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getId());
        ps.setString(2, entity.getTeam().getId());
        ps.setDouble(3, entity.getPgrade());
        ps.setDouble(4, entity.getLgrade());
        ps.setString(5, entity.getHosterAdmin().getId());
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean update(Grade entity) throws SQLException {
        String sql = "update grade set team_id=?, pgrade=?, lgrade=?, hoster_admin_id=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getTeam().getId());
        ps.setDouble(2, entity.getPgrade());
        ps.setDouble(3, entity.getLgrade());
        ps.setString(4, entity.getHosterAdmin().getId());
        ps.setString(5, entity.getId());
        return ps.executeUpdate() == 1;
    }

    public List<Grade> findByTeamId(String teamId) throws SQLException{
        ArrayList<Grade> grades = new ArrayList<>();
        String sql = "select id, team_id, pgrade, lgrade, hoster_admin_id from grade where team_id=?";
        PreparedStatement ps =connection.prepareStatement(sql);
        ps.setString(1,teamId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Grade grade = new Grade();
            grade.setId(rs.getString(1));
            Team team = new Team();
            team.setId(rs.getString(2));
            grade.setTeam(team);
            grade.setPgrade(rs.getDouble(3));
            grade.setLgrade(rs.getDouble(4));
            HosterAdmin hosterAdmin = new HosterAdmin();
            hosterAdmin.setId(rs.getString(5));
            grade.setHosterAdmin(hosterAdmin);

            grades.add(grade);
        }

        return grades;
    }
}
