/*
 * Created by JFormDesigner on Wed Dec 18 17:01:42 CST 2019
 */

package competition.management.system.view.hoster;

import competition.management.system.dao.*;
import competition.management.system.entity.*;
import competition.management.system.util.JDBCUtil;

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
public class HosterMainFrame extends JFrame {
    private HosterAdmin hosterAdmin;

    private List<HosterAdmin> hosterAdmins;
    private List<SchoolAdmin> schoolAdmins;
    private List<Team> teams;

    private final SchoolAdminDao schoolAdminDao = JDBCUtil.getSchoolAdminDao();
    private final TeamDao teamDao = JDBCUtil.getTeamDao();
    private final MemberDao memberDao = JDBCUtil.getMemberDao();
    private final GradeDao gradeDao = JDBCUtil.getGradeDao();
    private final ProductionDao productionDao = JDBCUtil.getProductionDao();
    private final TeacherDao teacherDao = JDBCUtil.getTeacherDao();
    private final HosterAdminDao hosterAdminDao = JDBCUtil.getHosterAdminDao();

    private String[] cols_teams;
    private String[] cols_schoolAdmins;
    private String[] cols_hosterAdmins;

    private String[][] rows_teams;
    private String[][] rows_schoolAdmins;
    private String[][] rows_hosterAdmins;

    public HosterMainFrame() {
        initComponents();
    }

    public void load() {
        buildTableTeams();
        buildTableHosterAdmins();
        buildTableSchoolAdmins();
    }

