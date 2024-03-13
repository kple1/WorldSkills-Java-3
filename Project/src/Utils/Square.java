package Utils;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Square extends JPanel {
	
	int x;
	int y;
	public Square (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(x, y, 156, 136);
	}
}
