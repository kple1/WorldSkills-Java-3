package Utils;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class DayBox extends JPanel {

	public DayBox(int day, String resultAuction) {
		setLayout(null);
		
		JLabel dayLabel = new JLabel(String.valueOf(day));
		dayLabel.setOpaque(true);
		dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dayLabel.setBackground(SystemColor.activeCaption);
		dayLabel.setBounds(0, 0, 86, 19);
		add(dayLabel);
		
		JLabel auctionCount = new JLabel(resultAuction);
		auctionCount.setHorizontalAlignment(SwingConstants.CENTER);
		auctionCount.setBounds(0, 25, 86, 19);
		add(auctionCount);
		
	}
}
