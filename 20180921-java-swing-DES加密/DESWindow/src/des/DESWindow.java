package des;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DESWindow {

	private JFrame frmDesaes;
	private JTextField textFieldSecretKey;
	private JTextField textFieldCiphertext;
	private JTextField textFieldPlaintext;
	
	private JButton buttonEncrypt;
	private JButton buttonDecrypt;
	
	private DES des=new DES();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DESWindow window = new DESWindow();
					window.frmDesaes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DESWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDesaes = new JFrame();
		frmDesaes.setFont(new Font("Consolas", Font.PLAIN, 12));
		frmDesaes.setResizable(false);
		frmDesaes.setTitle("DESWindow");
		frmDesaes.setBounds(100, 100, 450, 300);
		frmDesaes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDesaes.getContentPane().setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panelPlaintext = new JPanel();
		FlowLayout flowLayoutPlaintext = (FlowLayout) panelPlaintext.getLayout();
		flowLayoutPlaintext.setVgap(40);
		flowLayoutPlaintext.setHgap(20);
		frmDesaes.getContentPane().add(panelPlaintext);
		
		JLabel labelPlaintext = new JLabel("明文");
		panelPlaintext.add(labelPlaintext);
		
		textFieldPlaintext = new JTextField();
		textFieldPlaintext.setColumns(20);
		panelPlaintext.add(textFieldPlaintext);
		
		JPanel panelKeySecretKey = new JPanel();
		FlowLayout flowLayoutSecretKey = (FlowLayout) panelKeySecretKey.getLayout();
		flowLayoutSecretKey.setVgap(20);
		flowLayoutSecretKey.setHgap(20);
		frmDesaes.getContentPane().add(panelKeySecretKey);
		
		JLabel labelSecretKey = new JLabel("密钥");
		panelKeySecretKey.add(labelSecretKey);
		
		textFieldSecretKey = new JTextField();
		panelKeySecretKey.add(textFieldSecretKey);
		textFieldSecretKey.setColumns(20);
		
		JPanel panelCiphertext = new JPanel();
		FlowLayout flowLayoutCiphertext = (FlowLayout) panelCiphertext.getLayout();
		flowLayoutCiphertext.setVgap(0);
		flowLayoutCiphertext.setHgap(20);
		frmDesaes.getContentPane().add(panelCiphertext);
		
		JLabel labelCiphertext = new JLabel("密文");
		panelCiphertext.add(labelCiphertext);
		
		textFieldCiphertext = new JTextField();
		textFieldCiphertext.setColumns(20);
		panelCiphertext.add(textFieldCiphertext);
		
		JPanel panelButton = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelButton.getLayout();
		flowLayout.setHgap(50);
		buttonEncrypt=new JButton("加密");
		buttonEncrypt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String plaintext=textFieldPlaintext.getText();
				String secretKey=textFieldSecretKey.getText();
				if(plaintext==null||"".equals(plaintext)) {
					JOptionPane.showMessageDialog(frmDesaes, "请输入明文！", "提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(secretKey==null||"".equals(secretKey)) {
					JOptionPane.showMessageDialog(frmDesaes, "请输入密钥！", "提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String ciphertext=des.encrypt(plaintext, secretKey, DES.ENCRYPT);
				textFieldCiphertext.setText(ciphertext);
			}
		});
		
		buttonDecrypt=new JButton("解密");
		buttonDecrypt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ciphertext=textFieldCiphertext.getText();
				String secretKey=textFieldSecretKey.getText();
				if(ciphertext==null||"".equals(ciphertext)) {
					JOptionPane.showMessageDialog(frmDesaes, "请输入密文！", "提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(secretKey==null||"".equals(secretKey)) {
					JOptionPane.showMessageDialog(frmDesaes, "请输入密钥！", "提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String plaintext=des.encrypt(ciphertext, secretKey, DES.DECRYPT);
				textFieldPlaintext.setText(plaintext);
			}
		});
		
		panelButton.add(buttonEncrypt);
		panelButton.add(buttonDecrypt);
		frmDesaes.getContentPane().add(panelButton);
	}

}
