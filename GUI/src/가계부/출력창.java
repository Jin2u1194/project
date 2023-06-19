package 가계부;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class 출력창 extends JFrame implements MouseListener {
	JLabel 확인;
	JButton 삭제, 수정;
	JTable nad, asd;
	DefaultTableModel model, aasd;

	출력창() throws SQLException, ClassNotFoundException {
		setLayout(null);
		확인 = new JLabel("");
		model = new DefaultTableModel() {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		aasd = new DefaultTableModel() {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		aasd.addColumn("날짜");
		aasd.addColumn("결산");
		model.addColumn("순서");
		model.addColumn("금액");
		model.addColumn("분류");
		model.addColumn("사유");
		삭제 = new JButton("삭제");
		삭제.setBounds(200, 20, 100, 20);
		add(삭제);
		삭제.addMouseListener(this);
		수정 = new JButton("수정");
		수정.setBounds(300, 20, 100, 20);
		add(수정);
		수정.addMouseListener(this);
		확인.setBounds(245, 20, 250, 20);
		add(확인);
		nad = new JTable(model);
		asd = new JTable(aasd);
		JScrollPane scrollPane2 = new JScrollPane(asd, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setBounds(200, 40, 200, 300);
		add(scrollPane2);
		asd.addMouseListener(this);// 메인날짜
		JScrollPane scrollPane = new JScrollPane(nad, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(400, 40, 300, 300);
		add(scrollPane);
		nad.addMouseListener(this);// 내용물

		DB.DB((DefaultTableModel) asd.getModel());
		setBounds(20, 20, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		new 출력창();
	}

	@Override
	public void mouseClicked(MouseEvent e) {// 클릭
		Object 날짜, 순서, 금액, 분류, 사유, 유지 = null;
		if (e.getSource() == asd) {

			날짜 = asd.getModel().getValueAt(asd.getSelectedRow(), 0);
			DefaultTableModel Model = (DefaultTableModel) nad.getModel();
			유지 = 날짜;
			for (int i = Model.getRowCount() - 1; i >= 0; i--) {
				Model.removeRow(i);
			}
			try {
				DB.DB(Model, 날짜);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == 삭제) {
			날짜 = asd.getModel().getValueAt(asd.getSelectedRow(), 0);
			순서 = nad.getModel().getValueAt(nad.getSelectedRow(), 0);// 순서
			금액 = nad.getModel().getValueAt(nad.getSelectedRow(), 1);// 금액
			분류 = nad.getModel().getValueAt(nad.getSelectedRow(), 2);// 분류
			사유 = nad.getModel().getValueAt(nad.getSelectedRow(), 3);// 사유
			확인.setText(순서 + "번 " + 금액 + "원 " + 분류 + "을 삭제하였습니다");
			try {
				DB.DB(날짜, 순서, 금액, 분류, 사유);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} /*
			 * else if(e.getSource()==수정){//문제발생 날짜값이 -1임 System.out.print(유지);
			 * 순서=nad.getModel().getValueAt(nad.getSelectedRow(), 0 );
			 * 금액=nad.getModel().getValueAt(nad.getSelectedRow(), 1 );
			 * 분류=nad.getModel().getValueAt(nad.getSelectedRow(), 2 );
			 * 사유=nad.getModel().getValueAt(nad.getSelectedRow(), 3 );
			 * 
			 * try { DB.DB(순서,금액,분류,사유); } catch (ClassNotFoundException e1)
			 * {e1.printStackTrace();} catch (SQLException e1) {e1.printStackTrace();} }
			 */
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}