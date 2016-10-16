package frame;

///아이디 찾기 이름메일
///비번찾기 아이디 메일
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


class GetId extends JFrame implements ActionListener{
	Container con;
	
	JPanel main;
	
	JLabel id,backimg;
	
	JButton bt;
	
	LoginPage login;
	
	String select ="";
	StayR stayRoom;
	
	int index;
	
	public GetId(int index) {
		super("get Id-");
		
		this.index=index;
		
		this.con = getContentPane();

		setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sx = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int sy = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		setLocation(sx, sy);
		setLayout(null);
		
		if(this.index==10){
			this.select="회원탈퇴에 성공 하셨습니다.";
		}
		else if(this.index==9){
			this.select="회원정보가 변경되었습니다";
		}

		this.id = new JLabel(select);
		this.id.setForeground(new Color(255,0,0));
		this.id.setFont(new Font(null,Font.BOLD,17));
		this.id.setBounds(70, 70, 300, 50);
		
		this.backimg=new JLabel(new ImageIcon("photo/FindScreen.jpg"));
		this.backimg.setBounds(0, 0, 400, 300);
		
		
		this.bt=new JButton("ok",new ImageIcon("photo/OkbyIDPW.jpg"));
		this.bt.setBounds(20, 190, 360, 50);
		this.bt.addActionListener(this);
		
		this.main=new JPanel();
		this.main.setLayout(null);
		this.main.setBounds(0, 0, 400, 300);
		
		
		this.main.add(id);
		this.main.add(bt);
		this.main.add(backimg);
		
		this.con.add(main);
		setResizable(false);
		setVisible(true);
		
	}
	
	public GetId(int index,StayR stayRoom,int nullsu) {
		super("get Id-");
		
		this.index=index;
		
		this.stayRoom=stayRoom;
		
		this.con = getContentPane();

		setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sx = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int sy = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		setLocation(sx, sy);
		setLayout(null);
		
		if(this.index==10){
			this.select="회원탈퇴에 성공 하셨습니다.";
		}
		else if(this.index==9){
			this.select="회원정보가 변경되었습니다";
		}

		this.id = new JLabel(select);
		this.id.setForeground(new Color(255,0,0));
		this.id.setFont(new Font(null,Font.BOLD,17));
		this.id.setBounds(70, 70, 300, 50);
		
		this.backimg=new JLabel(new ImageIcon("photo/FindScreen.jpg"));
		this.backimg.setBounds(0, 0, 400, 300);
		
		
		this.bt=new JButton("ok",new ImageIcon("photo/OkbyIDPW.jpg"));
		this.bt.setBounds(20, 190, 360, 50);
		this.bt.addActionListener(this);
		
		this.main=new JPanel();
		this.main.setLayout(null);
		this.main.setBounds(0, 0, 400, 300);
		
		
		this.main.add(id);
		this.main.add(bt);
		this.main.add(backimg);
		
		this.con.add(main);
		setResizable(false);
		setVisible(true);
		
	}
	
	
	
	public GetId(int index,StayR stayRoom) {
		super("get Id-");
		
		this.index=index;
		
		this.stayRoom=stayRoom;
		this.con = getContentPane();

		setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sx = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int sy = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		setLocation(sx, sy);
		setLayout(null);
		
		if(this.index==10){
			this.select="회원탈퇴에 성공 하셨습니다.";
		}
		else if(this.index==9){
			this.select="회원정보가 변경되었습니다";
		}

		this.id = new JLabel(select);
		this.id.setForeground(new Color(255,0,0));
		this.id.setFont(new Font(null,Font.BOLD,17));
		this.id.setBounds(70, 70, 300, 50);
		
		this.backimg=new JLabel(new ImageIcon("photo/FindScreen.jpg"));
		this.backimg.setBounds(0, 0, 400, 300);
		
		
		this.bt=new JButton("ok",new ImageIcon("photo/OkbyIDPW.jpg"));
		this.bt.setBounds(20, 190, 360, 50);
		this.bt.addActionListener(this);
		
		this.main=new JPanel();
		this.main.setLayout(null);
		this.main.setBounds(0, 0, 400, 300);
		
		
		this.main.add(id);
		this.main.add(bt);
		this.main.add(backimg);
		
		this.con.add(main);
		setResizable(false);
		setVisible(true);
		
	}
	
