package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DB {

	public static String id = "root";
	public static String pw = "1234";
	public static String url = "jdbc:mysql://localhost:3306/?allowLoadLocalInfile=true";
	public static Statement st;
	public static Connection connection;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, id, pw);
			st = connection.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getDate(int i) {
		List<String> list = new ArrayList<>();
		String q = "SELECT b_date, COUNT(*) FROM auction.building where b_date LIKE '2024-0" + i + "%' GROUP BY b_date ORDER BY b_date, COUNT(b_no) DESC";
		try {
			ResultSet rs = st.executeQuery(q);
			while(rs.next()) list.add(rs.getString("b_date"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getDateCount(int i) {
		List<String> list = new ArrayList<>();
		String q = "SELECT b_date, COUNT(*) FROM auction.building where b_date LIKE '2024-0" + i + "%' GROUP BY b_date ORDER BY b_date, COUNT(b_no) DESC";
		try {
			ResultSet rs = st.executeQuery(q);
			while(rs.next()) list.add(rs.getString("COUNT(*)"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void delete(String table, String haveColumn, String where) {
		String q = "DELETE FROM " + table + " WHERE " + haveColumn + " = '" + where + "'";
		try { 
			st.executeUpdate("use auction");
			st.executeUpdate(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getIntData(String wantColumn, String tableName, String haveColumn, String where) {
		int save = 0;
		try {
			String q = "SELECT " + wantColumn + " FROM " + tableName + " where " + haveColumn + "='" + where + "'";
			ResultSet rs = st.executeQuery(q);
			if (rs.next())
				save = rs.getInt(wantColumn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}

	public static String getInterestPeoples(String buildingNum) {
		String save = "";
		try {
			st.executeUpdate("use auction");
			ResultSet rs = st.executeQuery("SELECT count(*) FROM interest WHERE b_no = '" + buildingNum + "'");
			if (rs.next())
				save = Integer.toString(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}

	public static List<String> getFiveBuilding() {
		List<String> save = new ArrayList<>();
		try {
			st.executeUpdate("USE auction");
			ResultSet rs = st.executeQuery(
					"SELECT b_no, COUNT(*) AS count FROM interest GROUP BY b_no ORDER BY count DESC LIMIT 5");
			while (rs.next()) {
				save.add(rs.getString("b_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static List<String> getDayAuctionCount() {
		List<String> save = new ArrayList<>();
		try {
			st.executeUpdate("USE auction");
			ResultSet rs = st.executeQuery(
					"SELECT COUNT(b_date) AS count FROM building GROUP BY b_date ORDER BY b_date");
			while (rs.next()) {
				save.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static List<String> getDayAuction() {
		List<String> save = new ArrayList<>();
		try {
			st.executeUpdate("USE auction");
			ResultSet rs = st.executeQuery("SELECT b_date FROM building GROUP BY b_date ORDER BY b_date");
			while (rs.next()) {
				save.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static String getMaxDate() {
		String save = "";
		try {
			st.executeUpdate("USE auction");
			ResultSet rs = st.executeQuery("SELECT MAX(b_date) FROM building");
			if (rs.next()) save = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static String getMinDate() {
		String save = "";
		try {
			st.executeUpdate("USE auction");
			ResultSet rs = st.executeQuery("SELECT MIN(b_date) FROM building");
			if (rs.next()) save = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static void insertBuilding(String b_name, String b_price, int b_type, String b_date, int a_no, int b_x, int b_y) {
		try {
			st.executeUpdate("USE auction");
			String sql = "INSERT INTO building (b_name, b_price, b_type, b_date, a_no, b_x, b_y, b_count) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, b_name);
			pstm.setString(2, b_price);
			pstm.setInt(3, b_type);
			pstm.setString(4, b_date);
			pstm.setInt(5, a_no);
			pstm.setInt(6, b_x);
			pstm.setInt(7, b_y);
			pstm.setInt(8, 0);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getData(String wantColumn, String getColumn, String where, String table) {
		String save = "";
		try {
			st.executeUpdate("USE auction");
			ResultSet resultSet = st.executeQuery(
					"SELECT " + wantColumn + " FROM " + table + " WHERE " + getColumn + " = '" + where + "'");
			if (resultSet.next())
				save = resultSet.getString(wantColumn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}

	public static List<String> getManyData(String wantColumn, String tableName, String haveColumn, String where) {
		List<String> list = new ArrayList<String>();
		try {
			st.executeUpdate("USE auction");
			String q = "SELECT " + wantColumn + " FROM " + tableName + " WHERE " + haveColumn + " = '" + where + "'";
			ResultSet rs = st.executeQuery(q);
			while (rs.next())
				list.add(rs.getString(wantColumn));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> getAllData(String wantColumn, String table) {
		List<String> save = new ArrayList<>();
		ResultSet resultSet = null;
		try {
			st.executeUpdate("USE auction");
			resultSet = st.executeQuery("SELECT " + wantColumn + " FROM " + table);
			while (resultSet.next()) {
				save.add(resultSet.getString(wantColumn));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}

	public static List<Integer> getAllIntData(String wantColumn, String table) {
		List<Integer> save = new ArrayList<>();
		ResultSet resultSet = null;
		try {
			st.executeUpdate("USE auction");
			resultSet = st.executeQuery("SELECT " + wantColumn + " FROM " + table);
			while (resultSet.next()) {
				save.add(resultSet.getInt(wantColumn));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static void insertInterest(String u_no, String b_no) {
		try { 
			String sql = "INSERT INTO interest (u_no, b_no) VALUES (?, ?)";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, u_no);
			pstm.setString(2, b_no);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insert(String id, String pw, String answer) {
		try {
			String sql = "INSERT INTO user (u_id, u_pw, u_answer) VALUES (?, ?, ?)";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			pstm.setString(3, answer);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertSaleAuction(String u_id, String b_name, String a_date, String b_price, String b_type) {
		try {
			String sql = "INSERT INTO saleauction (u_id, b_name, a_date, b_price, b_type) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, u_id);
			pstm.setString(2, b_name);
			pstm.setString(3, a_date);
			pstm.setString(4, b_price);
			pstm.setString(5, b_type);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String countInterestBuilding(String where) {
		String save = "";
		try {
			ResultSet rs = st.executeQuery("SELECT COUNT(i_no) FROM interest WHERE b_name = " + where);
			if (rs.next()) save = rs.getString(1); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static void main(String[] args) {
		List<String> list = DB.getDate(4);
		System.out.println(list);
	}
}
