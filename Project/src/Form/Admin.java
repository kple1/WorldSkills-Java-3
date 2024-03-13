package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
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
	public JFrame getFrame() {
		return frame;
	}
	
	public Admin() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("관리자");
		frame.setBounds(100, 100, 280, 220);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton auctionRegist = new JButton("경매 등록");
		auctionRegist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AuctionRegist ar = new AuctionRegist();
				ar.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		auctionRegist.setForeground(SystemColor.window);
		auctionRegist.setBackground(SystemColor.desktop);
		auctionRegist.setBounds(29, 25, 211, 34);
		frame.getContentPane().add(auctionRegist);
		
		JButton userManage = new JButton("회원관리");
		userManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserManage um = new UserManage();
				um.getFrame().setVisible(true);
			}
		});
		userManage.setForeground(SystemColor.window);
		userManage.setBackground(SystemColor.desktop);
		userManage.setBounds(29, 69, 211, 34);
		frame.getContentPane().add(userManage);
		
		JButton logout = new JButton("로그아웃");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "로그아웃되었습니다.");
				frame.dispose();
				Login login = new Login();
				login.getLogin().setVisible(true);
			}
		});
		logout.setForeground(SystemColor.window);
		logout.setBackground(SystemColor.desktop);
		logout.setBounds(29, 113, 211, 34);
		frame.getContentPane().add(logout);
	}
}
