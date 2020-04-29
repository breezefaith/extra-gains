/*
 * Created by JFormDesigner on Sat Dec 21 14:19:14 CST 2019
 */

package competition.management.system.view.hoster;

import competition.management.system.dao.HosterAdminDao;
import competition.management.system.dao.SchoolAdminDao;
import competition.management.system.entity.HosterAdmin;
import competition.management.system.entity.SchoolAdmin;
import competition.management.system.util.JDBCUtil;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

/**
 * @author zzc
 */
public class AddSchoolAdminFrame extends JFrame {
    private HosterMainFrame hosterMainFrame;

    private final SchoolAdminDao schoolAdminDao = JDBCUtil.getSchoolAdminDao();

    public AddSchoolAdminFrame() {
        initComponents();
    }

    public void load() {
        textField_id.setText(UUID.randomUUID().toString());
    }

    public HosterMainFrame getHosterMainFrame() {
        return hosterMainFrame;
    }

    public void setHosterMainFrame(HosterMainFrame hosterMainFrame) {
        this.hosterMainFrame = hosterMainFrame;
    }

    private void button_addActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.AddSchoolAdminFrame");
        SchoolAdmin schoolAdmin = new SchoolAdmin();
        schoolAdmin.setId(textField_id.getText());
        schoolAdmin.setCode(textField_code.getText());
        schoolAdmin.setName(textField_name.getText());
        schoolAdmin.setSchool(textField_school.getText());
        schoolAdmin.setPassword(String.valueOf(passwordField.getPassword()));

        try {
            if (schoolAdminDao.insert(schoolAdmin)) {
                JOptionPane.showMessageDialog(this, bundle.getString("AddSchoolAdminFrame.alert.add.success"), "", JOptionPane.INFORMATION_MESSAGE);
                hosterMainFrame.load();
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, bundle.getString("AddSchoolAdminFrame.alert.add.error"), "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, bundle.getString("AddSchoolAdminFrame.alert.add.error") + "\n" + ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.AddSchoolAdminFrame");
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        button_add = new JButton();
        textField_id = new JTextField();
        textField_code = new JTextField();
        textField_name = new JTextField();
        textField_school = new JTextField();
        passwordField = new JPasswordField();

        //======== this ========
        setTitle(bundle.getString("AddSchoolAdminFrame.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText(bundle.getString("AddSchoolAdminFrame.label1.text"));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(75, 55), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText(bundle.getString("AddSchoolAdminFrame.label2.text"));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(65, 90), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText(bundle.getString("AddSchoolAdminFrame.label3.text"));
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(65, 130), label3.getPreferredSize()));

        //---- label4 ----
        label4.setText(bundle.getString("AddSchoolAdminFrame.label4.text"));
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(65, 170), label4.getPreferredSize()));

        //---- label5 ----
        label5.setText(bundle.getString("AddSchoolAdminFrame.label5.text"));
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(65, 215), label5.getPreferredSize()));

        //---- button_add ----
        button_add.setText(bundle.getString("AddSchoolAdminFrame.button_add.text"));
        button_add.addActionListener(e -> button_addActionPerformed(e));
        contentPane.add(button_add);
        button_add.setBounds(new Rectangle(new Point(105, 275), button_add.getPreferredSize()));

        //---- textField_id ----
        textField_id.setEditable(false);
        contentPane.add(textField_id);
        textField_id.setBounds(105, 50, 135, textField_id.getPreferredSize().height);
        contentPane.add(textField_code);
        textField_code.setBounds(105, 85, 135, 30);
        contentPane.add(textField_name);
        textField_name.setBounds(105, 125, 135, 30);
        contentPane.add(textField_school);
        textField_school.setBounds(105, 165, 135, 30);
        contentPane.add(passwordField);
        passwordField.setBounds(105, 210, 135, passwordField.getPreferredSize().height);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
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
        setSize(290, 365);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JButton button_add;
    private JTextField textField_id;
    private JTextField textField_code;
    private JTextField textField_name;
    private JTextField textField_school;
    private JPasswordField passwordField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
