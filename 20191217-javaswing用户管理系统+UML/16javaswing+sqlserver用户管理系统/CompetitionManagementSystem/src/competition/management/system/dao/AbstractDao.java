package competition.management.system.dao;

import competition.management.system.entity.AbstractEntity;
import competition.management.system.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T extends AbstractEntity> {
    protected final Connection connection = JDBCUtil.getConnection();

    public abstract T findById(String id) throws SQLException;

    public abstract List<T> findAll() throws SQLException;

    public abstract boolean remove(String id) throws SQLException;

    public abstract boolean insert(T entity) throws SQLException;

    public abstract boolean update(T entity) throws SQLException;
}
