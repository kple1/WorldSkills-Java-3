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
import java.util.List;

public class DayBox extends JPanel {

	public DayBox(String day, String resultAuction, Color color, int month, JFrame frame, JPanel panel) {
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setLayout(new BorderLayout());

		JLabel topLabel = new JLabel(day, SwingConstants.RIGHT);
		topLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int getDay = LocalDateTime.now().getDayOfMonth();
				int getMonth = LocalDateTime.now().getMonthValue();
				int getYear = LocalDateTime.now().getYear();

				List<String> list = DB.getDate(getMonth);
				List<String> array = DB.getDateCount(getMonth);

				int lengthOfMonth = YearMonth.of(getYear, getMonth).lengthOfMonth();
				for (int i = 0; i < lengthOfMonth; i++) {
					if (!list.get(i).substring(8, 10).equals(String.valueOf(i + 1))) {
						list.add(i, "0");
						array.add(i, "0");
					}
				}
			

				if (month <= getMonth && Integer.parseInt(day) < getDay) {
					JOptionPane.showMessageDialog(null, "이전 날짜는 선택이 불가합니다.", "경고", JOptionPane.ERROR_MESSAGE);
				} else if (resultAuction.isEmpty()) {
					JOptionPane.showMessageDialog(null, "해당 날짜에는 경매일정이 없습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				} else {
					frame.setBounds(0, 0, 1000, 515);
					frame.setLocationRelativeTo(null);
					int changeResult = Integer.parseInt(resultAuction.substring(0, 1));
					for (int i = 0; i < changeResult; i++) {
//						String b_name = DB.getData("b_name", "b_date", day, "building");
//						String b_price = DB.getData("b_price", "b_date", day, "building");
//						String b_tpye = DB.getData("b_type", "b_date", day, "building");
//						String ar_name = DB.getData("ar_name", "b_date", day, "building");
//						String getInterest = DB.countInterestBuilding(b_name);
						panel.add(new AuctionShow("", "", "", "", ""));
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
