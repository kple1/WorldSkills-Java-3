package Form;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JTree;
import javax.swing.JSeparator;
import javax.swing.JList;

public class TestFormZone {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFormZone window = new TestFormZone();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestFormZone() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 866, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		ImageIcon aboutIcon = new ImageIcon("datafiles/building/Y-시티아파트1.jpg");

		String[] columnNames = { "Picture", "Description" };
		Object[][] data = {{ Main.imageIconSetSize(aboutIcon, 50, 50), "About" }};
		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};
	}
}
