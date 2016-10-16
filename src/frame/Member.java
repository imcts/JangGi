package frame;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

class gaip2 extends JFrame implements ActionListener, MouseListener, Runnable {
	Container con;
	
	List idList, nickList, emailList, tellList;

	LoginPage login;
	
	JPanel mainp;

	TextField id, // ���̵�
			name, // �̸�
			nickname, // �г�
			password, // ���
			pw2, // ��й�ȣȮ��
			tell2, // ����ó
			mail, // ���� �ּ�
			mail2; // ������ȣ

	JButton go, // ���ԿϷ� ��ư
			gender, // ���� ��ư
			gender2, // ���� ��ư
			emailbt,
			emailgo,
			back; // �������� ��ư

	Label lb, // ���̵� üũ ��
			lb2, // �г��� üũ ��
			lb3, // ��� ��ġ üũ
			lb4, lb5; // �̸�

	JLabel backImg,gen,genlb,tellb,maillb,emaillb;

	DataDAO datadao;
	
	String rid,rpassword,rnickname,rgender,remail,rtell,rname,mailnum="";
	
	int loginindex=0,idindex=0, passwordindex=0,genderindex=0, nicknameindex=0,emailindex=0,
			emailcheckindex=0,nameindex=0,tellindex=0,threadStop=0,genChoice=0;

