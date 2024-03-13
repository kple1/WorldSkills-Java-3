package Form;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Utils.RedCircle;

import javax.swing.JLayeredPane;

public class HugeMap {

	private JFrame frame;

	public JFrame getHegeMap() {
		return frame;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HugeMap window = new HugeMap(0, 0);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	int x;
	int y;
	
	public HugeMap(int x, int y) {
		this.x = x;
		this.y = y;
		initialize(); //초기화 시킨 후 마지막에 initialize (실수로 2시간 날림)
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("지도");
		frame.setBounds(100, 100, 918, 1020);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 900, 1000);
		frame.getContentPane().add(layeredPane);
		frame.setLocationRelativeTo(null);
		
		RedCircle rc = new RedCircle();
		rc.setBounds(x, y, 30, 30);
		rc.setOpaque(false);
		layeredPane.add(rc);
		
		ImageIcon image = new ImageIcon("datafiles/map/전체.jpg");
		JLabel map = new JLabel(Main.imageIconSetSize(image, 900, 1000));
		map.setBounds(0, 0, 900, 1000);
		layeredPane.add(map);
	}
}
