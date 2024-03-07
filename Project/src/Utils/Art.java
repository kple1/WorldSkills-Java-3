package Utils;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Art extends JPanel {
	
	private Image image;
	
	public Art(String imagePath) {
		ImageIcon icon = new ImageIcon(imagePath);
        image = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(),  this); //getWidth(), getHeight() -> Panel 크기에 따라 조정 (최대 사이즈)
    }
}
