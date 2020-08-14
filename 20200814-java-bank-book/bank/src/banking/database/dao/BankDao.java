package banking.database.dao;

import banking.database.entity.Branch;
import banking.database.entity.Customer;
import banking.database.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDao {
    private Connection connection = JDBCUtil.getConnection();

    public boolean insertCustomer(Customer customer) throws SQLException {
        String sql = "insert into CUSTOMERS(name, address, phone) values (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, customer.getName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPhone());

        return ps.execute();
    }

    public boolean deleteCustomer(Customer customer) throws SQLException {
        String sql = "delete from CUSTOMERS where CUSTOMER_NUM = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customer.getNum());

        return ps.execute();
    }

    public boolean insertBranch(Branch branch) throws SQLException {
        String sql = "insert into BRANCHES(branch_name, address) values (?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, branch.getName());
        ps.setString(2, branch.getAddress());

        return ps.execute();
    }

    public Branch findBranchByName(String name) throws SQLException {
        String sql = "select BRANCH_NUM, branch_name, address from BRANCHES where branch_name = ? limit 1";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);

        ResultSet resultSet = ps.executeQuery();
        Branch branch = null;
        while (resultSet.next()) {
            branch = new Branch();
            branch.setNum(resultSet.getInt(1));
            branch.setName(resultSet.getString(2));
            branch.setAddress(resultSet.getString(3));
        }
        return branch;
    }
}
