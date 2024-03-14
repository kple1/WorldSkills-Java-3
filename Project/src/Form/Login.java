package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.Timer;

import Data.DB;
import Utils.ChangeLogo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	public static JTextField id_textField;
	public static JTextField pw_textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JFrame getLogin() {
		return frame;
	}

	public Login() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 472, 242);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(frame);
		frame.setTitle("로그인");
		
		new ChangeLogo(frame);
		
		JLabel id_Label = new JLabel("아이디");
		id_Label.setFont(new Font("굴림", Font.PLAIN, 20));
		id_Label.setBounds(50, 53, 66, 24);
		frame.getContentPane().add(id_Label);
		
		JLabel pw_Label = new JLabel("비밀번호");
		pw_Label.setFont(new Font("굴림", Font.PLAIN, 20));
		pw_Label.setBounds(50, 97, 84, 24);
		frame.getContentPane().add(pw_Label);
		
		JButton loginButton = new JButton("로그인");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getId = DB.getData("u_id", "u_id", id_textField.getText(), "user");
				String getPw = DB.getData("u_pw", "u_id", id_textField.getText(), "user");
				if (id_textField.getText().isEmpty() || pw_textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "입력하지 않은 항목이 있습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				} else if (id_textField.getText().equals("admin") && pw_textField.getText().equals("1234")) {
					JOptionPane.showMessageDialog(null, "관리자님 환영합니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					Admin admin = new Admin();
					admin.getFrame().setVisible(true);
				} else if (getId.equals(id_textField.getText()) && getPw.equals(pw_textField.getText())) {
					JOptionPane.showMessageDialog(null, "환영합니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					Main main = new Main();
					main.getMain().setVisible(true);
				} else if (!getId.equals(id_textField.getText())) {
					JOptionPane.showMessageDialog(null, "존재하는 회원이 없습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginButton.setBackground(SystemColor.activeCaption);
		loginButton.setBounds(50, 141, 175, 33);
		frame.getContentPane().add(loginButton);
		
		JButton registerButton = new JButton("회원가입");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Register rg = new Register();
				rg.getRegister().setVisible(true);
			}
		});
		registerButton.setBackground(SystemColor.activeCaption);
		registerButton.setBounds(237, 141, 175, 33);
		frame.getContentPane().add(registerButton);
		
		id_textField = new JTextField();
		id_textField.setBounds(194, 53, 252, 25);
		frame.getContentPane().add(id_textField);
		id_textField.setColumns(10);
		
		pw_textField = new JTextField();
		pw_textField.setColumns(10);
		pw_textField.setBounds(194, 99, 252, 25);
		frame.getContentPane().add(pw_textField);
	}
}
