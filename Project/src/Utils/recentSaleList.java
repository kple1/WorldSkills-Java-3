package Utils;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Data.DB;
import Form.BuildingInfo;
import Form.HugeMap;
import Form.Login;
import Form.Main;

import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class recentSaleList extends JPanel {

	public String b_price;
	public String ar_name;
	public recentSaleList(String b_price, String ar_name, JFrame frame) {
		this.b_price = b_price;
		this.ar_name = ar_name;
		
		String getName = DB.getData("b_name", "b_price", b_price, "building");
		String getNo = DB.getData("b_no", "b_name", getName, "building");
		ImageIcon icon = new ImageIcon("C:\\Users\\User\\Desktop\\과제3 결과물\\datafiles\\building\\" + getName + "1.jpg");
		JLabel label = new JLabel(Main.imageIconSetSize(icon, 149, 105));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int getX = DB.getIntData("b_x", "building", "b_name", getName);
				int getY = DB.getIntData("b_y", "building", "b_name", getName);
				frame.dispose();
				HugeMap hugeMap = new HugeMap(getX, getY, "Main");
				hugeMap.getHegeMap().setVisible(true);
				
				String b_price = DB.getData("b_price", "b_no", getNo, "building");
	        	String b_name = DB.getData("b_name", "b_no", getNo, "building");
	        	String b_count = DB.getData("b_count", "b_no", getNo, "building");
	        	String a_no = DB.getData("a_no", "b_no", getNo, "building");
	        	String ar_name = DB.getData("ar_name", "ar_no", a_no, "area");
	        	String a_date = DB.getData("a_date", "a_no", a_no, "auction");
	        	int b_x = DB.getIntData("b_x", "building", "b_no", getNo);
	        	int b_y = DB.getIntData("b_y", "building", "b_no", getNo);
	        	int getType = DB.getIntData("b_type", "building", "b_no", getNo);
	        	
	        	String buildingType = "";
	        	if (getType == 0) {
	        		buildingType = "아파트";
	        	} else if (getType == 1) {
	        		buildingType = "주택";
	        	} else if (getType == 2) {
	        		buildingType = "오피스텔";
	        	}
	        	
	        	int resultPrice = 0;
	        	int gamJeongGa = 0;
	        	//유찰횟수 (감정가) 판별 / 0 : 100% +1 : -10%
	        	if (Integer.parseInt(b_count) == 0) {
	        		resultPrice = Integer.parseInt(b_price);
	        		gamJeongGa = 100;
	        	} else {
	        		resultPrice = Integer.parseInt(b_price) / (Integer.parseInt(b_count) * 10);
	        		gamJeongGa = (Integer.parseInt(b_count) * 10);
	        	}
	        	
	        	//750000000 -> 7억 5000만원 변경
	        	StringBuffer sb = new StringBuffer();
	        	if ((int) Math.log10(resultPrice) + 1 == 9) { //Math.log10이 0부터 세서 1추가 해서 비교하는 거임 (Math.log10 -> 숫자형 길이 셈)
	        		sb.append(String.valueOf(resultPrice).substring(0, 5) + "만원");
	        		sb.insert(1, "억");
	        	} else {
	        		sb.append(String.valueOf(resultPrice).substring(0, 4) + "만원");
	        	}
				BuildingInfo bi = new BuildingInfo(
						"<html><font color='blue' size='+1'>" + sb + "</font> 감정가익" + gamJeongGa + "%<html/>",
						String.valueOf(sb),
						a_date,
						a_no + "회",
						DB.getInterestPeoples(getNo) + "명",
						b_name,
						"지역: " + ar_name,
						"종류: " + buildingType);
				
				bi.getBuildingInfo().setVisible(true);
			}
		});
		label.setBounds(0, 0, 149, 105);
		
		StringBuffer sb = new StringBuffer();
    	if ((int) Math.log10(Integer.parseInt(b_price)) + 1 == 9) { //Math.log10이 0부터 세서 1추가 해서 비교하는 거임 (Math.log10 -> 숫자형 길이 셈)
    		sb.append(b_price.substring(0, 5) + "만원");
    		sb.insert(1, "억");
    	} else {
    		sb.append(b_price.substring(0, 4) + "만원");
    	}
    	
		JLayeredPane layeredPane = new JLayeredPane();
		
		JLabel area = new JLabel(ar_name);
		
		JLabel resultPrice = new JLabel(String.valueOf(sb));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addComponent(resultPrice)
						.addComponent(area, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(resultPrice)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(area)
					.addContainerGap())
		);
		setLayout(groupLayout);
		layeredPane.add(label);
	}
}
