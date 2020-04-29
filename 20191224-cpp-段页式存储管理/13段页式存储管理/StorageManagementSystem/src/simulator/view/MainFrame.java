/*
 * Created by JFormDesigner on Wed Dec 25 16:02:32 CST 2019
 */

package simulator.view;

import simulator.entity.Manager;
import simulator.entity.PCB;
import simulator.entity.Page;
import simulator.entity.Segment;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 主界面
 */
public class MainFrame extends JFrame {
    /**
     * 存储控制器
     */
    private Manager manager;
    /**
     * 进程树根节点
     */
    private DefaultMutableTreeNode root;

    public MainFrame() throws Exception {
        initComponents();
        //默认最大化显示
        setExtendedState(MAXIMIZED_BOTH);
    }

    public void load() throws Exception {
        buildProcessTree();
        buildMemoryStatusBar();
        buildTableSegmentTable();
        buildTablePageTable();
    }

    /**
     * 创建进程树
     */
    private void buildProcessTree() {
        //创建根节点
        root = new DefaultMutableTreeNode(new TreeNodeObject<String>("Root", TreeNodeTypeEnum.ROOT));
        treeProcess.setModel(new DefaultTreeModel(root));

        PCB[] pcbs = manager.getPcbs();
        Segment[] segments = manager.getSegTable();

        //遍历pcbs
        for (int i = 0; i < manager.getPcbCount(); i++) {
            TreeNodeObject<PCB> procNodeObject = new TreeNodeObject<>(pcbs[i], TreeNodeTypeEnum.PROCESS);
            DefaultMutableTreeNode procNode = new DefaultMutableTreeNode(procNodeObject);
            root.add(procNode);

            //遍历pcbs[i]的所有段
            for (int j = 0; j < manager.getSegCount(); j++) {
                if (segments[j].getPcbIdx() == pcbs[i].getPid()) {
                    TreeNodeObject<Segment> segNodeObject = new TreeNodeObject<>(segments[j], TreeNodeTypeEnum.SEGMENT);
                    DefaultMutableTreeNode segNode = new DefaultMutableTreeNode(segNodeObject);
                    procNode.add(segNode);

                    //遍历当前段的所有页
                    for (int k = 0; segments[j].getPageTable() != null && k < segments[j].getPageCount(); k++) {
                        TreeNodeObject<Page> pageNodeObject = new TreeNodeObject<>(segments[j].getPageTable()[k], TreeNodeTypeEnum.PAGE);
                        DefaultMutableTreeNode pageNode = new DefaultMutableTreeNode(pageNodeObject);
                        segNode.add(pageNode);
                    }
                }
            }
        }

        //展开根节点
        treeProcess.expandPath(new TreePath(root));
    }

    /**
     * 构建内存状态工具条
     */
    private void buildMemoryStatusBar() {
        //清空原有控件
        panelMemoryStatus.removeAll();

        int height = panelMemoryStatus.getHeight();
        int totalWidth = panelMemoryStatus.getWidth();
        int totalFrame = manager.getFrameCount();
        int num = 0;
        int addr = 0;
        int j = manager.getFrameStates()[0];
        int totalUsed = 0;

        for (int i = 0; i < totalFrame; i++) {
            if (manager.getFrameStates()[i] == j) {
                num++;
            } else {
                if (j == Segment.SEGMENT_OCCUPIED) {
                    totalUsed = totalUsed + num;
                }
                JButton blockView = new JButton();
                blockView.setToolTipText("" + num);
                blockView.setBackground(j == Segment.SEGMENT_OCCUPIED ? Color.RED : Color.GREEN);
                blockView.setPreferredSize(new Dimension((int) ((num * 1.0 / totalFrame) * totalWidth), height));
                panelMemoryStatus.add(blockView);

                j = manager.getFrameStates()[i];
                addr = i;
                num = 1;
            }
        }
        JButton blockView = new JButton();
        blockView.setToolTipText(String.valueOf(num));
        blockView.setBackground(j == 1 ? Color.RED : Color.GREEN);
        blockView.setSize(new Dimension((int) ((num * 1.0 / totalFrame) * totalWidth), height));
        blockView.setPreferredSize(new Dimension((int) ((num * 1.0 / totalFrame) * totalWidth), height));
        panelMemoryStatus.add(blockView);

        if (j == 1 && num == totalFrame) {
            totalUsed = num;
        }

        panelMemoryStatus.repaint();
        labelRate.setText(String.format("%7.2f%%", (totalUsed * 1.0 / totalFrame) * 100));
    }

