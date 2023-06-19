package 가계부;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class 입력창 extends JFrame implements ActionListener {
	JLabel 금액, 사유, 분류, 날짜, 확인;
	JButton 저장;
	JRadioButton[] 수입지출 = new JRadioButton[2];
	JTextField 입력0, 입력1, 입력2;

	입력창() {
		setLayout(null);
		ButtonGroup gb = new ButtonGroup();
		날짜 = new JLabel("날짜(-) : ");
		입력0 = new JTextField(20);
		금액 = new JLabel("금액 : ");
		입력1 = new JTextField(20);
		분류 = new JLabel("분류 : ");
		수입지출[0] = new JRadioButton("수입");
		수입지출[1] = new JRadioButton("지출");// 버튼 말고 오디오버튼이나 다른걸로 변경
		사유 = new JLabel("사용처 : ");
		입력2 = new JTextField(20);
		확인 = new JLabel("");
		저장 = new JButton("저장");

		날짜.setBounds(20, 20, 50, 20);
		add(날짜);
		입력0.setBounds(70, 20, 120, 20);
		add(입력0);
		금액.setBounds(20, 50, 50, 20);
		add(금액);
		입력1.setBounds(70, 50, 120, 20);
		add(입력1);
		분류.setBounds(20, 80, 50, 20);
		add(분류);
		for (int i = 0; i < 수입지출.length; i++) {
			수입지출[i].setBounds(70 + (i * 60), 80, 60, 20);
			add(수입지출[i]);
			gb.add(수입지출[i]);
		}
		사유.setBounds(20, 110, 50, 20);
		add(사유);
		입력2.setBounds(70, 110, 120, 20);
		add(입력2);
		확인.setBounds(20, 140, 190, 20);
		add(확인);
		저장.setBounds(130, 170, 60, 20);
		add(저장);
		저장.addActionListener(this);

		setTitle("입력창");
		setSize(550, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new 입력창();
	}

	public void actionPerformed(ActionEvent e) {// 계산
		// e는 무슨 버튼을 눌러서 여기로 들어왔는가?
		int money;
		money = Integer.parseInt(입력1.getText());

		String value = null, useing, s, day;
		day = 입력0.getText();
		if (수입지출[0].isSelected())
			value = "수입";// 이중에 뭐가 눌렸는가?
		else if (수입지출[1].isSelected())
			value = "지출";
		useing = 입력2.getText();
		확인.setText(day + "에 저장되었습니다");

		try {
			new DB(day, money, value, useing);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		입력0.setText("");
		입력1.setText("");
		입력2.setText("");
	}
}
