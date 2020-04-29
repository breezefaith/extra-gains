/*
 * Created by JFormDesigner on Sun Dec 22 09:40:54 CST 2019
 */

package competition.management.system.view.hoster;

import competition.management.system.dao.*;
import competition.management.system.entity.*;
import competition.management.system.util.JDBCUtil;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import javax.swing.*;

/**
 * @author zzc
 */
public class AddTeamFrame extends JFrame {
    private HosterMainFrame hosterMainFrame;

    private final SchoolAdminDao schoolAdminDao = JDBCUtil.getSchoolAdminDao();
    private final TeamDao teamDao = JDBCUtil.getTeamDao();
    private final MemberDao memberDao = JDBCUtil.getMemberDao();
    private final GradeDao gradeDao = JDBCUtil.getGradeDao();
    private final ProductionDao productionDao = JDBCUtil.getProductionDao();
    private final TeacherDao teacherDao = JDBCUtil.getTeacherDao();
    private final HosterAdminDao hosterAdminDao = JDBCUtil.getHosterAdminDao();

    public AddTeamFrame() {
        initComponents();
    }

    public void load(){
        textField_team_id.setText(UUID.randomUUID().toString());
        textField_teacher_id.setText(UUID.randomUUID().toString());
        textField_captain_id.setText(UUID.randomUUID().toString());
        textField_member1_id.setText(UUID.randomUUID().toString());
        textField_member2_id.setText(UUID.randomUUID().toString());

        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public HosterMainFrame getHosterMainFrame() {
        return hosterMainFrame;
    }

    public void setHosterMainFrame(HosterMainFrame hosterMainFrame) {
        this.hosterMainFrame = hosterMainFrame;
    }

    private void button1ActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.AddTeamFrame");

        try {
            JDBCUtil.getConnection().setAutoCommit(false);
            Teacher teacher = new Teacher();
            teacher.setId(textField_teacher_id.getText());
            teacher.setCode(textField_teacher_code.getText());
            teacher.setName(textField_teacher_name.getText());
            teacher.setTitle(textField_teacher_title.getText());
            teacher.setEmail(textField_teacher_email.getText());
            teacher.setPhone(textField_teacher_phone.getText());
            teacher.setSchool(textField_teacher_school.getText());
            teacher.setDepartment(textField_teacher_department.getText());

            teacherDao.insert(teacher);

            Team team = new Team();
            team.setId(textField_team_id.getText());
            team.setCode(textField_team_code.getText());
            team.setName(textField_team_name.getText());
            team.setGroup((String) comboBox_team_group.getSelectedItem());
            team.setSchool(textField_team_school.getText());
            List<SchoolAdmin> schoolAdmins = schoolAdminDao.findBySchoolName(team.getSchool());
            team.setSchoolAdmin(schoolAdmins.get(new Random().nextInt(schoolAdmins.size())));
            team.setTeacher(teacher);
            team.setProduction(new Production());

            teamDao.insert(team);

            Member cap = new Member();
            cap.setId(textField_captain_id.getText());
            cap.setIdCard(textField_captain_idCard.getText());
            cap.setName(textField_captain_name.getText());
            cap.setEmail(textField_captain_email.getText());
            cap.setPhone(textField_captain_phone.getText());
            cap.setSchool(textField_captain_school.getText());
            cap.setDepartment(textField_captain_department.getText());
            cap.setCls(textField_captain_class.getText());
            cap.setTeam(team);
            cap.setLeader(true);
            memberDao.insert(cap);

            Member member1 = new Member();
            member1.setId(textField_member1_id.getText());
            member1.setIdCard(textField_member1_idCard.getText());
            member1.setName(textField_member1_name.getText());
            member1.setEmail(textField_member1_email.getText());
            member1.setPhone(textField_member1_phone.getText());
            member1.setSchool(textField_member1_school.getText());
            member1.setDepartment(textField_member1_department.getText());
            member1.setCls(textField_member1_class.getText());
            member1.setTeam(team);
            member1.setLeader(true);
            memberDao.insert(member1);

            Member member2 = new Member();
            member2.setId(textField_member2_id.getText());
            member2.setIdCard(textField_member2_idCard.getText());
            member2.setName(textField_member2_name.getText());
            member2.setEmail(textField_member2_email.getText());
            member2.setPhone(textField_member2_phone.getText());
            member2.setSchool(textField_member2_school.getText());
            member2.setDepartment(textField_member2_department.getText());
            member2.setCls(textField_member2_class.getText());
            member2.setTeam(team);
            member2.setLeader(true);
            memberDao.insert(member2);

            JDBCUtil.getConnection().commit();
            setVisible(false);
            hosterMainFrame.load();
        } catch (SQLException ex) {
            try {
                JDBCUtil.getConnection().rollback();
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, bundle.getString("AddTeamFrame.alert.add.error") + "\n" + ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.AddTeamFrame");
        scrollPane1 = new JScrollPane();
        panel1 = new JPanel();
        label1 = new JLabel();
        textField_team_id = new JTextField();
        label2 = new JLabel();
        textField_team_code = new JTextField();
        textField_team_name = new JTextField();
        label4 = new JLabel();
        label5 = new JLabel();
        textField_team_school = new JTextField();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        comboBox_team_group = new JComboBox<>();
        label3 = new JLabel();
        label6 = new JLabel();
        textField_teacher_id = new JTextField();
        label10 = new JLabel();
        textField_teacher_code = new JTextField();
        label11 = new JLabel();
        textField_teacher_name = new JTextField();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();
        label16 = new JLabel();
        textField_teacher_phone = new JTextField();
        textField_teacher_email = new JTextField();
        textField_teacher_title = new JTextField();
        textField_teacher_school = new JTextField();
        textField_teacher_department = new JTextField();
        label18 = new JLabel();
        textField_captain_id = new JTextField();
        label19 = new JLabel();
        textField_captain_idCard = new JTextField();
        label20 = new JLabel();
        textField_captain_name = new JTextField();
        label21 = new JLabel();
        textField_captain_phone = new JTextField();
        label22 = new JLabel();
        textField_captain_email = new JTextField();
        label23 = new JLabel();
        textField_captain_school = new JTextField();
        label24 = new JLabel();
        textField_captain_department = new JTextField();
        label25 = new JLabel();
        textField_captain_class = new JTextField();
        label26 = new JLabel();
        textField_member1_id = new JTextField();
        label27 = new JLabel();
        textField_member1_idCard = new JTextField();
        label28 = new JLabel();
        textField_member1_name = new JTextField();
        label29 = new JLabel();
        textField_member1_phone = new JTextField();
        label30 = new JLabel();
        textField_member1_email = new JTextField();
        label31 = new JLabel();
        textField_member1_school = new JTextField();
        label32 = new JLabel();
        textField_member1_department = new JTextField();
        label33 = new JLabel();
        textField_member1_class = new JTextField();
        label34 = new JLabel();
        textField_member2_id = new JTextField();
        label35 = new JLabel();
        textField_member2_idCard = new JTextField();
        label36 = new JLabel();
        textField_member2_name = new JTextField();
        label37 = new JLabel();
        textField_member2_phone = new JTextField();
        label38 = new JLabel();
        textField_member2_email = new JTextField();
        label39 = new JLabel();
        textField_member2_school = new JTextField();
        label40 = new JLabel();
        textField_member2_department = new JTextField();
        label41 = new JLabel();
        textField_member2_class = new JTextField();
        toolBar1 = new JToolBar();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {
            scrollPane1.setAutoscrolls(true);

            //======== panel1 ========
            {
                panel1.setLayout(null);

                //---- label1 ----
                label1.setText(bundle.getString("AddTeamFrame.label1.text"));
                panel1.add(label1);
                label1.setBounds(25, 55, 40, 17);

                //---- textField_team_id ----
                textField_team_id.setEditable(false);
                panel1.add(textField_team_id);
                textField_team_id.setBounds(80, 45, 165, 30);

                //---- label2 ----
                label2.setText(bundle.getString("AddTeamFrame.label2.text_3"));
                panel1.add(label2);
                label2.setBounds(310, 50, 39, 17);
                panel1.add(textField_team_code);
                textField_team_code.setBounds(375, 45, 165, 30);
                panel1.add(textField_team_name);
                textField_team_name.setBounds(675, 45, 165, 30);

                //---- label4 ----
                label4.setText(bundle.getString("AddTeamFrame.label4.text_4"));
                panel1.add(label4);
                label4.setBounds(20, 100, 50, 17);

                //---- label5 ----
                label5.setText(bundle.getString("AddTeamFrame.label5.text_4"));
                panel1.add(label5);
                label5.setBounds(310, 100, 39, 17);
                panel1.add(textField_team_school);
                textField_team_school.setBounds(375, 95, 165, 30);

                //---- label7 ----
                label7.setText(bundle.getString("AddTeamFrame.label7.text_3"));
                label7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                panel1.add(label7);
                label7.setBounds(0, 0, 80, 27);

                //---- label8 ----
                label8.setText(bundle.getString("AddTeamFrame.label8.text_2"));
                label8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                panel1.add(label8);
                label8.setBounds(0, 145, 80, 27);

                //---- label9 ----
                label9.setText(bundle.getString("AddTeamFrame.label9.text_2"));
                label9.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
                panel1.add(label9);
                label9.setBounds(0, 310, 80, 27);

                //---- comboBox_team_group ----
                comboBox_team_group.setModel(new DefaultComboBoxModel<>(new String[] {
                    "A",
                    "B",
                    "C",
                    "D",
                    "E",
                    "F"
                }));
                panel1.add(comboBox_team_group);
                comboBox_team_group.setBounds(80, 95, 165, 30);

                //---- label3 ----
                label3.setText(bundle.getString("AddTeamFrame.label3.text_3"));
                panel1.add(label3);
                label3.setBounds(615, 50, 39, 17);

                //---- label6 ----
                label6.setText(bundle.getString("AddTeamFrame.label6.text_2"));
                panel1.add(label6);
                label6.setBounds(40, 190, 25, 17);

                //---- textField_teacher_id ----
                textField_teacher_id.setEditable(false);
                panel1.add(textField_teacher_id);
                textField_teacher_id.setBounds(80, 180, 165, 30);

                //---- label10 ----
                label10.setText(bundle.getString("AddTeamFrame.label10.text_2"));
                panel1.add(label10);
                label10.setBounds(310, 185, 39, 17);
                panel1.add(textField_teacher_code);
                textField_teacher_code.setBounds(375, 175, 165, 30);

                //---- label11 ----
                label11.setText(bundle.getString("AddTeamFrame.label11.text_2"));
                panel1.add(label11);
                label11.setBounds(615, 185, 39, 17);
                panel1.add(textField_teacher_name);
                textField_teacher_name.setBounds(675, 180, 165, 30);

                //---- label12 ----
                label12.setText(bundle.getString("AddTeamFrame.label12.text_2"));
                panel1.add(label12);
                label12.setBounds(30, 230, 40, 17);

                //---- label13 ----
                label13.setText(bundle.getString("AddTeamFrame.label13.text_2"));
                panel1.add(label13);
                label13.setBounds(310, 230, 39, 17);

                //---- label14 ----
                label14.setText(bundle.getString("AddTeamFrame.label14.text_2"));
                panel1.add(label14);
                label14.setBounds(615, 225, 39, 17);

                //---- label15 ----
                label15.setText(bundle.getString("AddTeamFrame.label15.text_2"));
                panel1.add(label15);
                label15.setBounds(30, 275, 40, 17);

                //---- label16 ----
                label16.setText(bundle.getString("AddTeamFrame.label16.text_2"));
                panel1.add(label16);
                label16.setBounds(310, 270, 39, 17);
                panel1.add(textField_teacher_phone);
                textField_teacher_phone.setBounds(80, 225, 165, 30);
                panel1.add(textField_teacher_email);
                textField_teacher_email.setBounds(375, 225, 165, 30);
                panel1.add(textField_teacher_title);
                textField_teacher_title.setBounds(675, 220, 165, 30);
                panel1.add(textField_teacher_school);
                textField_teacher_school.setBounds(80, 270, 165, 30);
                panel1.add(textField_teacher_department);
                textField_teacher_department.setBounds(375, 265, 165, 30);

                //---- label18 ----
                label18.setText(bundle.getString("AddTeamFrame.label18.text_2"));
                panel1.add(label18);
                label18.setBounds(15, 355, 50, 17);

                //---- textField_captain_id ----
                textField_captain_id.setEditable(false);
                panel1.add(textField_captain_id);
                textField_captain_id.setBounds(80, 345, 165, 30);

                //---- label19 ----
                label19.setText(bundle.getString("AddTeamFrame.label19.text_2"));
                panel1.add(label19);
                label19.setBounds(300, 350, 70, 17);
                panel1.add(textField_captain_idCard);
                textField_captain_idCard.setBounds(375, 340, 165, 30);

                //---- label20 ----
                label20.setText(bundle.getString("AddTeamFrame.label20.text_2"));
                panel1.add(label20);
                label20.setBounds(625, 350, 40, 17);
                panel1.add(textField_captain_name);
                textField_captain_name.setBounds(675, 345, 165, 30);

                //---- label21 ----
                label21.setText(bundle.getString("AddTeamFrame.label21.text_2"));
                panel1.add(label21);
                label21.setBounds(30, 395, 40, 17);
                panel1.add(textField_captain_phone);
                textField_captain_phone.setBounds(80, 390, 165, 30);

                //---- label22 ----
                label22.setText(bundle.getString("AddTeamFrame.label22.text_2"));
                panel1.add(label22);
                label22.setBounds(325, 395, 40, 17);
                panel1.add(textField_captain_email);
                textField_captain_email.setBounds(375, 390, 165, 30);

                //---- label23 ----
                label23.setText(bundle.getString("AddTeamFrame.label23.text_2"));
                panel1.add(label23);
                label23.setBounds(625, 395, 40, 17);
                panel1.add(textField_captain_school);
                textField_captain_school.setBounds(675, 390, 165, 30);

                //---- label24 ----
                label24.setText(bundle.getString("AddTeamFrame.label24.text_2"));
                panel1.add(label24);
                label24.setBounds(30, 440, 35, 17);
                panel1.add(textField_captain_department);
                textField_captain_department.setBounds(80, 435, 165, 30);

                //---- label25 ----
                label25.setText(bundle.getString("AddTeamFrame.label25.text_2"));
                panel1.add(label25);
                label25.setBounds(325, 440, 40, 17);
                panel1.add(textField_captain_class);
                textField_captain_class.setBounds(375, 435, 165, 30);

                //---- label26 ----
                label26.setText(bundle.getString("AddTeamFrame.label26.text_2"));
                panel1.add(label26);
                label26.setBounds(25, 495, 40, 17);

                //---- textField_member1_id ----
                textField_member1_id.setEditable(false);
                panel1.add(textField_member1_id);
                textField_member1_id.setBounds(80, 485, 165, 30);

                //---- label27 ----
                label27.setText(bundle.getString("AddTeamFrame.label27.text_2"));
                panel1.add(label27);
                label27.setBounds(300, 490, 70, 17);
                panel1.add(textField_member1_idCard);
                textField_member1_idCard.setBounds(375, 480, 165, 30);

                //---- label28 ----
                label28.setText(bundle.getString("AddTeamFrame.label28.text_2"));
                panel1.add(label28);
                label28.setBounds(630, 490, 35, 17);
                panel1.add(textField_member1_name);
                textField_member1_name.setBounds(675, 485, 165, 30);

                //---- label29 ----
                label29.setText(bundle.getString("AddTeamFrame.label29.text_2"));
                panel1.add(label29);
                label29.setBounds(30, 535, 35, 17);
                panel1.add(textField_member1_phone);
                textField_member1_phone.setBounds(80, 530, 165, 30);

                //---- label30 ----
                label30.setText(bundle.getString("AddTeamFrame.label30.text_2"));
                panel1.add(label30);
                label30.setBounds(325, 535, 35, 17);
                panel1.add(textField_member1_email);
                textField_member1_email.setBounds(375, 530, 165, 30);

                //---- label31 ----
                label31.setText(bundle.getString("AddTeamFrame.label31.text_2"));
                panel1.add(label31);
                label31.setBounds(625, 535, 35, 17);
                panel1.add(textField_member1_school);
                textField_member1_school.setBounds(675, 530, 165, 30);

                //---- label32 ----
                label32.setText(bundle.getString("AddTeamFrame.label32.text_2"));
                panel1.add(label32);
                label32.setBounds(30, 580, 35, 17);
                panel1.add(textField_member1_department);
                textField_member1_department.setBounds(80, 575, 165, 30);

                //---- label33 ----
                label33.setText(bundle.getString("AddTeamFrame.label33.text_2"));
                panel1.add(label33);
                label33.setBounds(325, 580, 35, 17);
                panel1.add(textField_member1_class);
                textField_member1_class.setBounds(375, 575, 165, 30);

                //---- label34 ----
                label34.setText(bundle.getString("AddTeamFrame.label34.text_2"));
                panel1.add(label34);
                label34.setBounds(25, 635, 40, 17);

                //---- textField_member2_id ----
                textField_member2_id.setEditable(false);
                panel1.add(textField_member2_id);
                textField_member2_id.setBounds(80, 625, 165, 30);

                //---- label35 ----
                label35.setText(bundle.getString("AddTeamFrame.label35.text_2"));
                panel1.add(label35);
                label35.setBounds(300, 630, 70, 17);
                panel1.add(textField_member2_idCard);
                textField_member2_idCard.setBounds(375, 620, 165, 30);

                //---- label36 ----
                label36.setText(bundle.getString("AddTeamFrame.label36.text_2"));
                panel1.add(label36);
                label36.setBounds(630, 630, 35, 17);
                panel1.add(textField_member2_name);
                textField_member2_name.setBounds(675, 625, 165, 30);

                //---- label37 ----
                label37.setText(bundle.getString("AddTeamFrame.label37.text_2"));
                panel1.add(label37);
                label37.setBounds(30, 675, 35, 17);
                panel1.add(textField_member2_phone);
                textField_member2_phone.setBounds(80, 670, 165, 30);

                //---- label38 ----
                label38.setText(bundle.getString("AddTeamFrame.label38.text_2"));
                panel1.add(label38);
                label38.setBounds(325, 675, 35, 17);
                panel1.add(textField_member2_email);
                textField_member2_email.setBounds(375, 670, 165, 30);

                //---- label39 ----
                label39.setText(bundle.getString("AddTeamFrame.label39.text_2"));
                panel1.add(label39);
                label39.setBounds(625, 675, 35, 17);
                panel1.add(textField_member2_school);
                textField_member2_school.setBounds(675, 670, 165, 30);

                //---- label40 ----
                label40.setText(bundle.getString("AddTeamFrame.label40.text_2"));
                panel1.add(label40);
                label40.setBounds(30, 720, 35, 17);
                panel1.add(textField_member2_department);
                textField_member2_department.setBounds(80, 715, 165, 30);

                //---- label41 ----
                label41.setText(bundle.getString("AddTeamFrame.label41.text_2"));
                panel1.add(label41);
                label41.setBounds(325, 720, 35, 17);
                panel1.add(textField_member2_class);
                textField_member2_class.setBounds(375, 715, 165, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel1.getComponentCount(); i++) {
                        Rectangle bounds = panel1.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel1.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel1.setMinimumSize(preferredSize);
                    panel1.setPreferredSize(preferredSize);
                }
            }
            scrollPane1.setViewportView(panel1);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);

        //======== toolBar1 ========
        {

            //---- button1 ----
            button1.setText(bundle.getString("AddTeamFrame.button1.text"));
            button1.addActionListener(e -> button1ActionPerformed(e));
            toolBar1.add(button1);
        }
        contentPane.add(toolBar1, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JPanel panel1;
    private JLabel label1;
    private JTextField textField_team_id;
    private JLabel label2;
    private JTextField textField_team_code;
    private JTextField textField_team_name;
    private JLabel label4;
    private JLabel label5;
    private JTextField textField_team_school;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JComboBox<String> comboBox_team_group;
    private JLabel label3;
    private JLabel label6;
    private JTextField textField_teacher_id;
    private JLabel label10;
    private JTextField textField_teacher_code;
    private JLabel label11;
    private JTextField textField_teacher_name;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JTextField textField_teacher_phone;
    private JTextField textField_teacher_email;
    private JTextField textField_teacher_title;
    private JTextField textField_teacher_school;
    private JTextField textField_teacher_department;
    private JLabel label18;
    private JTextField textField_captain_id;
    private JLabel label19;
    private JTextField textField_captain_idCard;
    private JLabel label20;
    private JTextField textField_captain_name;
    private JLabel label21;
    private JTextField textField_captain_phone;
    private JLabel label22;
    private JTextField textField_captain_email;
    private JLabel label23;
    private JTextField textField_captain_school;
    private JLabel label24;
    private JTextField textField_captain_department;
    private JLabel label25;
    private JTextField textField_captain_class;
    private JLabel label26;
    private JTextField textField_member1_id;
    private JLabel label27;
    private JTextField textField_member1_idCard;
    private JLabel label28;
    private JTextField textField_member1_name;
    private JLabel label29;
    private JTextField textField_member1_phone;
    private JLabel label30;
    private JTextField textField_member1_email;
    private JLabel label31;
    private JTextField textField_member1_school;
    private JLabel label32;
    private JTextField textField_member1_department;
    private JLabel label33;
    private JTextField textField_member1_class;
    private JLabel label34;
    private JTextField textField_member2_id;
    private JLabel label35;
    private JTextField textField_member2_idCard;
    private JLabel label36;
    private JTextField textField_member2_name;
    private JLabel label37;
    private JTextField textField_member2_phone;
    private JLabel label38;
    private JTextField textField_member2_email;
    private JLabel label39;
    private JTextField textField_member2_school;
    private JLabel label40;
    private JTextField textField_member2_department;
    private JLabel label41;
    private JTextField textField_member2_class;
    private JToolBar toolBar1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
