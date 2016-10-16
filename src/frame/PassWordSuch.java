package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PassWordSuch extends JFrame implements MouseListener, ActionListener {

	Container con;

	JPanel main;

	JLabel idd, msg, backimg, nummsg;
	TextField aid, emai, email;// ���̵�
	JButton go, no, check,back;

	DataDAO datadao;

	String id = "";

	LoginPage login;

	EmailTest mail;

	int emailok = 0;
	int emailnum1;
	String emailnum = "";

	public PassWordSuch(LoginPage login,DataDAO datadao) throws Exception {
		super("Find - ID");
		this.emailnum1 = (int) (Math.random() * 999999) + 1;
		this.emailnum += emailnum1;
		this.login = login;
		this.datadao = datadao;
		this.mail = new EmailTest("abcd@gmail.com", "12345678");

		this.con = getContentPane();

		setSize(422, 558);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sx = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int sy = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		setLocation(sx, sy);
		setLayout(null);

		this.main = new JPanel();
		this.main.setBounds(0, 0, 400, 500);
		this.main.setLayout(null);

		this.aid = new TextField();// �̸��ؽ�Ʈ�ʵ�
		this.aid.setBounds(40, 128, 320, 25);
		this.aid.setText("���Խ� �ۼ��� ���̵� �Է�.");
		this.aid.setFont(new Font(null, Font.BOLD, 18));
		this.aid.setForeground(new Color(255, 255, 255));
		this.aid.setBackground(new Color(209, 225, 196));
		this.aid.addMouseListener(this);

		this.idd = new JLabel("");
		this.idd.setBounds(40, 177, 320, 25);
		this.idd.setFont(new Font(null, Font.BOLD, 15));
		this.idd.setForeground(new Color(255, 0, 0));

		// �̸����ؽ�Ʈ�ʵ�
		this.emai = new TextField("���Խ� �ۼ��� e-mail �Է�.");
		this.emai.setBounds(40, 225, 320, 25);
		this.emai.setFont(new Font(null, Font.BOLD, 18));
		this.emai.setForeground(new Color(255, 255, 255));
		this.emai.setBackground(new Color(209, 225, 196));
		this.emai.addMouseListener(this);

		// ������ȣ �߼ۺ�.
		this.no = new JButton("mailgo", new ImageIcon("photo/emailGO.jpg"));
		this.no.setBounds(36, 288, 330, 45);
		this.no.addActionListener(this);

		this.msg = new JLabel("");
		this.msg.setBounds(36, 335, 320, 25);
		this.msg.setFont(new Font(null, Font.BOLD, 15));
		this.msg.setForeground(new Color(255, 0, 0));

		// ������ȣ �ؽ�Ʈ�ʵ�

		this.email = new TextField("������ȣ 6�ڸ�");
		this.email.setBounds(50, 390, 140, 25);
		this.email.setFont(new Font(null, Font.BOLD, 18));
		this.email.setForeground(new Color(255, 255, 255));
		this.email.setBackground(new Color(209, 225, 196));
		this.email.addMouseListener(this);

		this.nummsg = new JLabel();
		this.nummsg.setBounds(50, 415, 150, 25);
		this.nummsg.setFont(new Font(null, Font.BOLD, 15));
		this.nummsg.setForeground(new Color(255, 0, 0));

		this.check = new JButton("check", new ImageIcon("photo/emailgogo.jpg"));
		this.check.setBounds(220, 365, 150, 80);
		this.check.addActionListener(this);

		this.go = new JButton("ok", new ImageIcon("photo/FindIdOK.jpg"));// ã���ư
		this.go.setBounds(35, 450, 260, 40);
		this.go.addActionListener(this);
		
		this.back=new JButton("back",new ImageIcon("photo/findBack.jpg"));
		this.back.setBounds(300, 450, 70, 40);
		this.back.addActionListener(this);

		this.backimg = new JLabel(new ImageIcon("photo/FindPWback.jpg"));
		this.backimg.setBounds(0, 0, 400, 500);

		
		this.main.add(back);
		this.main.add(nummsg);
		this.main.add(go);
		this.main.add(check);
		this.main.add(email);
		this.main.add(msg);
		this.main.add(aid);
		this.main.add(idd);
		this.main.add(emai);
		this.main.add(no);

		this.main.add(backimg);

		this.con.add(main);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (e.getSource().equals(this.aid)) {
			this.aid.setText("");
			if(this.emai.getText().equals("")){
				this.emai.setText("���Խ� �ۼ��� e-mail �Է�.");
			}
			if(this.email.getText().equals("")){
				this.email.setText("������ȣ 6�ڸ�");
			}
		}

		if (e.getSource().equals(this.emai)) {
			this.emai.setText("");
			if(this.aid.getText().equals("")){
				this.aid.setText("���Խ� �ۼ��� ���̵� �Է�.");
			}
			if(this.email.getText().equals("")){
				this.email.setText("������ȣ 6�ڸ�");
			}

		}
		if (e.getSource().equals(this.email)) {
			email.setText("");
			if(this.aid.getText().equals("")){
				this.aid.setText("���Խ� �ۼ��� ���̵� �Է�.");
			}
			if(this.emai.getText().equals("")){
				this.emai.setText("���Խ� �ۼ��� e-mail �Է�.");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("check")) {
			if (this.emailnum.equals(this.email.getText())) {
				this.nummsg.setText("�����Ϸ� �Ǿ����ϴ�.");
				this.emailok = 1;
			} else {
				this.nummsg.setText("������ȣ ����ġ �ٽ� �Է����ּ���.");
				return;
			}

		}
		else if(e.getActionCommand().equals("back")){
			this.login.setVisible(true);
			this.dispose();
		}
		else if (e.getActionCommand().equals("mailgo")) {
			// �̸��� �߼� ��ư ��������.
			if (this.emai.getText().equals("���Խ� �ۼ��� e-mail �Է�.")) {
				this.msg.setText("�̸����� �Է��ϼž� ������ ���� �մϴ�.");
				return;
			}
			EmailTest mailgo = new EmailTest("sirupe89@gmail.com", "xo1004tks");
			try {
				mailgo.sendMail("JangGi-Game ������ȣ �Դϴ�.", "������ȣ : "
						+ this.emailnum + " 6�ڸ��� �Է��� ������ȣ Ȯ�ι�ư�� �����ּ���.",
						"imcts@nate.com", this.emai.getText());
				this.msg.setText("���������� �߼� �Ǿ����ϴ�.");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		else if (e.getActionCommand().equals("ok")) {
			if (this.emailok == 1) { // ���� �κ��� ok������ �������ٸ� !
				String arr[] = { this.aid.getText(), this.emai.getText() };
				boolean res;

				try {
					res = this.datadao.nameSerch(arr,1); // false ��� ���̵� ����.
														// true���?
					if (res == false) {
						this.idd.setText("�̸� �Ǵ� ���������� ��ġ���� �ʽ��ϴ�.");
						return;
					} else {
						new GetId(this.login.password, this.login,1);
						this.dispose();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				this.msg.setText("�̸��� ������ �Ϸ���� �ʾҽ��ϴ�.");
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