    private void buildTableTeams() {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.HosterMainFrame");
        try {
            cols_teams = new String[]{bundle.getString("HosterMainFrame.table_teams.cols.id"), bundle.getString("HosterMainFrame.table_teams.cols.code"), bundle.getString("HosterMainFrame.table_teams.cols.name"), bundle.getString("HosterMainFrame.table_teams.cols.production.name"), bundle.getString("HosterMainFrame.table_teams.cols.teacher.name")};
            teams = teamDao.findAll();

            rows_teams = new String[teams.size()][cols_teams.length];
            int i = 0, j = 0;
            for (Team team : teams) {
                j = 0;
                rows_teams[i][j++] = team.getId();
                rows_teams[i][j++] = team.getCode();
                rows_teams[i][j++] = team.getName();
                Production production = productionDao.findById(team.getProduction().getId());
                team.setProduction(production);
                if (team.getProduction() != null) {
                    rows_teams[i][j++] = team.getProduction().getId();
                }
                Teacher teacher = teacherDao.findById(team.getTeacher().getId());
                team.setTeacher(teacher);
                if (team.getTeacher() != null) {
                    rows_teams[i][j++] = team.getTeacher().getName();
                }
                i++;
            }

            DefaultTableModel tableModel = new DefaultTableModel(rows_teams, cols_teams) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table_teams.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void buildTableSchoolAdmins() {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.HosterMainFrame");
        try {
            cols_schoolAdmins = new String[]{bundle.getString("HosterMainFrame.table_schoolAdmins.cols.id"), bundle.getString("HosterMainFrame.table_schoolAdmins.cols.code"), bundle.getString("HosterMainFrame.table_schoolAdmins.cols.name"), bundle.getString("HosterMainFrame.table_schoolAdmins.cols.school"), bundle.getString("HosterMainFrame.table_schoolAdmins.cols.password")};
            schoolAdmins = schoolAdminDao.findAll();

            rows_schoolAdmins = new String[schoolAdmins.size()][cols_schoolAdmins.length];
            int i = 0, j = 0;
            for (SchoolAdmin schoolAdmin : schoolAdmins) {
                j = 0;
                rows_schoolAdmins[i][j++] = schoolAdmin.getId();
                rows_schoolAdmins[i][j++] = schoolAdmin.getCode();
                rows_schoolAdmins[i][j++] = schoolAdmin.getName();
                rows_schoolAdmins[i][j++] = schoolAdmin.getSchool();
                rows_schoolAdmins[i][j++] = schoolAdmin.getPassword();
                i++;
            }

            DefaultTableModel tableModel = new DefaultTableModel(rows_schoolAdmins, cols_schoolAdmins) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table_schoolAdmins.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void buildTableHosterAdmins() {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.HosterMainFrame");
        try {
            cols_hosterAdmins = new String[]{bundle.getString("HosterMainFrame.table_hosterAdmins.cols.id"), bundle.getString("HosterMainFrame.table_hosterAdmins.cols.code"), bundle.getString("HosterMainFrame.table_hosterAdmins.cols.name"), bundle.getString("HosterMainFrame.table_hosterAdmins.cols.school"), bundle.getString("HosterMainFrame.table_hosterAdmins.cols.password")};
            hosterAdmins = hosterAdminDao.findAll();

            rows_hosterAdmins = new String[hosterAdmins.size() - 1][cols_hosterAdmins.length];
            int i = 0, j = 0;
            for (HosterAdmin hosterAdmin : hosterAdmins) {
                if (hosterAdmin.getId().equals(this.hosterAdmin.getId())) {
                    continue;
                }
                j = 0;
                rows_hosterAdmins[i][j++] = hosterAdmin.getId();
                rows_hosterAdmins[i][j++] = hosterAdmin.getCode();
                rows_hosterAdmins[i][j++] = hosterAdmin.getName();
                rows_hosterAdmins[i][j++] = hosterAdmin.getSchool();
                rows_hosterAdmins[i][j++] = hosterAdmin.getPassword();
                i++;
            }

            DefaultTableModel tableModel = new DefaultTableModel(rows_hosterAdmins, cols_hosterAdmins) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table_hosterAdmins.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HosterAdmin getHosterAdmin() {
        return hosterAdmin;
    }

    public void setHosterAdmin(HosterAdmin hosterAdmin) {
        this.hosterAdmin = hosterAdmin;
    }

    private void menuItem_exitActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void button_team_viewActionPerformed(ActionEvent e) {
        try {
            TeamInfoFrame teamInfoFrame = new TeamInfoFrame();
            teamInfoFrame.setHosterMainFrame(this);
            teamInfoFrame.setHosterAdmin(hosterAdmin);

            Team team = teamDao.findById(rows_teams[table_teams.getSelectedRow()][0]);
            Production production = productionDao.findById(team.getProduction().getId());
            team.setProduction(production);
            Teacher teacher = teacherDao.findById(team.getTeacher().getId());
            team.setTeacher(teacher);
            teamInfoFrame.setTeam(team);

            setVisible(false);
            teamInfoFrame.load();
            teamInfoFrame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void button_team_removeActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.HosterMainFrame");
        try {
            String teamId = rows_teams[table_teams.getSelectedRow()][0];
            List<Member> members = memberDao.findByTeamId(teamId);
            for (Member member : members) {
                memberDao.remove(member.getId());
            }
            List<Grade> grades = gradeDao.findByTeamId(teamId);
            for (Grade grade : grades) {
                gradeDao.remove(grade.getId());
            }
            if (teamDao.remove(teamId)) {
                JOptionPane.showMessageDialog(this, bundle.getString("HosterMainFrame.alert.delete.success"), "", JOptionPane.INFORMATION_MESSAGE);
            }
            load();
            tabbedPane1.setSelectedIndex(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, bundle.getString("HosterMainFrame.alert.delete.success") + "\n" + ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void button_schoolAdmin_removeActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.HosterMainFrame");
        try {
            String schoolAdminId = rows_schoolAdmins[table_schoolAdmins.getSelectedRow()][0];
            if (schoolAdminDao.remove(schoolAdminId)) {
                JOptionPane.showMessageDialog(this, bundle.getString("HosterMainFrame.alert.delete.success"), "", JOptionPane.INFORMATION_MESSAGE);
            }
            load();
            tabbedPane1.setSelectedIndex(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, bundle.getString("HosterMainFrame.alert.delete.success") + "\n" + ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void button_housterAdmin_removeActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.HosterMainFrame");
        try {
            String hosterAdminId = rows_hosterAdmins[table_hosterAdmins.getSelectedRow()][0];
            if (hosterAdminDao.remove(hosterAdminId)) {
                JOptionPane.showMessageDialog(this, bundle.getString("HosterMainFrame.alert.delete.success"), "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, bundle.getString("HosterMainFrame.alert.delete.error"), "", JOptionPane.ERROR_MESSAGE);
            }
            load();
            tabbedPane1.setSelectedIndex(2);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, bundle.getString("HosterMainFrame.alert.delete.error") + "\n" + ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void button_team_addActionPerformed(ActionEvent e) {
        AddTeamFrame addTeamFrame = new AddTeamFrame();
        addTeamFrame.setHosterMainFrame(this);
        addTeamFrame.load();
        addTeamFrame.setVisible(true);
    }

    private void button_housterAdmin_addActionPerformed(ActionEvent e) {
        AddHosterAdminFrame addHosterAdminFrame = new AddHosterAdminFrame();
        addHosterAdminFrame.setHosterMainFrame(this);
        addHosterAdminFrame.load();
        addHosterAdminFrame.setVisible(true);
    }

    private void button_schoolAdmin_addActionPerformed(ActionEvent e) {
        AddSchoolAdminFrame addSchoolAdminFrame = new AddSchoolAdminFrame();
        addSchoolAdminFrame.setHosterMainFrame(this);
        addSchoolAdminFrame.load();
        addSchoolAdminFrame.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.hoster.HosterMainFrame");
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem_exit = new JMenuItem();
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        toolBar1 = new JToolBar();
        button_team_view = new JButton();
        button_team_add = new JButton();
        button_team_remove = new JButton();
        scrollPane3 = new JScrollPane();
        table_teams = new JTable();
        panel2 = new JPanel();
        toolBar2 = new JToolBar();
        button_schoolAdmin_view = new JButton();
        button_schoolAdmin_add = new JButton();
        button_schoolAdmin_remove = new JButton();
        scrollPane4 = new JScrollPane();
        table_schoolAdmins = new JTable();
        panel3 = new JPanel();
        toolBar3 = new JToolBar();
        button_hosterAdmin_view = new JButton();
        button_housterAdmin_add = new JButton();
        button_housterAdmin_remove = new JButton();
        scrollPane5 = new JScrollPane();
        table_hosterAdmins = new JTable();

        //======== this ========
        setTitle("\u4e3b\u529e\u65b9\u7ba1\u7406\u5458");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText(bundle.getString("HosterMainFrame.menu1.text"));

                //---- menuItem_exit ----
                menuItem_exit.setText(bundle.getString("HosterMainFrame.menuItem_exit.text"));
                menuItem_exit.addActionListener(e -> menuItem_exitActionPerformed(e));
                menu1.add(menuItem_exit);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== tabbedPane1 ========
        {

            //======== panel1 ========
            {
                panel1.setLayout(new BorderLayout());

                //======== toolBar1 ========
                {

                    //---- button_team_view ----
                    button_team_view.setText(bundle.getString("HosterMainFrame.button_team_view.text"));
                    button_team_view.addActionListener(e -> button_team_viewActionPerformed(e));
                    toolBar1.add(button_team_view);

                    //---- button_team_add ----
                    button_team_add.setText(bundle.getString("HosterMainFrame.button_team_add.text"));
                    button_team_add.addActionListener(e -> button_team_addActionPerformed(e));
                    toolBar1.add(button_team_add);

                    //---- button_team_remove ----
                    button_team_remove.setText(bundle.getString("HosterMainFrame.button_team_remove.text"));
                    button_team_remove.addActionListener(e -> button_team_removeActionPerformed(e));
                    toolBar1.add(button_team_remove);
                }
                panel1.add(toolBar1, BorderLayout.NORTH);

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(table_teams);
                }
                panel1.add(scrollPane3, BorderLayout.CENTER);
            }
            tabbedPane1.addTab(bundle.getString("HosterMainFrame.panel1.tab.title"), panel1);

            //======== panel2 ========
            {
                panel2.setLayout(new BorderLayout());

                //======== toolBar2 ========
                {

                    //---- button_schoolAdmin_view ----
                    button_schoolAdmin_view.setText(bundle.getString("HosterMainFrame.button_schoolAdmin_view.text"));
                    toolBar2.add(button_schoolAdmin_view);

                    //---- button_schoolAdmin_add ----
                    button_schoolAdmin_add.setText(bundle.getString("HosterMainFrame.button_schoolAdmin_add.text"));
                    button_schoolAdmin_add.addActionListener(e -> button_schoolAdmin_addActionPerformed(e));
                    toolBar2.add(button_schoolAdmin_add);

                    //---- button_schoolAdmin_remove ----
                    button_schoolAdmin_remove.setText(bundle.getString("HosterMainFrame.button_schoolAdmin_remove.text"));
                    button_schoolAdmin_remove.addActionListener(e -> button_schoolAdmin_removeActionPerformed(e));
                    toolBar2.add(button_schoolAdmin_remove);
                }
                panel2.add(toolBar2, BorderLayout.NORTH);

                //======== scrollPane4 ========
                {
                    scrollPane4.setViewportView(table_schoolAdmins);
                }
                panel2.add(scrollPane4, BorderLayout.CENTER);
            }
            tabbedPane1.addTab(bundle.getString("HosterMainFrame.panel2.tab.title"), panel2);

            //======== panel3 ========
            {
                panel3.setLayout(new BorderLayout());

                //======== toolBar3 ========
                {

                    //---- button_hosterAdmin_view ----
                    button_hosterAdmin_view.setText(bundle.getString("HosterMainFrame.button_hosterAdmin_view.text"));
                    toolBar3.add(button_hosterAdmin_view);

                    //---- button_housterAdmin_add ----
                    button_housterAdmin_add.setText(bundle.getString("HosterMainFrame.button_housterAdmin_add.text"));
                    button_housterAdmin_add.addActionListener(e -> button_housterAdmin_addActionPerformed(e));
                    toolBar3.add(button_housterAdmin_add);

                    //---- button_housterAdmin_remove ----
                    button_housterAdmin_remove.setText(bundle.getString("HosterMainFrame.button_housterAdmin_remove.text"));
                    button_housterAdmin_remove.addActionListener(e -> button_housterAdmin_removeActionPerformed(e));
                    toolBar3.add(button_housterAdmin_remove);
                }
                panel3.add(toolBar3, BorderLayout.NORTH);

                //======== scrollPane5 ========
                {
                    scrollPane5.setViewportView(table_hosterAdmins);
                }
                panel3.add(scrollPane5, BorderLayout.CENTER);
            }
            tabbedPane1.addTab(bundle.getString("HosterMainFrame.panel3.tab.title"), panel3);
        }
        contentPane.add(tabbedPane1, BorderLayout.CENTER);
        setSize(1010, 555);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem_exit;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JToolBar toolBar1;
    private JButton button_team_view;
    private JButton button_team_add;
    private JButton button_team_remove;
    private JScrollPane scrollPane3;
    private JTable table_teams;
    private JPanel panel2;
    private JToolBar toolBar2;
    private JButton button_schoolAdmin_view;
    private JButton button_schoolAdmin_add;
    private JButton button_schoolAdmin_remove;
    private JScrollPane scrollPane4;
    private JTable table_schoolAdmins;
    private JPanel panel3;
    private JToolBar toolBar3;
    private JButton button_hosterAdmin_view;
    private JButton button_housterAdmin_add;
    private JButton button_housterAdmin_remove;
    private JScrollPane scrollPane5;
    private JTable table_hosterAdmins;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
