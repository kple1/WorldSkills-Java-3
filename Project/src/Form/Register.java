package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import Data.DB;

import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register {

	private JFrame frame;
	private JTextField name;
	private JTextField id;
	private JTextField pw;
	private JTextField pwCheck;
	private JTextField phoneNum;
	private JTextField authCheck;
	private JTextField checkUserTextField;

	/**
	 * Launch the application.
	 */
	public JFrame getRegister() {
		return frame;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	double saveAuth = 0.0;
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 493, 558);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(frame);
		
		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setBounds(171, 10, 133, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(52, 79, 50, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("아이디");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(52, 133, 74, 24);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("비밀번호");
		lblNewLabel_1_2.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(52, 189, 93, 24);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("비밀번호 확인");
		lblNewLabel_1_3.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(52, 243, 133, 24);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("전화번호");
		lblNewLabel_1_4.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_1_4.setBounds(51, 295, 111, 24);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("인증번호 확인");
		lblNewLabel_1_5.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_1_5.setBounds(54, 347, 144, 24);
		frame.getContentPane().add(lblNewLabel_1_5);
		
		name = new JTextField();
		name.setBounds(190, 81, 144, 25);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(190, 135, 144, 25);
		frame.getContentPane().add(id);
		
		pw = new JTextField();
		pw.setColumns(10);
		pw.setBounds(190, 191, 144, 25);
		frame.getContentPane().add(pw);
		
		pwCheck = new JTextField();
		pwCheck.setColumns(10);
		pwCheck.setBounds(190, 242, 144, 25);
		frame.getContentPane().add(pwCheck);
		
		phoneNum = new JTextField();
		phoneNum.setColumns(10);
		phoneNum.setBounds(190, 295, 144, 25);
		frame.getContentPane().add(phoneNum);
		
		authCheck = new JTextField();
		authCheck.setColumns(10);
		authCheck.setBounds(190, 347, 144, 25);
		frame.getContentPane().add(authCheck);
		
		JButton btnNewButton = new JButton("인증번호 받기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (phoneNum.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요", "경고", JOptionPane.ERROR_MESSAGE);
				} else if (!Pattern.matches("^010-\\d{4}-\\d{4}$", phoneNum.getText())) { // ^ -> 문장의 시작 $ -> 문장의 끝 //d -> 정수 {} -> 수량
					JOptionPane.showMessageDialog(null, "전화번호 양식을 확인해주세요.", "경고", JOptionPane.ERROR_MESSAGE);
				} else {
					double random = (Math.random() * 8999) + 1000; //1000 ~ 9999 뽑을 경우 (Math.random() * 9999 - 1000) + 1000
					saveAuth = random;
					JOptionPane.showMessageDialog(null, "인증번호는 " + (int) random + " 입니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setBounds(346, 295, 121, 26);
		frame.getContentPane().add(btnNewButton);
		
		JButton checkAuth = new JButton("확인하기");
		checkAuth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!authCheck.getText().equals(String.valueOf((int) saveAuth))) {
					JOptionPane.showMessageDialog(null, "올바르지 않은 인증번호 입니다.", "경고", JOptionPane.ERROR_MESSAGE);
					saveAuth = 0;
				} else {
					JOptionPane.showMessageDialog(null, "인증되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		checkAuth.setBackground(SystemColor.activeCaption);
		checkAuth.setBounds(346, 345, 121, 26);
		frame.getContentPane().add(checkAuth);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(52, 395, 282, 23);
		frame.getContentPane().add(comboBox);
		
		checkUserTextField = new JTextField();
		checkUserTextField.setText("본인확인 질문 답변");
		checkUserTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				checkUserTextField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				checkUserTextField.setText("본인확인 질문 답변");
			}
		});
		checkUserTextField.setFont(new Font("굴림", Font.PLAIN, 15));
		checkUserTextField.setBounds(52, 428, 282, 26);
		frame.getContentPane().add(checkUserTextField);
		checkUserTextField.setColumns(10);
		
		JButton registerButton = new JButton("회원가입");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (String tables: DB.getAllData("u_id", "user")) {
					if (name.getText().isEmpty() || id.getText().isEmpty() || pw.getText().isEmpty() || pwCheck.getText().isEmpty() || phoneNum.getText().isEmpty() || checkAuth.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "빈칸이 있습니다.", "경고", JOptionPane.ERROR_MESSAGE);
						break;
					} else if (tables.equals(id.getText())) {
						JOptionPane.showMessageDialog(null, "아이디가 중복되었습니다.", "경고", JOptionPane.ERROR_MESSAGE);
						break;
					} else if (!Pattern.matches("(?!.*(.)\\1\\1\\1)([a-zA-Z0-9!@#$%]).{9,24}", pw.getText()) || 
							pw.getText().contains(id.getText()) || pw.getText().contains(phoneNum.getText())) {
						JOptionPane.showMessageDialog(null, "비밀번호 형식을 확인해주세요", "경고", JOptionPane.ERROR_MESSAGE);
						break;
					} else if (!pw.getText().equals(pwCheck.getText())) {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 안습니다.", "경고", JOptionPane.ERROR_MESSAGE);
						break;
					} else if (checkUserTextField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "본인확인 질문을 선택해주세요.", "경고", JOptionPane.ERROR_MESSAGE);
						break;
					} else if (authCheck.getText().isEmpty() && (int) saveAuth != Integer.parseInt(authCheck.getText())) {
						JOptionPane.showMessageDialog(null, "인증을 완료해주세요.", "경고", JOptionPane.ERROR_MESSAGE);
						break;
					} else {
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
						DB.insert(id.getText(), pw.getText(), checkUserTextField.getText());
						frame.dispose();
						Login login = new Login();
						login.getLogin().setVisible(true);
						break;
					}
				}
			}
		});
		registerButton.setBackground(SystemColor.activeCaption);
		registerButton.setBounds(171, 464, 163, 33);
		frame.getContentPane().add(registerButton);
	}
}
