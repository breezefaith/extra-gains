/*
 * Created by JFormDesigner on Wed Dec 18 17:02:23 CST 2019
 */

package competition.management.system.view.member;

import competition.management.system.dao.MemberDao;
import competition.management.system.dao.ProductionDao;
import competition.management.system.dao.TeacherDao;
import competition.management.system.dao.TeamDao;
import competition.management.system.entity.Member;
import competition.management.system.entity.Production;
import competition.management.system.entity.Teacher;
import competition.management.system.entity.Team;
import competition.management.system.util.JDBCUtil;
import competition.management.system.util.LoginStatusUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author zzc
 */
public class MemberMainFrame extends JFrame {
    private Member member = LoginStatusUtil.MEMBER_LOGGED_IN;

    private final MemberDao memberDao = JDBCUtil.getMemberDao();
    private final TeamDao teamDao = JDBCUtil.getTeamDao();
    private final ProductionDao productionDao = JDBCUtil.getProductionDao();
    private final TeacherDao teacherDao = JDBCUtil.getTeacherDao();

    public MemberMainFrame() {
        initComponents();
    }

    public void load() {
        buildTable();
    }

    private void buildTable() {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.member.MemberMainFrame");
        String[] cols = {bundle.getString("MemberMainFrame.cols.code"), bundle.getString("MemberMainFrame.cols.name"), bundle.getString("MemberMainFrame.cols.production.name"), bundle.getString("MemberMainFrame.cols.teacher.name")};
        String[][] rows = {};
        try {
            List<Team> teamList = teamDao.findBySchoolName(member.getSchool());
            rows = new String[teamList.size()][];
            int i = 0;
            for (Team team : teamList) {
                rows[i] = new String[4];
                rows[i][0] = team.getCode();
                rows[i][1] = team.getName();
                Production production = productionDao.findById(team.getProduction().getId());
                team.setProduction(production);
                if(team.getProduction() != null) {
                    rows[i][2] = team.getProduction().getId();
                }
                Teacher teacher = teacherDao.findById(team.getTeacher().getId());
                team.setTeacher(teacher);
                if(team.getTeacher() != null) {
                    rows[i][3] = team.getTeacher().getName();
                }
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DefaultTableModel tableModel = new DefaultTableModel(rows, cols){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table_teams.setModel(tableModel);
    }

    private void button_personActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.member.MemberMainFrame");
        if (LoginStatusUtil.MEMBER_LOGGED_IN == null) {
            JOptionPane.showMessageDialog(this, bundle.getString("MemberMainFrame.please_login"), "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.setVisible(false);
        PersonalInfoFrame personalInfoFrame = new PersonalInfoFrame(this);
        personalInfoFrame.setMember(LoginStatusUtil.MEMBER_LOGGED_IN);
        personalInfoFrame.load();
        personalInfoFrame.setVisible(true);
        personalInfoFrame.setResizable(false);
    }

    private void button_productionActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.member.MemberMainFrame");
        if (LoginStatusUtil.MEMBER_LOGGED_IN == null) {
            JOptionPane.showMessageDialog(this, bundle.getString("MemberMainFrame.please_login"), "", JOptionPane.ERROR_MESSAGE);
            return;
        }

        setVisible(false);
        ProductInfoFrame productInfoFrame = new ProductInfoFrame(this);
        productInfoFrame.setMember(LoginStatusUtil.MEMBER_LOGGED_IN);
        productInfoFrame.setProduct(LoginStatusUtil.MEMBER_LOGGED_IN.getTeam().getProduction());
        productInfoFrame.load();
        productInfoFrame.setVisible(true);
        productInfoFrame.setResizable(false);
    }

    private void button_changePasswordActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.member.MemberMainFrame");
        if (LoginStatusUtil.MEMBER_LOGGED_IN == null) {
            JOptionPane.showMessageDialog(this, bundle.getString("MemberMainFrame.please_login"), "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ChangePasswordFrame changePasswordFrame = new ChangePasswordFrame();
        changePasswordFrame.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.member.MemberMainFrame");
        menuBar1 = new JMenuBar();
        button_production = new JButton();
        button_changePassword = new JButton();
        button_person = new JButton();
        scrollPane1 = new JScrollPane();
        table_teams = new JTable();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //---- button_production ----
            button_production.setText(bundle.getString("MemberMainFrame.button_production.text"));
            button_production.addActionListener(e -> button_productionActionPerformed(e));
            menuBar1.add(button_production);

            //---- button_changePassword ----
            button_changePassword.setText(bundle.getString("MemberMainFrame.button_changePassword.text"));
            button_changePassword.addActionListener(e -> button_changePasswordActionPerformed(e));
            menuBar1.add(button_changePassword);

            //---- button_person ----
            button_person.setText(bundle.getString("MemberMainFrame.button_person.text"));
            button_person.addActionListener(e -> button_personActionPerformed(e));
            menuBar1.add(button_person);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table_teams);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JButton button_production;
    private JButton button_changePassword;
    private JButton button_person;
    private JScrollPane scrollPane1;
    private JTable table_teams;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
