package Utils;

import javax.swing.JPanel;

import Form.BuildingInfo;
import Form.HugeMap;
import Form.Main;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import Data.DB;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchFormPlugin extends JPanel {

	String buildingName;
	String price;
	String location;
	
	public SearchFormPlugin(String buildingName, String price, String location) {
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
		);
		panel.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(12, 10, 101, 101);
		panel.add(layeredPane);
		
		JLabel lblNewLabel_1 = new JLabel("공매");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(SystemColor.activeCaption);
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setBounds(12, 10, 33, 15);
		layeredPane.add(lblNewLabel_1);
		
		JLabel name = new JLabel(buildingName);
		name.setBounds(125, 10, 195, 15);
		panel.add(name);
		
		JLabel resultPrice = new JLabel(price);
		resultPrice.setBounds(125, 53, 195, 15);
		panel.add(resultPrice);
		
		JLabel locate = new JLabel(location);
		locate.setBounds(125, 96, 130, 15);
		panel.add(locate);
		
		JLabel lblNewLabel = new JLabel("위치보기");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int b_x = DB.getIntData("b_x", "building", "b_name", buildingName);
				int b_y = DB.getIntData("b_y", "building", "b_name", buildingName);
				HugeMap hm = new HugeMap(b_x, b_y);
				hm.getHegeMap().setVisible(true);
				
				String no = DB.getData("b_no", "b_name", buildingName, "building");
				String b_price = DB.getData("b_price", "b_no", no, "building");
				String b_name1 = DB.getData("b_name", "b_no", no, "building");
				String b_count = DB.getData("b_count", "b_no", no, "building");
				String a_no = DB.getData("a_no", "b_no", no, "building");
				String ar_name = DB.getData("ar_name", "ar_no", a_no, "area");
				String a_date = DB.getData("a_date", "a_no", a_no, "auction");
				int type = DB.getIntData("b_type", "building", "b_no", no);

				int resultPrice = 0;
				int gamJeongGa = 0;
				// 유찰횟수 (감정가) 판별 / 0 : 100% +1 : -10%
				if (Integer.parseInt(b_count) == 0) {
					resultPrice = Integer.parseInt(b_price);
					gamJeongGa = 100;
				} else {
					resultPrice = Integer.parseInt(b_price) / (Integer.parseInt(b_count) * 10);
					gamJeongGa = (Integer.parseInt(b_count) * 10);
				}

				// 750000000 -> 7억 5000만원 변경
				StringBuffer sb = new StringBuffer();
				if ((int) Math.log10(resultPrice) + 1 == 9) { // Math.log10이 0부터 세서 1추가 해서 비교하는 거임 (Math.log10 ->
																// 숫자형 길이 셈)
					sb.append(String.valueOf(resultPrice).substring(0, 5) + "만원");
					sb.insert(1, "억");
				} else {
					sb.append(String.valueOf(resultPrice).substring(0, 4) + "만원");
				}

				String buildingType = "none";
				if (type == 0) {
					buildingType = "아파트";
				} else if (type == 1) {
					buildingType = "주택";
				} else if (type == 2) {
					buildingType = "오피스텔";
				}
				
				BuildingInfo bi = new BuildingInfo(
						"<html><font color='blue' size='+1'>" + sb + "</font> 감정가익" + gamJeongGa + "%<html/>",
						String.valueOf(sb),
						a_date,
						b_count + "회",
						DB.getInterestPeoples(no) + "명",
						b_name1,
						"지역: " + ar_name,
						"종류: " + buildingType);
				
				bi.getBuildingInfo().setVisible(true);
				
			}
		});
		lblNewLabel.setForeground(SystemColor.activeCaption);
		lblNewLabel.setBounds(263, 96, 57, 15);
		panel.add(lblNewLabel);
		
		ImageIcon icon = new ImageIcon("datafiles/building/" + buildingName + "1.jpg");
		JLabel label = new JLabel(Main.imageIconSetSize(icon, 101, 101));
		label.setBounds(0,0,101,101); //꼭 해주기 꼭 해주기 꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기꼭 해주기 꼭 해주기
		layeredPane.add(label);
		setLayout(groupLayout);
	}
}