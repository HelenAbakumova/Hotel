package db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class JDBCManagerUtils {
private DataSource dataSource;

    public JDBCManagerUtils(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void closeConnection (Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollbackConnection(Connection connection){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
