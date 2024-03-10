package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import Utils.Art;

public class BuildingInfo {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuildingInfo window = new BuildingInfo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public JFrame getBuildingInfo() {
		return frame;
	}
	/**
	 * Create the application.
	 */
	public BuildingInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public JLabel resultPrice;
	public JLabel price;
	public JLabel auctionDay;
	public JLabel denyAuction;
	public JLabel favoriatePeoples;
	public JPanel imagePanel;
	public JLabel buildingName;
	public JLabel location;
	public JLabel type;
	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("빌딩 정보");
		frame.setBounds(100, 100, 559, 624);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		imagePanel = new JPanel();
		imagePanel.setBounds(12, 10, 239, 274);
		frame.getContentPane().add(imagePanel);
		
		buildingName = new JLabel("a");
		buildingName.setBounds(263, 79, 268, 15);
		frame.getContentPane().add(buildingName);
		
		location = new JLabel("a");
		location.setBounds(263, 135, 268, 15);
		frame.getContentPane().add(location);
		
		type = new JLabel("a");
		type.setBounds(263, 193, 268, 15);
		frame.getContentPane().add(type);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaptionBorder);
		panel.setBounds(12, 294, 519, 2);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("최저가격");
		lblNewLabel.setBounds(12, 306, 57, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("감정평가");
		lblNewLabel_1.setBounds(12, 355, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("유찰횟수");
		lblNewLabel_2.setBounds(12, 406, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("경매일");
		lblNewLabel_3.setBounds(12, 454, 57, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("관심인원");
		lblNewLabel_4.setBounds(12, 504, 57, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaptionBorder);
		panel_1.setBounds(12, 529, 519, 2);
		frame.getContentPane().add(panel_1);
		
		JButton btnNewButton = new JButton("관심 건물");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setBounds(12, 541, 247, 34);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("매각하기");
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.setBounds(271, 541, 260, 34);
		frame.getContentPane().add(btnNewButton_1);
		
		resultPrice = new JLabel("New label");
		resultPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		resultPrice.setBounds(248, 306, 283, 20);
		frame.getContentPane().add(resultPrice);
		
		price = new JLabel("New label");
		price.setHorizontalAlignment(SwingConstants.RIGHT);
		price.setBounds(248, 355, 283, 15);
		frame.getContentPane().add(price);
		
		denyAuction = new JLabel("New label");
		denyAuction.setHorizontalAlignment(SwingConstants.RIGHT);
		denyAuction.setBounds(248, 406, 283, 15);
		frame.getContentPane().add(denyAuction);
		
		auctionDay = new JLabel("New label");
		auctionDay.setHorizontalAlignment(SwingConstants.RIGHT);
		auctionDay.setBounds(248, 454, 283, 15);
		frame.getContentPane().add(auctionDay);
		
		favoriatePeoples = new JLabel("New label");
		favoriatePeoples.setHorizontalAlignment(SwingConstants.RIGHT);
		favoriatePeoples.setBounds(248, 504, 283, 15);
		frame.getContentPane().add(favoriatePeoples);
	}
}
