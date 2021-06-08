package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public class JDBCManager {
    private JDBCManagerUtils jdbcManagerUtils;

    public JDBCManager(JDBCManagerUtils jdbcManagerUtils) {
        this.jdbcManagerUtils = jdbcManagerUtils;
    }

    public <T> T doInTransaction (Function<Connection, T> operation){
        T results = null;
        Connection connection = jdbcManagerUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            results = operation.apply(connection);
            connection.commit();
        } catch (SQLException e) {
            jdbcManagerUtils.rollbackConnection(connection);
        }finally {
            jdbcManagerUtils.closeConnection(connection);
        }
        return results;
    }
}
