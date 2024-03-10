package Utils;

import javax.swing.*;

import Form.Map;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FavoriateAuction extends JPanel {
	private JLabel imageLabel;
	private JLabel textLabel;

	public FavoriateAuction(ImageIcon icon, String text) {
		setLayout(new BorderLayout()); // 중요한 배치도

		imageLabel = new JLabel(icon);
		add(imageLabel, BorderLayout.NORTH);
		imageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Map map = new Map();
				map.getMap().setVisible(true);
			}
		});
		textLabel = new JLabel(text);
		add(textLabel, BorderLayout.WEST);
	}
}
