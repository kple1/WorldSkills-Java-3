package Form;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Data.DB;
import Utils.ImageRender;

public class myHome {

	private JFrame frame;
	private JTable myFavoriateList;
	private JTable recentList;
	private JTable slaeListTable;

	public JFrame getMyHome() {
		return frame;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myHome window = new myHome(null, null, null, null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	String saleDay;
	String buildingName;
	String salePrice;
	String buildingType;
	public myHome(String saleDay, String buildingName, String salePrice, String buildingType) {
		this.saleDay = saleDay;
		this.buildingName = buildingName;
		this.salePrice = salePrice;
		this.buildingType = buildingType;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 535, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("나의 관심목록");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 17, 97, 24);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("최근 본 목록");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(12, 202, 97, 24);
		frame.getContentPane().add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 495, 130);
		frame.getContentPane().add(scrollPane);

		myFavoriateList = new JTable();
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] { 
						"\uBC88\uD638", "\uAC74\uBB3C\uBA85", "\uC9C0\uC5ED", "\uC885\uB958", "\uACBD\uB9E4\uC77C" 
				}
			);

		int count1 = 0;
		String u_no = DB.getData("u_no", "u_id", Login.id_textField.getText(), "user");
		List<String> b_no = DB.getManyData("b_no", "interest", "u_no", u_no);
		for (String list : b_no) {
			String b_name = DB.getData("b_name", "b_no", list, "building");
			String a_no = DB.getData("a_no", "b_no", list, "building");
			String ar_name = DB.getData("ar_name", "ar_no", a_no, "area");
			int b_type = DB.getIntData("b_type", "building", "b_no", list);
			String a_date = DB.getData("b_date", "b_no", list, "building");

			String buildingType = "";
			if (b_type == 0) {
				buildingType = "아파트";
			} else if (b_type == 1) {
				buildingType = "주택";
			} else {
				buildingType = "오피스텔";
			}
			model.addRow(new Object[] { ++count1, b_name, ar_name, buildingType, a_date });
		}
		myFavoriateList.setModel(model);

		myFavoriateList.getColumnModel().getColumn(1).setPreferredWidth(247);
		myFavoriateList.getColumnModel().getColumn(2).setPreferredWidth(98);
		myFavoriateList.getColumnModel().getColumn(3).setPreferredWidth(89);
		myFavoriateList.getColumnModel().getColumn(4).setPreferredWidth(113);
		scrollPane.setViewportView(myFavoriateList);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 225, 495, 119);
		frame.getContentPane().add(scrollPane_1);

		recentList = new JTable();
		DefaultTableModel model2 = new DefaultTableModel(new Object[][] {

		}, new String[] { "\uBC88\uD638", "\uC774\uBBF8\uC9C0", "\uAC74\uBB3C\uBA85", "\uC885\uB958",
				"\uACBD\uB9E4\uC77C" });

		int count2 = 0;
		List<String> b_no_look = DB.getManyData("b_no", "look", "u_no", u_no);
		for (String list : b_no_look) {
			String b_name = DB.getData("b_name", "b_no", list, "building");
			String a_no = DB.getData("a_no", "b_no", list, "building");
			int b_type = DB.getIntData("b_type", "building", "b_no", list);
			String a_date = DB.getData("b_date", "b_no", list, "building");

			String buildingType = "";
			if (b_type == 0) {
				buildingType = "아파트";
			} else if (b_type == 1) {
				buildingType = "주택";
			} else {
				buildingType = "오피스텔";
			}
			ImageIcon icon = new ImageIcon("datafiles/building/" + b_name + "1.jpg");
			model2.addRow(new Object[] { ++count2, Main.imageIconSetSize(icon, 50, 50), b_name, buildingType, a_date });
		}

		recentList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = recentList.getSelectedColumn();
				int selectCol = recentList.getSelectedRow();
				if (selectCol == 1) {
					int x = DB.getIntData("b_x", "building", "b_name", String.valueOf(recentList.getValueAt(selectRow, 2)));
					int y = DB.getIntData("b_y", "building", "b_name", String.valueOf(recentList.getValueAt(selectRow, 2)));

					frame.dispose();
					HugeMap hugeMap = new HugeMap(x, y);
					hugeMap.getHegeMap().setVisible(true);
				}
			}
		});

		recentList.setModel(model2);
		recentList.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
		recentList.getColumnModel().getColumn(2).setPreferredWidth(270);
		recentList.getColumnModel().getColumn(4).setPreferredWidth(126);
		recentList.setRowHeight(50);
		scrollPane_1.setViewportView(recentList);

		JLabel lblNewLabel_1_1 = new JLabel("매각 내역");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(12, 385, 97, 24);
		frame.getContentPane().add(lblNewLabel_1_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 411, 495, 125);
		frame.getContentPane().add(scrollPane_2);

		slaeListTable = new JTable();
		DefaultTableModel model3 = new DefaultTableModel(
				new Object[][] {

				}, 
				new String[] {
						"\uB9E4\uAC01\uC77C", "\uAC74\uBB3C\uBA85", "\uB9E4\uAC01\uAE08\uC561", "\uC885\uB958" 
				}
			);
		model3.addRow(new Object[]{saleDay, buildingName, salePrice, buildingType}); //추가 후
		slaeListTable.setModel(model3); // 모델 설정
		slaeListTable.getColumnModel().getColumn(0).setPreferredWidth(179);
		slaeListTable.getColumnModel().getColumn(1).setPreferredWidth(142);
		slaeListTable.getColumnModel().getColumn(2).setPreferredWidth(153);
		slaeListTable.getColumnModel().getColumn(3).setPreferredWidth(148);
		scrollPane_2.setViewportView(slaeListTable);
	}
}
