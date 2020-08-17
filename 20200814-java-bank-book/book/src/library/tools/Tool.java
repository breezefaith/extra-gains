package library.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

public class Tool {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null) {
                ResourceBundle bundle = ResourceBundle.getBundle("library/config/jdbc");
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

    public static String getFixLenthString(int strLength) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strLength; i++) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
