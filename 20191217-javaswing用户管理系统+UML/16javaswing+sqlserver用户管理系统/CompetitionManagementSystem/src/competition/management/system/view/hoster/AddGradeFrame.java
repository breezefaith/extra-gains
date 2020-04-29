/*
 * Created by JFormDesigner on Sat Dec 21 14:25:32 CST 2019
 */

package competition.management.system.view.hoster;

import competition.management.system.dao.GradeDao;
import competition.management.system.entity.Grade;
import competition.management.system.entity.HosterAdmin;
import competition.management.system.entity.Team;
import competition.management.system.util.JDBCUtil;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

/**
 * @author zzc
 */
public class AddGradeFrame extends JFrame {
    private HosterAdmin hosterAdmin;
    private Team team;
    private TeamInfoFrame teamInfoFrame;

    private final GradeDao gradeDao = JDBCUtil.getGradeDao();

    public AddGradeFrame() {
        initComponents();
    }

    public HosterAdmin getHosterAdmin() {
        return hosterAdmin;
    }

    public void setHosterAdmin(HosterAdmin hosterAdmin) {
        this.hosterAdmin = hosterAdmin;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public TeamInfoFrame getTeamInfoFrame() {
        return teamInfoFrame;
    }

    public void setTeamInfoFrame(TeamInfoFrame teamInfoFrame) {
        this.teamInfoFrame = teamInfoFrame;
    }

    private void button_addActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.AddGradeFrame");

        Grade grade = new Grade();
        grade.setId(UUID.randomUUID().toString());
        grade.setHosterAdmin(hosterAdmin);
        grade.setTeam(team);
        grade.setPgrade(Double.valueOf(textField_pgrade.getText()));
        grade.setLgrade(Double.valueOf(textField_lgrade.getText()));

        try {
            if (gradeDao.insert(grade)) {
                JOptionPane.showMessageDialog(this, bundle.getString("AddGradeFrame.alert.add.success"), "", JOptionPane.INFORMATION_MESSAGE);
                teamInfoFrame.load();
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, bundle.getString("AddGradeFrame.alert.add.error"), "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, bundle.getString("AddGradeFrame.alert.add.error") + "\n" + ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.AddGradeFrame");
        textField_pgrade = new JTextField();
        textField_lgrade = new JTextField();
        button_add = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textField_pgrade);
        textField_pgrade.setBounds(140, 40, 145, textField_pgrade.getPreferredSize().height);
        contentPane.add(textField_lgrade);
        textField_lgrade.setBounds(140, 90, 145, textField_lgrade.getPreferredSize().height);

        //---- button_add ----
        button_add.setText(bundle.getString("AddGradeFrame.button_add.text"));
        button_add.addActionListener(e -> button_addActionPerformed(e));
        contentPane.add(button_add);
        button_add.setBounds(new Rectangle(new Point(125, 145), button_add.getPreferredSize()));

        //---- label1 ----
        label1.setText(bundle.getString("AddGradeFrame.label1.text"));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(70, 45), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText(bundle.getString("AddGradeFrame.label2.text"));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(70, 95), label2.getPreferredSize()));

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
        setSize(320, 245);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField textField_pgrade;
    private JTextField textField_lgrade;
    private JButton button_add;
    private JLabel label1;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