    /**
     * 构建段表
     */
    private void buildTableSegmentTable() {
        Segment[] segmentTable = manager.getSegTable();
        String[] cols = new String[]{"段号", "状态", "页表大小", "页表始址"};
        String[][] rows = new String[manager.getSegCount()][cols.length];
        for (int i = 0; i < manager.getSegCount(); i++) {
            rows[i][0] = String.valueOf(i);
            rows[i][1] = segmentTable[i].getState() == Segment.SEGMENT_OCCUPIED ? "占用" : "空闲";
            rows[i][2] = String.valueOf(segmentTable[i].getPageCount());
            rows[i][3] = segmentTable[i].getPageTable() == null ? String.valueOf(segmentTable[i].getPageTable()) : String.valueOf(segmentTable[i].getPageTable().hashCode());
        }

        DefaultTableModel model = new DefaultTableModel(rows, cols) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableSegmentTable.setModel(model);
    }

    /**
     * 构建页表结构
     */
    private void buildTablePageTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"页号", "状态", "存储块"});
        tablePageTable.setModel(model);
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    private void menuItemExitActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    /**
     * 点击创建进程按钮事件处理
     *
     * @param e
     */
    private void buttonCreateActionPerformed(ActionEvent e) {
        if (manager.getLeftFrameCount() <= 0) {
            JOptionPane.showMessageDialog(this, "无可用内存", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        CreationProcessFrame creationProcessFrame = new CreationProcessFrame();
        creationProcessFrame.setMainFrame(this);
        creationProcessFrame.setManager(manager);
        creationProcessFrame.setVisible(true);
    }

    /**
     * 点击回收进程事件处理
     *
     * @param e
     */
    private void buttonRecycleProcessActionPerformed(ActionEvent e) {
        try {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeProcess.getLastSelectedPathComponent();
            TreeNodeObject treeNodeObject = (TreeNodeObject) node.getUserObject();
            if (treeNodeObject == null || treeNodeObject.getType() != TreeNodeTypeEnum.PROCESS) {
                JOptionPane.showMessageDialog(this, "请选中一个进程节点", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            PCB pcb = (PCB) treeNodeObject.getObj();
            manager.recycleProcess(pcb);
            load();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 点击回首段事件处理
     *
     * @param e
     */
    private void buttonRecycleSegmentActionPerformed(ActionEvent e) {
        try {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeProcess.getLastSelectedPathComponent();
            TreeNodeObject treeNodeObject = (TreeNodeObject) node.getUserObject();
            if (treeNodeObject == null || treeNodeObject.getType() != TreeNodeTypeEnum.SEGMENT) {
                JOptionPane.showMessageDialog(this, "请选中一个段节点", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Segment segment = (Segment) treeNodeObject.getObj();
            manager.recycleSegment(segment);
            load();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 进程树选中节点切换事件处理
     *
     * @param e
     */
    private void treeProcessValueChanged(TreeSelectionEvent e) {
        try {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeProcess.getLastSelectedPathComponent();
            if (node == null) {
                return;
            }
            TreeNodeObject nodeObject = (TreeNodeObject) node.getUserObject();

            if (nodeObject != null && nodeObject.getType() == TreeNodeTypeEnum.SEGMENT) {
                Segment segment = (Segment) nodeObject.getObj();
                if (segment.getState() == Segment.SEGMENT_FREE) {
                    JOptionPane.showMessageDialog(this, "此段未分配", "Error", JOptionPane.ERROR_MESSAGE);
                }
                DefaultTableModel model = (DefaultTableModel) tablePageTable.getModel();
                model.setRowCount(segment.getPageCount());
                for (int i = 0; i < segment.getPageCount(); i++) {
                    Page page = segment.getPageTable()[i];
                    tablePageTable.getModel().setValueAt(String.valueOf(i), i, 0);
                    tablePageTable.getModel().setValueAt(page.getState() == Page.PAGE_OCCUPIED ? "占用" : "空闲", i, 1);
                    tablePageTable.getModel().setValueAt(String.valueOf(page.getFrameNo()), i, 2);
                }
            } else {
                tablePageTable.removeAll();
                DefaultTableModel model = (DefaultTableModel) tablePageTable.getModel();
                model.setRowCount(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menuFlie = new JMenu();
        menuItemExit = new JMenuItem();
        menuHelp = new JMenu();
        menuItemAbout = new JMenuItem();
        toolBar1 = new JToolBar();
        buttonCreate = new JButton();
        buttonRecycleProcess = new JButton();
        buttonRecycleSegment = new JButton();
        splitPane1 = new JSplitPane();
        scrollPane3 = new JScrollPane();
        treeProcess = new JTree();
        panelMain = new JPanel();
        toolBar2 = new JToolBar();
        labelRate = new JLabel();
        label2 = new JLabel();
        panelMemoryStatus = new JPanel();
        splitPane2 = new JSplitPane();
        scrollPane1 = new JScrollPane();
        tableSegmentTable = new JTable();
        scrollPane2 = new JScrollPane();
        tablePageTable = new JTable();

        //======== this ========
        setTitle("\u5185\u5b58\u7ba1\u7406\u6a21\u62df\u5668");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menuFlie ========
            {
                menuFlie.setText("\u6587\u4ef6");

                //---- menuItemExit ----
                menuItemExit.setText("\u9000\u51fa");
                menuItemExit.addActionListener(e -> menuItemExitActionPerformed(e));
                menuFlie.add(menuItemExit);
            }
            menuBar1.add(menuFlie);

            //======== menuHelp ========
            {
                menuHelp.setText("\u5e2e\u52a9");

                //---- menuItemAbout ----
                menuItemAbout.setText("\u5173\u4e8e");
                menuHelp.add(menuItemAbout);
            }
            menuBar1.add(menuHelp);
        }
        setJMenuBar(menuBar1);

        //======== toolBar1 ========
        {
            toolBar1.setFloatable(false);

            //---- buttonCreate ----
            buttonCreate.setText("\u521b\u5efa\u8fdb\u7a0b");
            buttonCreate.addActionListener(e -> buttonCreateActionPerformed(e));
            toolBar1.add(buttonCreate);

            //---- buttonRecycleProcess ----
            buttonRecycleProcess.setText("\u56de\u6536\u8fdb\u7a0b");
            buttonRecycleProcess.addActionListener(e -> buttonRecycleProcessActionPerformed(e));
            toolBar1.add(buttonRecycleProcess);

            //---- buttonRecycleSegment ----
            buttonRecycleSegment.setText("\u56de\u6536\u6bb5");
            buttonRecycleSegment.addActionListener(e -> buttonRecycleSegmentActionPerformed(e));
            toolBar1.add(buttonRecycleSegment);
        }
        contentPane.add(toolBar1, BorderLayout.NORTH);

        //======== splitPane1 ========
        {
            splitPane1.setResizeWeight(0.2);
            splitPane1.setOneTouchExpandable(true);
            splitPane1.setEnabled(false);

            //======== scrollPane3 ========
            {

                //---- treeProcess ----
                treeProcess.setAutoscrolls(true);
                treeProcess.addTreeSelectionListener(e -> treeProcessValueChanged(e));
                scrollPane3.setViewportView(treeProcess);
            }
            splitPane1.setLeftComponent(scrollPane3);

            //======== panelMain ========
            {
                panelMain.setLayout(new BorderLayout());

                //======== toolBar2 ========
                {
                    toolBar2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

                    //---- labelRate ----
                    labelRate.setMaximumSize(new Dimension(60, 15));
                    labelRate.setMinimumSize(new Dimension(60, 15));
                    labelRate.setPreferredSize(new Dimension(60, 15));
                    toolBar2.add(labelRate);

                    //---- label2 ----
                    label2.setText("\u5185\u5b58\u4f7f\u7528\u7387");
                    toolBar2.add(label2);
                    toolBar2.addSeparator();

                    //======== panelMemoryStatus ========
                    {
                        panelMemoryStatus.setMinimumSize(new Dimension(400, 20));
                        panelMemoryStatus.setMaximumSize(new Dimension(400, 20));
                        panelMemoryStatus.setPreferredSize(new Dimension(400, 20));
                        panelMemoryStatus.setAutoscrolls(true);
                        panelMemoryStatus.setFocusable(false);
                        panelMemoryStatus.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
                    }
                    toolBar2.add(panelMemoryStatus);
                }
                panelMain.add(toolBar2, BorderLayout.PAGE_END);

                //======== splitPane2 ========
                {
                    splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
                    splitPane2.setResizeWeight(0.5);

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(tableSegmentTable);
                    }
                    splitPane2.setTopComponent(scrollPane1);

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setViewportView(tablePageTable);
                    }
                    splitPane2.setBottomComponent(scrollPane2);
                }
                panelMain.add(splitPane2, BorderLayout.CENTER);
            }
            splitPane1.setRightComponent(panelMain);
        }
        contentPane.add(splitPane1, BorderLayout.CENTER);

        setSize(1005, 665);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menuFlie;
    private JMenuItem menuItemExit;
    private JMenu menuHelp;
    private JMenuItem menuItemAbout;
    private JToolBar toolBar1;
    private JButton buttonCreate;
    private JButton buttonRecycleProcess;
    private JButton buttonRecycleSegment;
    private JSplitPane splitPane1;
    private JScrollPane scrollPane3;
    private JTree treeProcess;
    private JPanel panelMain;
    private JToolBar toolBar2;
    private JLabel labelRate;
    private JLabel label2;
    private JPanel panelMemoryStatus;
    private JSplitPane splitPane2;
    private JScrollPane scrollPane1;
    private JTable tableSegmentTable;
    private JScrollPane scrollPane2;
    private JTable tablePageTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
