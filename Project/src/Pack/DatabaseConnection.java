
package Pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:src/sqliteDataBaseDependencies/carRental.db");
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException("Failed to connect to the database", e);
            }
        }
        return connection;
    }
}