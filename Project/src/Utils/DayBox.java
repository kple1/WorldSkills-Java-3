package Utils;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;

public class DayBox extends JPanel {

	public DayBox(int day, String resultAuction, Color color) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setLayout(new BorderLayout());

        JLabel topLabel = new JLabel(String.valueOf(day), SwingConstants.RIGHT);
        topLabel.setOpaque(true);
        topLabel.setBackground(color);
        JLabel bottomLabel = new JLabel(resultAuction, SwingConstants.LEFT);

        add(topLabel, BorderLayout.NORTH);
        add(bottomLabel, BorderLayout.SOUTH); 
	}
}
