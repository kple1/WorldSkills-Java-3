package Utils;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Data.DB;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;

public class DayBox extends JPanel {

	JLabel topLabel = new JLabel("", SwingConstants.RIGHT);
	
	public DayBox(String day, String resultAuction, Color color, int month, JFrame frame, JPanel panel) {
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setLayout(new BorderLayout());

		String setMonth = "";
		if ((int) Math.log10(month) + 1 == 1) {
			setMonth = "0" + month;
		}

		List<String> getNo = DB.getManyData("b_no", "building", "b_date", "2024-" + setMonth + "-" + day);
		
		StringBuilder sb = new StringBuilder();
		for (String loop: getNo) {
			String b_name = DB.getData("b_name", "b_no", loop, "building");
			sb.append(b_name + "<br>");
		}
		
		List<String> array = DB.getDateCount(month);
		String[] ra = resultAuction.split("개");
		if (Collections.max(array).equals(ra[0])) {
			topLabel.setText("<html><font color = 'blue'>" + day + "</font><html/>");
		} else {
			topLabel.setText(day);
		}
		topLabel.setToolTipText("<html>" + String.valueOf(sb) + "<html/>");
		topLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int getDay = LocalDateTime.now().getDayOfMonth();
				int getMonth = LocalDateTime.now().getMonthValue();
				int getYear = LocalDateTime.now().getYear();

				List<String> list = DB.getDate(month);
				List<String> array = DB.getDateCount(month);

				int lengthOfMonth = YearMonth.of(getYear, month).lengthOfMonth();
				for (int i = 0; i < lengthOfMonth; i++) {
					if (i >= list.size()) {
						list.add(i, "0");
						array.add(i, "0");
					} else {
						if (Integer.parseInt(list.get(i).substring(8, 10)) != (i + 1)) {
							list.add(i, "0");
							array.add(i, "0");
						}
					}
				}

				if (month <= getMonth && Integer.parseInt(day) < getDay) {
					JOptionPane.showMessageDialog(null, "이전 날짜는 선택이 불가합니다.", "경고", JOptionPane.ERROR_MESSAGE);
				} else if (resultAuction.isEmpty()) {
					JOptionPane.showMessageDialog(null, "해당 날짜에는 경매일정이 없습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				} else {
					frame.setBounds(0, 0, 1000, 515);
					frame.setLocationRelativeTo(null);
					
					panel.removeAll();
					
					for (String loop: getNo) {
						String b_name = DB.getData("b_name", "b_no", loop, "building");
						String b_price = DB.getData("b_price", "b_no", loop, "building");
						int b_type = DB.getIntData("b_type", "building", "b_no", loop);
						
						String buildingType = "";
						if (b_type == 0) {
							buildingType = "아파트";
						} else if (b_type == 1) {
							buildingType = "주택";
						} else {
							buildingType = "오피스텔";
						}
						
						String a_no = DB.getData("a_no", "b_no", loop, "building");
						String ar_name = DB.getData("ar_name", "ar_no", a_no, "area");
						String interestCount = DB.getInterestPeoples(loop);
	
						panel.add(new AuctionShow(b_name, b_price, buildingType, ar_name, interestCount, frame));
					}
				}
			}
		});

		topLabel.setOpaque(true);
		topLabel.setBackground(color);
		JLabel bottomLabel = new JLabel(resultAuction, SwingConstants.LEFT);

		add(topLabel, BorderLayout.NORTH);
		add(bottomLabel, BorderLayout.SOUTH);
	}
}