	int idc=0,nicknamec=0,emailc=0,tellc=0;
	
	
	public gaip2(LoginPage login,DataDAO datadao) throws Exception {
		super("Sign in -");
		
		this.login=login;
		
		this.con = getContentPane();
		this.datadao=datadao;
		this.idList = this.datadao.getUserinfo(0);
		this.nickList=this.datadao.getUserinfo(2);
		this.emailList=this.datadao.getUserinfo(3);
		this.tellList=this.datadao.getUserinfo(4);
		
		this.mailnum+=(int)(Math.random()*999999)+1;

		setSize(575, 820);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sx = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int sy = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		setLocation(sx, sy);
		setLayout(null);

		this.backImg = new JLabel(new ImageIcon("photo/SigninBack.jpg"));
		this.backImg.setBounds(0, 0, 550, 770);

		this.mainp = new JPanel();
		this.mainp.setBounds(0, 0, 550, 770);
		this.mainp.setLayout(null);

		// ////////////////////���̵�///////////////////////////

		this.id = new TextField("���̵� �Է��ϼ���.");

		this.id.setBounds(45, 135, 250, 35);
		this.id.setFont(new Font(null, Font.BOLD, 19));
		this.id.addActionListener(this);
		this.id.addMouseListener(this);

		this.lb = new Label();
		this.lb.setBounds(310, 135, 200, 35);
		this.lb.setBackground(new Color(255, 255, 255));
		this.lb.setFont(new Font(null, Font.BOLD, 15));

		// //////////////////��й�ȣ//////////////////

		this.password = new TextField("��й�ȣ�� �Է����ּ���.");
		this.password.setBounds(45, 195, 250, 35);
		this.password.setFont(new Font(null, Font.BOLD, 19));
		this.password.setBackground(new Color(255, 255, 255));
		this.password.addMouseListener(this);
		this.lb5 = new Label("");
		this.lb5.setBounds(310, 195, 170, 35);
		this.lb5.setBackground(new Color(255, 255, 255));
		this.lb5.setFont(new Font(null, Font.BOLD, 15));

		this.pw2 = new TextField("��й�ȣ Ȯ���Դϴ�.");
		this.pw2.setBounds(45, 255, 250, 35);
		this.pw2.setFont(new Font(null, Font.BOLD, 19));
		this.pw2.setBackground(new Color(255, 255, 255));
		this.pw2.addMouseListener(this);

		this.lb3 = new Label("");
		this.lb3.setBounds(310, 255, 170, 30);
		this.lb3.setBackground(new Color(255, 255, 255));
		this.lb3.setFont(new Font(null, Font.BOLD, 15));

		// //////////////�̸�//////////////////////////////

		this.name = new TextField("�̸��� �Է��ϼ���.");
		this.name.setBounds(45, 320, 250, 35);
		this.name.setFont(new Font(null, Font.BOLD, 19));
		this.name.setBackground(new Color(255, 255, 255));
		this.name.addMouseListener(this);

		this.lb4 = new Label("");
		this.lb4.setBounds(310, 320, 200, 30);
		this.lb4.setBackground(new Color(255, 255, 255));
		this.lb4.setFont(new Font(null, Font.BOLD, 15));

		// ///////////////////�г���/////////////////////

		this.nickname = new TextField("�г����� �Է��ϼ���.");
		this.nickname.setBounds(45, 370, 250, 35);
		this.nickname.setFont(new Font(null, Font.BOLD, 19));
		this.nickname.setBackground(new Color(255, 255, 255));
		this.nickname.addMouseListener(this);

		this.lb2 = new Label();
		this.lb2.setBounds(310, 370, 200, 30);
		this.lb2.setBackground(new Color(255, 255, 255));
		this.lb2.setFont(new Font(null, Font.BOLD, 15));
		
		// ///////////����/////////////////
		this.gen=new JLabel("�� ��");
		this.gen.setBounds(45, 410, 100, 50);
		this.gen.setBackground(new Color(255,255,255));
		this.gen.setForeground(new Color(198,198,198));
		this.gen.setFont(new Font(null, Font.BOLD, 17));
		
		this.genlb=new JLabel();
		this.genlb.setBounds(100, 410, 150, 50);
		this.genlb.setFont(new Font(null, Font.BOLD, 17));
		this.genlb.setBackground(new Color(255,255,255));
		
		
		this.gender = new JButton("����",new ImageIcon("photo/manBasic.jpg"));
		this.gender.setBounds(299, 415, 100, 40);
		this.gender.addMouseListener(this);
		this.gender.addActionListener(this);
		
		this.gender2 = new JButton("����",new ImageIcon("photo/girlBasic.jpg"));
		this.gender2.setBounds(400, 415, 100, 40);
		this.gender2.addActionListener(this);
		this.gender2.addMouseListener(this);

		
		// ////////////////////����ó//////////////////

		this.tell2 = new TextField("�޴�����ȣ�� �Է��ϼ���.");
		this.tell2.setBounds(45, 475, 250, 35);
		this.tell2.setFont(new Font(null, Font.BOLD, 19));
		this.tell2.setBackground(new Color(255, 255, 255));
		this.tell2.addMouseListener(this);
		
		this.tellb=new  JLabel();
		this.tellb.setBounds(310, 475, 200, 30);
		this.tellb.setBackground(new Color(255, 255, 255));
		this.tellb.setFont(new Font(null, Font.BOLD, 15));
		
		// �����Է� /////////////////////////////////


		this.mail = new TextField("�̸����� �Է����ּ���.");
		this.mail.setBounds(45, 525, 250, 35);
		this.mail.setFont(new Font(null, Font.BOLD, 19));
		this.mail.setBackground(new Color(255, 255, 255));
		this.mail.addMouseListener(this);
		
		this.maillb=new JLabel();
		this.maillb.setBounds(310, 525, 200, 30);
		this.maillb.setBackground(new Color(255, 255, 255));
		this.maillb.setFont(new Font(null, Font.BOLD, 15));
		
		
		
		// �������� /////////////////////////////////

		this.mail2 = new TextField("������ȣ");
		this.mail2.setBounds(45, 575, 130, 30);
		this.mail2.setFont(new Font(null, Font.BOLD, 19));
		this.mail2.setBackground(new Color(255, 255, 255));
		this.mail2.addMouseListener(this);

		this.emailbt = new JButton("checknum",new ImageIcon("photo/checkin.jpg"));
		this.emailbt.setBounds(350, 570, 150, 40);
		this.emailbt.addActionListener(this);

		this.emaillb=new JLabel();
		this.emaillb.setBounds(190, 575, 200, 30);
		this.emaillb.setBackground(new Color(255, 255, 255));
		this.emaillb.setFont(new Font(null, Font.BOLD, 15));
		
		//////////////////���� ������ /////////////////
		
		this.emailgo=new JButton("emailgo",new ImageIcon("photo/EmailgoBysign.jpg"));
		this.emailgo.addActionListener(this);
		this.emailgo.setBounds(32, 622, 483, 49);
		
		
		
		// /////////////////���ԿϷ��ư/////////////////

		this.go = new JButton("go",new ImageIcon("photo/newSign.jpg"));
		this.go.addActionListener(this);
		this.go.setBounds(32, 679, 400, 50);
		
		
		// //////////////////////////////////////////////
		
		this.back=new JButton("back",new ImageIcon("photo/memberBack.jpg"));
		this.back.addActionListener(this);
		this.back.setBounds(435, 679, 83, 50);
		
		
		this.mainp.add(back);
		this.mainp.add(genlb);
		this.mainp.add(emailgo);
		this.mainp.add(emaillb);
		this.mainp.add(tellb);
		this.mainp.add(gen);
		this.mainp.add(id);
		this.mainp.add(lb);
		this.mainp.add(password);
		this.mainp.add(lb5);
		this.mainp.add(pw2);
		this.mainp.add(lb3);
		this.mainp.add(name);
		this.mainp.add(lb4);
		this.mainp.add(nickname);
		this.mainp.add(lb2);
		this.mainp.add(gender);	 
		this.mainp.add(gender2);
		this.mainp.add(tell2); 
		this.mainp.add(mail);
		this.mainp.add(maillb);
		this.mainp.add(emailbt);
		this.mainp.add(mail2);
		this.mainp.add(go);
		
		this.mainp.add(backImg);

		this.con.add(mainp);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int res;
		System.out.println(this.mailnum);
		if(e.getActionCommand().equals("back")){
			this.threadStop=1;
			this.login.setVisible(true);
			this.dispose();
		}
		if(e.getActionCommand().equals("go")){
			int r=-1;
			res=	this.formCheck();//���⿡�� ���Է»����� �ִٸ� ����� �ȳѾ��.
			if(res==0){
				return;
			}else{
				try {
					r = this.datadao.dataInput(rid, rnickname, rpassword, rname, rgender,rtell, remail);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(r==-1){
					this.emaillb.setForeground(new Color(255, 0, 0));
					this.emaillb.setText("Error : �����ڹ���.");
				}else{
					
					new GetId(this.login, 0);
					this.dispose();
				}
			}
		}
		
		if(e.getActionCommand().equals("emailgo")){
			if(mail.getText().equals("")){
				this.emaillb.setForeground(new Color(255,0,0));
				this.emaillb.setText("������ �Է����ּ���.");
				return;
			}else{
			EmailTest mailgo =	new EmailTest("sirupe89@gmail.com","xo1004tks"); 
			try {
				mailgo.sendMail("JangGi-Game ȸ������ ������ȣ �Դϴ�.", "������ȣ : "+this.mailnum+" 6�ڸ��� �Է��� ������ȣ Ȯ�ι�ư�� �����ּ���."
						, "imcts@nate.com", this.mail.getText());
				System.out.println(mail.getText());
				this.emaillb.setForeground(new Color(255,0,0));
				this.emaillb.setText("���� ���� �߼�.");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			}
		}
		if(e.getActionCommand().equals("����")){
			this.rgender="����";
			this.gender.setIcon(new ImageIcon("photo/manChoice.jpg"));
			this.gender2.setIcon(new ImageIcon("photo/girlBasic.jpg"));
			this.genderindex=1;
			this.genChoice=1;
		}
		if(e.getActionCommand().equals("����")){
			this.rgender="����";
			this.gender.setIcon(new ImageIcon("photo/manBasic.jpg"));
			this.gender2.setIcon(new ImageIcon("photo/girlChoice.jpg"));
			this.genderindex=1;
			this.genChoice=1;
		}
		if(e.getActionCommand().equals("checknum")){
			if(this.mailnum.equals(mail2.getText())){
				this.emaillb.setForeground(new Color(146, 169, 109));
				this.emaillb.setText("���� �Ǿ����ϴ�.");
				this.loginindex=1;
			}else{
				this.emaillb.setForeground(new Color(255, 0, 0));
				this.emaillb.setText("������ȣ ����ġ.");
				this.loginindex=0;
			}
		}
		
	}

	private int formCheck() {
		int res=1;
		
		if(this.loginindex==0){
			this.emaillb.setForeground(new Color(255, 0, 0));
			this.emaillb.setText("�������� �ʾҽ��ϴ�.");
			res=0;
		}
		if(this.idindex==0){
			lb.setForeground(new Color(255, 0, 0));
			lb.setText("�ʼ��Է� �Դϴ�.");
			res=0;
		}
		if(this.passwordindex==0){
			this.lb3.setForeground(new Color(255, 0, 0));
			this.lb3.setText("�ʼ��Է� �Դϴ�.");
			this.lb5.setForeground(new Color(255, 0, 0));
			this.lb5.setText("�ʼ��Է� �Դϴ�.");
			res=0;
		}
		if(this.genderindex==0){
			this.genlb.setForeground(new Color(255, 0, 0));
			this.genlb.setText("������ �����ϼ���.");
			res=0;
		}
		else {
			this.genlb.setText("");
			this.genChoice=1;
		}
		if(this.nicknameindex==0){
			this.lb2.setForeground(new Color(255, 0, 0));
			this.lb2.setText("�ʼ��Է� �Դϴ�.");
			res=0;
		}
		if(this.emailindex==0){
			this.maillb.setForeground(new Color(255, 0, 0));
			this.maillb.setText("�ʼ��Է� �Դϴ�.");
			res=0;
		}
		if(this.nameindex==0){
			lb4.setForeground(new Color(255, 0, 0));
			lb4.setText("�ʼ��Է� �Դϴ�.");
			res=0;
		}
		if(this.tellindex==0){
			 tellb.setForeground(new Color(255, 0, 0));
			 tellb.setText("�ʼ��Է� �Դϴ�.");
			 res=0;
		}
		return res;
	}


	@Override
	public void mouseReleased(MouseEvent e) {

		// �� �ؽ�Ʈ �ʵ� ���콺 Ŭ���� ��ĭ����

		if (e.getSource() == id) {
			this.id.setText("");
			
		}

		if (e.getSource() == nickname) {
			this.nickname.setText("");
		}

		if (e.getSource() == password) {
			this.password.setText("");
			this.password.setEchoChar('*');
		}

		if (e.getSource() == pw2) {
			this.pw2.setText("");
			this.pw2.setEchoChar('*');
		}

		if (e.getSource() == name) {
			this.name.setText("");
		}

		if (e.getSource() == tell2) {
			this.tell2.setText("");
		}

		if (e.getSource() == mail) {
			this.mail.setText("");
		}

		if (e.getSource().equals(mail2)) {
			this.mail2.setText("");
		}

	}

	@Override
	public void run() {
		// ///////�ǽð� �˻� �� ���ΰ�
		while (true) {
			if(this.threadStop==1){
				break;
			}
			if(this.genChoice==1){
				this.genlb.setText("");
			}
			if (!(id.getText().equals("���̵� �Է��ϼ���."))) {
				Pattern p = Pattern.compile("[a-zA-Z0-9]{5,8}");
				Matcher m = p.matcher(id.getText());
				Iterator it = this.idList.iterator();
				
				if(idList.size()==0){
					idc=1;
				}
				
				while(it.hasNext()){
					if(((String)it.next()).equals(this.id.getText())){
						lb.setForeground(new Color(255, 0, 0));
						lb.setText("������� ���̵��Դϴ�.");
						 this.idindex=0;
						this. idc=0;
						 break;
					}else{
						this.idc=1;
					}
				}
						
			
				if (m.matches() == false) {
					lb.setForeground(new Color(255, 0, 0));
					lb.setText("���̵�� 5-8�����Է°���.");
					 this.idindex=0;
				} 
			
				if(m.matches() && this.idc==1){
					lb.setForeground(new Color(146, 169, 109));
				    lb.setText("��� ������ ���̵��Դϴ�.");
				    this.rid=this.id.getText();
				    this.idindex=1;
				}
			}

			
			 if (!(password.getText().equals("��й�ȣ�� �Է����ּ���."))) {// ����� ��й�ȣ�� �Է��ϱ� ����������
				Pattern p = Pattern.compile("[��-Şa-zA-Z0-9]{5,20}");
				Matcher m = p.matcher(password.getText());
				if (m.matches() == false) {
					this.lb5.setForeground(new Color(255, 0, 0));
					this.lb5.setText("��й�ȣ�� �Է��ϼ���.");
					
				}else{
					this.lb5.setForeground(new Color(146, 169, 109));
					this.lb5.setText("������ ��й�ȣ �Դϴ�.");
					
				}
			}

			 if (!(pw2.getText().equals("��й�ȣ Ȯ���Դϴ�."))) {// ����� ��й�ȣ�� �Է��ϱ� ����������
					if (!(this.password.getText().equals(pw2.getText()))) {
						this.lb3.setForeground(new Color(255, 0, 0));
						this.lb3.setText("��ġ���� �ʽ��ϴ�.");
						this.passwordindex=0;
					}
					if(this.password.getText().equals(pw2.getText())){ //�ΰ��� ������ 
						this.lb3.setForeground(new Color(146, 169, 109));
						this.lb3.setText("��й�ȣ�� ��ġ�մϴ�.");
						 this.rpassword=this.pw2.getText();
						this.passwordindex=1;
					}
				}

			 if (!(name.getText().equals("�̸��� �Է��ϼ���."))) {
				Pattern p = Pattern.compile("[��-Ş]{1,10}");
				Matcher m = p.matcher(name.getText());
		
				
				if (m.matches() == false) {
					lb4.setForeground(new Color(255, 0, 0));
					lb4.setText("�̸��� �Է����ּ���.");
					this.nameindex=0;
				}
				else{
					lb4.setForeground(new Color(146, 169, 109));
					lb4.setText("�����̸� �̽ó׿�.");
					this.rname=name.getText();
					this.nameindex=1;
				}
			}
			 
			 if (!(nickname.getText().equals("�г����� �Է��ϼ���."))) {
				Pattern p = Pattern.compile("[��-Şa-zA-Z0-9]{3,9}");
				Matcher m = p.matcher(nickname.getText());
				Iterator it = this.nickList.iterator();
				
				if(nickList.size()==0){
					this.nicknamec=1;
				}
				
				while(it.hasNext()){
					if(((String)it.next()).equals(this.nickname.getText())){
						lb2.setForeground(new Color(255, 0, 0));
						lb2.setText("������� �г��� �Դϴ�.");
						this.nicknameindex=0;
						this.nicknamec=0;
						break;
					}else{
						this.nicknamec=1;
					}
				}
				
				
				if (m.matches() == false) {
					lb2.setForeground(new Color(255, 0, 0));
					lb2.setText("�г����� �Է����ּ���.");
					this.nicknameindex=0;
				} 
				if(m.matches() && this.nicknamec==1){
						lb2.setForeground(new Color(146, 169, 109));
						lb2.setText("��� ������ �г����Դϴ�.");
						this.rnickname=this.nickname.getText();
						this.nicknameindex=1;
				}
			}
			 
			 
			 if(!(tell2.getText().equals("�޴�����ȣ�� �Է��ϼ���."))){
				 Pattern p = Pattern.compile("[0-9]{3}-[0-9]{4}-[0-9]{4}");
				 Matcher m = p.matcher(tell2.getText());
				 Iterator it = tellList.iterator();
				 
				 if(tellList.size()==0){
					 this.tellc=1;
				 }
				 
				 while(it.hasNext()){
					 
					 if(((String)it.next()).equals(this.tell2.getText())){
						 tellb.setForeground(new Color(255, 0, 0));
						 tellb.setText("������� ����ó �Դϴ�.");
						 this.tellindex=0;
						 this.tellc=0;
						 break;
					 }else{
						 this.tellc=1;
					 }
				 }
				 
				 if(m.matches()==false){
					 tellb.setForeground(new Color(255, 0, 0));
					 tellb.setText("����ó�� �Է����ּ���.");
					 this.tellindex=0;
				 }
				 if(m.matches() && this.tellc==1){
					 tellb.setForeground(new Color(146, 169, 109));
					 tellb.setText("�ٸ��� �Է��ϼ̽��ϴ�.");
					 this.rtell=this.tell2.getText();
					 this.tellindex=1;
				 }
			 }
			 if(!(mail.getText().equals("�̸����� �Է����ּ���."))){
				 Pattern p = Pattern.compile("[a-zA-Z0-9]*@[a-zA-Z.]*.\\.[a-zA-Z]*.");
				 Matcher m = p.matcher(mail.getText());
				 Iterator it = this.emailList.iterator();
				 
				 if(emailList.size()==0){
					 this.emailc=1;
				 }
				 
				 while(it.hasNext()){
					 if(((String)it.next()).equals(this.mail.getText())){
						 maillb.setForeground(new Color(255, 0, 0));
						 maillb.setText("������� e-mail�Դϴ�.");
						 this.emailindex=0;
						 this.emailc=0;
						 break;
					 }else{
						 this.emailc=1;
					 }
				 }
				 
				 if(m.matches()==false){
					 maillb.setForeground(new Color(255, 0, 0));
					 maillb.setText("e-mail ����� �ƴմϴ�.");
					 this.emailindex=0;
				 }
				 if(m.matches() && this.emailc==1){
					 maillb.setForeground(new Color(146, 169, 109));
					 maillb.setText("�ٸ��� �Է��ϼ̽��ϴ�.");
					 this.remail=this.mail.getText();
					 this.emailindex=1;
				 }
			 }
			 
			


		}// while ����
	}// run ����

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-gendererated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-gendererated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-gendererated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-gendererated method stub

	}
}
