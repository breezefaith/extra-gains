import javax.swing.*;

public class NetAnalyser {
    public static void main(String[] args) {
        int probes = 0;
        if (args.length == 0 || Integer.valueOf(args[0]) < 10 || Integer.valueOf(args[0]) > 20) {
            probes = 10;
        } else {
            probes = Integer.valueOf(args[0]);
        }

        JFrame jFrame = new JFrame();
        JPanel jPanel = new NetAnalyserPanel(probes);
        jFrame.setTitle("NetAnalyser v1.0");
        jFrame.setBounds(100, 100, 1100, 400);
        jFrame.setContentPane(jPanel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
