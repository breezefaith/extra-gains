/*
 * Created by JFormDesigner on Wed Dec 25 17:09:56 CST 2019
 */

package simulator.view;

import simulator.entity.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InitFrame extends JFrame {
    private Manager manager;

    public InitFrame() {
        initComponents();
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    private void buttonStartActionPerformed(ActionEvent e) {
        try {
            int frameNum = Integer.valueOf(textFieldPageNum.getText());
            int frameSize = Integer.valueOf(textFieldFrameSize.getText());
            int segNum = Integer.valueOf(textFieldSegmentTableLength.getText());

            manager.init(frameNum, frameSize, segNum);

            this.setVisible(false);
            MainFrame mainFrame = new MainFrame();
            mainFrame.setManager(manager);
            mainFrame.setVisible(true);
            mainFrame.load();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void load() {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textFieldPageNum = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        textFieldFrameSize = new JTextField();
        textFieldSegmentTableLength = new JTextField();
        buttonStart = new JButton();

        //======== this ========
        setTitle("\u521d\u59cb\u5316\u6a21\u62df\u5668");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u9875\u9762\u6570(1~10000)");
        contentPane.add(label1);
        label1.setBounds(60, 35, 110, 25);

        //---- textFieldPageNum ----
        contentPane.add(textFieldPageNum);
        textFieldPageNum.setBounds(175, 35, 115, textFieldPageNum.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u9875\u5927\u5c0f");
        contentPane.add(label2);
        label2.setBounds(110, 80, 50, 15);

        //---- label3 ----
        label3.setText("\u6bb5\u8868\u5927\u5c0f(1~100)");
        contentPane.add(label3);
        label3.setBounds(60, 120, 103, label3.getPreferredSize().height);

        //---- textFieldFrameSize ----
        contentPane.add(textFieldFrameSize);
        textFieldFrameSize.setBounds(175, 75, 115, 21);

        //---- textFieldSegmentTableLength ----
        contentPane.add(textFieldSegmentTableLength);
        textFieldSegmentTableLength.setBounds(175, 115, 115, 21);

        //---- buttonStart ----
        buttonStart.setText("\u8fdb\u5165\u7cfb\u7edf");
        buttonStart.addActionListener(e -> buttonStartActionPerformed(e));
        contentPane.add(buttonStart);
        buttonStart.setBounds(new Rectangle(new Point(140, 170), buttonStart.getPreferredSize()));

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

        setSize(360, 260);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textFieldPageNum;
    private JLabel label2;
    private JLabel label3;
    private JTextField textFieldFrameSize;
    private JTextField textFieldSegmentTableLength;
    private JButton buttonStart;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
