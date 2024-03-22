package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Data.DB;
import Utils.BlueCircle;
import Utils.ChangeLogo;
import Utils.RedCircle;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Map {

	public static JFrame frame;
	private static JTextField searchField;
	public static JTable table;
	private static JScrollPane scrollPane_1;
	private static JLayeredPane layeredPane;

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

	public Map() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 940, 1000);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		new ChangeLogo(frame);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	Main main = new Main();
            	main.getMain().setVisible(true);
            }
        });
        
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
					}
				);

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
				createMap();
			}
		});
		table.setBackground(new Color(255, 255, 255));

		List<String> b_name = DB.getAllData("b_name", "building");

		DefaultTableModel model = new DefaultTableModel(new Object[][] {

		}, new String[] { "\uBC88\uD638", "\uAC74\uBB3C\uBA85" });
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
		layeredPane.setPreferredSize(new Dimension(900, 1000)); // 패널 크기 설정 필수

		scrollPane_1.setViewportView(layeredPane);
		ImageIcon icon = new ImageIcon("datafiles\\map\\전체.jpg");
		JLabel label1 = new JLabel(Main.imageIconSetSize(icon, 900, 1000));
		label1.setBounds(0, 0, 900, 1000);

		// 포인트 추가
		List<String> no = DB.getAllData("b_no", "building");
		List<String> b_name = DB.getAllData("b_name", "building");
		List<Integer> x = DB.getAllIntData("b_x", "building");
		List<Integer> y = DB.getAllIntData("b_y", "building");
		for (String array : no) {
			BlueCircle bc = new BlueCircle();

			// 검색할 때
			if (b_name.get(Integer.parseInt(array) - 1).contains(searchField.getText()) || searchField.getText().isEmpty()) {
				bc.setBounds(x.get(Integer.parseInt(array) - 1), y.get(Integer.parseInt(array) - 1), 30, 30);
				bc.setOpaque(false); // 패널의 배경제거
				layeredPane.add(bc); // 원이 최상위
			} else {
				continue;
			}
			

			// 테이블을 클릭할 때, 빨간색 포인트를 클릭할 때
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				String getTableColumn = (String) table.getValueAt(selectedRow, 1);
				int b_x = DB.getIntData("b_x", "building", "b_name", getTableColumn);
				int b_y = DB.getIntData("b_y", "building", "b_name", getTableColumn);
					RedCircle rc = new RedCircle();
					rc.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							HugeMap hugeMap = new HugeMap(b_x, b_y, "Map");
							hugeMap.getHegeMap().setVisible(true);
							frame.setVisible(false);
							
							String b_price = DB.getData("b_price", "b_name", getTableColumn, "building");
							String b_count = DB.getData("b_count", "b_name", getTableColumn, "building");
							String a_no = DB.getData("a_no", "b_name", getTableColumn, "building");
							String ar_name = DB.getData("ar_name", "ar_no", a_no, "area");
							String a_date = DB.getData("a_date", "a_no", a_no, "auction");
							int type = DB.getIntData("b_type", "building", "b_name", getTableColumn);

							int resultPrice = 0;
							int gamJeongGa = 0;
							// 유찰횟수 (감정가) 판별 / 0 : 100% +1 : -10%
							if (Integer.parseInt(b_count) == 0) {
								resultPrice = Integer.parseInt(b_price);
								gamJeongGa = 100;
							} else {
								resultPrice = Integer.parseInt(b_price) / (Integer.parseInt(b_count) * 10);
								gamJeongGa = (Integer.parseInt(b_count) * 10);
							}

							// 750000000 -> 7억 5000만원 변경
							StringBuffer sb = new StringBuffer();
							if ((int) Math.log10(resultPrice) + 1 == 9) { // Math.log10이 0부터 세서 1추가 해서 비교하는 거임 (Math.log10 ->
																			// 숫자형 길이 셈)
								sb.append(String.valueOf(resultPrice).substring(0, 5) + "만원");
								sb.insert(1, "억");
							} else {
								sb.append(String.valueOf(resultPrice).substring(0, 4) + "만원");
							}

							String buildingType = "none";
							if (type == 0) {
								buildingType = "아파트";
							} else if (type == 1) {
								buildingType = "주택";
							} else if (type == 2) {
								buildingType = "오피스텔";
							}
							
							BuildingInfo bi = new BuildingInfo(
									"<html><font color='blue' size='+1'>" + sb + "</font> 감정가익" + gamJeongGa + "%<html/>",
									String.valueOf(sb),
									a_date,
									b_count + "회",
									DB.getInterestPeoples(array) + "명",
									getTableColumn,
									"지역: " + ar_name,
									"종류: " + buildingType);
							
							bi.getBuildingInfo().setVisible(true);
						}
					});
					rc.setBounds(b_x, b_y, 30, 30);
					rc.setOpaque(false);
					layeredPane.add(rc);
					layeredPane.remove(bc);
			}
		}
		layeredPane.add(label1); // 아래 지도
	}
}
