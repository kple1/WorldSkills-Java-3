package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import Data.DB;
import Utils.SearchFormPlugin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class Search {

	private JFrame frame;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search window = new Search();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getSearch() {
		return frame;
	}

	public Search() {
		initialize();
	}

	public String textBoxData = "";

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 588);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 10, 98, 23);
		comboBox.addItem("건물명 검색");
		comboBox.addItem("지역 검색");
		frame.getContentPane().add(comboBox);

		textField = new JTextField();
		textField.setBounds(122, 11, 481, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 730, 496);
		frame.getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2, 10, 10));
		scrollPane.setViewportView(panel);
		
		JButton resetButton = new JButton("초기화");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				justCreatePanel(panel);
				comboBox.setSelectedItem("건물명 검색");
			}
		});
		resetButton.setBackground(SystemColor.activeCaption);
		resetButton.setBounds(656, 10, 86, 23);
		frame.getContentPane().add(resetButton);

		JLabel searchButton = new JLabel("");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textBoxData = comboBox.getSelectedItem().toString();
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "검색내용을 입력해 주세요.", "정보", JOptionPane.INFORMATION_MESSAGE);
				} else {
					createJPanel(panel);
					if (countSearchFound == 0) {
						JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.");
						panel.removeAll();
						panel.revalidate();
						panel.repaint();
						justCreatePanel(panel);
					}
				}
			}
		});
		ImageIcon icon = new ImageIcon("datafiles/image/검색.png");
		searchButton.setIcon(Main.imageIconSetSize(icon, 29, 29));
		searchButton.setBounds(615, 8, 29, 29);

		frame.getContentPane().add(searchButton);
		createJPanel(panel);
	}

	public int countSearchFound = 0;
	
	public void createJPanel(JPanel panel) {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		countSearchFound = 0;
		
		List<String> b_no = DB.getAllData("b_no", "building");
		for (String list : b_no) {
			String a_no = DB.getData("a_no", "b_no", list, "building");
			String location = DB.getData("ar_name", "ar_no", a_no, "area");
			String b_name = DB.getData("b_name", "b_no", list, "building");
			String price = DB.getData("b_price", "b_no", list, "building");
			String ar_name = DB.getData("ar_name", "ar_no", a_no, "area");

			StringBuffer sb = new StringBuffer();
			if ((int) Math.log10(Integer.parseInt(price)) + 1 == 9) { // Math.log10이 0부터 세서 1추가 해서 비교하는 거임 (Math.log10
																		// -> 숫자형 길이 셈)
				sb.append(String.valueOf(price).substring(0, 5) + "만원");
				sb.insert(1, "억");
			} else {
				sb.append(String.valueOf(price).substring(0, 4) + "만원");
			}
			// 검색결과
			SearchFormPlugin sfp = new SearchFormPlugin(b_name, String.valueOf(sb), location);
			if (!textField.getText().isEmpty()) {
				if (textBoxData.equals("건물명 검색")) {
					if (b_name.contains(textField.getText())) {
						panel.add(sfp);
						++countSearchFound;
					} else {
						continue;
					}
				} else if (textBoxData.equals("지역 검색")) {
					if (ar_name.contains(textField.getText())) {
						panel.add(sfp);
						++countSearchFound;
					} else {
						continue;
					}
				}
			} else {
				panel.add(sfp);
			}
		}
	}
	
	public void justCreatePanel(JPanel panel) {
		List<String> b_no = DB.getAllData("b_no", "building");
		for (String list : b_no) {
			String a_no = DB.getData("a_no", "b_no", list, "building");
			String location = DB.getData("ar_name", "ar_no", a_no, "area");
			String b_name = DB.getData("b_name", "b_no", list, "building");
			String price = DB.getData("b_price", "b_no", list, "building");
			String ar_name = DB.getData("ar_name", "ar_no", a_no, "area");
			
			StringBuffer sb = new StringBuffer();
			if ((int) Math.log10(Integer.parseInt(price)) + 1 == 9) { // Math.log10이 0부터 세서 1추가 해서 비교하는 거임 (Math.log10
																		// -> 숫자형 길이 셈)
				sb.append(String.valueOf(price).substring(0, 5) + "만원");
				sb.insert(1, "억");
			} else {
				sb.append(String.valueOf(price).substring(0, 4) + "만원");
			}
			
			SearchFormPlugin sfp = new SearchFormPlugin(b_name, String.valueOf(sb), location);
			panel.add(sfp);
		}
	}
}
