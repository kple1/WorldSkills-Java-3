package Form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Data.DB;
import Utils.ChangeLogo;
import Utils.DayBox;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Auction {

	private JFrame frame;
	public int getDay = LocalDateTime.now().getDayOfMonth();
	public int getYear = LocalDateTime.now().getYear();
	public int getMonth = LocalDateTime.now().getMonthValue();
	public JLabel resultDay;
	public String setMonth = "";
	public int intSetMonth = 0;
	public JLabel nextButton;

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
		frame.setBounds(100, 100, 635, 515);
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

	public void createDay(JPanel panel, JFrame frame) {
		int lengthOfMonth = YearMonth.of(getYear, getMonth).lengthOfMonth();
		int beforeLengthOfMonth = YearMonth.of(getYear, getMonth - 1).lengthOfMonth();

		if ((int) Math.log10(getMonth) + 1 == 1) {
			equalLabel = getYear + "-0" + getMonth;
			setMonth = getYear + "-0" + getMonth + " (35)";
			intSetMonth = getMonth;
			resultDay = new JLabel(setMonth);
		} else {
			equalLabel = getYear + "-" + getMonth;
			setMonth = getYear + "-" + getMonth + " (35)";
			intSetMonth = getMonth;
			resultDay = new JLabel(setMonth);
		}
		resultDay.setFont(new Font("굴림", Font.PLAIN, 20));
		resultDay.setBounds(250, 25, 123, 29);
		frame.getContentPane().add(resultDay);

		panel.add(new DayBox(beforeLengthOfMonth, "", Color.LIGHT_GRAY));

		for (int i = 1; i <= lengthOfMonth; i++) {
			panel.add(new DayBox(i, "", Color.LIGHT_GRAY));
		}

		int allColumns = 42 - lengthOfMonth;
		for (int j = 1; j < allColumns; j++) {
			panel.add(new DayBox(j, "", Color.LIGHT_GRAY));
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
