package 가계부;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class 가계부프로그램 extends JFrame implements MouseListener {
	JLabel 금액, 사유, 분류, 날짜, 확인;
	JButton 저장, 삭제, 수정;
	JRadioButton[] 수입지출 = new JRadioButton[3];
	JTextField 입력0, 입력1, 입력2;
	JTable nad, asd;
	DefaultTableModel model, aasd;

	가계부프로그램() throws SQLException, ClassNotFoundException {
		setLayout(null);// 입력
		ButtonGroup gb = new ButtonGroup();
		날짜 = new JLabel("날짜(-) : ");
		입력0 = new JTextField(20);
		금액 = new JLabel("금액 : ");
		입력1 = new JTextField(20);
		분류 = new JLabel("분류 : ");
		수입지출[0] = new JRadioButton("수입");
		수입지출[1] = new JRadioButton("지출");
		수입지출[2] = new JRadioButton("수정");// 버튼 말고 오디오버튼이나 다른걸로 변경
		사유 = new JLabel("사용처 : ");
		입력2 = new JTextField(20);
		확인 = new JLabel("");
		저장 = new JButton("저장");

		날짜.setBounds(525, 20, 50, 20);
		add(날짜);
		입력0.setBounds(575, 20, 180, 20);
		add(입력0);
		금액.setBounds(525, 50, 50, 20);
		add(금액);
		입력1.setBounds(575, 50, 180, 20);
		add(입력1);
		분류.setBounds(525, 80, 50, 20);
		add(분류);
		for (int i = 0; i < 수입지출.length; i++) {
			수입지출[i].setBounds(575 + (i * 60), 80, 60, 20);
			this.add(수입지출[i]);
			gb.add(수입지출[i]);
		}
		사유.setBounds(525, 110, 50, 20);
		add(사유);
		입력2.setBounds(575, 110, 180, 20);
		add(입력2);
		저장.setBounds(635, 170, 60, 20);
		add(저장);
		저장.addMouseListener(this);

		// 출력

		aasd = new DefaultTableModel() {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		model = new DefaultTableModel() {
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
		삭제.setBounds(20, 320, 100, 20);
		add(삭제);
		삭제.addMouseListener(this);
		수정 = new JButton("수정");
		수정.setBounds(120, 320, 100, 20);
		add(수정);
		수정.addMouseListener(this);
		확인.setBounds(525, 140, 250, 20);
		add(확인);

		asd = new JTable(aasd);
		nad = new JTable(model);

		JScrollPane scrollPane2 = new JScrollPane(asd, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setBounds(20, 20, 200, 300);
		add(scrollPane2);
		asd.addMouseListener(this);// 메인날짜
		JScrollPane scrollPane = new JScrollPane(nad, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(220, 20, 300, 300);
		add(scrollPane);
		nad.addMouseListener(this);// 내용물

		DB.DB((DefaultTableModel) asd.getModel());
		setBounds(20, 20, 800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("가계부");
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		new 가계부프로그램();
	}

	public void mouseClicked(MouseEvent e) {
		Object 날짜, 순서, 금액, 분류, 사유;
		String value = null, useing, day;
		DefaultTableModel 내용물 = (DefaultTableModel) nad.getModel();
		DefaultTableModel 메인날짜 = (DefaultTableModel) asd.getModel();

		if (e.getSource() == asd) {// 메인날짜에서 클릭
			날짜 = asd.getModel().getValueAt(asd.getSelectedRow(), 0);

			try {
				DB.DB(내용물, 날짜);
			} catch (ClassNotFoundException | SQLException e1) {
			}
		}

		else if (e.getSource() == 삭제) {
			날짜 = asd.getModel().getValueAt(asd.getSelectedRow(), 0);
			순서 = nad.getModel().getValueAt(nad.getSelectedRow(), 0);// 순서
			금액 = nad.getModel().getValueAt(nad.getSelectedRow(), 1);// 금액
			분류 = nad.getModel().getValueAt(nad.getSelectedRow(), 2);// 분류
			사유 = nad.getModel().getValueAt(nad.getSelectedRow(), 3);// 사유
			확인.setText(날짜 + "일 " + 순서 + "번을 삭제하였습니다");
			try {
				DB.DB(날짜, 순서, 금액, 분류, 사유);
				DB.DB(메인날짜);
			} catch (ClassNotFoundException | SQLException e1) {
			}
		}
		/*
		 * else if(e.getSource()==수정){//문제발생 날짜값이 -1임
		 * 날짜=asd.getModel().getValueAt(asd.getSelectedRow(), 0);
		 * 순서=nad.getModel().getValueAt(nad.getSelectedRow(), 0 );
		 * 금액=nad.getModel().getValueAt(nad.getSelectedRow(), 1 );
		 * 분류=nad.getModel().getValueAt(nad.getSelectedRow(), 2 );
		 * 사유=nad.getModel().getValueAt(nad.getSelectedRow(), 3 );
		 * 
		 * 입력0.setText((String)날짜); if(분류.equals("수입")) 수입지출[0].setSelected(true); else
		 * if(분류.equals("지출")) 수입지출[1].setSelected(true); 입력2.setText((String)사유);
		 * 
		 * try { DB.DB(날짜,순서,금액,분류,사유); } catch (ClassNotFoundException | SQLException
		 * e1) {} }
		 */

		else if (e.getSource() == 저장) {// 문제발생 날짜값이 -1임
			int money = Integer.parseInt(입력1.getText());

			day = 입력0.getText();
			if (수입지출[0].isSelected())
				value = "수입";
			else if (수입지출[1].isSelected())
				value = "지출";
			수입지출[0].setSelected(false);
			수입지출[1].setSelected(false);
			useing = 입력2.getText();
			확인.setText(day + "에 저장되었습니다");

			try {
				new DB(day, money, value, useing);
				DB.DB(메인날짜);
			} catch (ClassNotFoundException | SQLException e1) {
			}

			입력0.setText("");
			입력1.setText("");
			입력2.setText("");
		}
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
