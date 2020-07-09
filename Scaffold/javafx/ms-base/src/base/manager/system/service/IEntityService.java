package base.manager.system.service;

import base.manager.system.entity.AbstractEntity;

import java.sql.SQLException;
import java.util.List;

public interface IEntityService<T extends AbstractEntity> {
    boolean insert(T entity) throws SQLException;

    boolean update(T entity) throws SQLException;

    boolean remove(String id) throws SQLException;

    T findById(String id) throws SQLException;

    List<T> findAll() throws SQLException;
}
