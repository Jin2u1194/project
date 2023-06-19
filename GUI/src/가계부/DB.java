package 가계부;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class DB {
	static Connection conn;
	static Statement stmt;
	static ResultSet result = null;
	static int num;
	static int money, cou, num0, sum, 합계, i;
	static String day, value, useing, day1 = null, 메인날짜 = null;
	JLabel 금액, 사유, 분류, 날짜, 확인;

	private static String DELETE_SQL = "delete from 가계부 where day= '%s' and num = %d and money = %d and value = '%s' and useing = '%s'";
	private static String INSERT_SQL = "insert into 가계부 values('%s',(select nvl(max(num), 0)+1 from 가계부),%d,'%s','%s')";// day,money,value,useing
	private static String SELECT_DAY_SQL = "select * from 가계부 where day = '%s'";
	private static String SELECT_DAY_NUM_SQL = "select * from 가계부 order by day,num";
	private static String UPDATE_SQL = "";

	public DB() {
	}

	public static void DB(Object a, Object b, Object c, Object d, Object e)
			throws SQLException, ClassNotFoundException {// 삭제
		conn = DB접속.connect();
		stmt = conn.createStatement();
		System.out.println(a+", "+b+", "+c+", "+d+", "+e);
		if(e==null) {
			e="";
		}
		String delete_sql = String.format(DELETE_SQL, a, b, c, d, e);
		stmt.executeUpdate(delete_sql);
		stmt.close();
		conn.close();
	}

	public DB(String a, int b, String c, String d) throws SQLException, ClassNotFoundException {// 입력
		conn = DB접속.connect();
		stmt = conn.createStatement();
		if(d==null) {
			d="";
		}
		String insert_sql = String.format(INSERT_SQL, a, b, c, d);
		stmt.executeUpdate(insert_sql);

		stmt.close();
		conn.close();
		result.close();
	}

	public static void DB(DefaultTableModel tableModel) throws SQLException, ClassNotFoundException {
		conn = DB접속.connect();
		stmt = conn.createStatement();

		result = stmt.executeQuery(SELECT_DAY_NUM_SQL);// 날자를 정렬하고 날자에 대해서 순서를 정렬해라
		cou = 0;
		sum = 0;

		for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}

		while (result.next()) {
			cou++;
			day = result.getString(1);
			num = result.getInt(2);
			money = result.getInt(3);
			value = result.getString(4);
			useing = result.getString(5);

			if (cou == 1)
				day1 = day;

			if (day1.equals(day))
				;
			else {
				tableModel.addRow(new String[] { day1, String.format("%d", sum) });

				day1 = day;
				sum = 0;
			}
			if (value.equals("수입"))
				sum = sum + money;
			else if (value.equals("지출"))
				sum = sum - money;
		}

		if (cou == 0)
			;
		else {
			tableModel.addRow(new String[] { day1, String.format("%d", sum) });
		}

		result.close();
		stmt.close();
		conn.close();
	}

	public static void DB(DefaultTableModel model, Object a) throws SQLException, ClassNotFoundException {// 날짜별 화면 출력
		conn = DB접속.connect();
		stmt = conn.createStatement();
		String select_day_sql = String.format(SELECT_DAY_SQL, a);
		result = stmt.executeQuery(select_day_sql);

		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}

		i = 0;
		while (result.next()) {
			day = result.getString(1);// 날짜
			num = result.getInt(2);// 들어온순서
			money = result.getInt(3);// 금액
			value = result.getString(4);// 수입지출
			useing = result.getString(5);// 사용처
			model.addRow(new String[] { String.format("%d", num), String.format("%d", money), value, useing });
			i++;
		}
		result.close();
		stmt.close();
		conn.close();
	}
}
