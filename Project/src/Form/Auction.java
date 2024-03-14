package Form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Data.DB;
import Utils.AuctionShow;
import Utils.ChangeLogo;
import Utils.DayBox;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;

public class Auction {

	private JFrame frame;
	public int getDay = LocalDateTime.now().getDayOfMonth();
	public int getYear = LocalDateTime.now().getYear();
	public int getMonth = LocalDateTime.now().getMonthValue();
	public JLabel resultDay;
	public String setMonth = "";
	public int intSetMonth = 0;
	public JLabel nextButton;
	public JPanel panel_1;

	public JFrame getAuction() {
		return frame;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Auction window = new Auction();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Auction() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("경매일정");
		frame.setBounds(100, 100, 635, 515); //635
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
				Main main = new Main();
				main.getMain().setVisible(true);
			}
		});
		
		new ChangeLogo(frame);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(638, 81, 334, 348);
		frame.getContentPane().add(scrollPane);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel monday = new JLabel("월");
		monday.setHorizontalAlignment(SwingConstants.CENTER);
		monday.setBounds(133, 56, 74, 15);
		frame.getContentPane().add(monday);

		JLabel tuesday = new JLabel("화");
		tuesday.setHorizontalAlignment(SwingConstants.CENTER);
		tuesday.setBounds(209, 56, 74, 15);
		frame.getContentPane().add(tuesday);

		JLabel wednesday = new JLabel("수");
		wednesday.setHorizontalAlignment(SwingConstants.CENTER);
		wednesday.setBounds(285, 56, 74, 15);
		frame.getContentPane().add(wednesday);

		JLabel thursday = new JLabel("목");
		thursday.setHorizontalAlignment(SwingConstants.CENTER);
		thursday.setBounds(359, 56, 74, 15);
		frame.getContentPane().add(thursday);

		JLabel friday = new JLabel("금");
		friday.setHorizontalAlignment(SwingConstants.CENTER);
		friday.setBounds(433, 56, 74, 15);
		frame.getContentPane().add(friday);

		JLabel saturday = new JLabel("토");
		saturday.setHorizontalAlignment(SwingConstants.CENTER);
		saturday.setBounds(512, 56, 62, 15);
		frame.getContentPane().add(saturday);

		JLabel sunday = new JLabel("일");
		sunday.setHorizontalAlignment(SwingConstants.CENTER);
		sunday.setBounds(57, 56, 74, 15);
		frame.getContentPane().add(sunday);

		JPanel panel = new JPanel();
		panel.setBounds(57, 81, 517, 348);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(6, 7, 0, 0));
		createDay(panel, frame);

		JLabel beforeButton = new JLabel("<");
		beforeButton.setEnabled(false);
		beforeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String getMinDate = DB.getMinDate().substring(0, 7);
				if (getMinDate.equals(equalLabel)) {
					beforeButton.setEnabled(false);
				} else {
					nextButton.setEnabled(true);
					getMonth -= 1;
					reset(panel, frame);
				}
			}
		});
		beforeButton.setBounds(28, 242, 17, 15);
		frame.getContentPane().add(beforeButton);

		nextButton = new JLabel(">");
		nextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String getYearAndMonth = DB.getMaxDate().substring(0, 7);
				if (getYearAndMonth.equals(equalLabel)) {
					nextButton.setEnabled(false);
				} else {
					beforeButton.setEnabled(true);
					getMonth += 1;
					reset(panel, frame);
				}
			}
		});
		nextButton.setBounds(592, 242, 17, 15);
		frame.getContentPane().add(nextButton);
	}

	String equalLabel = "";
	int saveManyAuction = 0;
	int totalAuction = 0;
	public void createDay(JPanel panel, JFrame frame) {
		int lengthOfMonth = YearMonth.of(getYear, getMonth).lengthOfMonth();
		int beforeLengthOfMonth = YearMonth.of(getYear, getMonth - 1).lengthOfMonth();
		
		if ((int) Math.log10(getMonth) + 1 == 1) {
			equalLabel = getYear + "-0" + getMonth;
			setMonth = getYear + "-0" + getMonth + " (" + totalAuction +")";
			intSetMonth = getMonth;
			resultDay = new JLabel(setMonth);
		} else {
			equalLabel = getYear + "-" + getMonth;
			setMonth = getYear + "-" + getMonth + " (" + totalAuction +")";
			intSetMonth = getMonth;
			resultDay = new JLabel(setMonth);
		}
		resultDay.setFont(new Font("굴림", Font.PLAIN, 20));
		resultDay.setBounds(250, 25, 123, 29);
		frame.getContentPane().add(resultDay);

		panel.add(new DayBox(String.valueOf(beforeLengthOfMonth), "", Color.LIGHT_GRAY, intSetMonth, frame, panel_1));

		List<String> list = DB.getDate(intSetMonth);
		List<String> array = DB.getDateCount(intSetMonth);
		for (int i = 0; i < lengthOfMonth; i++) {
			if (!list.get(i).substring(8, 10).equals(String.valueOf(i + 1))) {
				list.add(i, "0");
				array.add(i, "0");
			}
		}
		
		for (int i = 0; i < lengthOfMonth; i++) {
			if (array.get(i).equals("0")) {
				panel.add(new DayBox(String.valueOf(i + 1), "", Color.LIGHT_GRAY, intSetMonth, frame, panel_1));
			} else {
				if (saveManyAuction == i + 1) {
					panel.add(new DayBox("<html><font color = 'blue'>" + (i + 1) + "개</font><html/>", array.get(i), Color.CYAN, intSetMonth, frame, panel_1));
				} else {
					panel.add(new DayBox(String.valueOf(i + 1), array.get(i) + "개", Color.CYAN, intSetMonth, frame, panel_1));
				}
			}
		}

		int allColumns = 42 - lengthOfMonth;
		for (int j = 1; j < allColumns; j++) {
			panel.add(new DayBox(String.valueOf(j), "", Color.LIGHT_GRAY, intSetMonth, frame, panel_1));
		}
	}

	public void reset(JPanel panel, JFrame frame) {
		panel.removeAll();
		frame.remove(resultDay);
		createDay(panel, frame);
		panel.revalidate();
		panel.repaint();
		frame.revalidate();
		frame.repaint();
	}
}
