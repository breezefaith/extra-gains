package ems.dao;

import ems.entity.Employee;
import ems.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private Connection connection = JDBCUtil.getConnection();


    public List<Employee> findAll() throws SQLException {
        ArrayList<Employee> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from EMPLOYEE";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEid(rs.getString(1));
                employee.setName(rs.getString(2));
                employee.setCountry(rs.getString(3));
                employee.setProvince(rs.getString(4));
                employee.setCity(rs.getString(5));
                employee.setPostalCode(rs.getString(6));
                employee.setStreetAddress(rs.getString(7));

                list.add(employee);
            }

            return list;
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    public Employee findById(String eid) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;
        String sql = "select * from EMPLOYEE where EID=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, eid);
            rs = ps.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setEid(rs.getString(1));
                employee.setName(rs.getString(2));
                employee.setCountry(rs.getString(3));
                employee.setProvince(rs.getString(4));
                employee.setCity(rs.getString(5));
                employee.setPostalCode(rs.getString(6));
                employee.setStreetAddress(rs.getString(7));
            }

            return employee;
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    public int insert(Employee employee) throws SQLException {
        PreparedStatement ps = null;
        String sql = "insert into employee values(?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getEid());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getCountry());
            ps.setString(4, employee.getProvince());
            ps.setString(5, employee.getCity());
            ps.setString(6, employee.getPostalCode());
            ps.setString(7, employee.getStreetAddress());

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
    }

    public int update(Employee employee) throws SQLException {
        PreparedStatement ps = null;
        String sql = "update employee set NAME=?, COUNTRY=?, PROVINCE=?,CITY=?,POSTAL_CODE=?,STREET_ADDRESS=? where EID = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getCountry());
            ps.setString(3, employee.getProvince());
            ps.setString(4, employee.getCity());
            ps.setString(5, employee.getPostalCode());
            ps.setString(6, employee.getStreetAddress());
            ps.setString(7, employee.getEid());

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
    }

    public int delete(String eid) throws SQLException {
        PreparedStatement ps = null;
        String sql = "delete from EMPLOYEE where EID = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, eid);

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
    }
}
