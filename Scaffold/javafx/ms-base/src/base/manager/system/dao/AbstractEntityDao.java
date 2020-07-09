package base.manager.system.dao;

import base.manager.system.util.JDBCUtil;

import javax.swing.text.html.parser.Entity;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractEntityDao<T extends Entity> {
    private final Connection connection = JDBCUtil.getConnection();

    public abstract int insert(T entity) throws SQLException;

    public abstract int update(T entity) throws SQLException;

    public abstract int remove(String id) throws SQLException;

    public abstract T findById(String id) throws SQLException;

    public abstract List<T> findAll() throws SQLException;
}
