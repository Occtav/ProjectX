package SongOLayin.DataPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// establish connection with the database
public class ProjectXConnection {

    Connection conn;

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn == null) {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/manele?user=root&password=password&serverTimezone=UTC");
        }
        return conn;
    }


}
