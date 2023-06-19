package 로그인프로그램;

import DB.DB접속;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	static Connection conn;
	static Statement stmt;
	static ResultSet result = null;

	private static String INSERT_SQL = "insert into 로그인 values('%s', '%s', '%s', %d, '사용자')";// id,pw,name,pin
	private static String SELECT_ID_SQL = "select * from 로그인 where id = '%s'";
	private static String SELECT_PIN_SQL = "select * from 로그인 where PIN = %d";
	private static String UPDATE_SQL = "";

	public static void DB(String id, String pw, String name, String pin) throws SQLException, ClassNotFoundException {// 회원가입
		conn = DB접속.connect("로그인");
		stmt = conn.createStatement();
		System.out.println("회원가입 작동 " + id + " " + pw + " " + name + " " + pin + " ");
		stmt.executeUpdate("insert into 로그인 values('" + id + "', '" + pw + "', '" + name + "', " + pin + ", '사용자')");

		DB접속.close();
	}

	public static void DB(String id, String pw, String 연결) throws SQLException {// 로그인
		conn = DB접속.connect("로그인");
		stmt = conn.createStatement();

		if (연결.equals("로그인")) {
			try {
				System.out.println(id);
				result = stmt.executeQuery("select * from 로그인 where id = '" + id + "'");
				String 아이디 = null, 비번 = null, 이름 = null, 역할 = null, 전화번호 = null;

				while (result.next()) {
					아이디 = result.getString(1);
					비번 = result.getString(2);
					이름 = result.getString(3);
					전화번호 = result.getString(4);
					역할 = result.getString(5);
				}

				if (아이디 != null) {
					if (비번.equals(pw)) {
						System.out.println(역할 + "가 로그인했습니다");
					} else {
						System.out.println("비밀번호가 다릅니다");
					}
				}

				else {
					System.out.println("아이디가 존재하지않습니다");
				}
			} catch (Exception e) {
				System.out.println(e + "확인");
			} finally {
				DB접속.close();
			}
		}

		else if (연결.equals("찾기")) {
			try {
				result = stmt.executeQuery("select * from 로그인 where PIN =" + pw);

				String 아이디 = null, 비번 = null, 이름 = null, 역할 = null, 핀 = null;
				;

				while (result.next()) {
					아이디 = result.getString(1);
					비번 = result.getString(2);
					이름 = result.getString(3);
					핀 = result.getString(4);
					역할 = result.getString(5);
				}

				if (pw.equals(핀)) {
					if (이름.equals(id)) {
						System.out.println("해당 PIN으로 등록된 아이디는 '" + 아이디 + "' 이고 비번은 '" + 비번 + "' 입니다");
					} else {
						System.out.println("해당이름이 없습니다");
					}
				} else {
					System.out.println("해당 PIN이 존재하지않습니다");
				}
			} catch (Exception e) {
				System.out.println("--");
			} finally {
				DB접속.close();
			}
		}
	}
}
