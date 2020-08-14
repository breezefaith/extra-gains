package banking.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCUtil {
    private static Connection connection;

    public static synchronized Connection getConnection() {
        try {
            if (connection == null) {
                ResourceBundle bundle = ResourceBundle.getBundle("banking/database/config/jdbc");
                String driver = bundle.getString("database.driver");
                String url = bundle.getString("database.url");
                String user = bundle.getString("database.user");
                String password = bundle.getString("database.password");

                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
