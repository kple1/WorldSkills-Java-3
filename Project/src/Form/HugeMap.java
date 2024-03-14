package Form;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Utils.ChangeLogo;
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
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		new ChangeLogo(frame);
		
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	frame.dispose();
            	Main main = new Main();
            	main.getMain().setVisible(true);
            }
        });
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 900, 1000);
		frame.getContentPane().add(layeredPane);
		frame.setLocationRelativeTo(null);
		
		RedCircle rc = new RedCircle();
		rc.setBounds(x, y, 30, 30);
		rc.setOpaque(false);
		layeredPane.add(rc);
		
		ImageIcon image = new ImageIcon("C:\\Users\\User\\Desktop\\과제3 결과물\\datafiles\\map\\전체.jpg");
		JLabel map = new JLabel(Main.imageIconSetSize(image, 900, 1000));
		map.setBounds(0, 0, 900, 1000);
		layeredPane.add(map);
	}
}
