package Utils;

import javax.swing.JPanel;

import Data.DB;
import Form.HugeMap;
import Form.Main;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class AuctionShow extends JPanel {

	String b_name;
	String b_price;
	String b_type;
	String ar_name;
	String interestCount;
	JFrame frame;
	public AuctionShow(String b_name, String b_price, String b_type, String ar_name, String interestCount, JFrame frame) {
		this.b_name = b_name;
		this.b_price = b_price;
		this.b_type = b_type;
		this.ar_name = ar_name;
		this.interestCount = interestCount;
		this.frame = frame;
		initialize();
	}

	private void initialize() {
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		JPanel panel_1 = new JPanel();
		
		ImageIcon icon = new ImageIcon("datafiles\\building\\" + b_name + "1.jpg");
		JLabel label = new JLabel(Main.imageIconSetSize(icon, 91, 91));
		panel_1.add(label);
		
		JLabel buildingName = new JLabel(b_name);
		
		JLabel buildingPrice = new JLabel(b_price);
		
		JLabel buildingType = new JLabel(b_type);
		
		JLabel location = new JLabel("위치보기");
		location.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				int x = DB.getIntData("b_x", "building", "b_name", b_name);
				int y = DB.getIntData("b_y", "building", "b_name", b_name);
				HugeMap map = new HugeMap(x, y, "Auction");
				map.getHegeMap().setVisible(true);
			}
		});
		location.setForeground(SystemColor.activeCaption);
		location.setBackground(new Color(240, 240, 240));
		
		JLabel areaName = new JLabel(ar_name + ",");
		
		JLabel favorCount = new JLabel("관심개수: " + interestCount);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(buildingName, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
						.addComponent(buildingPrice, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(buildingType, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(location, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(areaName, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(favorCount, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(buildingName)
							.addGap(10)
							.addComponent(buildingPrice)
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(buildingType)
								.addComponent(location))
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(areaName)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(1)
									.addComponent(favorCount))))))
		);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);
	}

}
