package banking.database.dao;

import banking.database.entity.Transaction;
import banking.database.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AbstractDao {
    protected Connection connection = JDBCUtil.getConnection();

    public void setAutoTransaction(boolean auto) throws SQLException {
        connection.setAutoCommit(auto);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public boolean insertTransaction(Transaction transaction) throws SQLException {
        String sql = "insert into TRANSACTIONS(\"ACCOUNT_TYPE\", \"ACCOUNT_NUM\", \"TRANS_DATE\", \"TRANS_AMT\", \"TRANS_TYPE\", \"trans_comments\") values(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, transaction.getAccountType().ordinal());
        ps.setString(2, transaction.getAccount().getAccountNum());
        ps.setDate(3, transaction.getTransDate());
        ps.setDouble(4, transaction.getTransAmt());
        ps.setString(5, transaction.getTransComments());

        return ps.executeLargeUpdate() == 1;
    }
}
