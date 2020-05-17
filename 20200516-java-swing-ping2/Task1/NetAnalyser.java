import javax.swing.*;

public class NetAnalyser {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        JPanel jPanel = new NetAnalyserPanel();
        jFrame.setTitle("NetAnalyser v1.0");
        jFrame.setBounds(100,100,1100,400);
        jFrame.setContentPane(jPanel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
