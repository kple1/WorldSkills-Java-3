package Utils;

import java.awt.Graphics;
import java.awt.SystemColor;

import javax.swing.JPanel;

public class BlueCircle extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(SystemColor.activeCaption);
		g.fillOval(5, 5, 13, 13);
	}
}
