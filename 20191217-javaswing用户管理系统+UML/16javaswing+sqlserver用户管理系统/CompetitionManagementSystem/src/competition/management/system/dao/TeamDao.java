package competition.management.system.dao;

import competition.management.system.entity.Production;
import competition.management.system.entity.SchoolAdmin;
import competition.management.system.entity.Teacher;
import competition.management.system.entity.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDao extends AbstractDao<Team> {
    public Team findById(String id) throws SQLException {
        Team team = null;
        String sql = "select id, code, name, group_in, school, school_admin_id, teacher_id, production_id from team where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            team = new Team();
            team.setId(rs.getString(1));
            team.setCode(rs.getString(2));
            team.setName(rs.getString(3));
            team.setGroup(rs.getString(4));
            team.setSchool(rs.getString(5));
            SchoolAdmin schoolAdmin = new SchoolAdmin();
            schoolAdmin.setId(rs.getString(6));
            team.setSchoolAdmin(schoolAdmin);
            Teacher teacher = new Teacher();
            teacher.setId(rs.getString(7));
            team.setTeacher(teacher);
            Production production = new Production();
            production.setId(rs.getString(8));
            team.setProduction(production);
        }
        return team;
    }

    @Override
    public List<Team> findAll() throws SQLException {
        ArrayList<Team> teams = new ArrayList<>();
        String sql = "select id, code, name, group_in, school, school_admin_id, teacher_id, production_id from team";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Team team = new Team();
            team.setId(rs.getString(1));
            team.setCode(rs.getString(2));
            team.setName(rs.getString(3));
            team.setGroup(rs.getString(4));
            team.setSchool(rs.getString(5));
            SchoolAdmin schoolAdmin = new SchoolAdmin();
            schoolAdmin.setId(rs.getString(6));
            team.setSchoolAdmin(schoolAdmin);
            Teacher teacher = new Teacher();
            teacher.setId(rs.getString(7));
            team.setTeacher(teacher);
            Production production = new Production();
            production.setId(rs.getString(8));
            team.setProduction(production);

            teams.add(team);
        }
        return teams;
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "delete from team where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean insert(Team entity) throws SQLException {
        String sql = "insert into team(id, code, name, group_in, school, school_admin_id, teacher_id, production_id) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getId());
        ps.setString(2, entity.getCode());
        ps.setString(3, entity.getName());
        ps.setString(4, entity.getGroup());
        ps.setString(5, entity.getSchool());
        ps.setString(6, entity.getSchoolAdmin().getId());
        ps.setString(7, entity.getTeacher().getId());
        ps.setString(8, entity.getProduction().getId());
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean update(Team entity) throws SQLException {
        String sql = "update team set code=?, name=?, group_in=?, school=?, school_admin_id=?, teacher_id=?, production_id=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getCode());
        ps.setString(2, entity.getName());
        ps.setString(3, entity.getGroup());
        ps.setString(4, entity.getSchool());
        ps.setString(5, entity.getSchoolAdmin().getId());
        ps.setString(6, entity.getTeacher().getId());
        ps.setString(7, entity.getProduction().getId());
        ps.setString(8, entity.getId());
        return ps.executeUpdate() == 1;
    }

    public List<Team> findBySchoolName(String school) throws SQLException {
        ArrayList<Team> teams = new ArrayList<>();
        String sql = "select id, code, name, group_in, school, school_admin_id, teacher_id, production_id from team where school = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, school);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Team team = new Team();
            team.setId(rs.getString(1));
            team.setCode(rs.getString(2));
            team.setName(rs.getString(3));
            team.setGroup(rs.getString(4));
            team.setSchool(rs.getString(5));
            SchoolAdmin schoolAdmin = new SchoolAdmin();
            schoolAdmin.setId(rs.getString(6));
            team.setSchoolAdmin(schoolAdmin);
            Teacher teacher = new Teacher();
            teacher.setId(rs.getString(7));
            team.setTeacher(teacher);
            Production production = new Production();
            production.setId(rs.getString(8));
            team.setProduction(production);

            teams.add(team);
        }
        return teams;
    }
}
