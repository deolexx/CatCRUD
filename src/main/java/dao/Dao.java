package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Base data access interface
 *
 * @param <T>  - generic param type for Data Access Object
 * @param <ID> - type of the primary key field
 */
public interface Dao<T, ID> {
    /**
     * List of basic methods for
     * CRUD actions
     */
    Optional<T> find(ID id) throws SQLException, ClassNotFoundException;

    List<T> findAll() throws SQLException, ClassNotFoundException;

    boolean save(T o) throws SQLException, ClassNotFoundException;

    boolean update(T o) throws SQLException, ClassNotFoundException;

    boolean delete(T o) throws SQLException, ClassNotFoundException;
}
