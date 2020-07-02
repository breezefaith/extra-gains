import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登录窗口
 */
class LoginFrame extends JFrame {
    private JPanel panel1;
    private JLabel label1;
    private JTextField textField_name;
    private JLabel label2;
    private JPasswordField passwordField;
    private JPanel panel2;
    private JButton button_login;
    private JButton button_reset;

    private String name = "杨祥胜";
    private String password = "123456";

    public LoginFrame() {
        initComponents();
    }

    private void initComponents() {
        //容器1
        panel1 = new JPanel();
        label1 = new JLabel();
        textField_name = new JTextField();
        label2 = new JLabel();
        passwordField = new JPasswordField();
        //容器2
        panel2 = new JPanel();
        button_login = new JButton();
        button_reset = new JButton();

        //主容器设置为Border布局
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //panel1设为流式布局
        panel1.setLayout(new FlowLayout());

        //---- label1 ----
        label1.setText("姓名");
        panel1.add(label1);

        //---- textField_name ----
        textField_name.setPreferredSize(new Dimension(150, 30));
        textField_name.setMinimumSize(new Dimension(150, 30));
        textField_name.setMaximumSize(new Dimension(150, 30));
        panel1.add(textField_name);

        //---- label2 ----
        label2.setText("密码");
        panel1.add(label2);

        //---- passwordField ----
        passwordField.setPreferredSize(new Dimension(150, 30));
        passwordField.setMinimumSize(new Dimension(150, 30));
        passwordField.setMaximumSize(new Dimension(150, 30));
        panel1.add(passwordField);

        contentPane.add(panel1, BorderLayout.NORTH);

        //panel2设为空布局
        panel2.setLayout(null);

        //---- button_login ----
        button_login.setText("登录");
        panel2.add(button_login);
        button_login.setBounds(new Rectangle(new Point(190, 35), button_login.getPreferredSize()));
        button_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickLogin(e);
            }
        });

        //---- button_reset ----
        button_reset.setText("重置");
        panel2.add(button_reset);
        button_reset.setBounds(new Rectangle(new Point(315, 35), button_reset.getPreferredSize()));
        button_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickReset(e);
            }
        });

        contentPane.add(panel2, BorderLayout.CENTER);

        setTitle("Java考试登录界面");//设置窗口标题
        setSize(new Dimension(600, 300));//设置窗口大小
        setPreferredSize(new Dimension(600, 300));//设置窗口大小
        setResizable(false);//不可放大缩小
        setLocationRelativeTo(null);//居中显示
        setDefaultCloseOperation(EXIT_ON_CLOSE);//点击关闭时退出

        pack();
    }

    /**
     * 处理点击登录按钮事件
     *
     * @param e
     */
    private void clickLogin(ActionEvent e) {
        if (name.equals(textField_name.getText()) && password.equals(new String(passwordField.getPassword()))) {
            JOptionPane.showMessageDialog(this, String.format("%s已通过验证，可以开始考试", name), "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, String.format("密码错误，请重置后重新输入"), "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * 处理点击重置按钮事件
     *
     * @param e
     */
    private void clickReset(ActionEvent e) {
        JOptionPane.showMessageDialog(this, String.format("您将信息重置"), "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        textField_name.setText("");
        passwordField.setText("");
    }
}

public class T2 {
    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();
        frame.setVisible(true);
    }
}
