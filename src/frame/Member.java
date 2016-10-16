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

	TextField id, // 아이디
			name, // 이름
			nickname, // 닉넴
			password, // 비번
			pw2, // 비밀번호확인
			tell2, // 연락처
			mail, // 메일 주소
			mail2; // 인증번호

	JButton go, // 가입완료 버튼
			gender, // 남자 버튼
			gender2, // 여자 버튼
			emailbt,
			emailgo,
			back; // 메일인증 버튼

	Label lb, // 아이디 체크 라벨
			lb2, // 닉네임 체크 라벨
			lb3, // 비번 일치 체크
			lb4, lb5; // 이름

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

		// ////////////////////아이디///////////////////////////

		this.id = new TextField("아이디를 입력하세요.");

		this.id.setBounds(45, 135, 250, 35);
		this.id.setFont(new Font(null, Font.BOLD, 19));
		this.id.addActionListener(this);
		this.id.addMouseListener(this);

		this.lb = new Label();
		this.lb.setBounds(310, 135, 200, 35);
		this.lb.setBackground(new Color(255, 255, 255));
		this.lb.setFont(new Font(null, Font.BOLD, 15));

		// //////////////////비밀번호//////////////////

		this.password = new TextField("비밀번호를 입력해주세요.");
		this.password.setBounds(45, 195, 250, 35);
		this.password.setFont(new Font(null, Font.BOLD, 19));
		this.password.setBackground(new Color(255, 255, 255));
		this.password.addMouseListener(this);
		this.lb5 = new Label("");
		this.lb5.setBounds(310, 195, 170, 35);
		this.lb5.setBackground(new Color(255, 255, 255));
		this.lb5.setFont(new Font(null, Font.BOLD, 15));

		this.pw2 = new TextField("비밀번호 확인입니다.");
		this.pw2.setBounds(45, 255, 250, 35);
		this.pw2.setFont(new Font(null, Font.BOLD, 19));
		this.pw2.setBackground(new Color(255, 255, 255));
		this.pw2.addMouseListener(this);

		this.lb3 = new Label("");
		this.lb3.setBounds(310, 255, 170, 30);
		this.lb3.setBackground(new Color(255, 255, 255));
		this.lb3.setFont(new Font(null, Font.BOLD, 15));

		// //////////////이름//////////////////////////////

		this.name = new TextField("이름을 입력하세요.");
		this.name.setBounds(45, 320, 250, 35);
		this.name.setFont(new Font(null, Font.BOLD, 19));
		this.name.setBackground(new Color(255, 255, 255));
		this.name.addMouseListener(this);

		this.lb4 = new Label("");
		this.lb4.setBounds(310, 320, 200, 30);
		this.lb4.setBackground(new Color(255, 255, 255));
		this.lb4.setFont(new Font(null, Font.BOLD, 15));

		// ///////////////////닉네임/////////////////////

		this.nickname = new TextField("닉네임을 입력하세요.");
		this.nickname.setBounds(45, 370, 250, 35);
		this.nickname.setFont(new Font(null, Font.BOLD, 19));
		this.nickname.setBackground(new Color(255, 255, 255));
		this.nickname.addMouseListener(this);

		this.lb2 = new Label();
		this.lb2.setBounds(310, 370, 200, 30);
		this.lb2.setBackground(new Color(255, 255, 255));
		this.lb2.setFont(new Font(null, Font.BOLD, 15));
		
		// ///////////성별/////////////////
		this.gen=new JLabel("성 별");
		this.gen.setBounds(45, 410, 100, 50);
		this.gen.setBackground(new Color(255,255,255));
		this.gen.setForeground(new Color(198,198,198));
		this.gen.setFont(new Font(null, Font.BOLD, 17));
		
		this.genlb=new JLabel();
		this.genlb.setBounds(100, 410, 150, 50);
		this.genlb.setFont(new Font(null, Font.BOLD, 17));
		this.genlb.setBackground(new Color(255,255,255));
		
		
		this.gender = new JButton("남자",new ImageIcon("photo/manBasic.jpg"));
		this.gender.setBounds(299, 415, 100, 40);
		this.gender.addMouseListener(this);
		this.gender.addActionListener(this);
		
		this.gender2 = new JButton("여자",new ImageIcon("photo/girlBasic.jpg"));
		this.gender2.setBounds(400, 415, 100, 40);
		this.gender2.addActionListener(this);
		this.gender2.addMouseListener(this);

		
		// ////////////////////연락처//////////////////

		this.tell2 = new TextField("휴대폰번호를 입력하세요.");
		this.tell2.setBounds(45, 475, 250, 35);
		this.tell2.setFont(new Font(null, Font.BOLD, 19));
		this.tell2.setBackground(new Color(255, 255, 255));
		this.tell2.addMouseListener(this);
		
		this.tellb=new  JLabel();
		this.tellb.setBounds(310, 475, 200, 30);
		this.tellb.setBackground(new Color(255, 255, 255));
		this.tellb.setFont(new Font(null, Font.BOLD, 15));
		
		// 메일입력 /////////////////////////////////


		this.mail = new TextField("이메일을 입력해주세요.");
		this.mail.setBounds(45, 525, 250, 35);
		this.mail.setFont(new Font(null, Font.BOLD, 19));
		this.mail.setBackground(new Color(255, 255, 255));
		this.mail.addMouseListener(this);
		
		this.maillb=new JLabel();
		this.maillb.setBounds(310, 525, 200, 30);
		this.maillb.setBackground(new Color(255, 255, 255));
		this.maillb.setFont(new Font(null, Font.BOLD, 15));
		
		
		
		// 메일인증 /////////////////////////////////

		this.mail2 = new TextField("인증번호");
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
		
		//////////////////메일 보내기 /////////////////
		
		this.emailgo=new JButton("emailgo",new ImageIcon("photo/EmailgoBysign.jpg"));
		this.emailgo.addActionListener(this);
		this.emailgo.setBounds(32, 622, 483, 49);
		
		
		
		// /////////////////가입완료버튼/////////////////

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
			res=	this.formCheck();//여기에서 미입력사항이 있다면 절대로 안넘어가지.
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
					this.emaillb.setText("Error : 관리자문의.");
				}else{
					
					new GetId(this.login, 0);
					this.dispose();
				}
			}
		}
		
		if(e.getActionCommand().equals("emailgo")){
			if(mail.getText().equals("")){
				this.emaillb.setForeground(new Color(255,0,0));
				this.emaillb.setText("메일을 입력해주세요.");
				return;
			}else{
			EmailTest mailgo =	new EmailTest("sirupe89@gmail.com","xo1004tks"); 
			try {
				mailgo.sendMail("JangGi-Game 회원가입 인증번호 입니다.", "인증번호 : "+this.mailnum+" 6자리를 입력후 인증번호 확인버튼을 눌러주세요."
						, "imcts@nate.com", this.mail.getText());
				System.out.println(mail.getText());
				this.emaillb.setForeground(new Color(255,0,0));
				this.emaillb.setText("인증 메일 발송.");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			}
		}
		if(e.getActionCommand().equals("남자")){
			this.rgender="남자";
			this.gender.setIcon(new ImageIcon("photo/manChoice.jpg"));
			this.gender2.setIcon(new ImageIcon("photo/girlBasic.jpg"));
			this.genderindex=1;
			this.genChoice=1;
		}
		if(e.getActionCommand().equals("여자")){
			this.rgender="여자";
			this.gender.setIcon(new ImageIcon("photo/manBasic.jpg"));
			this.gender2.setIcon(new ImageIcon("photo/girlChoice.jpg"));
			this.genderindex=1;
			this.genChoice=1;
		}
		if(e.getActionCommand().equals("checknum")){
			if(this.mailnum.equals(mail2.getText())){
				this.emaillb.setForeground(new Color(146, 169, 109));
				this.emaillb.setText("인증 되었습니다.");
				this.loginindex=1;
			}else{
				this.emaillb.setForeground(new Color(255, 0, 0));
				this.emaillb.setText("인증번호 불일치.");
				this.loginindex=0;
			}
		}
		
	}

	private int formCheck() {
		int res=1;
		
		if(this.loginindex==0){
			this.emaillb.setForeground(new Color(255, 0, 0));
			this.emaillb.setText("인증되지 않았습니다.");
			res=0;
		}
		if(this.idindex==0){
			lb.setForeground(new Color(255, 0, 0));
			lb.setText("필수입력 입니다.");
			res=0;
		}
		if(this.passwordindex==0){
			this.lb3.setForeground(new Color(255, 0, 0));
			this.lb3.setText("필수입력 입니다.");
			this.lb5.setForeground(new Color(255, 0, 0));
			this.lb5.setText("필수입력 입니다.");
			res=0;
		}
		if(this.genderindex==0){
			this.genlb.setForeground(new Color(255, 0, 0));
			this.genlb.setText("성별을 선택하세요.");
			res=0;
		}
		else {
			this.genlb.setText("");
			this.genChoice=1;
		}
		if(this.nicknameindex==0){
			this.lb2.setForeground(new Color(255, 0, 0));
			this.lb2.setText("필수입력 입니다.");
			res=0;
		}
		if(this.emailindex==0){
			this.maillb.setForeground(new Color(255, 0, 0));
			this.maillb.setText("필수입력 입니다.");
			res=0;
		}
		if(this.nameindex==0){
			lb4.setForeground(new Color(255, 0, 0));
			lb4.setText("필수입력 입니다.");
			res=0;
		}
		if(this.tellindex==0){
			 tellb.setForeground(new Color(255, 0, 0));
			 tellb.setText("필수입력 입니다.");
			 res=0;
		}
		return res;
	}


	@Override
	public void mouseReleased(MouseEvent e) {

		// 각 텍스트 필드 마우스 클릭시 빈칸으로

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
		// ///////실시간 검사 라벨 붙인거
		while (true) {
			if(this.threadStop==1){
				break;
			}
			if(this.genChoice==1){
				this.genlb.setText("");
			}
			if (!(id.getText().equals("아이디를 입력하세요."))) {
				Pattern p = Pattern.compile("[a-zA-Z0-9]{5,8}");
				Matcher m = p.matcher(id.getText());
				Iterator it = this.idList.iterator();
				
				if(idList.size()==0){
					idc=1;
				}
				
				while(it.hasNext()){
					if(((String)it.next()).equals(this.id.getText())){
						lb.setForeground(new Color(255, 0, 0));
						lb.setText("사용중인 아이디입니다.");
						 this.idindex=0;
						this. idc=0;
						 break;
					}else{
						this.idc=1;
					}
				}
						
			
				if (m.matches() == false) {
					lb.setForeground(new Color(255, 0, 0));
					lb.setText("아이디는 5-8글자입력가능.");
					 this.idindex=0;
				} 
			
				if(m.matches() && this.idc==1){
					lb.setForeground(new Color(146, 169, 109));
				    lb.setText("사용 가능한 아이디입니다.");
				    this.rid=this.id.getText();
				    this.idindex=1;
				}
			}

			
			 if (!(password.getText().equals("비밀번호를 입력해주세요."))) {// 여기는 비밀번호를 입력하기 시작했을때
				Pattern p = Pattern.compile("[가-힇a-zA-Z0-9]{5,20}");
				Matcher m = p.matcher(password.getText());
				if (m.matches() == false) {
					this.lb5.setForeground(new Color(255, 0, 0));
					this.lb5.setText("비밀번호를 입력하세요.");
					
				}else{
					this.lb5.setForeground(new Color(146, 169, 109));
					this.lb5.setText("안전한 비밀번호 입니다.");
					
				}
			}

			 if (!(pw2.getText().equals("비밀번호 확인입니다."))) {// 여기는 비밀번호를 입력하기 시작했을때
					if (!(this.password.getText().equals(pw2.getText()))) {
						this.lb3.setForeground(new Color(255, 0, 0));
						this.lb3.setText("일치하지 않습니다.");
						this.passwordindex=0;
					}
					if(this.password.getText().equals(pw2.getText())){ //두개가 같으면 
						this.lb3.setForeground(new Color(146, 169, 109));
						this.lb3.setText("비밀번호가 일치합니다.");
						 this.rpassword=this.pw2.getText();
						this.passwordindex=1;
					}
				}

			 if (!(name.getText().equals("이름을 입력하세요."))) {
				Pattern p = Pattern.compile("[가-힇]{1,10}");
				Matcher m = p.matcher(name.getText());
		
				
				if (m.matches() == false) {
					lb4.setForeground(new Color(255, 0, 0));
					lb4.setText("이름을 입력해주세요.");
					this.nameindex=0;
				}
				else{
					lb4.setForeground(new Color(146, 169, 109));
					lb4.setText("좋은이름 이시네요.");
					this.rname=name.getText();
					this.nameindex=1;
				}
			}
			 
			 if (!(nickname.getText().equals("닉네임을 입력하세요."))) {
				Pattern p = Pattern.compile("[가-힇a-zA-Z0-9]{3,9}");
				Matcher m = p.matcher(nickname.getText());
				Iterator it = this.nickList.iterator();
				
				if(nickList.size()==0){
					this.nicknamec=1;
				}
				
				while(it.hasNext()){
					if(((String)it.next()).equals(this.nickname.getText())){
						lb2.setForeground(new Color(255, 0, 0));
						lb2.setText("사용중인 닉네임 입니다.");
						this.nicknameindex=0;
						this.nicknamec=0;
						break;
					}else{
						this.nicknamec=1;
					}
				}
				
				
				if (m.matches() == false) {
					lb2.setForeground(new Color(255, 0, 0));
					lb2.setText("닉네임을 입력해주세요.");
					this.nicknameindex=0;
				} 
				if(m.matches() && this.nicknamec==1){
						lb2.setForeground(new Color(146, 169, 109));
						lb2.setText("사용 가능한 닉네임입니다.");
						this.rnickname=this.nickname.getText();
						this.nicknameindex=1;
				}
			}
			 
			 
			 if(!(tell2.getText().equals("휴대폰번호를 입력하세요."))){
				 Pattern p = Pattern.compile("[0-9]{3}-[0-9]{4}-[0-9]{4}");
				 Matcher m = p.matcher(tell2.getText());
				 Iterator it = tellList.iterator();
				 
				 if(tellList.size()==0){
					 this.tellc=1;
				 }
				 
				 while(it.hasNext()){
					 
					 if(((String)it.next()).equals(this.tell2.getText())){
						 tellb.setForeground(new Color(255, 0, 0));
						 tellb.setText("사용중인 연락처 입니다.");
						 this.tellindex=0;
						 this.tellc=0;
						 break;
					 }else{
						 this.tellc=1;
					 }
				 }
				 
				 if(m.matches()==false){
					 tellb.setForeground(new Color(255, 0, 0));
					 tellb.setText("연락처를 입력해주세요.");
					 this.tellindex=0;
				 }
				 if(m.matches() && this.tellc==1){
					 tellb.setForeground(new Color(146, 169, 109));
					 tellb.setText("바르게 입력하셨습니다.");
					 this.rtell=this.tell2.getText();
					 this.tellindex=1;
				 }
			 }
			 if(!(mail.getText().equals("이메일을 입력해주세요."))){
				 Pattern p = Pattern.compile("[a-zA-Z0-9]*@[a-zA-Z.]*.\\.[a-zA-Z]*.");
				 Matcher m = p.matcher(mail.getText());
				 Iterator it = this.emailList.iterator();
				 
				 if(emailList.size()==0){
					 this.emailc=1;
				 }
				 
				 while(it.hasNext()){
					 if(((String)it.next()).equals(this.mail.getText())){
						 maillb.setForeground(new Color(255, 0, 0));
						 maillb.setText("사용중인 e-mail입니다.");
						 this.emailindex=0;
						 this.emailc=0;
						 break;
					 }else{
						 this.emailc=1;
					 }
				 }
				 
				 if(m.matches()==false){
					 maillb.setForeground(new Color(255, 0, 0));
					 maillb.setText("e-mail 양식이 아닙니다.");
					 this.emailindex=0;
				 }
				 if(m.matches() && this.emailc==1){
					 maillb.setForeground(new Color(146, 169, 109));
					 maillb.setText("바르게 입력하셨습니다.");
					 this.remail=this.mail.getText();
					 this.emailindex=1;
				 }
			 }
			 
			


		}// while 종료
	}// run 종료

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
