/*
 * Created by JFormDesigner on Wed Dec 18 17:44:35 CST 2019
 */

package competition.management.system.view.school;

import competition.management.system.dao.SchoolAdminDao;
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
public class ChangePasswordFrame extends JFrame {
    private final SchoolAdminDao schoolAdminDao = JDBCUtil.getSchoolAdminDao();

    public ChangePasswordFrame() {
        initComponents();
    }

    private void buttonChangeActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.school.ChangePasswordFrame");
        try {
            if (schoolAdminDao.changePassword(LoginStatusUtil.SCHOOL_ADMIN_LOGGED_IN.getId(), String.copyValueOf(passwordField_new.getPassword()))) {
                LoginStatusUtil.SCHOOL_ADMIN_LOGGED_IN.setPassword(String.copyValueOf(passwordField_new.getPassword()));
                JOptionPane.showMessageDialog(this.getContentPane(), bundle.getString("ChangePasswordFrame.success"), "", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this.getContentPane(), bundle.getString("ChangePasswordFrame.fail"), "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.school.ChangePasswordFrame");
        label1 = new JLabel();
        passwordField_new = new JPasswordField();
        button_change = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText(bundle.getString("ChangePasswordFrame.label1.text"));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(100, 95), label1.getPreferredSize()));
        contentPane.add(passwordField_new);
        passwordField_new.setBounds(155, 90, 135, passwordField_new.getPreferredSize().height);

        //---- button_change ----
        button_change.setText(bundle.getString("ChangePasswordFrame.button_change.text"));
        button_change.addActionListener(e -> buttonChangeActionPerformed(e));
        contentPane.add(button_change);
        button_change.setBounds(140, 160, 95, button_change.getPreferredSize().height);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(400, 300);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JPasswordField passwordField_new;
    private JButton button_change;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
