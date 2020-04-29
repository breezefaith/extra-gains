/*
 * Created by JFormDesigner on Thu Dec 19 08:23:22 CST 2019
 */

package competition.management.system.view.school;

import java.awt.event.*;
import competition.management.system.dao.MemberDao;
import competition.management.system.entity.Member;
import competition.management.system.entity.SchoolAdmin;
import competition.management.system.util.JDBCUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author zzc
 */
public class PersonalInfoFrame extends JFrame {
    private SchoolAdmin schoolAdmin;
    private Member member;
    private TeamInfoFrame teamInfoFrame;

    private final MemberDao memberDao = JDBCUtil.getMemberDao();

    public PersonalInfoFrame() {
        initComponents();
    }

    public void load() {
        textField_id.setText(member.getId());
        textField_team.setText(member.getTeam().getName());
        textField_isLeader.setText(member.getLeader() ? "是" : "否");
        textField_idCard.setText(member.getIdCard());
        textField_name.setText(member.getName());
        textField_sex.setText(member.getSex());
        textField_phone.setText(member.getPhone());
        textField_email.setText(member.getEmail());
        textField_school.setText(member.getSchool());
        textField_department.setText(member.getDepartment());
        textField_class.setText(member.getCls());
    }

    public SchoolAdmin getSchoolAdmin() {
        return schoolAdmin;
    }

    public void setSchoolAdmin(SchoolAdmin schoolAdmin) {
        this.schoolAdmin = schoolAdmin;
    }

    public TeamInfoFrame getTeamInfoFrame() {
        return teamInfoFrame;
    }

    public void setTeamInfoFrame(TeamInfoFrame teamInfoFrame) {
        this.teamInfoFrame = teamInfoFrame;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    private void buttonUpdateActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.school.PersonalInfoFrame");
        member.setIdCard(textField_idCard.getText());
        member.setName(textField_name.getText());
        member.setPhone(textField_phone.getText());
        member.setEmail(textField_email.getText());
        member.setSchool(textField_school.getText());
        member.setDepartment(textField_department.getText());
        member.setCls(textField_class.getText());
        member.setSex(textField_sex.getText());

        try {
            if (memberDao.update(member) == true) {
                JOptionPane.showMessageDialog(this, bundle.getString("PersonalInfoFrame.success"), "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, bundle.getString("PersonalInfoFrame.fail"), "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void thisWindowClosing(WindowEvent e) {
        if(teamInfoFrame != null){
            teamInfoFrame.load();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.school.PersonalInfoFrame");
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        button_update = new JButton();
        textField_id = new JTextField();
        textField_team = new JTextField();
        textField_idCard = new JTextField();
        textField_name = new JTextField();
        textField_phone = new JTextField();
        textField_email = new JTextField();
        textField_sex = new JTextField();
        textField_school = new JTextField();
        textField_department = new JTextField();
        textField_class = new JTextField();
        label11 = new JLabel();
        textField_isLeader = new JTextField();

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText(bundle.getString("PersonalInfoFrame.label1.text"));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(90, 45), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText(bundle.getString("PersonalInfoFrame.label2.text"));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(55, 155), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText(bundle.getString("PersonalInfoFrame.label3.text"));
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(80, 190), label3.getPreferredSize()));

        //---- label4 ----
        label4.setText(bundle.getString("PersonalInfoFrame.label4.text"));
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(70, 225), label4.getPreferredSize()));

        //---- label5 ----
        label5.setText(bundle.getString("PersonalInfoFrame.label5.text"));
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(80, 265), label5.getPreferredSize()));

        //---- label6 ----
        label6.setText(bundle.getString("PersonalInfoFrame.label6.text"));
        contentPane.add(label6);
        label6.setBounds(new Rectangle(new Point(80, 300), label6.getPreferredSize()));

        //---- label7 ----
        label7.setText(bundle.getString("PersonalInfoFrame.label7.text"));
        contentPane.add(label7);
        label7.setBounds(new Rectangle(new Point(80, 335), label7.getPreferredSize()));

        //---- label8 ----
        label8.setText(bundle.getString("PersonalInfoFrame.label8.text"));
        contentPane.add(label8);
        label8.setBounds(new Rectangle(new Point(80, 370), label8.getPreferredSize()));

        //---- label9 ----
        label9.setText(bundle.getString("PersonalInfoFrame.label9.text"));
        contentPane.add(label9);
        label9.setBounds(new Rectangle(new Point(80, 410), label9.getPreferredSize()));

        //---- label10 ----
        label10.setText(bundle.getString("PersonalInfoFrame.label10.text"));
        contentPane.add(label10);
        label10.setBounds(80, 80, 35, label10.getPreferredSize().height);

        //---- button_update ----
        button_update.setText(bundle.getString("PersonalInfoFrame.button_update.text"));
        button_update.addActionListener(e -> buttonUpdateActionPerformed(e));
        contentPane.add(button_update);
        button_update.setBounds(new Rectangle(new Point(155, 460), button_update.getPreferredSize()));

        //---- textField_id ----
        textField_id.setEditable(false);
        contentPane.add(textField_id);
        textField_id.setBounds(135, 35, 170, textField_id.getPreferredSize().height);

        //---- textField_team ----
        textField_team.setEditable(false);
        contentPane.add(textField_team);
        textField_team.setBounds(135, 75, 170, 30);
        contentPane.add(textField_idCard);
        textField_idCard.setBounds(135, 150, 170, 30);
        contentPane.add(textField_name);
        textField_name.setBounds(135, 185, 170, textField_name.getPreferredSize().height);
        contentPane.add(textField_phone);
        textField_phone.setBounds(135, 220, 170, 30);
        contentPane.add(textField_email);
        textField_email.setBounds(135, 255, 170, 30);
        contentPane.add(textField_sex);
        textField_sex.setBounds(135, 295, 170, 30);
        contentPane.add(textField_school);
        textField_school.setBounds(135, 330, 170, 30);
        contentPane.add(textField_department);
        textField_department.setBounds(135, 365, 170, 30);
        contentPane.add(textField_class);
        textField_class.setBounds(135, 405, 170, 30);

        //---- label11 ----
        label11.setText(bundle.getString("PersonalInfoFrame.label11.text"));
        contentPane.add(label11);
        label11.setBounds(45, 120, 65, label11.getPreferredSize().height);

        //---- textField_isLeader ----
        textField_isLeader.setEditable(false);
        contentPane.add(textField_isLeader);
        textField_isLeader.setBounds(135, 115, 170, textField_isLeader.getPreferredSize().height);

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
        setSize(395, 550);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JButton button_update;
    private JTextField textField_id;
    private JTextField textField_team;
    private JTextField textField_idCard;
    private JTextField textField_name;
    private JTextField textField_phone;
    private JTextField textField_email;
    private JTextField textField_sex;
    private JTextField textField_school;
    private JTextField textField_department;
    private JTextField textField_class;
    private JLabel label11;
    private JTextField textField_isLeader;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
