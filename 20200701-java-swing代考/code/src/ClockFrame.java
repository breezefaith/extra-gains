import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClockFrame extends JFrame {
    private JLabel label;

    //日期格式化器
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH时：mm分：ss秒");

    public ClockFrame() {
        initComponents();
    }

    private void initComponents() {
        setLocationRelativeTo(null);//居中显示
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true); // 去掉窗口的装饰
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定的窗口装饰风格

        //主容器设置为空布局，指定大小
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setSize(200, 50);
        contentPane.setPreferredSize(new Dimension(200, 50));

        label = new JLabel("", JLabel.CENTER);
        contentPane.add(label);
        //大小
        label.setSize(200, 50);
        label.setPreferredSize(new Dimension(200, 50));
        //字体
        label.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        //颜色
        label.setForeground(Color.RED);

        pack();

        //创建线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //获取当前时间
                    Calendar calendar = Calendar.getInstance();
                    //更新文本
                    label.setText(sdf.format(calendar.getTime()));
                }
            }
        });
        //启动线程
        thread.start();

        //鼠标事件处理
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //双击结束程序
                if (e.getClickCount() == 2) {
                    System.exit(0);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    public static void main(String[] args) {
        ClockFrame frame = new ClockFrame();
        frame.setVisible(true);
    }
}
