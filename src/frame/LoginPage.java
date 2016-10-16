package frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class LoginPage extends JFrame implements ActionListener {
	Container con;
	DataDAO dataDao;

	JPanel mainp;
	TextField tx;
	TextField pw;
	ImageIcon image;

	JButton login, gaip, idget, pwget;

	JLabel lb, rowlb;

	Label p1, p2;

	String id, pw2, nickname, name, gen, gpsu, password;

	int jum;

	int userStartIndex = 0, loginSuccess = 0;

	public LoginPage() throws Exception {
		super("Jang-Gi Log-in");
		this.con = getContentPane();

		setSize(820, 657);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sx = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int sy = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		setLocation(sx, sy);
		setLayout(null);

		this.dataDao = new DataDAO(this);

		this.mainp = new JPanel();
		this.mainp.setBounds(0, 0, 800, 600);
		this.mainp.setBackground(new Color(5, 255, 255));
		this.mainp.setLayout(null);

		this.image = new ImageIcon("photo/Back.jpg");
		this.lb = new JLabel(image);
		this.lb.setBounds(0, 0, 800, 700);

		this.rowlb = new JLabel("");
		this.rowlb.setForeground(new Color(255, 0, 0));
		this.rowlb.setBounds(235, 495, 250, 40);

		this.tx = new TextField();
		this.tx.setBounds(280, 263, 300, 40);
		this.tx.setFont(new Font(null, Font.BOLD, 29));
		this.tx.setForeground(new Color(255, 255, 255));
		this.tx.setBackground(new Color(185, 198, 173));

		this.pw = new TextField();
		this.pw.setBounds(280, 330, 300, 40);
		this.pw.setFont(new Font(null, Font.BOLD, 29));
		this.pw.setForeground(new Color(255, 255, 255));
		this.pw.setEchoChar('*');
		this.pw.setBackground(new Color(185, 198, 173));

		this.login = new JButton("login", new ImageIcon("photo/login.jpg"));
		this.login.setBounds(234, 396, 350, 50);
		this.login.addActionListener(this);

		this.gaip = new JButton("gaip", new ImageIcon("photo/newID.jpg"));
		this.gaip.setBounds(234, 462, 100, 35);
		this.gaip.addActionListener(this);

		this.idget = new JButton("idget", new ImageIcon("photo/findID.jpg"));
		this.idget.setBounds(363, 462, 90, 35);
		this.idget.addActionListener(this);

		this.pwget = new JButton("pwget", new ImageIcon("photo/findPw.jpg"));
		this.pwget.setBounds(480, 462, 102, 35);
		this.pwget.addActionListener(this);

		this.mainp.add(pwget);
		this.mainp.add(idget);
		this.mainp.add(gaip);
		this.mainp.add(login);
		this.mainp.add(tx);
		this.mainp.add(pw);
		this.mainp.add(lb);

		this.con.add(rowlb);
		this.con.add(mainp);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("login")) { // 로그인 버튼 누르면 db랑 연동. 아이디와
													// 비밀번호가 맞는지 체크. 맞다면 진입.
			boolean res;
			String[] arr = { this.tx.getText(), this.pw.getText() };
			try {
				res = this.dataDao.idSerch(arr);
				if (res == false) {
					this.rowlb.setText("ID 또는 PW가 일치하지 않습니다.");
					this.tx.setText("");
					this.pw.setText("");
					return;
				} else {
					this.loginSuccess = 1;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			if (this.loginSuccess == 1) {
				this.tx.setText("");
				this.pw.setText("");
				this.setVisible(false);
				try {
					new User(this.id, this.name, this.nickname, this.gen,
							this.jum, this.gpsu, this);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}

		else if (e.getActionCommand().equals("gaip")) {
			this.setVisible(false);
			this.tx.setText("");
			this.pw.setText("");

			try {
				new Thread(new gaip2(this, this.dataDao)).start();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("idget")) {
			this.setVisible(false);
			try {
				new IdSuch(this, this.dataDao);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} else if (e.getActionCommand().equals("pwget")) {
			this.setVisible(false);
			try {
				new PassWordSuch(this, this.dataDao);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

	}

	public static void main(String[] args) throws Exception {
		new LoginPage();
	}

}