	public GetId(LoginPage login,int index) {
		super("get Id-");
		this.login=login;
		
		this.index=index;
		
		this.con = getContentPane();

		setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sx = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int sy = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		setLocation(sx, sy);
		setLayout(null);
		
		if(this.index==0){
			this.select="회원 가입에 성공 하셨습니다.";
		}

		this.id = new JLabel(select);
		this.id.setForeground(new Color(255,0,0));
		this.id.setFont(new Font(null,Font.BOLD,17));
		this.id.setBounds(70, 70, 300, 50);
		
		this.backimg=new JLabel(new ImageIcon("photo/FindScreen.jpg"));
		this.backimg.setBounds(0, 0, 400, 300);
		
		
		this.bt=new JButton("ok",new ImageIcon("photo/OkbyIDPW.jpg"));
		this.bt.setBounds(20, 190, 360, 50);
		this.bt.addActionListener(this);
		
		this.main=new JPanel();
		this.main.setLayout(null);
		this.main.setBounds(0, 0, 400, 300);
		
		
		this.main.add(id);
		this.main.add(bt);
		this.main.add(backimg);
		
		this.con.add(main);
		setResizable(false);
		setVisible(true);
		
	}
	
	public GetId(String target,LoginPage login,int index) {
		super("get Id-");
		
		this.index=index;
		
		this.login=login;
		this.con = getContentPane();

		setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sx = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int sy = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		setLocation(sx, sy);
		setLayout(null);
		
		if(this.index==0){
			this.select="찾으신 아이디 : ";
		}
		else{
			this.select="찾으신 비밀번호 : ";
		}
		
		this.id = new JLabel(select+target);
		this.id.setForeground(new Color(255,0,0));
		this.id.setFont(new Font(null,Font.BOLD,17));
		this.id.setBounds(70, 70, 300, 50);
		
		this.backimg=new JLabel(new ImageIcon("photo/FindScreen.jpg"));
		this.backimg.setBounds(0, 0, 400, 300);
		
		
		this.bt=new JButton("ok",new ImageIcon("photo/OkbyIDPW.jpg"));
		this.bt.setBounds(20, 190, 360, 50);
		this.bt.addActionListener(this);
		
		this.main=new JPanel();
		this.main.setLayout(null);
		this.main.setBounds(0, 0, 400, 300);
		
		
		this.main.add(id);
		this.main.add(bt);
		this.main.add(backimg);
		
		this.con.add(main);
		setResizable(false);
		setVisible(true);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.index==10){
			this.stayRoom.logout();
			this.dispose();
		}
		if(this.index==9){
			this.stayRoom.setVisible(true);
			this.dispose();
		} else {
			this.setVisible(false);
			this.login.setVisible(true);
			this.dispose();
		}
	}
	
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////





class IdSuch extends JFrame implements ActionListener, MouseListener {

	Container con;

	JPanel main;

	JLabel idd, msg,backimg, nummsg;
	TextField aid, emai, email;// 아이디
	JButton go, no, check,back;

	DataDAO datadao;

	String id = "";
	
	LoginPage login;
	
	EmailTest mail;
	
	int emailok=0;
	int emailnum1;
	String emailnum="";

