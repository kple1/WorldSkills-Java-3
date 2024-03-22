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
					HugeMap window = new HugeMap(0, 0, "");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	int x;
	int y;
	String formName;
	public HugeMap(int x, int y, String formName) {
		this.x = x;
		this.y = y;
		this.formName = formName;
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
            	if (formName.equals("Auction")) {
            		frame.dispose();
            		Auction auction = new Auction();
            		auction.getAuction().setVisible(true);
            	} else if (formName.equals("myHome")) {
            		frame.dispose();
            		myHome mh = new myHome();
            		mh.getMyHome().setVisible(true);
            	} else if (formName.equals("Map")) {
            		Map map = new Map();
            		map.getMap().setVisible(true);
            		frame.dispose();
            	} else if (formName.equals("Search")) {
            		Search s = new Search();
            		s.getSearch().setVisible(true);
            		frame.dispose();
            	} else {
            		Main main = new Main();
            		main.getMain().setVisible(true);
            		frame.dispose();
            	}
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
		
		ImageIcon image = new ImageIcon("datafiles\\map\\전체.jpg");
		JLabel map = new JLabel(Main.imageIconSetSize(image, 900, 1000));
		map.setBounds(0, 0, 900, 1000);
		layeredPane.add(map);
	}
}
