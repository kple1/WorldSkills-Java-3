package Utils;

import javax.swing.*;

import Data.DB;
import Form.BuildingInfo;
import Form.HugeMap;
import Form.Main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FavoriateAuction extends JPanel {
	private JLabel imageLabel;
	private JLabel textLabel;

	public FavoriateAuction(ImageIcon icon, String text, int x, int y, StringBuffer sb, int gamJeongGa, String a_date, String a_no, String array, String b_name, String ar_name, String buildingType, JFrame frame) {
		setLayout(new BorderLayout());

		imageLabel = new JLabel(icon);
		add(imageLabel, BorderLayout.NORTH);
		imageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				
				HugeMap hugeMap = new HugeMap(x, y);
				hugeMap.getHegeMap().setVisible(true);
	        	
				BuildingInfo bi = new BuildingInfo(
						"<html><font color='blue' size='+1'>" + sb + "</font> 감정가익" + gamJeongGa + "%<html/>",
						String.valueOf(sb),
						a_date,
						a_no + "회",
						DB.getInterestPeoples(array) + "명",
						b_name,
						"지역: " + ar_name,
						"종류: " + buildingType);

	        	bi.getBuildingInfo().setVisible(true);
			}
		});
		textLabel = new JLabel(text);
		add(textLabel, BorderLayout.WEST);
	}
}
