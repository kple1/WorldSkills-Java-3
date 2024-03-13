package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			pstm.executeUpdate(); // executeUpdate() 메서드에 SQL 쿼리문을 전달하지 않음
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//test bro huh huh
	public static void main(String[] args) {
		List<String> list = DB.getFiveBuilding();
		for (String array : list) {
			System.out.println(DB.getData("b_name", "b_no", array, "building"));
		}
	}
}