	public IdSuch(LoginPage login,DataDAO datadao) throws Exception {
		super("Find - ID");
		this.emailnum1=(int)(Math.random()*999999)+1;
		this.emailnum+=emailnum1;
		this.login=login;
		this.datadao=datadao;
		this.mail=new EmailTest("abcd@gmail.com","12345678");
		
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

		this.aid = new TextField();// 이름텍스트필드
		this.aid.setBounds(40, 128, 320, 25);
		this.aid.setText("가입시 작성한 이름 입력.");
		this.aid.setFont(new Font(null,Font.BOLD,18));
		this.aid.setForeground(new Color(255,255,255));
		this.aid.setBackground(new Color(209,225,196));
		this.aid.addMouseListener(this);
		
		
		this.idd = new JLabel("");
		this.idd.setBounds(40,177, 320, 25);
		this.idd.setFont(new Font(null, Font.BOLD, 15));
		this.idd.setForeground(new Color(255,0,0));
		
		// 이메일텍스트필드
		this.emai = new TextField("가입시 작성한 e-mail 입력.");
		this.emai.setBounds(40, 225, 320, 25);
		this.emai.setFont(new Font(null,Font.BOLD,18));
		this.emai.setForeground(new Color(255,255,255));
		this.emai.setBackground(new Color(209,225,196));
		this.emai.addMouseListener(this);
		
		
		//인증번호 발송부.
		this.no = new JButton("mailgo",new ImageIcon("photo/emailGO.jpg"));
		this.no.setBounds(36, 288, 330, 45);
		this.no.addActionListener(this);
		
		this.msg = new JLabel("");
		this.msg.setBounds(36, 335, 320, 25);
		this.msg.setFont(new Font(null, Font.BOLD, 15));
		this.msg.setForeground(new Color(255,0,0));
		
		
		
		// 인증번호 텍스트필드
		
		this.email = new TextField("인증번호 6자리");
		this.email.setBounds(50, 390, 140, 25);
		this.email.setFont(new Font(null,Font.BOLD,18));
		this.email.setForeground(new Color(255,255,255));
		this.email.setBackground(new Color(209,225,196));
		this.email.addMouseListener(this);
		

		this.nummsg=new JLabel();
		this.nummsg.setBounds(50, 415, 150, 25);
		this.nummsg.setFont(new Font(null, Font.BOLD, 15));
		this.nummsg.setForeground(new Color(255,0,0));
		
		
		
		this.check=new JButton("check",new ImageIcon("photo/emailgogo.jpg"));
		this.check.setBounds(220, 365, 150, 80);
		this.check.addActionListener(this);
		
		this.go = new JButton("ok",new ImageIcon("photo/FindIdOK.jpg"));// 찾기버튼
		this.go.setBounds(35, 450, 260, 40);
		this.go.addActionListener(this);

		this.back=new JButton("back",new ImageIcon("photo/findBack.jpg"));
		this.back.setBounds(300, 450, 70, 40);
		this.back.addActionListener(this);

		this.backimg=new JLabel(new ImageIcon("photo/FindIDback.jpg"));
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
	public void actionPerformed(ActionEvent e) {
		// 인증번호와 확인 하는 부분 필요.
		// 인증번호 보내기 버튼 보내면 메일 보내는 부분 필요.
		//문자열이 입력되어있을때, 땅겨 오면 문제가 생긴다. 그렇다는건. 
		
		if(e.getActionCommand().equals("check")){
			if(this.emailnum.equals(this.email.getText())){
				this.nummsg.setText("인증완료 되었습니다.");
				this.emailok=1;
			}
			else{
				this.nummsg.setText("인증번호 불일치.");
				return;
			}
			
		}
		else if(e.getActionCommand().equals("back")){
			this.login.setVisible(true);
			this.dispose();
		}
		else if (e.getActionCommand().equals("mailgo")) {
			//이메일 발송 버튼 눌렀을시.
			if(this.emai.getText().equals("가입시 작성한 e-mail 입력.")){
				this.msg.setText("이메일을 입력하셔야 전송이 가능 합니다.");
				return;
			}
			EmailTest mailgo =	new EmailTest("sirupe89@gmail.com","xo1004tks"); 
			try {
				mailgo.sendMail("JangGi-Game 인증번호 입니다.", "인증번호 : "+this.emailnum+" 6자리를 입력후 인증번호 확인버튼을 눌러주세요."
						, "imcts@nate.com", this.emai.getText());
				this.msg.setText("인증메일이 발송 되었습니다.");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		else if (e.getActionCommand().equals("ok")) {			
			if (this.emailok == 1) { // 메일 부분이 ok인증이 떨어졌다면 !
				String arr[] = { this.aid.getText(), this.emai.getText() };
				boolean res;

				try {
					res = this.datadao.nameSerch(arr,0); // false 라면 아이디가 없음.
														// true라면?
					if (res == false) {
						this.idd.setText("이름 또는 메일정보가 일치하지 않습니다.");
						return;
					} else {
						new GetId(this.login.id, this.login,0);
						this.dispose();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				this.msg.setText("이메일 인증이 완료되지 않았습니다.");
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (e.getSource().equals(this.aid)) {
			this.aid.setText("");
			if(this.emai.getText().equals("")){
				this.emai.setText("가입시 작성한 e-mail 입력.");
			}
			if(this.email.getText().equals("")){
				this.email.setText("인증번호 6자리");
			}
		}

		if (e.getSource().equals(this.emai)) {
			this.emai.setText("");
			if(this.aid.getText().equals("")){
				this.aid.setText("가입시 작성한 이름 입력.");
			}
			if(this.email.getText().equals("")){
				this.email.setText("인증번호 6자리");
			}

		}
		if (e.getSource().equals(this.email)) {
			email.setText("");
			if(this.aid.getText().equals("")){
				this.aid.setText("가입시 작성한 이름 입력.");
			}
			if(this.emai.getText().equals("")){
				this.emai.setText("가입시 작성한 e-mail 입력.");
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

	}
	
}
