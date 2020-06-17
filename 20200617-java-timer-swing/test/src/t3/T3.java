package t3;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class LogoPanel extends JPanel {
    ImageIcon icon;
    Image img;

    public LogoPanel() {
        icon = new ImageIcon(getClass().getResource("logo.jpg"));
        img = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

class QQLoginFrame extends JFrame {
    private JPanel panel_logo;
    private JLabel label1;
    private JLabel label2;
    private JTextField text_username;
    private JPasswordField passwordField;
    private JButton btn_login;
    private JCheckBox chk_remember;
    private JLabel lab_forget;

    private boolean remember;

    public QQLoginFrame() {
        initComponents();
    }

    private void initComponents() {
        panel_logo = new LogoPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        text_username = new JTextField();
        passwordField = new JPasswordField();
        btn_login = new JButton();
        chk_remember = new JCheckBox();
        lab_forget = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        contentPane.add(panel_logo);
        panel_logo.setBounds(70, 45, 300, 200);

        //---- label1 ----
        label1.setText("用户名");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(110, 275), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("密码");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(120, 325), label2.getPreferredSize()));

        contentPane.add(text_username);
        text_username.setBounds(165, 270, 165, text_username.getPreferredSize().height);

        contentPane.add(passwordField);
        passwordField.setBounds(165, 320, 165, passwordField.getPreferredSize().height);

        //---- btn_login ----
        btn_login.setText("登录");
        btn_login.addActionListener(e -> btn_loginActionPerformed(e));
        contentPane.add(btn_login);
        btn_login.setBounds(new Rectangle(new Point(165, 395), btn_login.getPreferredSize()));

        //---- chk_rember ----
        chk_remember.setText("记住密码");
        contentPane.add(chk_remember);
        chk_remember.setBounds(new Rectangle(new Point(165, 360), chk_remember.getPreferredSize()));
        chk_remember.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                chk_rememberStateChanged(e);
            }
        });

        //---- lab_forget ----
        lab_forget.setText("忘记密码");
        lab_forget.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lab_forgetMouseClicked(e);
            }
        });
        contentPane.add(lab_forget);
        lab_forget.setBounds(new Rectangle(new Point(335, 325), lab_forget.getPreferredSize()));

        pack();
        setSize(450, 600);
        setTitle("QQ登录界面");
        setLocationRelativeTo(getOwner());
    }

    private void btn_loginActionPerformed(ActionEvent e) {
        String username = text_username.getText();
        String password = new String(passwordField.getPassword());
        boolean remember = this.remember;

        if ("user_name".equals(username) && "123456".equals(password)) {
            if (remember) {
                JOptionPane.showMessageDialog(this, "登录成功且记住密码", null, JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "登录成功且不记住密码", null, JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "账号或密码错误", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void chk_rememberStateChanged(ChangeEvent e) {
        JCheckBox checkBox = (JCheckBox) e.getSource();
        this.remember = checkBox.isSelected();
    }

    private void lab_forgetMouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(this, "忘记密码", null, JOptionPane.WARNING_MESSAGE);
    }
}

public class T3 {
    public static void main(String[] args) {
        JFrame frame = new QQLoginFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
