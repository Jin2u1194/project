package 로그인프로그램;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class 로그인프로그램 extends JFrame implements ActionListener {

	JLabel ID, PW;
	JButton Loging, 회원가입, 아이디비번찾기;
	JTextField ID1, PW1;

	로그인프로그램() {
		setLayout(null);

		ID = new JLabel("ID : ");
		ID1 = new JTextField(10);
		PW = new JLabel("PW : ");
		PW1 = new JTextField(10);
		Loging = new JButton("로그인");
		회원가입 = new JButton("가입");
		아이디비번찾기 = new JButton("찾기");

		ID.setBounds(20, 20, 50, 20);
		add(ID);
		ID1.setBounds(70, 20, 100, 20);
		add(ID1);
		PW.setBounds(20, 50, 50, 20);
		add(PW);
		PW1.setBounds(70, 50, 100, 20);
		add(PW1);

		Loging.setBounds(20, 80, 150, 20);
		add(Loging);
		Loging.addActionListener(this);
		회원가입.setBounds(20, 110, 75, 20);
		add(회원가입);
		회원가입.addActionListener(this);
		아이디비번찾기.setBounds(95, 110, 75, 20);
		add(아이디비번찾기);
		아이디비번찾기.addActionListener(this);

		setTitle("로그인");
		setBounds(700, 350, 200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new 로그인프로그램();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String txt, id = ID1.getText(), pw = PW1.getText(), 연결;
		if (obj == Loging) {
			try {
				연결 = Loging.getText();
				DB.DB(id, pw, 연결);
			} catch (SQLException e1) {
			}

		}

		else if (obj == 회원가입) {
			txt = 회원가입.getText();
			회원가입 가입 = new 회원가입(txt);
		}

		else if (obj == 아이디비번찾기) {
			txt = 아이디비번찾기.getText();
			아이디비번찾기 찾기 = new 아이디비번찾기(txt);// 찾기만 완성하면 기본적인 시스템 완성
		}
	}

}
