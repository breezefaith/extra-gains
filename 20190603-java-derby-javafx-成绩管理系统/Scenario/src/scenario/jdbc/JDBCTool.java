package scenario.jdbc;

import scenario.Main;
import scenario.dao.GradeDao;
import scenario.dao.PersonDao;
import scenario.dao.RoleDao;
import scenario.dao.StudentDao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTool {
    private static Connection connection = null;
    private static PersonDao personDao = new PersonDao();
    private static StudentDao studentDao = new StudentDao();
    private static RoleDao roleDao = new RoleDao();
    private static GradeDao gradeDao = new GradeDao();

    private JDBCTool() {

    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties properties = new Properties();
                properties.load(Main.class.getResourceAsStream("jdbc/jdbc.properties"));
                Class.forName(properties.getProperty("driver"));
                connection = DriverManager.getConnection(properties.getProperty("url"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static PersonDao getPersonDao() {
        return personDao;
    }

    public static StudentDao getStudentDao() {
        return studentDao;
    }

    public static RoleDao getRoleDao() {
        return roleDao;
    }

    public static GradeDao getGradeDao() {
        return gradeDao;
    }

    private void initiallize() throws IOException, SQLException {
        URL url = Main.class.getResource("sql/StudentSarah.sql");
        BufferedReader reader = new BufferedReader(new FileReader(url.getPath()));
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        String sql=null;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line);
            if (line.endsWith(";")) {
                sql = stringBuffer.toString();
                System.out.println(sql.substring(0,sql.length()-1));
                statement.execute(sql.substring(0,sql.length()-1));
                stringBuffer.delete(0, stringBuffer.length());
            }
            stringBuffer.append("\n");
        }
        System.out.println("initiallizing success!");
    }

    public static void main(String[] args) throws IOException, SQLException {
        new JDBCTool().initiallize();
    }
}
