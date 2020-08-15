package banking.database.dao;

import banking.database.util.JDBCUtil;

import java.sql.Connection;

public class AbstractDao {
    protected Connection connection = JDBCUtil.getConnection();
}
