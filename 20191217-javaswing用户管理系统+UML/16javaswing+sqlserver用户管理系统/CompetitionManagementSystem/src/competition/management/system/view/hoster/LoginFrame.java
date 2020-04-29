/*
 * Created by JFormDesigner on Wed Dec 18 17:08:58 CST 2019
 */

package competition.management.system.view.hoster;

import competition.management.system.dao.*;
import competition.management.system.entity.HosterAdmin;
import competition.management.system.util.JDBCUtil;
import competition.management.system.util.LoginStatusUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author zzc
 */
public class LoginFrame extends JFrame {
    private ResourceBundle bundle;

    private final MemberDao memberDao = JDBCUtil.getMemberDao();
    private final TeamDao teamDao = JDBCUtil.getTeamDao();
    private final TeacherDao teacherDao = JDBCUtil.getTeacherDao();
    private final ProductionDao productionDao = JDBCUtil.getProductionDao();
    private final SchoolAdminDao schoolAdminDao = JDBCUtil.getSchoolAdminDao();
    private final GradeDao gradeDao = JDBCUtil.getGradeDao();
    private final HosterAdminDao hosterAdminDao = JDBCUtil.getHosterAdminDao();

    public LoginFrame() {
        initComponents();

        //textField_username.setText("C12344");
        //passwordField_password.setText("aaaaaa");
    }

    private void button1ActionPerformed(ActionEvent e) {
        try {
            HosterAdmin hosterAdmin = hosterAdminDao.findByUsernameAndPassword(textField_username.getText(), String.copyValueOf(passwordField_password.getPassword()));
            this.setVisible(false);
            LoginStatusUtil.HOSTER_ADMIN = hosterAdmin;
            HosterMainFrame hosterMainFrame = new HosterMainFrame();
            hosterMainFrame.setHosterAdmin(hosterAdmin);
            hosterMainFrame.load();
            hosterMainFrame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.LoginFrame");
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        textField_username = new JTextField();
        passwordField_password = new JPasswordField();
        button_login = new JButton();
        label_error = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText(bundle.getString("LoginFrame.label1.text"));
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(140, 25), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText(bundle.getString("LoginFrame.label2.text"));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(100, 100), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText(bundle.getString("LoginFrame.label3.text"));
        contentPane.add(label3);
        label3.setBounds(100, 145, 39, 17);
        contentPane.add(textField_username);
        textField_username.setBounds(150, 95, 165, textField_username.getPreferredSize().height);
        contentPane.add(passwordField_password);
        passwordField_password.setBounds(150, 140, 165, passwordField_password.getPreferredSize().height);

        //---- button_login ----
        button_login.setText(bundle.getString("LoginFrame.button_login.text"));
        button_login.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button_login);
        button_login.setBounds(new Rectangle(new Point(150, 210), button_login.getPreferredSize()));

        //---- label_error ----
        label_error.setText(bundle.getString("LoginFrame.label_error.text"));
        label_error.setForeground(new Color(255, 51, 0));
        label_error.setVisible(false);
        contentPane.add(label_error);
        label_error.setBounds(145, 180, 115, label_error.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(400, 300));
        setSize(400, 300);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textField_username;
    private JPasswordField passwordField_password;
    private JButton button_login;
    private JLabel label_error;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
