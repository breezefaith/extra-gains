import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/*
 * Created by JFormDesigner on Mon Jun 01 18:41:25 CST 2020
 */


/**
 * @author zzc
 */
public class Main extends JFrame {
    private JPanel panelDisplay;
    private JPanel panel2;
    private JLabel label1;
    private JTextField textFieldX;
    private JLabel label2;
    private JTextField textFieldY;
    private JLabel label3;
    private JTextField textFieldR;
    private JLabel label4;
    private JTextField textFieldG;
    private JLabel label5;
    private JTextField textFieldB;
    private JButton buttonOk;
    private JButton buttonReset;
    private JButton[][] buttons;

    public Main() {
        initComponents();
        buttons = new JButton[2][3];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton[3];
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setEnabled(false);
                buttons[i][j].setBackground(null);
                panelDisplay.add(buttons[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setTitle("Swing");
        main.setVisible(true);
    }

    private void buttonOkActionPerformed(ActionEvent e) {
        try {
            int x = Integer.parseInt(textFieldX.getText());
            int y = Integer.parseInt(textFieldY.getText());
            int r = Integer.parseInt(textFieldR.getText());
            int g = Integer.parseInt(textFieldG.getText());
            int b = Integer.parseInt(textFieldB.getText());

            if (x < 1 || x > 2 || y < 1 || y > 3 || r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
                throw new Exception();
            }

            buttons[x - 1][y - 1].setBackground(new Color(r, g, b));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "请检查输入", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buttonResetActionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setBackground(null);
            }
        }
    }

    private void initComponents() {
        panelDisplay = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        textFieldX = new JTextField(3);
        label2 = new JLabel();
        textFieldY = new JTextField(3);
        label3 = new JLabel();
        textFieldR = new JTextField(3);
        label4 = new JLabel();
        textFieldG = new JTextField(3);
        label5 = new JLabel();
        textFieldB = new JTextField(3);
        buttonOk = new JButton();
        buttonReset = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(2, 2));

        //======== panelDisplay ========
        {
            panelDisplay.setLayout(new GridLayout(2, 3, 2, 2));
        }
        contentPane.add(panelDisplay, BorderLayout.CENTER);

        //======== panel2 ========
        {
            panel2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            panel2.setLayout(new FlowLayout());

            //---- label1 ----
            label1.setText("X(1~2)");
            panel2.add(label1);
            textFieldX.setToolTipText("1~2");
            panel2.add(textFieldX);

            //---- label2 ----
            label2.setText("Y(1~3)");
            panel2.add(label2);
            textFieldY.setToolTipText("1~3");
            panel2.add(textFieldY);

            //---- label3 ----
            label3.setText("R(0~255)");
            panel2.add(label3);
            textFieldR.setToolTipText("0~255");
            panel2.add(textFieldR);

            //---- label4 ----
            label4.setText("G(0~255)");
            panel2.add(label4);
            textFieldG.setToolTipText("0~255");
            panel2.add(textFieldG);

            //---- label5 ----
            label5.setText("B(0~255)");
            panel2.add(label5);
            textFieldB.setToolTipText("0~255");
            panel2.add(textFieldB);

            //---- buttonOk ----
            buttonOk.setText("确定");
            buttonOk.addActionListener(e -> buttonOkActionPerformed(e));
            panel2.add(buttonOk);

            //---- buttonReset ----
            buttonReset.setText("重置");
            buttonReset.addActionListener(e -> buttonResetActionPerformed(e));
            panel2.add(buttonReset);
        }
        contentPane.add(panel2, BorderLayout.SOUTH);

        setSize(885, 440);
        setLocationRelativeTo(getOwner());
    }
}
