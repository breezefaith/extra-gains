/*
 * Created by JFormDesigner on Thu Dec 19 19:07:23 CST 2019
 */

package competition.management.system.view.member;

import competition.management.system.dao.GradeDao;
import competition.management.system.dao.HosterAdminDao;
import competition.management.system.dao.MemberDao;
import competition.management.system.dao.TeamDao;
import competition.management.system.entity.Grade;
import competition.management.system.entity.HosterAdmin;
import competition.management.system.entity.Member;
import competition.management.system.entity.Team;
import competition.management.system.util.JDBCUtil;
import competition.management.system.util.LoginStatusUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author zzc
 */
public class TeamInfoFrame extends JFrame {
    private Member member;
    private Team team;
    private List<Member> members;
    private List<Grade> grades;

    private final TeamDao teamDao = JDBCUtil.getTeamDao();
    private final MemberDao memberDao = JDBCUtil.getMemberDao();
    private final GradeDao gradeDao = JDBCUtil.getGradeDao();
    private final HosterAdminDao hosterAdminDao = JDBCUtil.getHosterAdminDao();

    public TeamInfoFrame() {
        initComponents();
    }

    public void load() {
        textField_id.setText(team.getId());
        textField_code.setText(team.getCode());
        textField_name.setText(team.getName());
        textField_group.setText(team.getGroup());
        textField_school.setText(team.getSchool());
        textField_production.setText(team.getProduction().getName());
        textField_teacher.setText(team.getTeacher().getName());

        try {
            members = memberDao.findByTeamId(team.getId());
            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            for (Member member : members) {
                stringBuilder.append(member.getName());
                if (member.getLeader() == true) {
                    stringBuilder.append("(C)");
                }
                if (i < members.size() - 1) {
                    stringBuilder.append(",");
                }
            }
            textField_members.setText(stringBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        buildTable();
    }

    private void buildTable() {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.member.TeamInfoFrame");
        try {
            grades = gradeDao.findByTeamId(team.getId());
            String[] cols = {bundle.getString("TeamInfoFrame.cols.ID"), bundle.getString("TeamInfoFrame.cols.pgrade"), bundle.getString("TeamInfoFrame.cols.lgrade"), bundle.getString("TeamInfoFrame.cols.hosterAdminName")};
            DefaultTableModel tableModel = new DefaultTableModel(listTo2dArray(grades), cols) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table_grade.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
            grades = new ArrayList<>();
        }
    }

    private String[][] listTo2dArray(List<Grade> grades) {
        String[][] rows = new String[grades.size()][];
        int i = 0;
        for (Grade grade : grades) {
            rows[i] = new String[4];
            rows[i][0] = grade.getId();
            rows[i][1] = grade.getPgrade().toString();
            rows[i][2] = grade.getLgrade().toString();
            try {
                HosterAdmin hosterAdmin = hosterAdminDao.findById(grade.getHosterAdmin().getId());
                grade.setHosterAdmin(hosterAdmin);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rows[i][3] = grade.getHosterAdmin().getName();
            i++;
        }
        return rows;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    private void button_teacherInfoActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button_prodInfoActionPerformed(ActionEvent e) {
        ProductInfoFrame productInfoFrame = new ProductInfoFrame(null);
        productInfoFrame.setMember(member);
        productInfoFrame.setProduct(team.getProduction());
        productInfoFrame.load();
        productInfoFrame.setVisible(true);
        productInfoFrame.setResizable(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.member.TeamInfoFrame");
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        textField_id = new JTextField();
        textField_code = new JTextField();
        textField_name = new JTextField();
        textField_group = new JTextField();
        textField_school = new JTextField();
        textField_teacher = new JTextField();
        textField_production = new JTextField();
        label8 = new JLabel();
        textField_members = new JTextField();
        scrollPane1 = new JScrollPane();
        table_grade = new JTable();
        button_prodInfo = new JButton();

        //======== this ========
        setTitle(bundle.getString("TeamInfoFrame.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText(bundle.getString("TeamInfoFrame.label1.text"));
        contentPane.add(label1);
        label1.setBounds(75, 40, 25, label1.getPreferredSize().height);

        //---- label2 ----
        label2.setText(bundle.getString("TeamInfoFrame.label2.text"));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(360, 35), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText(bundle.getString("TeamInfoFrame.label3.text"));
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(675, 35), label3.getPreferredSize()));

        //---- label4 ----
        label4.setText(bundle.getString("TeamInfoFrame.label4.text"));
        contentPane.add(label4);
        label4.setBounds(65, 85, 40, label4.getPreferredSize().height);

        //---- label5 ----
        label5.setText(bundle.getString("TeamInfoFrame.label5.text"));
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(360, 85), label5.getPreferredSize()));

        //---- label6 ----
        label6.setText(bundle.getString("TeamInfoFrame.label6.text"));
        contentPane.add(label6);
        label6.setBounds(650, 85, 58, label6.getPreferredSize().height);

        //---- label7 ----
        label7.setText(bundle.getString("TeamInfoFrame.label7.text"));
        contentPane.add(label7);
        label7.setBounds(65, 135, 40, label7.getPreferredSize().height);

        //---- textField_id ----
        textField_id.setEditable(false);
        contentPane.add(textField_id);
        textField_id.setBounds(115, 30, 165, textField_id.getPreferredSize().height);
        contentPane.add(textField_code);
        textField_code.setBounds(410, 30, 165, 30);
        contentPane.add(textField_name);
        textField_name.setBounds(710, 30, 165, 30);
        contentPane.add(textField_group);
        textField_group.setBounds(115, 80, 165, 30);
        contentPane.add(textField_school);
        textField_school.setBounds(410, 80, 165, 30);
        contentPane.add(textField_teacher);
        textField_teacher.setBounds(710, 80, 165, 30);
        contentPane.add(textField_production);
        textField_production.setBounds(115, 125, 165, 30);

        //---- label8 ----
        label8.setText(bundle.getString("TeamInfoFrame.label8.text"));
        contentPane.add(label8);
        label8.setBounds(360, 135, 29, label8.getPreferredSize().height);
        contentPane.add(textField_members);
        textField_members.setBounds(410, 125, 165, 30);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table_grade);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(70, 175, 805, 295);

        //---- button_prodInfo ----
        button_prodInfo.setText(bundle.getString("TeamInfoFrame.button_prodInfo.text"));
        button_prodInfo.addActionListener(e -> button_prodInfoActionPerformed(e));
        contentPane.add(button_prodInfo);
        button_prodInfo.setBounds(285, 125, 25, button_prodInfo.getPreferredSize().height);

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
        setSize(935, 515);
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
    private JTextField textField_id;
    private JTextField textField_code;
    private JTextField textField_name;
    private JTextField textField_group;
    private JTextField textField_school;
    private JTextField textField_teacher;
    private JTextField textField_production;
    private JLabel label8;
    private JTextField textField_members;
    private JScrollPane scrollPane1;
    private JTable table_grade;
    private JButton button_prodInfo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
