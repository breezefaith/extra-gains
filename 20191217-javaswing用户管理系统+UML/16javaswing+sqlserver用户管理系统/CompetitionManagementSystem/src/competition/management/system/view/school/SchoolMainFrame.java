/*
 * Created by JFormDesigner on Wed Dec 18 17:02:52 CST 2019
 */

package competition.management.system.view.school;

import competition.management.system.dao.*;
import competition.management.system.entity.Production;
import competition.management.system.entity.SchoolAdmin;
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
public class SchoolMainFrame extends JFrame {
    private SchoolAdmin schoolAdmin;
    private String[] cols;
    private String[][] rows;

    private final SchoolAdminDao schoolAdminDao = JDBCUtil.getSchoolAdminDao();
    private final TeamDao teamDao = JDBCUtil.getTeamDao();
    private final MemberDao memberDao = JDBCUtil.getMemberDao();
    private final GradeDao gradeDao = JDBCUtil.getGradeDao();
    private final ProductionDao productionDao = JDBCUtil.getProductionDao();
    private final TeacherDao teacherDao = JDBCUtil.getTeacherDao();
    private final HosterAdminDao hosterAdminDao = JDBCUtil.getHosterAdminDao();

    public SchoolMainFrame() {
        initComponents();
    }

    public void load() {
        buildTable();
    }

    private void buildTable() {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.school.SchoolMainFrame");
        cols = new String[]{bundle.getString("SchoolMainFrame.cols.id"), bundle.getString("SchoolMainFrame.cols.code"), bundle.getString("SchoolMainFrame.cols.name"), bundle.getString("SchoolMainFrame.cols.production.name"), bundle.getString("SchoolMainFrame.cols.teacher.name")};
        rows = new String[][]{};
        try {
            List<Team> teamList = teamDao.findBySchoolName(schoolAdmin.getSchool());
            rows = new String[teamList.size()][];
            int i = 0;
            for (Team team : teamList) {
                rows[i] = new String[5];
                rows[i][0] = team.getId();
                rows[i][1] = team.getCode();
                rows[i][2] = team.getName();
                Production production = productionDao.findById(team.getProduction().getId());
                team.setProduction(production);
                if (team.getProduction() != null) {
                    rows[i][3] = team.getProduction().getId();
                }
                Teacher teacher = teacherDao.findById(team.getTeacher().getId());
                team.setTeacher(teacher);
                if (team.getTeacher() != null) {
                    rows[i][4] = team.getTeacher().getName();
                }
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DefaultTableModel tableModel = new DefaultTableModel(rows, cols) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table_teams.setModel(tableModel);
    }

    public SchoolAdmin getSchoolAdmin() {
        return schoolAdmin;
    }

    public void setSchoolAdminDao(SchoolAdmin schoolAdmin) {
        this.schoolAdmin = schoolAdmin;
    }

    private void button_changePasswordActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.school.SchoolMainFrame");
        if (LoginStatusUtil.SCHOOL_ADMIN_LOGGED_IN == null) {
            JOptionPane.showMessageDialog(this, bundle.getString("SchoolMainFrame.please_login"), "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ChangePasswordFrame changePasswordFrame = new ChangePasswordFrame();
        changePasswordFrame.setVisible(true);
    }

    private void button_teamInfoActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.school.SchoolMainFrame");
        if (table_teams.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, bundle.getString("SchoolMainFrame.please_select"), "", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TeamInfoFrame teamInfoFrame = new TeamInfoFrame();
        teamInfoFrame.setSchoolAdmin(schoolAdmin);
        try {
            Team team = teamDao.findById(rows[table_teams.getSelectedRow()][0]);
            Production production = productionDao.findById(team.getProduction().getId());
            team.setProduction(production);
            Teacher teacher = teacherDao.findById(team.getTeacher().getId());
            team.setTeacher(teacher);
            teamInfoFrame.setTeam(team);

            teamInfoFrame.setSchoolMainFrame(this);
            setVisible(false);
            teamInfoFrame.load();
            teamInfoFrame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.school.SchoolMainFrame");
        menuBar1 = new JMenuBar();
        button_teamInfo = new JButton();
        button_changePassword = new JButton();
        scrollPane1 = new JScrollPane();
        table_teams = new JTable();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //---- button_teamInfo ----
            button_teamInfo.setText(bundle.getString("SchoolMainFrame.button_teamInfo.text"));
            button_teamInfo.addActionListener(e -> button_teamInfoActionPerformed(e));
            menuBar1.add(button_teamInfo);

            //---- button_changePassword ----
            button_changePassword.setText(bundle.getString("SchoolMainFrame.button_changePassword.text"));
            button_changePassword.addActionListener(e -> button_changePasswordActionPerformed(e));
            menuBar1.add(button_changePassword);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {

            //---- table_teams ----
            table_teams.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table_teams.setAutoCreateRowSorter(true);
            scrollPane1.setViewportView(table_teams);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);
        setSize(1125, 610);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JButton button_teamInfo;
    private JButton button_changePassword;
    private JScrollPane scrollPane1;
    private JTable table_teams;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
