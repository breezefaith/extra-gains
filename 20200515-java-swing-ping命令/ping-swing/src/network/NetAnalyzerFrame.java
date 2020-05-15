/*
 * Created by JFormDesigner on Fri May 15 21:38:02 CST 2020
 */

package network;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class NetAnalyzerFrame extends JFrame {
    private String os;

    public NetAnalyzerFrame() {
        os = System.getProperty("os.name");
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (os.startsWith("Windows")) {
            pingInWindows();
        } else if (os.startsWith("Mac")) {
            pingInMac();
        } else if (os.startsWith("Linux")) {
            pingInLinux();
        }
    }

    private void pingInWindows() {
        Process p = null;
        String line = null;
        try {
            p = Runtime.getRuntime().exec(String.format("cmd /c ping -n %s %s", String.valueOf(spinner1.getValue()), textField1.getText()));
            p.waitFor();
            if (p.exitValue() == 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
                while ((line = reader.readLine()) != null) {
                    textArea1.append(line + "\n");
                }
                scrollPane1.setVisible(true);
                label4.setVisible(false);
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream(), "GBK"));
                while ((line = reader.readLine()) != null) {
                    System.err.println(line);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void pingInLinux() {

    }

    private void pingInMac() {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        spinner1 = new JSpinner();
        button1 = new JButton();
        label4 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        label5 = new JLabel();

        //======== this ========
        setTitle("NetAnalyzer v1.0");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("Enter Test URL & no. of probes and click on Process");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(15, 35), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("Test URL");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(15, 75), label2.getPreferredSize()));
        contentPane.add(textField1);
        textField1.setBounds(70, 70, 325, textField1.getPreferredSize().height);

        //---- label3 ----
        label3.setText("No. of probes");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(50, 135), label3.getPreferredSize()));

        //---- spinner1 ----
        spinner1.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        contentPane.add(spinner1);
        spinner1.setBounds(new Rectangle(new Point(150, 130), spinner1.getPreferredSize()));

        //---- button1 ----
        button1.setText("Process");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(135, 195), button1.getPreferredSize()));

        //---- label4 ----
        label4.setText("Your output will appear here...");
        label4.setBackground(Color.white);
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(440, 20), label4.getPreferredSize()));

        //======== scrollPane1 ========
        {
            scrollPane1.setVisible(false);
            scrollPane1.setViewportView(textArea1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(440, 20, 440, 230);

        //---- label5 ----
        label5.setText("Histogram");
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(920, 20), label5.getPreferredSize()));

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
        setSize(1280, 315);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JLabel label3;
    private JSpinner spinner1;
    private JButton button1;
    private JLabel label4;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JLabel label5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
