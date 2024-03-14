package Form;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Utils.ChangeLogo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectHugeMap {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectHugeMap window = new SelectHugeMap();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectHugeMap() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("지도");
		frame.setBounds(100, 100, 918, 1020);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		new ChangeLogo(frame);
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AuctionRegist.X.setText(String.valueOf(e.getX()));
				AuctionRegist.Y.setText(String.valueOf(e.getY()));
				frame.dispose();
			}
		});
		panel.setBounds(0, 0, 900, 1000);
		ImageIcon icon = new ImageIcon("C:\\Users\\User\\Desktop\\과제3 결과물\\datafiles\\map\\전체.jpg");
		JLabel label = new JLabel(Main.imageIconSetSize(icon, 900, 1000));
		panel.add(label);
		frame.getContentPane().add(panel);
	}

}
