package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Data.DB;
import Utils.ChangeLogo;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserManage {

	private JFrame frame;
	private JTextField textField;
	private JTable buildingFavor;

	public JFrame getFrame() {
		return frame;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManage window = new UserManage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserManage() {
		initialize();
	}

	public JComboBox comboBox;

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("회원관리");
		frame.setBounds(100, 100, 579, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		new ChangeLogo(frame);
		comboBox = new JComboBox();
		comboBox.addItem("건물 매각 내역");
		comboBox.addItem("건물 관심목록 내역");
		comboBox.setBounds(12, 10, 147, 23);
		frame.getContentPane().add(comboBox);

		textField = new JTextField();
		textField.setBounds(171, 10, 292, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		DefaultTableModel model = new DefaultTableModel(new Object[][] {

		}, new String[] { "\uBC88\uD638", "\uC544\uC774\uB514", "\uBE44\uBC00\uBC88\uD638", "\uAC74\uBB3C\uBA85" });

		table_1 = new JTable();
		DefaultTableModel model1 = new DefaultTableModel(new Object[][] {},
				new String[] { "\uBC88\uD638", "\uC544\uC774\uB514", "\uBE44\uBC00\uBC88\uD638", "\uAC74\uBB3C\uBA85",
						"\uB9E4\uAC01\uC77C", "\uB9E4\uAC01\uAE08\uC561" });
		frame.getContentPane().add(table_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 545, 351);
		frame.getContentPane().add(scrollPane);

		buildingFavor = new JTable();
		
		JButton button = new JButton("검색");
		tableRowAdd(model, buildingFavor, scrollPane, table_1, model1, button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "검색할 내용을 입력해주세요.", "경고", JOptionPane.INFORMATION_MESSAGE);
				} else {
					i = 0;
					count = 0;
					model.setRowCount(0);
					tableRowAdd(model, buildingFavor, scrollPane, table_1, model1, button);
					if (count == 0) {
						JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.", "경고", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRowAdd(model, buildingFavor, scrollPane, table_1, model1, button);
			}
		});
		
		button.setBackground(SystemColor.activeCaption);
		button.setBounds(472, 10, 85, 23);
		frame.getContentPane().add(button);
	}

	static int i = 0;
	static int count = 0;
	private JTable table_1;

	public void tableRowAdd(DefaultTableModel model, JTable table, JScrollPane scrollPane, JTable table1, DefaultTableModel model1, JButton button) {
		List<String> i_no = DB.getAllData("i_no", "interest");
		if (comboBox.getSelectedItem().equals("건물 관심목록 내역")) {
			textField.setVisible(true);
			button.setVisible(true);
			for (String list : i_no) {
				String b_no = DB.getData("b_no", "i_no", list, "interest");
				String u_no = DB.getData("u_no", "i_no", list, "interest");
				String id = DB.getData("u_id", "u_no", u_no, "user");
				String pw = DB.getData("u_pw", "u_no", u_no, "user");
				String b_name = DB.getData("b_name", "b_no", b_no, "building");

				if (b_name.contains(textField.getText()) || textField.getText().isEmpty()) {
					model.addRow(new Object[] { ++i, id, pw, b_name });
					++count;
				} else {
					continue;
				}
				table.setModel(model);
				table.getColumnModel().getColumn(1).setPreferredWidth(115);
				table.getColumnModel().getColumn(2).setPreferredWidth(113);
				table.getColumnModel().getColumn(3).setPreferredWidth(319);
				scrollPane.setViewportView(table);
			}
		} else if (comboBox.getSelectedItem().equals("건물 매각 내역")) {
			textField.setVisible(false);
			button.setVisible(false);
			List<String> s_no = DB.getAllData("s_no", "saleauction");
			for (String list : s_no) {
				String getId = DB.getData("u_id", "s_no", list, "saleauction");
				String getPw = DB.getData("u_pw", "u_id", getId, "user");
				String getBuildingName = DB.getData("b_name", "s_no", list, "saleauction");
				String getSaleDay = DB.getData("a_date", "s_no", list, "saleauction");
				String getSalePrice = DB.getData("b_price", "s_no", list, "saleauction");

				StringBuffer sb = new StringBuffer();
				sb.append(getSalePrice);
				if (sb.length() == 9) {
					sb.insert(3, ",");
					sb.insert(7, ",");
				} else {
					sb.insert(2, ",");
					sb.insert(6, ",");
				}
				model1.addRow(new Object[] { ++i, getId, getPw, getBuildingName, getSaleDay, getSalePrice });
				table1.setModel(model1);
				scrollPane.setViewportView(table1);
			}
		}
	}
}
