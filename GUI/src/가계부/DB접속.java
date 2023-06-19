package 가계부;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DB접속 {
	public static final String databaseDriver = "oracle.jdbc.driver.OracleDriver";
	public static final String databaseUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String databaseUser = "jin2u";
	public static final String databasePassword = "4869";
	public static Connection connection = null;

	public static void main(String[] args) {
		connect();
		// Close TEST
		close();

	}

	public static Connection connect() { // DB CONNECT
		try {
			Class.forName(databaseDriver);
			connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
			if (connection != null) {
			} else
				System.out.println("Connection Failed");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "데이터베이스가 연결되지 않았습니다", "경고!!", JOptionPane.WARNING_MESSAGE);
			System.err.println("Connection Error! : " + e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}

	public static void close() { // DB USE AFTER CLOSE
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.err.println("Connection Closing Failed! : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
