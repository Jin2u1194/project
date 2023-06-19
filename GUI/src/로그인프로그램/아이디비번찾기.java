package 로그인프로그램;

//https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=bestheroz&logNo=103730014
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class 아이디비번찾기 extends JFrame implements ActionListener {

	JLabel Id = new JLabel("아이디"), Pw = new JLabel("비번"), Name = new JLabel("이름"), Pin = new JLabel("PIN");
	JButton Save = new JButton("저장");
	JTextField Id1 = new JTextField(10), Pw1 = new JTextField(10), Name1 = new JTextField(10),
			Pin1 = new JTextField(10);
	String txt;

	아이디비번찾기(String txt) {
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

		c.add(p, BorderLayout.CENTER);
		c.add(Save, BorderLayout.SOUTH);

		Save.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = Name1.getText(), pin = Pin1.getText();
		try {
			DB.DB(name, pin, txt);
			Id.setText("아이디");
			Id1.setText("");
			Pw1.setText("");
			Name1.setText("");
			Pin1.setText("");
		} catch (SQLException e1) {
			/* "아이디가 같은경우 오류" */Id.setText("다른아이디입력요청");
			Id1.setText("");
		}
	}
}
