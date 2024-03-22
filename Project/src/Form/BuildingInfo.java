package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Data.DB;
import Utils.ChangeLogo;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuildingInfo {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuildingInfo window = new BuildingInfo(null, null, null, null, null, null, null, null);
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
	
	String resultPrice;
	String price;
	String auctionDay;
	String denyAuction;
	String favoriatePeoples;
	public static String buildingName;
	String location;
	String type;

	public BuildingInfo(String resultPrice, String price, String auctionDay, String denyAuction, String favoriatePeoples, String buildingName, String location, String type) {
		this.resultPrice = resultPrice;
		this.price = price;
		this.auctionDay = auctionDay;
		this.denyAuction = denyAuction;
		this.favoriatePeoples = favoriatePeoples;
		this.buildingName = buildingName;
		this.location = location;
		this.type = type;
		initialize();
	}
	
	int count = 1;
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("빌딩 정보");
		frame.setBounds(100, 100, 559, 624);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		new ChangeLogo(frame);
		
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	frame.dispose();
            	Main main = new Main();
            	main.getMain().setVisible(true);
            }
        });
        
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel buildingName = new JLabel(BuildingInfo.buildingName);
		buildingName.setBounds(263, 79, 268, 15);
		frame.getContentPane().add(buildingName);
		
		JLabel location = new JLabel(this.location);
		location.setBounds(263, 135, 268, 15);
		frame.getContentPane().add(location);
		
		JLabel type = new JLabel(this.type);
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getUserNo = DB.getData("u_no", "u_id", Login.id_textField.getText(), "user");
				List<String> list = DB.getManyData("b_no", "interest", "u_no", getUserNo);
				String b_no = DB.getData("b_no", "b_name", BuildingInfo.buildingName, "building");
				
				int count = 0;
				for (String array: list) {
					String getBuildingNum = DB.getData("b_name", "b_no", array, "building");
					if (BuildingInfo.buildingName.equals(getBuildingNum)) {
						JOptionPane.showMessageDialog(null, "이미 관심 목록에 등록되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
						++count;
						break;
					}
				}
				
				if (count == 0) {
					DB.insertInterest(getUserNo, b_no);
					JOptionPane.showMessageDialog(null, "관심 목록에 추가되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setBounds(12, 541, 247, 34);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("매각하기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String b_no = DB.getData("b_no", "b_name", BuildingInfo.buildingName, "building");
				String b_date = DB.getData("b_date", "b_name", BuildingInfo.buildingName, "building");
				String b_name = DB.getData("b_name", "b_name", BuildingInfo.buildingName, "building");
				String b_price = DB.getData("b_price", "b_name", BuildingInfo.buildingName, "building");
				int b_type = DB.getIntData("b_type", "building", "b_no", b_no);
				
				String buildingType = "";
				if (b_type == 0) {
					buildingType = "아파트";
				} else if (b_type == 1) {
					buildingType = "주택";
				} else if (b_type == 2) {
					buildingType = "오피스텔";
				}
				
				HugeMap hm = new HugeMap(0, 0, "Main");
				hm.getHegeMap().setVisible(false);
				
				for(Window window : Window.getWindows()) {
				    window.dispose();
				}
				
				StringBuffer sb = new StringBuffer();
				sb.append(b_price);
				if ((int) Math.log10(Integer.parseInt(b_price)) + 1 == 9) {
					sb.insert(3, ",");
					sb.insert(7, ",");
				} else {
					sb.insert(2, ",");
					sb.insert(6, ",");
				}
				
				DB.delete("interest", "b_no", b_no);
				DB.insertSaleAuction(Login.id_textField.getText(), b_name, b_date, b_price, buildingType);
				
				myHome myhome = new myHome();
				myhome.getMyHome().setVisible(true);
				JOptionPane.showMessageDialog(null, "매각이 완료되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.setBounds(271, 541, 260, 34);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel resultPrice = new JLabel(this.resultPrice);
		resultPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		resultPrice.setBounds(248, 306, 283, 20);
		frame.getContentPane().add(resultPrice);
		
		JLabel price = new JLabel(this.price);
		price.setHorizontalAlignment(SwingConstants.RIGHT);
		price.setBounds(248, 355, 283, 15);
		frame.getContentPane().add(price);
		
		JLabel denyAuction = new JLabel(this.denyAuction);
		denyAuction.setHorizontalAlignment(SwingConstants.RIGHT);
		denyAuction.setBounds(248, 406, 283, 15);
		frame.getContentPane().add(denyAuction);
		
		JLabel auctionDay = new JLabel(this.auctionDay);
		auctionDay.setHorizontalAlignment(SwingConstants.RIGHT);
		auctionDay.setBounds(248, 454, 283, 15);
		frame.getContentPane().add(auctionDay);
		
		JLabel favoriatePeoples = new JLabel(this.favoriatePeoples);
		favoriatePeoples.setHorizontalAlignment(SwingConstants.RIGHT);
		favoriatePeoples.setBounds(248, 504, 283, 15);
		frame.getContentPane().add(favoriatePeoples);
		
		JLayeredPane imagePanel = new JLayeredPane();
		imagePanel.setBounds(12, 10, 239, 274);
		frame.getContentPane().add(imagePanel);

		ImageIcon icon = new ImageIcon("datafiles\\building\\" + BuildingInfo.buildingName + count + ".jpg");
		JLabel label = new JLabel(icon);
		
		JLabel leftButton = new JLabel("<");
		leftButton.setHorizontalAlignment(SwingConstants.LEFT);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (count == 1) {
					count = 3;
				} else {
					count -= 1;
				}
				ImageIcon icon = new ImageIcon("datafiles\\building\\" + BuildingInfo.buildingName + count + ".jpg");
				label.setIcon(Main.imageIconSetSize(icon, 239, 274));
				imagePanel.revalidate();
				imagePanel.repaint();
			}
		});
		
		leftButton.setFont(new Font("굴림", Font.BOLD, 17));
		leftButton.setForeground(SystemColor.desktop);
		leftButton.setBounds(12, 130, 20, 15);
		imagePanel.add(leftButton);
		
		JLabel rightButton = new JLabel(">");
		rightButton.setHorizontalAlignment(SwingConstants.RIGHT);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (count == 3) {
					count = 1;
				} else {
					count += 1;
				}
				ImageIcon icon = new ImageIcon("C:\\Users\\User\\Desktop\\과제3 결과물\\datafiles\\building\\" + BuildingInfo.buildingName + count + ".jpg");
				label.setIcon(Main.imageIconSetSize(icon, 239, 274));
				imagePanel.revalidate();
				imagePanel.repaint();
			}
		});
		rightButton.setFont(new Font("굴림", Font.BOLD, 17));
		rightButton.setForeground(SystemColor.desktop);
		rightButton.setBounds(207, 130, 20, 15);
		imagePanel.add(rightButton);
		
		label.setBounds(0, 0, 239, 274);
		imagePanel.add(label);
	}
}
