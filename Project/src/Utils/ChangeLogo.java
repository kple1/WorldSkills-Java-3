package Utils;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class ChangeLogo {
	private ImageIcon icon1;
	private ImageIcon icon2;
	private boolean isIcon1 = true;
	
	public ChangeLogo(JFrame frame) {
		icon1 = new ImageIcon(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\과제3 결과물\\datafiles\\icon\\icon1.png"));
		icon2 = new ImageIcon(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\과제3 결과물\\datafiles\\icon\\icon2.png"));

		frame.setIconImage(icon1.getImage());

		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isIcon1) {
					frame.setIconImage(icon2.getImage());
					isIcon1 = false;
				} else {
					frame.setIconImage(icon1.getImage());
					isIcon1 = true;
				}
			}
		});
		timer.start();
	}
}
