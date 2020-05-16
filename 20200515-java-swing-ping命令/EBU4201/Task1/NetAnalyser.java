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
 * This class extends from JFrame, and has a main method for running the application.
 *
 * @see javax.swing.JFrame
 */
public class NetAnalyser extends JFrame {
    /**
     * The name of OS.
     */
    private String os = System.getProperty("os.name");
    /**
     * The textfield of test url.
     */
    private JTextField textFieldUrl;
    /**
     * The spinner of probes' number.
     */
    private JSpinner spinnerNo;
    /**
     * The button for executing ping command.
     */
    private JButton buttonProcess;
    /**
     * The label of output.
     */
    private JLabel labelOutput;
    /**
     * The scroll panel of output.
     */
    private JScrollPane scrollPaneOutput;
    /**
     * The textarea of output.
     */
    private JTextArea textAreaOutput;
    /**
     * The label of histogram bin1.
     */
    private JLabel bin1;
    /**
     * The label of histogram bin1.
     */
    private JLabel bin2;
    /**
     * The label of histogram bin2.
     */
    private JLabel bin3;
    /**
     * The label of histogram strip1.
     */
    private JLabel strip1;
    /**
     * The label of histogram strip2.
     */
    private JLabel strip2;
    /**
     * The label of histogram strip3.
     */
    private JLabel strip3;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label5;

    /**
     * The default constructor.
     */
    public NetAnalyser() {
        initComponents();
    }

    /**
     * The event handler for clicking the Process button.
     *
     * @param e click event
     */
    private void buttonProcessActionPerformed(ActionEvent e) {
        // clear textarea
        textAreaOutput.setText("");
        // get the test url
        String url = textFieldUrl.getText();
        // get probes' number
        String no = String.valueOf(spinnerNo.getValue());
        // the actual command
        String cmd = null;
        if (os.startsWith("Windows")) {
            // in Windows OS
            cmd = String.format("cmd /c ping -n %s %s", no, url);
        } else if (os.startsWith("Mac")) {
            // in Mac OS
            cmd = String.format("ping -c %s %s", no, url);
        } else if (os.startsWith("Linux")) {
            // in Linux OS
            cmd = String.format("ping -c %s %s", no, url);
        }
        // execute the command
        execCommand(cmd);
    }

    /**
     * Execute a command by using Process. This method also displays
     * the results of the execution on the corresponding control.
     *
     * @param cmd the actual command.
     */
    public void execCommand(String cmd) {
        //system process
        Process p = null;
        // read the output of process line by line
        String line = null;

        // store the RTTs of all probes
        ArrayList<Integer> RTTs = new ArrayList<>();
        // store the RTTs' min(0), max(1), average(2)
        ArrayList<Integer> RTTStatistics = new ArrayList<>();
        // integer value of max RTT
        int maxRTT = -1;
        // integer value of min RTT
        int minRTT = -1;
        // interval size
        int div = 0;
        // bin1's frequecy
        int bin1Freq = 0;
        // bin2's frequecy
        int bin2Freq = 0;
        // bin3's frequecy
        int bin3Freq = 0;
        //strip1
        String s1 = "";
        //strip2
        String s2 = "";
        //strip3
        String s3 = "";
        //pattern for every probe line
        Pattern pattern4One = Pattern.compile("=\\d*ms");
        //pattern for the last statistical line
        Pattern pattern4Total = Pattern.compile("=(\\s+|\\t+)\\d*ms");
        //pattern for extracting digital
        Pattern pattern4NoNumber = Pattern.compile("[^0-9]");
        Matcher m4One = null;
        Matcher m4Total = null;
        try {
            //get the system process of the command execution
            p = Runtime.getRuntime().exec(cmd);
            // wait for ending
            p.waitFor();
            //if exit normally
            if (p.exitValue() == 0) {
                //read the output to buffer
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
                //read line by line
                while ((line = reader.readLine()) != null) {
                    //output to textarea
                    textAreaOutput.append(line + "\n");

                    m4One = pattern4One.matcher(line);
                    m4Total = pattern4Total.matcher(line);
                    // if the last statistical line
                    while (m4Total.find()) {
                        RTTStatistics.add(Integer.valueOf(pattern4NoNumber.matcher(m4Total.group(0)).replaceAll("")));
                    }
                    //every probe line
                    while (m4One.find()) {
                        RTTs.add(Integer.valueOf(pattern4NoNumber.matcher(m4One.group(0)).replaceAll("")));
                    }
                }

                minRTT = RTTStatistics.get(0);
                maxRTT = RTTStatistics.get(1);
                // calculate the interval
                div = (int) Math.ceil((maxRTT - minRTT) / 3.0);
                div = div <= 0 ? 1 : div;

                // count the frequencies
                for (Integer RTT : RTTs) {
                    if (RTT >= minRTT && RTT < minRTT + div) {
                        bin1Freq++;
                    } else if (RTT >= minRTT + div && RTT < minRTT + 2 * div) {
                        bin2Freq++;
                    } else if (RTT >= minRTT + 2 * div && RTT <= minRTT + 3 * div) {
                        bin3Freq++;
                    }
                }

                // generate the strips
                for (int i = 0; i < bin1Freq; i++) {
                    s1 += "*  ";
                }
                for (int i = 0; i < bin2Freq; i++) {
                    s2 += "*  ";
                }
                for (int i = 0; i < bin3Freq; i++) {
                    s3 += "*  ";
                }

                // show in UI
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

    /**
     * Initialize the controls.
     */
    private void initComponents() {
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
        buttonProcess.addActionListener(e -> buttonProcessActionPerformed(e));
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
    }

    /**
     * Application entry.
     *
     * @param args click event
     */
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
