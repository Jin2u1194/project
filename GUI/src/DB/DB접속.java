package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DB접속 {
	public static final String databaseDriver = "oracle.jdbc.driver.OracleDriver";
    public static final String databaseUrl = "jdbc:oracle:thin:@192.168.35.180:1521:xe";
    public static final String databaseUser = "jin2u";
    public static final String databasePassword = "4869";
    public static Connection connection = null;

    public static void main(String[] args) {
       System.out.println("show"); //* Connection TEST
       String s=null;
       connect(s);
        // Close TEST
        close();

    }

    public static Connection connect(String 이름) { //DB CONNECT
    	String URL = String.format(databaseUrl, 이름);
        try {
            Class.forName(databaseDriver);
            connection = DriverManager.getConnection(URL, databaseUser, databasePassword);
            if (connection != null) System.out.println("Connection Succeed");
            else System.out.println("Connection Failed");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "데이터베이스가 연결되지 않았습니다", "경고!!", JOptionPane.WARNING_MESSAGE);
            System.err.println("Connection Error! : " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public static void close() { //DB USE AFTER CLOSE
        try {
            if (connection != null) {
                System.out.println("Connection Close");
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Connection Closing Failed! : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
