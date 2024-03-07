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

	public static String getData(String wantColumn, String getColumn, String where, String table) {
		String save = "";
		try {
			st.executeUpdate("USE auction");
			ResultSet resultSet = st.executeQuery("SELECT " + wantColumn + " FROM " + table +" WHERE " + getColumn + " = " + where);
			if (resultSet.next()) save = resultSet.getString(wantColumn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
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

	public static void main(String[] args) {
		System.out.println(getAllData("u_id", "user"));
	}
}
