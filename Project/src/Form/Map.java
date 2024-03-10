package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Data.DB;
import Utils.BlueCircle;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Map {

	public static JFrame frame;
	private static JTextField searchField;
	private JTable table;
	private static JScrollPane scrollPane_1;
	private static JLayeredPane layeredPane;

	/**
	 * Launch the application.
	 */
	public JFrame getMap() {
		return frame;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Map window = new Map();
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
	public Map() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 937, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		searchField = new JTextField();
		searchField.setBounds(12, 10, 788, 21);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);

		JButton SearchButton = new JButton("검색");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMap();
				
				List<String> b_name = DB.getAllData("b_name", "building");

				DefaultTableModel model = new DefaultTableModel(
					new Object[][] {

					}, 
					new String[] { 
						"\uBC88\uD638", "\uAC74\uBB3C\uBA85" 
					});
				
					int count = 1;
					for (int i = 0; i < b_name.size(); i++) {
						if (b_name.get(i).contains(searchField.getText()) || searchField.getText().isEmpty()) {
							model.addRow(new Object[] { count++, b_name.get(i) });
						} else {
							continue;
						}
					}
				table.setModel(model);
			}
		});
		SearchButton.setBackground(SystemColor.activeCaption);
		SearchButton.setBounds(812, 9, 97, 23);
		frame.getContentPane().add(SearchButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 41, 900, 74);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, table.getValueAt(table.getSelectedRow(), 1), "정보", JOptionPane.INFORMATION_MESSAGE); //성공적 (테이블의 row와 열 갖고 오기)
			}
		});
		table.setBackground(new Color(255, 255, 255));

		List<String> b_name = DB.getAllData("b_name", "building");

		DefaultTableModel model = new DefaultTableModel(
			new Object[][] {

			}, 
			new String[] { 
				"\uBC88\uD638", "\uAC74\uBB3C\uBA85" 
			});
		for (int i = 0; i < b_name.size(); i++) {
			model.addRow(new Object[] { i + 1, b_name.get(i) });
		}
		table.setModel(model);

		table.getColumnModel().getColumn(0).setPreferredWidth(129);
		table.getColumnModel().getColumn(1).setPreferredWidth(499);
		scrollPane.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 126, 900, 825);
		frame.getContentPane().add(scrollPane_1);
		
		createMap();
	}
	
	public static void createMap() {
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(900, 1000)); //패널 크기 설정 필수
		
		scrollPane_1.setViewportView(layeredPane);
		ImageIcon icon = new ImageIcon("C:\\Users\\Leepl\\Desktop\\대회\\정보기술 공개과제\\제3과제\\datafiles\\map\\전체.jpg");
		JLabel label1 = new JLabel(Main.imageIconSetSize(icon, 900, 1000));
		label1.setBounds(0, 0, 900, 1000);
		
		//포인트 추가
		List<String> no = DB.getAllData("b_no", "building");
		List<String> b_name = DB.getAllData("b_name", "building");
		List<String> x = DB.getAllData("b_x", "building");
		List<String> y = DB.getAllData("b_y", "building");
		for (String array: no) {
			BlueCircle bc = new BlueCircle();
        	
			bc.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					BuildingInfo bi = new BuildingInfo();
					String b_price = DB.getData("b_price", "b_no", array, "building");
		        	String b_name1 = DB.getData("b_name", "b_no", array, "building");
		        	String b_count = DB.getData("b_count", "b_no", array, "building");
		        	String a_no = DB.getData("a_no", "b_no", array, "building");
		        	String ar_name = DB.getData("ar_name", "ar_no", a_no, "area");
		        	String a_date = DB.getData("a_date", "a_no", a_no, "auction");
		        	String type = DB.getData("b_type", "a_no", array, "building");
		        	
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
		        	
		        	String buildingType = "";
		        	if (type.equals("0")) {
		        		buildingType = "아파트";
		        	} else if (type.equals("1")) {
		        		buildingType = "주택";
		        	} else if (type.equals("2")) {
		        		buildingType = "오피스텔";
		        	}
		        	
					bi.resultPrice.setText("<html><font color='blue' size='+1'>" + sb + "</font> 감정가익" + gamJeongGa + "%<html/>");
					bi.price.setText(String.valueOf(sb));
					bi.denyAuction.setText(b_count + "회");
					bi.auctionDay.setText(a_date);
					
					ImageIcon icon = new ImageIcon("C:\\Users\\Leepl\\Desktop\\대회\\정보기술 공개과제\\제3과제\\datafiles\\building\\" + b_name1 + "1.jpg");
					JLabel label = new JLabel(Main.imageIconSetSize(icon, bi.imagePanel.getWidth(), bi.imagePanel.getHeight()));
					bi.imagePanel.add(label);
					
					bi.buildingName.setText(b_name1);
					bi.location.setText("지역: " + ar_name);
					bi.type.setText("종류: " + buildingType);
					bi.favoriatePeoples.setText(DB.getInterestPeoples(array) + "명");
					bi.getBuildingInfo().setVisible(true);
				}
			});
			
			if (b_name.get(Integer.parseInt(array) - 1).contains(searchField.getText()) || searchField.getText().isEmpty()) {
				bc.setBounds(Integer.parseInt(x.get(Integer.parseInt(array) - 1)), Integer.parseInt(y.get(Integer.parseInt(array) - 1)), 30, 30);
				bc.setOpaque(false); //패널의 배경제거
				layeredPane.add(bc); //원이 최상위
			} else {
				continue;
			}
		}
		layeredPane.add(label1); //아래 지도
		frame.revalidate();
		frame.repaint();
	}
}
