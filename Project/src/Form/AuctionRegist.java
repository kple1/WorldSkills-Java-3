package Form;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

import Data.DB;
import Utils.ChangeLogo;
import Utils.Square;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;

public class AuctionRegist {

	private JFrame frame;
	private JTextField buildingName;
	private JTextField price;
	public static JTextField X;
	public static JTextField Y;
	int type = 0;
	int a_date = 0;

	public JFrame getFrame() {
		return frame;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuctionRegist window = new AuctionRegist();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AuctionRegist() {
		initialize();
	}

	boolean selectedPanel1 = false;
	boolean selectedPanel2 = false;
	boolean selectedPanel3 = false;
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("경매 등록");
		frame.setBounds(100, 100, 548, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(new Square(12, 10));
		
		new ChangeLogo(frame);
		
		JLabel lblNewLabel = new JLabel("건물명");
		lblNewLabel.setBounds(12, 172, 57, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("가격");
		lblNewLabel_1.setBounds(12, 207, 38, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("경매일");
		lblNewLabel_2.setBounds(12, 243, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("동네");
		lblNewLabel_3.setBounds(12, 278, 57, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("좌표");
		lblNewLabel_4.setBounds(12, 315, 57, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		buildingName = new JTextField();
		buildingName.setBounds(109, 169, 411, 21);
		frame.getContentPane().add(buildingName);
		buildingName.setColumns(10);
		
		price = new JTextField();
		price.setBounds(109, 204, 129, 21);
		frame.getContentPane().add(price);
		price.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("천원");
		lblNewLabel_5.setBounds(250, 207, 38, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("종류");
		lblNewLabel_5_1.setBounds(362, 207, 38, 15);
		frame.getContentPane().add(lblNewLabel_5_1);
		
		JComboBox buildingTypeComboBox = new JComboBox();
		buildingTypeComboBox.addItem("아파트");
		buildingTypeComboBox.addItem("주택");
		buildingTypeComboBox.addItem("오피스텔");
		buildingTypeComboBox.setBounds(412, 203, 108, 23);
		frame.getContentPane().add(buildingTypeComboBox);
		
		JComboBox yearComboBox = new JComboBox();
		int getYear = LocalDateTime.now().getYear();
		for (int i = getYear; i <= 2030; i++) {
			yearComboBox.addItem(i);
		}
		yearComboBox.setBounds(109, 239, 100, 23);
		frame.getContentPane().add(yearComboBox);
		
		JLabel lblNewLabel_6 = new JLabel("년");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(221, 243, 12, 15);
		frame.getContentPane().add(lblNewLabel_6);
		
		JComboBox MonthComboBox = new JComboBox();
		int getMonth = LocalDateTime.now().getMonthValue();
		for (int i = 1; i <= 12; i++) {
			if ((int)Math.log10(i) + 1 == 1) {
				MonthComboBox.addItem("0" + i);
			} else {
				MonthComboBox.addItem(i);
			}
		}
		MonthComboBox.setSelectedItem(getMonth);
		MonthComboBox.setBounds(260, 239, 90, 23);
		frame.getContentPane().add(MonthComboBox);
		
		JLabel lblNewLabel_6_1 = new JLabel("월");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_1.setBounds(349, 243, 23, 15);
		frame.getContentPane().add(lblNewLabel_6_1);
		
		JComboBox DayComboBox = new JComboBox();
		int getMonthDay = YearMonth.of(getYear, getMonth).lengthOfMonth();
		for (int i = 1; i <= getMonthDay; i++) {
			DayComboBox.addItem(i);
		}
		int getDayOfMonth = LocalDateTime.now().getDayOfMonth();
		DayComboBox.setSelectedItem(getDayOfMonth);
		DayComboBox.setBounds(412, 239, 66, 23);
		frame.getContentPane().add(DayComboBox);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("일");
		lblNewLabel_6_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_1_1.setBounds(478, 243, 23, 15);
		frame.getContentPane().add(lblNewLabel_6_1_1);
		
		JComboBox locationComboBox = new JComboBox();
		
		String[] list = new String[] {"불당1동", "불당2동", "쌍용1동", "쌍용2동", "쌍용3동", "성정1동", "성정2동", "백석동", "부성2동"};
		for (String array: list) {
			locationComboBox.addItem(array);
		}
		locationComboBox.setBounds(109, 274, 100, 23);
		frame.getContentPane().add(locationComboBox);
		
		JLabel lblNewLabel_6_2 = new JLabel("X");
		lblNewLabel_6_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_2.setBounds(109, 315, 12, 15);
		frame.getContentPane().add(lblNewLabel_6_2);
		
		X = new JTextField();
		X.setEnabled(false);
		X.setBounds(133, 312, 57, 21);
		frame.getContentPane().add(X);
		X.setColumns(10);
		
		JLabel lblNewLabel_6_2_1 = new JLabel("Y");
		lblNewLabel_6_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_2_1.setBounds(197, 315, 12, 15);
		frame.getContentPane().add(lblNewLabel_6_2_1);
		
		Y = new JTextField();
		Y.setEnabled(false);
		Y.setColumns(10);
		Y.setBounds(221, 312, 57, 21);
		frame.getContentPane().add(Y);
		
		JButton locationSettingButton = new JButton("위치설정하기");
		locationSettingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectHugeMap shm = new SelectHugeMap();
				shm.getFrame().setVisible(true);
			}
		});
		locationSettingButton.setForeground(SystemColor.window);
		locationSettingButton.setBackground(SystemColor.activeCaption);
		locationSettingButton.setBounds(279, 348, 121, 23);
		frame.getContentPane().add(locationSettingButton);
		
		if (buildingTypeComboBox.getSelectedItem().equals("아파트")) {
			type = 0;
		} else if (buildingTypeComboBox.getSelectedItem().equals("주택")) {
			type = 1;
		} else if (buildingTypeComboBox.getSelectedItem().equals("오피스텔")) {
			type = 2;
		}
		
		for (int i = 0; i < 9; i++) {
			if (locationComboBox.getSelectedItem().equals(list[i])) {
				a_date = i + 1;
			}	
		}
		
		String date = yearComboBox.getSelectedItem() + "-" + MonthComboBox.getSelectedItem() + "-" + DayComboBox.getSelectedItem();
		JButton endButton = new JButton("등록하기");
		List<Path> imageList = new ArrayList<>();
		List<String> fileList = new ArrayList<>();
		JFileChooser fileDlg = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
	    fileDlg.setFileFilter(filter);
		
		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long getLP = (int) Math.log10(Long.parseLong(price.getText())) + 1;
				if (!selectedPanel1 || !selectedPanel2 || !selectedPanel3) {
					JOptionPane.showMessageDialog(null, "이미지를 모두 선택해주세요.", "경고", JOptionPane.ERROR_MESSAGE);
				} else if (buildingName.getText().isEmpty() || price.getText().isEmpty() || X.getText().isEmpty() || Y.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "입력하지 않은 항목이 있습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				} else if (Pattern.matches("", price.getText())) {
					JOptionPane.showMessageDialog(null, "가격은 0이 아닌 숫자만 입력 가능합니다.", "경고", JOptionPane.ERROR_MESSAGE);
				} else if (getLP < 7 && getLP > 12) {
					JOptionPane.showMessageDialog(null, "가격은 최소 백만원 단위, 최대 천억 단위까지 입력 가능합니다.", "경고", JOptionPane.ERROR_MESSAGE);
				} else if (X.getText().isEmpty() || Y.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "위치를 설정해주세요", "경고", JOptionPane.ERROR_MESSAGE);
				} else {
					for (int i = 0; i < 3; i++) {
						 Path sourcePath = imageList.get(i);
						 int pos = fileList.get(i).lastIndexOf(".");
						 String fileName = buildingName.getText() + (i + 1) + fileList.get(i).substring(pos);
			             Path destinationPath = Paths.get("C:\\Users\\User\\Desktop\\과제3 결과물\\datafiles\\building\\" + fileName);
			             try {
							Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					DB.insertBuilding(buildingName.getText(), price.getText(), type, date, a_date, Integer.parseInt(X.getText()), Integer.parseInt(Y.getText()));
					JOptionPane.showMessageDialog(null, "등록이 완료되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		endButton.setForeground(SystemColor.window);
		endButton.setBackground(SystemColor.activeCaption);
		endButton.setBounds(404, 348, 121, 23);
		frame.getContentPane().add(endButton);
		
		JPanel panel = new Square(0, 0);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedPanel1 = true;

				int result = fileDlg.showOpenDialog(null); //오픈
				if(result == JFileChooser.APPROVE_OPTION) {
					ImageIcon image = new ImageIcon(fileDlg.getSelectedFile().getPath());
					File selectedFile = fileDlg.getSelectedFile();
					imageList.add(selectedFile.toPath());
					fileList.add(selectedFile.getName());
					JLabel label = new JLabel(Main.imageIconSetSize(image, 150, 130));
					panel.add(label);
				}
				panel.revalidate();
				panel.repaint();
			}
		});
		panel.setBounds(12, 10, 158, 138);
		frame.getContentPane().add(panel);
		
		JPanel panel_1  = new Square(0, 0);
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedPanel2 = true;
				
				int result = fileDlg.showOpenDialog(null); //오픈
				if(result == JFileChooser.APPROVE_OPTION) {
					ImageIcon image = new ImageIcon(fileDlg.getSelectedFile().getPath());
					File selectedFile = fileDlg.getSelectedFile();
					imageList.add(selectedFile.toPath());
					fileList.add(selectedFile.getName());
					JLabel label = new JLabel(Main.imageIconSetSize(image, 150, 130));
					panel_1.add(label);
				}
				
				panel_1.revalidate();
				panel_1.repaint();
			}
		});
		panel_1.setBounds(182, 10, 158, 138);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new Square(0, 0);
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedPanel3 = true;

				int result = fileDlg.showOpenDialog(null); //오픈
				if(result == JFileChooser.APPROVE_OPTION) {
					ImageIcon image = new ImageIcon(fileDlg.getSelectedFile().getPath());	    
					File selectedFile = fileDlg.getSelectedFile();
					imageList.add(selectedFile.toPath());
					fileList.add(selectedFile.getName());
					JLabel label = new JLabel(Main.imageIconSetSize(image, 150, 130));
					panel_2.add(label);
				}
				
				panel_2.revalidate();	
				panel_2.repaint();
			}
		});
		panel_2.setBounds(349, 10, 158, 138);
		frame.getContentPane().add(panel_2);
	}
}
