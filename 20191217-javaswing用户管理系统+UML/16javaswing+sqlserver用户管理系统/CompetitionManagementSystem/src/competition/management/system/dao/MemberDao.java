package competition.management.system.dao;

import competition.management.system.entity.Member;
import competition.management.system.entity.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao extends AbstractDao<Member> {
    @Override
    public Member findById(String id) throws SQLException {
        Member member = null;
        String sql = "select id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader from member where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            member = new Member();
            member.setId(rs.getString(1));
            member.setIdCard(rs.getString(2));
            member.setName(rs.getString(3));
            member.setPhone(rs.getString(4));
            member.setEmail(rs.getString(5));
            member.setSex(rs.getString(6));
            member.setCls(rs.getString(7));
            member.setDepartment(rs.getString(8));
            member.setSchool(rs.getString(9));
            member.setPassword(rs.getString(10));
            Team team = new Team();
            team.setId(rs.getString(11));
            member.setTeam(team);
            member.setLeader(rs.getBoolean(12));
        }
        return member;
    }

    @Override
    public List<Member> findAll() throws SQLException {
        ArrayList<Member> members = new ArrayList<>();
        Member member = null;
        String sql = "select id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader from member";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            member = new Member();
            member.setId(rs.getString(1));
            member.setIdCard(rs.getString(2));
            member.setName(rs.getString(3));
            member.setPhone(rs.getString(4));
            member.setEmail(rs.getString(5));
            member.setSex(rs.getString(6));
            member.setCls(rs.getString(7));
            member.setDepartment(rs.getString(8));
            member.setSchool(rs.getString(9));
            member.setPassword(rs.getString(10));
            Team team = new Team();
            team.setId(rs.getString(11));
            member.setTeam(team);
            member.setLeader(rs.getBoolean(12));

            members.add(member);
        }
        return members;
    }

    @Override
    public boolean remove(String id) throws SQLException {
        String sql = "delete from member where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean insert(Member entity) throws SQLException {
        String sql = "insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getId());
        ps.setString(2, entity.getIdCard());
        ps.setString(3, entity.getName());
        ps.setString(4, entity.getPhone());
        ps.setString(5, entity.getEmail());
        ps.setString(6, entity.getSex());
        ps.setString(7, entity.getCls());
        ps.setString(8, entity.getDepartment());
        ps.setString(9, entity.getSchool());
        ps.setString(10, entity.getPassword());
        ps.setString(11, entity.getTeam().getId());
        ps.setBoolean(12, entity.getLeader());
        return ps.executeUpdate() == 1;
    }

    @Override
    public boolean update(Member entity) throws SQLException {
        String sql = "update member set id_card=?, name=?, phone=?, email=?, sex=?, class=?, department=?, school=?, password=?, team_id=?, is_leader=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entity.getIdCard());
        ps.setString(2, entity.getName());
        ps.setString(3, entity.getPhone());
        ps.setString(4, entity.getEmail());
        ps.setString(5, entity.getSex());
        ps.setString(6, entity.getCls());
        ps.setString(7, entity.getDepartment());
        ps.setString(8, entity.getSchool());
        ps.setString(9, entity.getPassword());
        ps.setString(10, entity.getTeam().getId());
        ps.setBoolean(11, entity.getLeader());
        ps.setString(12, entity.getId());
        return ps.executeUpdate() == 1;
    }

    public Member findByUsernameAndPassword(String username, String password) throws SQLException {
        Member member = null;
        String sql = "select id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader from member where id_card = ? and password = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            member = new Member();
            member.setId(rs.getString(1));
            member.setIdCard(rs.getString(2));
            member.setName(rs.getString(3));
            member.setPhone(rs.getString(4));
            member.setEmail(rs.getString(5));
            member.setSex(rs.getString(6));
            member.setCls(rs.getString(7));
            member.setDepartment(rs.getString(8));
            member.setSchool(rs.getString(9));
            member.setPassword(rs.getString(10));
            Team team = new Team();
            team.setId(rs.getString(11));
            member.setTeam(team);
            member.setLeader(rs.getBoolean(12));
        }
        return member;
    }

    public boolean changePassword(String id, String newPassword) throws SQLException {
        String sql = "update member set password=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, newPassword);
        ps.setString(2, id);
        return ps.executeUpdate() == 1;
    }

    public List<Member> findByTeamId(String teamId) throws SQLException{
        ArrayList<Member> members = new ArrayList<>();
        Member member = null;
        String sql = "select id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader from member where team_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, teamId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            member = new Member();
            member.setId(rs.getString(1));
            member.setIdCard(rs.getString(2));
            member.setName(rs.getString(3));
            member.setPhone(rs.getString(4));
            member.setEmail(rs.getString(5));
            member.setSex(rs.getString(6));
            member.setCls(rs.getString(7));
            member.setDepartment(rs.getString(8));
            member.setSchool(rs.getString(9));
            member.setPassword(rs.getString(10));
            Team team = new Team();
            team.setId(rs.getString(11));
            member.setTeam(team);
            member.setLeader(rs.getBoolean(12));

            members.add(member);
        }
        return members;
    }
}
