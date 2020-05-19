/*
 * Created by JFormDesigner on Wed Dec 25 17:02:33 CST 2019
 */

package simulator.view;

import simulator.entity.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 创建进程窗口
 */
public class CreationProcessFrame extends JFrame {
    /**
     * 存储控制器
     */
    private Manager manager;
    /**
     * 主窗口对象
     */
    private MainFrame mainFrame;

    public CreationProcessFrame() {
        initComponents();
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    private void buttonConfirmActionPerformed(ActionEvent e) {
        try {
            int num = Integer.valueOf(textFieldNum.getText());
            int[] size = new int[Manager.MAX_SEGMENT_NUM_IN_PCB];
            if (num > 0) {
                size[0] = Integer.valueOf(textFieldSize1.getText());
            }
            if (num > 1) {
                size[1] = Integer.valueOf(textFieldSize2.getText());
            }
            if (num > 2) {
                size[2] = Integer.valueOf(textFieldSize3.getText());
            }
            if (num > 3) {
                size[3] = Integer.valueOf(textFieldSize4.getText());
            }
            if (num > 4) {
                size[4] = Integer.valueOf(textFieldSize5.getText());
            }
            manager.createProcess(num, size);
            //创建进程成功后关闭窗口并刷新主窗口
            this.setVisible(false);
            mainFrame.load();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textFieldNum = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        textFieldSize1 = new JTextField();
        textFieldSize2 = new JTextField();
        textFieldSize3 = new JTextField();
        textFieldSize4 = new JTextField();
        textFieldSize5 = new JTextField();
        buttonConfirm = new JButton();

        //======== this ========
        setTitle("\u521b\u5efa\u8fdb\u7a0b");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u6bb5\u6570(1~5)");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(60, 40, 80, 20);
        contentPane.add(textFieldNum);
        textFieldNum.setBounds(150, 40, 100, textFieldNum.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u7b2c1\u6bb5\u5927\u5c0f");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label2);
        label2.setBounds(60, 110, 85, 25);

        //---- label3 ----
        label3.setText("\u7b2c2\u6bb5\u5927\u5c0f");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label3);
        label3.setBounds(60, 150, 85, 25);

        //---- label4 ----
        label4.setText("\u7b2c3\u6bb5\u5927\u5c0f");
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label4);
        label4.setBounds(60, 190, 85, 25);

        //---- label5 ----
        label5.setText("\u7b2c4\u6bb5\u5927\u5c0f");
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label5);
        label5.setBounds(60, 230, 85, 25);

        //---- label6 ----
        label6.setText("\u7b2c5\u6bb5\u5927\u5c0f");
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label6);
        label6.setBounds(60, 270, 85, 25);
        contentPane.add(textFieldSize1);
        textFieldSize1.setBounds(150, 110, 100, 21);
        contentPane.add(textFieldSize2);
        textFieldSize2.setBounds(150, 150, 100, 21);
        contentPane.add(textFieldSize3);
        textFieldSize3.setBounds(150, 190, 100, 21);
        contentPane.add(textFieldSize4);
        textFieldSize4.setBounds(150, 230, 100, 21);
        contentPane.add(textFieldSize5);
        textFieldSize5.setBounds(150, 270, 100, 21);

        //---- buttonConfirm ----
        buttonConfirm.setText("\u786e\u5b9a");
        buttonConfirm.addActionListener(e -> buttonConfirmActionPerformed(e));
        contentPane.add(buttonConfirm);
        buttonConfirm.setBounds(new Rectangle(new Point(135, 335), buttonConfirm.getPreferredSize()));

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

        setSize(335, 440);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textFieldNum;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JTextField textFieldSize1;
    private JTextField textFieldSize2;
    private JTextField textFieldSize3;
    private JTextField textFieldSize4;
    private JTextField textFieldSize5;
    private JButton buttonConfirm;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
