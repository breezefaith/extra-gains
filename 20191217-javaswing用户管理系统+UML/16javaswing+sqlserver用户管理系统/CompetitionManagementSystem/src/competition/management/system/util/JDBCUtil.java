package competition.management.system.util;

import competition.management.system.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class JDBCUtil {
    private static Connection connection = null;
    private static String url;
    private static String driver;
    private static String username;
    private static String password;

    private static SchoolAdminDao schoolAdminDao = null;
    private static MemberDao memberDao = null;
    private static TeamDao teamDao = null;
    private static ProductionDao productionDao = null;
    private static TeacherDao teacherDao = null;
    private static GradeDao gradeDao = null;
    private static HosterAdminDao hosterAdminDao = null;

    private JDBCUtil() {

    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                ResourceBundle resource = ResourceBundle.getBundle("competition/management/system/config/jdbc");
                driver = resource.getString("driver");
                url = resource.getString("url");
                username = resource.getString("username");
                password = resource.getString("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        return connection;
    }

    public static SchoolAdminDao getSchoolAdminDao() {
        if (schoolAdminDao == null) {
            schoolAdminDao = new SchoolAdminDao();
        }
        return schoolAdminDao;
    }

    public static MemberDao getMemberDao() {
        if (memberDao == null) {
            memberDao = new MemberDao();
        }
        return memberDao;
    }

    public static TeamDao getTeamDao() {
        if (teamDao == null) {
            teamDao = new TeamDao();
        }
        return teamDao;
    }

    public static TeacherDao getTeacherDao() {
        if (teacherDao == null) {
            teacherDao = new TeacherDao();
        }
        return teacherDao;
    }

    public static ProductionDao getProductionDao() {
        if (productionDao == null) {
            productionDao = new ProductionDao();
        }
        return productionDao;
    }

    public static GradeDao getGradeDao() {
        if (gradeDao == null) {
            gradeDao = new GradeDao();
        }
        return gradeDao;
    }

    public static HosterAdminDao getHosterAdminDao() {
        if (hosterAdminDao == null) {
            hosterAdminDao = new HosterAdminDao();
        }
        return hosterAdminDao;
    }
}
