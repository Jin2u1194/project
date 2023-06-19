package 로그인프로그램;

//https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=bestheroz&logNo=103730014
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class 회원가입 extends JFrame implements ActionListener {

	JLabel Id = new JLabel("아이디"), Pw = new JLabel("비번"), Name = new JLabel("이름"), Pin = new JLabel("PIN");
	JButton Save = new JButton("저장");
	JTextField Id1 = new JTextField(10), Pw1 = new JTextField(10), Name1 = new JTextField(10),
			Pin1 = new JTextField(10);
	String txt;

	회원가입(String txt) {
		super(txt);
		this.txt = txt;
		display();
		setBounds(700, 350, 280, 250);
		setVisible(true);
	}

	public void display() {
		Container c = getContentPane();
		JPanel p = new JPanel(new GridLayout(4, 2));

		c.add(p, BorderLayout.CENTER);
		c.add(Save, BorderLayout.SOUTH);

		p.add(Id);
		p.add(Id1);

		p.add(Pw);
		p.add(Pw1);

		p.add(Name);
		p.add(Name1);

		p.add(Pin);
		p.add(Pin1);

		Pin1.setEditable(false);

		c.add(p, BorderLayout.CENTER);
		c.add(Save, BorderLayout.SOUTH);

		Save.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = Id1.getText(), pw = Pw1.getText(), name = Name1.getText(), pin = Pin1.getText();
		Random ran = new Random();
		int PIN = ran.nextInt(1000) + 1;

		if (isStringEmpty(id) || isStringEmpty(pw) || isStringEmpty(name)) {
			if (isStringEmpty(id))
				Id1.setText("입력해주세요");
			if (isStringEmpty(pw))
				Pw1.setText("입력해주세요");
			if (isStringEmpty(name))
				Name1.setText("입력해주세요");

		} else {
			try {
				System.out.println(PIN);
				DB.DB(id, pw, name, String.valueOf(PIN));
				Id.setText("아이디");
				Id1.setText("");
				Pw1.setText("");
				Name1.setText("");
				Pin1.setText("" + PIN);
				Pin.setText("PIN num은 찾기에 사용됩니다");
			} catch (ClassNotFoundException | SQLException e1) {
				/* "아이디가 같은경우 오류" */Id1.setToolTipText("다른아이디입력요청");
			}
		}
	}

	private boolean isStringEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}
}
