import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sat May 16 21:11:14 CST 2020
 */


/**
 * @author Brainrain
 */
public class NetAnalyserPanel extends JPanel {
    private JPanel panel_main;
    private JTextField textField_url;
    private JSpinner spinner_no;
    private JButton button_process;
    private JTextArea textArea_result;
    private JTextField textField_bar1;
    private JTextField textField_bar2;
    private JTextField textField_bar3;
    private JLabel label_bar1;
    private JLabel label_bar2;
    private JLabel label_bar3;

    /**
     * the pattern for matching every RTT
     */
    private Pattern pattern = Pattern.compile("=\\d+ms");

    /**
     * RTTs' array
     */
    private int[] arr_rtt;
    /**
     * the max value of arr_rtt
     */
    private int max;
    /**
     * the min value of arr_rtt
     */
    private int min;
    /**
     * the interval of bar
     */
    private int interval;
    /**
     * the length of 1st bar
     */
    private int bar1len;
    /**
     * the length of 2nd bar
     */
    private int bar2len;
    /**
     * the length of 3rd bar
     */
    private int bar3len;

    /**
     * the default constructor
     */
    public NetAnalyserPanel() {
        createUI();
    }

    /**
     * initialize components
     */
    private void createUI() {
        setPreferredSize(new Dimension(1100, 400));
        setLayout(new BorderLayout());

        panel_main = new JPanel();
        add(panel_main, BorderLayout.WEST);
        panel_main.setPreferredSize(new Dimension(350, 600));
        panel_main.setLayout(null);

        JLabel label_tiltip = new JLabel("Enter Test URL & no. of probes and click on Process");
        panel_main.add(label_tiltip);
        label_tiltip.setBounds(10, 10, label_tiltip.getPreferredSize().width, 30);

        JLabel label_url = new JLabel("Test URL");
        panel_main.add(label_url);
        label_url.setBounds(20, 70, label_url.getPreferredSize().width, 30);

        textField_url = new JTextField();
        panel_main.add(textField_url);
        textField_url.setBounds(90, 70, 200, 30);

        JLabel label_no = new JLabel("No. of probes");
        panel_main.add(label_no);
        label_no.setBounds(60, 150, label_no.getPreferredSize().width, 30);

        spinner_no = new JSpinner();
        panel_main.add(spinner_no);
        spinner_no.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        spinner_no.setBounds(175, 150, 100, 30);

        button_process = new JButton("Process");
        panel_main.add(button_process);
        button_process.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickBtnProcess();
            }
        });
        button_process.setBounds(new Rectangle(new Point(120, 250), button_process.getPreferredSize()));

        JScrollPane scrollPane1 = new JScrollPane();
        add(scrollPane1, BorderLayout.CENTER);
        scrollPane1.setPreferredSize(new Dimension(300, 600));

        textArea_result = new JTextArea("Your output will appear here...\n");
        scrollPane1.setViewportView(textArea_result);
        textArea_result.setMargin(new Insets(10, 10, 10, 10));
        textArea_result.setEditable(false);

        JPanel panel2 = new JPanel();
        add(panel2, BorderLayout.EAST);
        panel2.setPreferredSize(new Dimension(400, 600));
        panel2.setLayout(null);

        label_bar1 = new JLabel("bar1");
        panel2.add(label_bar1);
        label_bar1.setHorizontalAlignment(SwingConstants.RIGHT);
        label_bar1.setBounds(30, 70, 120, 30);

        label_bar2 = new JLabel("bar2");
        panel2.add(label_bar2);
        label_bar2.setHorizontalAlignment(SwingConstants.RIGHT);
        label_bar2.setBounds(30, 160, 120, 30);

        label_bar3 = new JLabel("bar3");
        panel2.add(label_bar3);
        label_bar3.setHorizontalAlignment(SwingConstants.RIGHT);
        label_bar3.setBounds(30, 250, 120, 30);

        JLabel label_histogram = new JLabel("Histogram");
        panel2.add(label_histogram);
        label_histogram.setBounds(25, 10, 150, 30);

        textField_bar1 = new JTextField();
        panel2.add(textField_bar1);
        textField_bar1.setEditable(false);
        textField_bar1.setBounds(160, 70, 200, 30);

        textField_bar2 = new JTextField();
        panel2.add(textField_bar2);
        textField_bar2.setEditable(false);
        textField_bar2.setBounds(160, 160, 200, 30);

        textField_bar3 = new JTextField();
        panel2.add(textField_bar3);
        textField_bar3.setEditable(false);
        textField_bar3.setBounds(160, 250, 200, 30);
    }

    /**
     * click event of button Process
     */
    private void clickBtnProcess() {
        //reset histogram
        resetHistogram();
        Process p = null;
        try {
            if (validateUrl(textField_url.getText().trim()) == false) {
                textArea_result.append("The test url \"" + textField_url.getText() + "\" is invalid.\n");
                return;
            }
            // execute the ping cmd
            p = Runtime.getRuntime().exec("cmd /c ping -n " + spinner_no.getValue() + " " + textField_url.getText().trim());
            p.waitFor();

            //if ping executed successfully
            if (p.exitValue() == 0) {
                ArrayList<Integer> integers = new ArrayList<>();
                int i = 0;
                String line = null;
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
                while ((line = reader.readLine()) != null) {
                    textArea_result.append(line + "\n");
                    // extract RTTs
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        integers.add(Integer.valueOf(matcher.group().replaceAll("[^0-9]", "")));
                    }
                }

                if (integers.size() == 0) {
                    textArea_result.append("All of the requests were timeout.\n");
                    return;
                }
                // convert array list to int array
                arr_rtt = integers.stream().mapToInt(Integer::valueOf).toArray();
                // get max
                max = max(arr_rtt);
                // get min
                min = min(arr_rtt);

                // calculate data
                calResult();
                // paint histogram
                paintHistogram();
            } else {
                textArea_result.append("Test URL: " + textField_url.getText().trim() + "\n");
                textArea_result.append("The ping command failed to execute.\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            textArea_result.append(ex.getMessage() + "\n");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            textArea_result.append(ex.getMessage() + "\n");
        }
    }

    /**
     * check if url is valid
     *
     * @param url the url from textfield_url
     * @return true: valid, false: invalid
     */
    private boolean validateUrl(String url) {
        return url != null && !"".equals(url.trim()) && url.contains(".");
    }

    /**
     * analyze the execution result
     */
    private void calResult() {
        for (int i = 0; i < arr_rtt.length; i++) {
            if (arr_rtt[i] >= min && arr_rtt[i] < min + interval) {
                bar1len++;
            } else if (arr_rtt[i] >= min + interval && arr_rtt[i] < min + 2 * interval) {
                bar2len++;
            } else if (arr_rtt[i] >= min + 2 * interval && arr_rtt[i] <= min + 3 * interval) {
                bar3len++;
            }
        }
    }

    /**
     * reset the histogram's components
     */
    private void resetHistogram() {
        label_bar1.setText("bar1");
        label_bar1.setText("bar2");
        label_bar1.setText("bar3");
        textField_bar1.setText("");
        textField_bar2.setText("");
        textField_bar3.setText("");

        bar1len = 0;
        bar2len = 0;
        bar3len = 0;
        if (max - min < 3) {
            interval = 1;
        } else {
            interval = (int) Math.ceil((max - min) / 3.0);
        }
    }

    /**
     * draw the histogram
     */
    private void paintHistogram() {
        // set abscissa axis
        label_bar1.setText(min + "<=RTT<" + (min + interval));
        label_bar2.setText((min + interval) + "<=RTT<" + (min + 2 * interval));
        label_bar3.setText((min + 2 * interval) + "<=RTT<=" + (min + 3 * interval));

        // paint bars
        paintBar(textField_bar1, bar1len);
        paintBar(textField_bar2, bar2len);
        paintBar(textField_bar3, bar3len);
    }

    /**
     * draw the specific bar
     *
     * @param textField the bar text field
     * @param len       the frequency
     */
    private void paintBar(JTextField textField, int len) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append("X ");
        }
        textField.setText(sb.toString());
    }

    /**
     * get the max value of an array
     *
     * @param arr an array of integers
     * @return the max element
     */
    private int max(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = max < arr[i] ? arr[i] : max;
        }
        return max;
    }

    /**
     * get the min value of an array
     *
     * @param arr an array of integers
     * @return the min element
     */
    private int min(int[] arr) {
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            min = min > arr[i] ? arr[i] : min;
        }
        return min;
    }
}
