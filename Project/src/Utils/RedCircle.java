package Utils;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RedCircle extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red);
		g.fillOval(5, 5, 13, 13);
	}	
}
