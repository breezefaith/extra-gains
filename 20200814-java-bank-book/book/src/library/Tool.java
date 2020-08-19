package library;

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
                String driver = "oracle.jdbc.driver.OracleDriver";
                String url = "";
                String user = "";
                String password = "";

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
