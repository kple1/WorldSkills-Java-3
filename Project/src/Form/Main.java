package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;

import Data.DB;
import Utils.Art;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import java.awt.GridLayout;

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel searchButton = new JLabel();
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Search s = new Search();
				s.getSearch().setVisible(true);
			}
		});
		ImageIcon imgIcon1 = new ImageIcon("G:\\내 드라이브\\대회\\정보기술 공개과제\\제3과제\\datafiles\\image\\검색.png");
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
		ImageIcon imgIcon2 = new ImageIcon("G:\\내 드라이브\\대회\\정보기술 공개과제\\제3과제\\datafiles\\image\\마이홈.png");
		myHomeButton.setIcon(imageIconSetSize(imgIcon2, 40, 40));
		myHomeButton.setBounds(683, 10, 40, 40);
		frame.getContentPane().add(myHomeButton);

		JLabel auctionButton = new JLabel("");
		auctionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				auction a = new auction();
				a.getAuction().setVisible(true);
			}
		});
		ImageIcon imgIcon3 = new ImageIcon("G:\\내 드라이브\\대회\\정보기술 공개과제\\제3과제\\datafiles\\image\\경매일정.png");
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

		JPanel mapPanel = new Art("G:\\내 드라이브\\대회\\정보기술 공개과제\\제3과제\\datafiles\\map\\전체.jpg");
		mapPanel.setBounds(10, 88, 385, 442);
		frame.getContentPane().add(mapPanel);
		JButton viewMap = new JButton("지도 보기");
		mapPanel.add(viewMap);
		viewMap.setFont(new Font("굴림", Font.PLAIN, 18));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(406, 343, 381, 185);
		frame.getContentPane().add(scrollPane_1);

		JPanel panel_1 = new JPanel();
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel favoriate = new JPanel();
		favoriate.setBounds(407, 118, 380, 185);
		createLabel(favoriate);
		frame.getContentPane().add(favoriate);
		favoriate.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel favoriateAuctionLabel = new JLabel("인기경매");
		favoriateAuctionLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		favoriateAuctionLabel.setBounds(407, 88, 64, 20);
		frame.getContentPane().add(favoriateAuctionLabel);

		JLabel saleList = new JLabel("최근 매각 내역");
		saleList.setFont(new Font("굴림", Font.PLAIN, 15));
		saleList.setBounds(407, 313, 96, 20);
		frame.getContentPane().add(saleList);
	}

	public static JPanel createLabel(JPanel panel) {
	    List<String> list = new ArrayList<String>();
	    list.addAll(DB.getAllData("b_name", "building"));
	    
	    for (String sort : list) {
	        for (int i = 1; i <= 3; i++) {
	            JLabel label = new JLabel();
	            ImageIcon imgIcon = new ImageIcon("G:\\내 드라이브\\대회\\정보기술 공개과제\\제3과제\\datafiles\\building\\" + sort + i + ".jpg");
	            label.setIcon(imageIconSetSize(imgIcon, panel.getWidth(), (int) (panel.getHeight() * 0.7)));
	            panel.add(label);
	        }
	    }
	    return panel;
	}

	public static ImageIcon imageIconSetSize(ImageIcon icon, int x, int y) {
		Image ximage = icon.getImage();
		Image yimage = ximage.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(yimage);
		return image;
	}
}
