package Form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Data.DB;
import Utils.Art;
import Utils.ChangeLogo;
import Utils.FavoriateAuction;
import Utils.recentSaleList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Main {

	public static JFrame frame;

	public JFrame getMain() {
		return frame;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 814, 577);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		new ChangeLogo(frame);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	frame.dispose();
            	Login login = new Login();
            	login.getLogin().setVisible(true);
            }
        });

		JLabel searchButton = new JLabel();
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Search s = new Search();
				s.getSearch().setVisible(true);
			}
		});
		ImageIcon imgIcon1 = new ImageIcon("C:\\Users\\User\\Desktop\\과제3 결과물\\datafiles\\image\\검색.png");
		searchButton.setIcon(imageIconSetSize(imgIcon1, 40, 40));
		searchButton.setBounds(631, 10, 40, 40);
		frame.getContentPane().add(searchButton);

		JLabel myHomeButton = new JLabel("");
		myHomeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				myHome mh = new myHome();
				mh.getMyHome().setVisible(true);
			}
		});
		ImageIcon imgIcon2 = new ImageIcon("C:\\Users\\User\\Desktop\\과제3 결과물\\datafiles\\image\\마이홈.png");
		myHomeButton.setIcon(imageIconSetSize(imgIcon2, 40, 40));
		myHomeButton.setBounds(683, 10, 40, 40);
		frame.getContentPane().add(myHomeButton);

		JLabel auctionButton = new JLabel("");
		auctionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Auction a = new Auction();
				a.getAuction().setVisible(true);
			}
		});
		ImageIcon imgIcon3 = new ImageIcon("C:\\Users\\User\\Desktop\\과제3 결과물\\datafiles\\image\\경매일정.png");
		auctionButton.setIcon(imageIconSetSize(imgIcon3, 40, 40));
		auctionButton.setBounds(747, 10, 40, 40);
		frame.getContentPane().add(auctionButton);

		JLabel SearchLabel = new JLabel("검색");
		SearchLabel.setFont(new Font("굴림", Font.PLAIN, 14));
		SearchLabel.setBounds(641, 60, 29, 18);
		frame.getContentPane().add(SearchLabel);

		JLabel SearchLabel_1 = new JLabel("마이홈");
		SearchLabel_1.setFont(new Font("굴림", Font.PLAIN, 14));
		SearchLabel_1.setBounds(685, 60, 50, 18);
		frame.getContentPane().add(SearchLabel_1);

		JLabel SearchLabel_2 = new JLabel("경매일정");
		SearchLabel_2.setFont(new Font("굴림", Font.PLAIN, 14));
		SearchLabel_2.setBounds(736, 60, 64, 18);
		frame.getContentPane().add(SearchLabel_2);

		JLabel logoutButton = new JLabel("경매알림이");
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				Login login = new Login();
				login.getLogin().setVisible(true);
			}
		});
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setBackground(Color.WHITE);
		logoutButton.setFont(new Font("굴림", Font.PLAIN, 18));
		logoutButton.setBounds(23, 33, 103, 18);
		frame.getContentPane().add(logoutButton);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 800, 78);
		frame.getContentPane().add(panel);

		JPanel mapPanel = new Art("C:\\Users\\User\\Desktop\\과제3 결과물\\datafiles\\map\\전체.jpg");
		mapPanel.setBounds(10, 88, 385, 442);
		frame.getContentPane().add(mapPanel);
		JButton viewMap = new JButton("지도 보기");
		viewMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Map map = new Map();
				map.getMap().setVisible(true);
			}
		});
		mapPanel.add(viewMap);
		viewMap.setFont(new Font("굴림", Font.PLAIN, 18));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVisible(true);
		scrollPane_1.setBounds(406, 343, 381, 185);
		frame.getContentPane().add(scrollPane_1);

		JPanel panel_1 = new JPanel(new GridLayout(0, 2, 10, 0));
		List<String> s_no = DB.getAllData("s_no", "saleauction");
		for (String list: s_no) {
			String getPrice = DB.getData("b_price", "s_no", list, "saleauction");
			String a_no = DB.getData("a_no", "b_price", getPrice, "building");
			String getArea = DB.getData("ar_name", "ar_no", a_no, "area");
			panel_1.add(new recentSaleList(getPrice, getArea, frame));
		}
		scrollPane_1.setViewportView(panel_1);

		JLabel favoriateAuctionLabel = new JLabel("인기경매");
		favoriateAuctionLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		favoriateAuctionLabel.setBounds(407, 88, 64, 20);
		frame.getContentPane().add(favoriateAuctionLabel);

		JLabel saleList = new JLabel("최근 매각 내역");
		saleList.setFont(new Font("굴림", Font.PLAIN, 15));
		saleList.setBounds(407, 313, 96, 20);
		frame.getContentPane().add(saleList);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(407, 107, 380, 196);
		frame.getContentPane().add(scrollPane);

		JPanel panel_2 = new JPanel(new GridLayout(1, 5, 10, 0));
		List<String> list = DB.getFiveBuilding();
        for (String array: list) {
        	String b_price = DB.getData("b_price", "b_no", array, "building");
        	String b_name = DB.getData("b_name", "b_no", array, "building");
        	String b_count = DB.getData("b_count", "b_no", array, "building");
        	String a_no = DB.getData("a_no", "b_no", array, "building");
        	String ar_name = DB.getData("ar_name", "ar_no", a_no, "area");
        	String a_date = DB.getData("a_date", "a_no", a_no, "auction");
        	int b_x = DB.getIntData("b_x", "building", "b_no", array);
        	int b_y = DB.getIntData("b_y", "building", "b_no", array);
        	int getType = DB.getIntData("b_type", "building", "b_no", array);
        	
        	String buildingType = "";
        	if (getType == 0) {
        		buildingType = "아파트";
        	} else if (getType == 1) {
        		buildingType = "주택";
        	} else if (getType == 2) {
        		buildingType = "오피스텔";
        	}
        	
        	int resultPrice = 0;
        	int gamJeongGa = 0;
        	//유찰횟수 (감정가) 판별 / 0 : 100% +1 : -10%
        	if (Integer.parseInt(b_count) == 0) {
        		resultPrice = Integer.parseInt(b_price);
        		gamJeongGa = 100;
        	} else {
        		resultPrice = Integer.parseInt(b_price) / (Integer.parseInt(b_count) * 10);
        		gamJeongGa = (Integer.parseInt(b_count) * 10);
        	}
        	
        	//750000000 -> 7억 5000만원 변경
        	StringBuffer sb = new StringBuffer();
        	if ((int) Math.log10(resultPrice) + 1 == 9) { //Math.log10이 0부터 세서 1추가 해서 비교하는 거임 (Math.log10 -> 숫자형 길이 셈)
        		sb.append(String.valueOf(resultPrice).substring(0, 5) + "만원");
        		sb.insert(1, "억");
        	} else {
        		sb.append(String.valueOf(resultPrice).substring(0, 4) + "만원");
        	}
        	
        	ImageIcon icon1 = new ImageIcon("C:\\Users\\User\\Desktop\\과제3 결과물\\datafiles\\building\\" + b_name + "1.jpg");
        	panel_2.add(new FavoriateAuction(imageIconSetSize(icon1, 150, 80),
        			"<html><font size = '+1'>" + sb + "</font>"
        			+ "<br><font color='blue'>감정가 " + gamJeongGa + "%</font>"
        			+ "<br>" + ar_name
        			+ "<br>경매일:" + a_date + "<html/>", b_x, b_y, sb, gamJeongGa, a_date, a_no, array, b_name, ar_name, buildingType, frame));
        }
		scrollPane.setViewportView(panel_2);
	}

	public static ImageIcon imageIconSetSize(ImageIcon icon, int x, int y) {
		Image ximage = icon.getImage();
		Image yimage = ximage.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(yimage);
		return image;
	}
}
