package task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brainrain
 */
public class NetAnalyser extends JFrame {
    private String os = System.getProperty("os.name");

    public NetAnalyser() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        textAreaOutput.setText("");
        String url = textFieldUrl.getText();
        String no = String.valueOf(spinnerNo.getValue());
        String cmd = null;
        if (os.startsWith("Windows")) {
            cmd = String.format("cmd /c ping -n %s %s", no, url);
        } else if (os.startsWith("Mac")) {
            cmd = String.format("ping -c %s %s", no, url);
        } else if (os.startsWith("Linux")) {
            cmd = String.format("ping -c %s %s", no, url);
        }
        execCommand(cmd);
    }

    public void execCommand(String cmd) {
        Process p = null;
        String line = null;

        ArrayList<Integer> RTTs = new ArrayList<>();
        ArrayList<Integer> RTTStatistics = new ArrayList<>();
        int maxRTT = -1;
        int minRTT = -1;
        int div = 0;
        int bin1Freq = 0;
        int bin2Freq = 0;
        int bin3Freq = 0;
        String s1 = "";
        String s2 = "";
        String s3 = "";
        Pattern pattern4One = Pattern.compile("=\\d*ms");
        Pattern pattern4Total = Pattern.compile("=(\\s+|\\t+)\\d*ms");
        Pattern pattern4NoNumber = Pattern.compile("[^0-9]");
        Matcher m4One = null;
        Matcher m4Total = null;
        try {
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            if (p.exitValue() == 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
                while ((line = reader.readLine()) != null) {
                    textAreaOutput.append(line + "\n");

                    m4One = pattern4One.matcher(line);
                    m4Total = pattern4Total.matcher(line);
                    while (m4Total.find()) {
                        RTTStatistics.add(Integer.valueOf(pattern4NoNumber.matcher(m4Total.group(0)).replaceAll("")));
                    }
                    while (m4One.find()) {
                        RTTs.add(Integer.valueOf(pattern4NoNumber.matcher(m4One.group(0)).replaceAll("")));
                    }
                }

                minRTT = RTTStatistics.get(0);
                maxRTT = RTTStatistics.get(1);
                div = (int) Math.ceil((maxRTT - minRTT) / 3.0);
                div = div <= 0 ? 1 : div;

                for (Integer RTT : RTTs) {
                    if (RTT >= minRTT && RTT < minRTT + div) {
                        bin1Freq++;
                    } else if (RTT >= minRTT + div && RTT < minRTT + 2 * div) {
                        bin2Freq++;
                    } else if (RTT >= minRTT + 2 * div && RTT <= minRTT + 3 * div) {
                        bin3Freq++;
                    }
                }

                for (int i = 0; i < bin1Freq; i++) {
                    s1 += "*  ";
                }
                for (int i = 0; i < bin2Freq; i++) {
                    s2 += "*  ";
                }
                for (int i = 0; i < bin3Freq; i++) {
                    s3 += "*  ";
                }

                bin1.setText(String.format("%d<=RTT<%d", minRTT, minRTT + div));
                bin2.setText(String.format("%d<=RTT<%d", minRTT + div, minRTT + 2 * div));
                bin3.setText(String.format("%d<=RTT<=%d", minRTT + 2 * div, minRTT + 3 * div));
                strip1.setText(s1);
                strip2.setText(s2);
                strip3.setText(s3);

                scrollPaneOutput.setVisible(true);
                labelOutput.setVisible(false);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        textFieldUrl = new JTextField();
        label3 = new JLabel();
        spinnerNo = new JSpinner();
        buttonProcess = new JButton();
        labelOutput = new JLabel();
        scrollPaneOutput = new JScrollPane();
        textAreaOutput = new JTextArea();
        label5 = new JLabel();
        bin1 = new JLabel();
        bin2 = new JLabel();
        bin3 = new JLabel();
        strip1 = new JLabel();
        strip2 = new JLabel();
        strip3 = new JLabel();

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
        contentPane.add(textFieldUrl);
        textFieldUrl.setBounds(70, 70, 325, textFieldUrl.getPreferredSize().height);

        //---- label3 ----
        label3.setText("No. of probes");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(50, 135), label3.getPreferredSize()));

        //---- spinnerNo ----
        spinnerNo.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        contentPane.add(spinnerNo);
        spinnerNo.setBounds(new Rectangle(new Point(150, 130), spinnerNo.getPreferredSize()));

        //---- buttonProcess ----
        buttonProcess.setText("Process");
        buttonProcess.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(buttonProcess);
        buttonProcess.setBounds(new Rectangle(new Point(135, 195), buttonProcess.getPreferredSize()));

        //---- labelOutput ----
        labelOutput.setText("Your output will appear here...");
        labelOutput.setBackground(Color.white);
        contentPane.add(labelOutput);
        labelOutput.setBounds(new Rectangle(new Point(440, 20), labelOutput.getPreferredSize()));

        //======== scrollPaneOutput ========
        {
            scrollPaneOutput.setVisible(false);
            scrollPaneOutput.setViewportView(textAreaOutput);
        }
        contentPane.add(scrollPaneOutput);
        scrollPaneOutput.setBounds(440, 20, 440, 230);

        //---- label5 ----
        label5.setText("Histogram");
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(915, 20), label5.getPreferredSize()));
        contentPane.add(bin1);
        bin1.setBounds(915, 65, 120, 20);
        contentPane.add(bin2);
        bin2.setBounds(915, 120, 120, 20);
        contentPane.add(bin3);
        bin3.setBounds(915, 175, 120, 20);
        contentPane.add(strip1);
        strip1.setBounds(1035, 65, 155, 20);
        contentPane.add(strip2);
        strip2.setBounds(1035, 120, 155, 20);
        contentPane.add(strip3);
        strip3.setBounds(1035, 175, 155, 20);

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

        setSize(1280, 315);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField textFieldUrl;
    private JLabel label3;
    private JSpinner spinnerNo;
    private JButton buttonProcess;
    private JLabel labelOutput;
    private JScrollPane scrollPaneOutput;
    private JTextArea textAreaOutput;
    private JLabel label5;
    private JLabel bin1;
    private JLabel bin2;
    private JLabel bin3;
    private JLabel strip1;
    private JLabel strip2;
    private JLabel strip3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new NetAnalyser();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
