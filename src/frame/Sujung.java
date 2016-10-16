package frame;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Su extends JFrame implements ActionListener,MouseListener{
   Container con;
   JPanel main;
   JLabel id;///���̵�
   JLabel idd;///���̵�ǥ��
   JLabel msg;//��й�ȣȮ��
   
   ImageIcon image;
   JLabel lb;
   DataDAO dataDao;
   User user;
   StayR stayRoom;
   
   TextField pw;//��й�ȣ �Է�
   
   JButton ok;//Ȯ��   
   Su1 su1;
   public Su(User user,StayR stayRoom) throws Exception {
      super("info Change-");
      this.con=getContentPane();
       setSize(380,300);
         Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
         int sx=(int)(screen.getWidth()/2 - super.getWidth()/2);
         int sy=(int)(screen.getHeight()/2 - super.getHeight()/2);
         setLocation(sx,sy);
         setLayout(null);
         
         this.stayRoom=stayRoom;
         this.user=user;
                  
         this.dataDao=new DataDAO();
         
         this.main=new JPanel();
         this.main.setBounds(0, 0, 380, 400);
         this.main.setBackground(new Color(5,255,255));
         this.main.setLayout(null);
        
         
         this.image=new ImageIcon("photo/susu.jpg");
         this.lb=new JLabel(image);
         this.lb.setBounds(-20,0, 567, 444);
        
  
         
         
         this.pw=new TextField("��й�ȣ�� �Է��ϼ���.");
         this.pw.setBounds(60, 120, 240, 30);
         this.pw.setFont(new Font(null, Font.BOLD, 20));
         this.pw.addMouseListener(this);
         
         this.msg =new JLabel();
         this.msg.setBounds(60, 150, 300, 25);
         this.msg.setForeground(new Color(255,0,0));
         this.msg.setFont(new Font(null, Font.BOLD, 15));
         
         
         
         this.ok=new JButton("ok",new ImageIcon("photo/infoSetOk.jpg"));
         this.ok.setBounds(130,180, 90, 40);
         this.ok.addActionListener(this);
         
         
         this.main.add(pw);
         this.main.add(ok);
         this.main.add(msg);
         this.main.add(lb);
         
         
         this.con.add(main);
         this.setVisible(true);
         
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource().equals(ok))
      {
    	  boolean res=false;
    	  String [] arr = {this.user.id, this.pw.getText()};
    	  if(this.pw.getText().equals("") || this.pw.getText().equals("��й�ȣ�� �Է��ϼ���.")){
    		  this.msg.setText("��й�ȣ�� �Է����ּ���.");
    	  }else{
    		  
    		  try {
			res =	this.dataDao.deleteidSerch(arr);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
    		  if(res==false){
    			  this.msg.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
    		  }else{
    			  try {
					new Su1(this.user,this.stayRoom);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			  this.dispose();
    		  }
    	  }
    	  
    		  
       
      }
   }
   @Override
   public void mouseClicked(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }@Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }@Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }@Override
   public void mousePressed(MouseEvent e) {
      if(e.getSource().equals(pw))
      {
         if(pw.getText().equals("��й�ȣ�� �Է��ϼ���."))
         {
            pw.setText("");
            pw.setEchoChar('*');
         }
      }
      
   }@Override
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }
   
   
}
















/////////////////////////////////////////////////////////////////////////////////



class Su1 extends JFrame implements ActionListener,MouseListener,Runnable{
    Container con;
    JPanel main;
    
    JLabel m;//
    ImageIcon image,image1,image2,image3;

    JLabel msg1,pwlb,pwwlb,tellb,emaillb;//������ȣ ����ġ
 
    TextField //�������
     pww,//�����Һ�й�ȣ
     pww1,//�ٽ��Է�
     tel,//��ȭ��ȣ����
     email,//�̸��� ����
     injeung;//������ȣ �Է�ĭ
    
    User user;
    StayR stayRoom;
    
    DataDAO dataDao;
    JButton email_in;//�̸��� ������ȣ �߼� ��ư
    
    JButton in;//�̸��� ������ȣ
    JButton in1;//������ȣ Ȯ��
    
    JButton su;//������ư
    JButton back;
    
    
    int oktell=0, okemail=0,okinjung=0,okpw=0;
    int random=(int)(Math.random()*999999)+1;
    
    public Su1(User user, StayR stayRoom) throws Exception {
       super();
       this.user=user;
       this.stayRoom=stayRoom;
       
       this.dataDao=new DataDAO();
       this.con=getContentPane();
        setSize(460,680);
       this.main=new JPanel();
       
          Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
          int sx=(int)(screen.getWidth()/2 - super.getWidth()/2);
          int sy=(int)(screen.getHeight()/2 - super.getHeight()/2);
          setLocation(sx,sy);
          setLayout(null);
                   
          this.main=new JPanel();
          this.main.setBounds(0, 0, 480, 700);
          this.main.setBackground(new Color(5,255,255));
          this.main.setLayout(null);
          
       
          this.m=new JLabel( new ImageIcon("photo/123.jpg"));
          this.m.setBounds(-30, 0, 767, 800);
      
          
          this.pww=new TextField("���ο� ��й�ȣ�� �Է��ϼ���");
          this.pww.setBounds(54, 175, 343, 35);
          this.pww.setFont(new Font(null, Font.BOLD, 20));
          this.pww.addMouseListener(this);
          
          this.pwlb=new JLabel("������ ��ġ �����ø�, ������ ������ �Է����ּ���.");
          this.pwlb.setBounds(54, 215, 400, 20);
          this.pwlb.setFont(new Font(null,Font.BOLD,15));
          this.pwlb.setForeground(new Color(146, 169, 109));
         

          this.pww1=new TextField("�ѹ� �� �Է��ϼ���");
          this.pww1.setBounds(54, 245,343, 35);
          this.pww1.setFont(new Font(null, Font.BOLD, 20));
          this.pww1.addMouseListener(this);
          this.pwwlb=new JLabel();
          this.pwwlb.setBounds(54, 285, 400, 20);
          this.pwwlb.setFont(new Font(null,Font.BOLD,15));
          this.pwwlb.setForeground(new Color(255,0,0));
          
          
          this.tel=new TextField("        ���ο� ��ȭ��ȣ �Է�");
          this.tel.setBounds(54, 370, 343, 30);
          this.tel.setFont(new Font(null, Font.BOLD, 20));
          this.tel.addMouseListener(this);
       
          this.tellb=new JLabel("010-0000-1111�� �������� �Է����ּ���.");
          this.tellb.setBounds(54, 400, 400, 20);
          this.tellb.setFont(new Font(null,Font.BOLD,15));
          this.tellb.setForeground(new Color(146, 169, 109));
          
          this.email=new TextField("      ���ο� E-mail �Է��ϱ� ");
          this.email.setBounds(54, 420, 343, 30);
          this.email.setFont(new Font(null, Font.BOLD, 20));
          this.email.addMouseListener(this);
       
          this.emaillb=new JLabel();
          this.emaillb.setBounds(54, 450, 400, 20);
          this.emaillb.setFont(new Font(null,Font.BOLD,15));
          this.emaillb.setForeground(new Color(255, 0, 0));
          
          this.email_in=new JButton("emailgo",new ImageIcon("photo/emailggo.jpg"));
          this.email_in.setBounds(54, 472 ,343, 35);
          this.email_in.addActionListener(this);
          
          
          this.injeung=new TextField("������ȣ");
          this.injeung.setBounds(54, 510, 185, 35);
          this.injeung.setFont(new Font(null, Font.BOLD, 22));
          this.injeung.addMouseListener(this);
          
          
       
          this.in1=new JButton("injung",new ImageIcon("photo/33.jpg"));
          this.in1.setBounds(243, 510, 154,38);
          this.in1.addActionListener(this);
      
          
        
          this.in=new JButton("edit",new ImageIcon("photo/su.jpg"));
          this.in.setBounds(80, 570, 230, 40);
          this.in.addActionListener(this);
          
          this.back=new JButton(" ",new ImageIcon("photo/backs.jpg"));
          this.back.setBounds(330, 570, 50, 40);
          this.back.addActionListener(this);
          
          this.msg1 =new JLabel();
          this.msg1.setBounds(54, 540, 220, 25);
          this.msg1.setForeground(new Color(255,0,0));
          this.msg1.setFont(new Font(null,Font.BOLD ,15));
          
          
          
          
          
          
          
          this.main.add(emaillb);
          this.main.add(tellb);
          this.main.add(pwwlb);
          this.main.add(pwlb);
          this.main.add(back);
          this.main.add(injeung);
          this.main.add(in);
          this.main.add(email_in);
          this.main.add(in1);
          this.main.add(pww);
          this.main.add(pww1);
          this.main.add(tel);
          this.main.add(email);
          this.main.add(msg1);
          this.main.add(m);
          
          
          
          this.con.add(main);
          this.setVisible(true);
          
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
    	if(e.getActionCommand().equals(" ")){
    	   this.stayRoom.setVisible(true);
    	   this.dispose();
       }
       
       
       else if(e.getActionCommand().equals("edit")){
    	   if(this.pww.getText().equals(this.pww1.getText()) && !(this.pww.getText().equals("")) && !(this.pww1.getText().equals(""))){//�ΰ��� ����� ��ġ�ߴٸ�, 
    		 this.okpw=1;//��� ���� �Ϸ�
    		 this.pwwlb.setForeground(new Color(146, 169, 109));
    		 this.pwwlb.setText("�ΰ��� ��й�ȣ�� ��ġ �մϴ�.");
    		
    	   }
    	   else if(this.pww.getText().equals("") || this.pww1.getText().equals("")){
    		   this.pwwlb.setForeground(new Color(255,0,0));
     		  this.pwwlb.setText("��й�ȣ�� �Է����ּ���.");
     		  this.okpw=0;
     		  return;
    	   }
    	   else{
    		  this.pwwlb.setForeground(new Color(255,0,0));
    		  this.pwwlb.setText("�Է��Ͻ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
    		  this.okpw=0;
    		  return;
    	   }
    	   

    	   
   
    	   
    	   
    	   Pattern p1 = Pattern.compile("[0-9]{3}-[0-9]{4}-[0-9]{4}");
		   Matcher m1 = p1.matcher(tel.getText());
		   
		   if(m1.matches()==false){
			   this.tellb.setForeground(new Color(255,0,0));
			   this.tellb.setText("010-0000-1111�� �������� �Է����ּ���.");
			   this.oktell=0;
			   return;
			   
		   }
		   
		   else if(tel.getText().equals("") || tel.getText().equals("        ���ο� ��ȭ��ȣ �Է�")){
			   this.tellb.setForeground(new Color(255,0,0));
			   this.tellb.setText("����ó�� �Է����ּ���.");
			   this.oktell=0;
			   return;
		   }
		   else {
			   this.tellb.setForeground(new Color(146, 169, 109));
			   this.tellb.setText("��Ŀ� �°� �Է��ϼ̽��ϴ�.");
			   this.oktell=1;
		   }
		   
		   
	 	   Pattern p = Pattern.compile("[a-zA-Z0-9]*@[a-zA-Z.]*.\\.[a-zA-Z]*.");
    	   Matcher m = p.matcher(email.getText());
    	  
    	   if(m.matches()==false){
    		   this.emaillb.setText("�̸��� ����� ��ġ���� �ʽ��ϴ�.");
    		   this.okemail=1;
    		   return;
    	   }
    	   
    	   else if(email.getText().equals("") || email.getText().equals("      ���ο� E-mail �Է��ϱ� ")){
    		   this.emaillb.setText("�̸����� �Է��ϼž� ������ ���۵˴ϴ�.");
    		   this.okemail=1;
    		   return;
		   }
    	   else {
    		   this.emaillb.setForeground(new Color(146, 169, 109));
    		   this.emaillb.setText("��Ŀ� �°� �Է��ϼ̽��ϴ�.");
    		   this.okemail=1;
    	   }
    	   
    	   
    	   if(this.okinjung!=1){
    		   this.msg1.setForeground(new Color(255, 0, 0));
    		   this.msg1.setText("�������� �����̽��ϴ�.");
    		   return;
    	   }
		   
    	  
    	   if(this.okemail==1 && this.okinjung==1 && this.okpw==1 && this.oktell==1){
    	
    		   int rs=-1;
    		   String set="password";
    		   try {
				rs = this.dataDao.dataEdit(this.user.id,set,this.pww.getText());
				if(rs==-1){
	    			   this.msg1.setText("�����ڿ��� �����ϼ���.");
	    			   return; 
	    		   }
				
				rs=-1;
				set="tell";
				rs=this.dataDao.dataEdit(this.user.id, set, this.tel.getText());
				
				if(rs==-1){
	    			   this.msg1.setText("�����ڿ��� �����ϼ���.");
	    			   return; 
	    		   }
				
				rs=-1;
				set="email";
				rs=this.dataDao.dataEdit(this.user.id, set, this.email.getText());
				
				if(rs==-1){
	    			   this.msg1.setText("�����ڿ��� �����ϼ���.");
	    			   return; 
	    		   }
	    		   else{
	    			   this.setVisible(false);
	    			   new GetId(9,this.stayRoom);
	    			   this.dispose();
	    		   }
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
    		   
    	   }
       }
       
       
       
       else if(e.getActionCommand().equals("emailgo")){
    	  
    	   Pattern p = Pattern.compile("[a-zA-Z0-9]*@[a-zA-Z.]*.\\.[a-zA-Z]*.");
    	   Matcher m = p.matcher(email.getText());
    	  
    	   if(m.matches()==false){
    		   this.emaillb.setForeground(new Color(255, 0, 0));
    		   this.emaillb.setText("�̸��� ����� ��ġ���� �ʽ��ϴ�.");
    		   return;
    	   }
    	   
    	   if(email.getText().equals("") || email.getText().equals("      ���ο� E-mail �Է��ϱ� ")){
    		   this.emaillb.setText("�̸����� �Է��ϼž� ������ ���۵˴ϴ�.");
		   }
    	   
		   else{
			this.okemail=1;
		    this.emaillb.setText("");
		   EmailTest mailgo =	new EmailTest("sirupe89@gmail.com","xo1004tks"); 
		   try {
			mailgo.sendMail("JangGi-Game �������� ������ȣ �Դϴ�.", "������ȣ : "+this.random+" 6�ڸ��� �Է��� ������ȣ Ȯ�ι�ư�� �����ּ���."
						, "imcts@nate.com", this.email.getText());
		   } catch (Exception e1) {
			e1.printStackTrace();
		}
		    this.emaillb.setForeground(new Color(146, 169, 109));
			this.emaillb.setText("���� ���� �߼�."); 
	   }
    	   
       }
       
       
       else if(e.getActionCommand().equals("injung")){
    	   if(this.injeung.getText().equals(this.random+"")){
    		   this.okinjung=1;
    		   this.msg1.setForeground(new Color(146, 169, 109));
    		   this.msg1.setText("���� ����.");
    	   }
    	   else{
    		   this.okinjung=0;
    		   this.msg1.setForeground(new Color(255, 0, 0));
    		   this.msg1.setText("������ȣ ����ġ.");
    	   }
    	   
       }
    
    }
    @Override
    public void mouseClicked(MouseEvent e) {
       // TODO Auto-generated method stub
       
    }@Override
    public void mouseEntered(MouseEvent e) {
       // TODO Auto-generated method stub
       
    }@Override
    public void mouseExited(MouseEvent e) {
       // TODO Auto-generated method stub
       
    }
    
    
    	
    	
    	
    
    
    @Override
    public void mousePressed(MouseEvent e) {
       if(e.getSource().equals(pww))
       { 	
             pww.setText("");
             this.pww.setEchoChar('*');
           
       }
       else if(e.getSource().equals(pww1))
       {
    	  
             pww1.setText("");
             this.pww1.setEchoChar('*');
       }
       else if(e.getSource().equals(tel))
       {	
             tel.setText("");
       }
       else if(e.getSource().equals(email))
       {
    	  
             email.setText("");
       }
       else if(e.getSource().equals(injeung))
       {
    	  
    	   injeung.setText("");
          
       }
       
    }@Override
    public void mouseReleased(MouseEvent e) {
       
       
    }
    @Override
    public void run() {
       
    }
 }






////////////////////////////////////////////////////////////////////////////////////////////////////////////////////








class Tal extends JFrame implements ActionListener{
   Container con;
   JPanel main;
   JLabel lb;//
   ImageIcon image;
   StayR stayRoom;
   JButton ok;//Ż�����
   JButton no;//���
   int exitok=0;
   
   public Tal(StayR stayRoom) {
      super();
      
      this.con=getContentPane();
      
       setSize(490,420);
         Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
         int sx=(int)(screen.getWidth()/2 - super.getWidth()/2);
         int sy=(int)(screen.getHeight()/2 - super.getHeight()/2);
         setLocation(sx,sy);
         setLayout(null);
        
         this.stayRoom=stayRoom;
                  
         this.main=new JPanel();
         this.main.setBounds(0, 0,520, 400);
       
         this.main.setBackground(new Color(5,255,255));
         this.main.setLayout(null);
         
         
         this.image=new ImageIcon("photo/bye.jpg");
         this.lb = new JLabel(image);
        this.lb.setBounds(-50, 0, 680, 550);
         
         this.ok=new JButton("yes",new ImageIcon("photo/talOk.jpg"));
         this.ok.setBounds(110,280, 110, 40);
         this.ok.setFont(new Font(null , Font.BOLD, 19));
         this.ok.addActionListener(this);
         
         this.no=new JButton("no",new ImageIcon("photo/backspace.jpg"));
         this.no.setBounds(280, 280, 55, 40);
         this.no.setFont(new Font(null , Font.BOLD, 19));
         this.no.addActionListener(this);
         
         
        
         this.main.add(no);
         this.main.add(ok);
         this.main.add(lb);
         
         this.con.add(main);
         this.setVisible(true);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
   }
   @Override
   public void actionPerformed(ActionEvent e) {
	   
      if(e.getSource().equals(ok))
      {
         this.setVisible(false);
         try {
        	
			new Tal1(stayRoom);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
         
      }else if(e.getSource().equals(no))
      {
        	this.stayRoom.setVisible(true);
        	this.dispose();
      }
      
   }
   
}




class Tal1 extends JFrame implements ActionListener,MouseListener{
   Container con;
   JPanel main;
   
   TextField id;
   TextField pw;
   TextField email;//�̸����Է�
   TextField email_in;//������ȣ �Է�

   JLabel idlb, pwlb, emaillb, injuenglb;
   
   JLabel lb;
   
   StayR stayRoom;
   
   JButton in,//������ȣ ��
   in1,//������ȣ Ȯ��
    go,back;//Ż���ϱ�
   DataDAO dataDao;
   
   ImageIcon image;
   int okid=0, okpw=0, okemail=0, okinjung=0;
   int random=(int)(Math.random()*999999)+1;
   
   public Tal1(StayR stayRoom) throws Exception {
 	 super("Member exit-");
      this.con=getContentPane();
      this.main=new JPanel();
       setSize(470,500);
         Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
         int sx=(int)(screen.getWidth()/2 - super.getWidth()/2);
         int sy=(int)(screen.getHeight()/2 - super.getHeight()/2);
         setLocation(sx,sy);
         setLayout(null);
         
         this.stayRoom=stayRoom;
                  
         this.dataDao=new DataDAO();
         
         this.main=new JPanel();
         this.main.setBounds(0, 0, 470, 500);
         this.main.setBackground(new Color(5,255,255));
         this.main.setLayout(null);
         
         this.image= new ImageIcon("photo/ttc.jpg");
         
         this.lb = new JLabel(image);
         
         this.lb.setBounds(-25,-20, 625, 600);
         
         this.id= new TextField("���̵� �Է��ϼ���");
         this.id.setBounds(50, 100, 345, 30);
         this.id.setFont(new Font(null , Font.BOLD, 19));
         this.id.addActionListener(this);
         this.id.addMouseListener(this);
         
         this.idlb=new JLabel("");
         this.idlb.setBackground(new Color(255,255,255));
         this.idlb.setForeground(new Color(255,0,0));
         this.idlb.setBounds(50, 137, 350, 15);
         this.idlb.setFont(new Font(null,Font.BOLD,15));
         this.main.add(idlb);
         
         
         
         this.pw = new TextField("��й�ȣ�� �Է��ϼ���");
         this.pw.setBounds(50, 160, 345, 28);
         this.pw.setFont(new Font(null , Font.BOLD, 19));
         this.pw.addActionListener(this);
         this.pw.addMouseListener(this);
         
         
         
         this.pwlb=new JLabel("");
         this.pwlb.setBackground(new Color(255,255,255));
         this.pwlb.setForeground(new Color(255,0,0));
         this.pwlb.setBounds(50, 197, 350, 15);
         this.pwlb.setFont(new Font(null,Font.BOLD,15));
         this.main.add(pwlb);
         
         
         
         
         this.email =new TextField("�̸����� �Է��ϼ���");
         this.email.setBounds(50,220, 345, 28);
         this.email.setFont(new Font(null , Font.BOLD, 19));
         this.email.addActionListener(this);
         this.email.addMouseListener(this); 
         
         
         this.emaillb=new JLabel("");
         this.emaillb.setBackground(new Color(255,255,255));
         this.emaillb.setForeground(new Color(255,0,0));
         this.emaillb.setBounds(50, 255, 350, 15);
         this.emaillb.setFont(new Font(null,Font.BOLD,15));
         this.main.add(emaillb); 
         
         
         
         
         this.in1=new JButton("email",new ImageIcon("photo/emailGOO.jpg"));
         this.in1.setBounds(50, 275, 343, 40);
         this.in1.addActionListener(this);
         
         
         this.email_in = new TextField("������ȣ �Է�");
         this.email_in.setBounds(50,320, 190, 28);
         this.email_in.setFont(new Font(null , Font.BOLD, 19));
         this.email_in.addActionListener(this);
         this.email_in.addMouseListener(this);
         
         this.injuenglb=new JLabel("");
         this.injuenglb.setBackground(new Color(255,255,255));
         this.injuenglb.setForeground(new Color(255,0,0));
         this.injuenglb.setBounds(50, 350, 350, 15);
         this.injuenglb.setFont(new Font(null,Font.BOLD,15));
         this.main.add(injuenglb);
         
         
         
         this.in=new JButton("injung",new ImageIcon("photo/ingng.jpg"));
         this.in.setBounds(242,320, 150, 40);
         this.in.addActionListener(this);
         
         this.go= new JButton("go",new ImageIcon("photo/taltae.jpg"));
         this.go.setBounds(100, 375, 120, 50);
         this.go.addActionListener(this);
         
         this.back=new JButton("back",new ImageIcon("photo/Talback.jpg"));
         this.back.setBounds(250, 375, 80, 50);
         this.back.addActionListener(this);
         
         this.main.add(back);
         this.main.add(id);
         this.main.add(pw);
         this.main.add(email);
         this.main.add(email_in);
         this.main.add(in);
         this.main.add(in1);
         this.main.add(go);
        
         this.main.add(lb);
         
         this.con.add(main);
         this.setVisible(true);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   @Override
   public void actionPerformed(ActionEvent e) {

	  
	   
	 if(e.getActionCommand().equals("email")){
		 
			Pattern p = Pattern.compile("[a-zA-Z0-9]*@[a-zA-Z.]*.\\.[a-zA-Z]*.");
			Matcher m = p.matcher(email.getText());

			if (m.matches() == false) {
				this.emaillb.setForeground(new Color(255, 0, 0));
				this.emaillb.setText("�̸��� ����� ��ġ���� �ʽ��ϴ�.");
				this.okemail=0;
				return;
			}
		   
		   if(email.getText().equals("") || email.getText().equals("�̸����� �Է��ϼ���")){
			   this.emaillb.setForeground(new Color(255,0,0));
			   this.emaillb.setText("�̸����� �Է��ϼ���.");
			   this.okemail=0;
		   }
		   else{
			   this.okemail=1;
			   this.emaillb.setText("");
		   EmailTest mailgo =	new EmailTest("sirupe89@gmail.com","xo1004tks"); 
		   try {
			mailgo.sendMail("JangGi-Game ȸ��Ż�� ������ȣ �Դϴ�.", "������ȣ : "+this.random+" 6�ڸ��� �Է��� ������ȣ Ȯ�ι�ư�� �����ּ���."
						, "imcts@nate.com", this.email.getText());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			this.emaillb.setForeground(new Color(146, 169, 109));
			this.emaillb.setText("���� ���� �߼�."); 
	   }
	   }
	   
	   else if(e.getActionCommand().equals("injung")){
		  if(email_in.getText().equals(""+this.random)){
			  this.injuenglb.setForeground(new Color(146, 169, 109));
			  this.injuenglb.setText("�����Ϸ� �Ǿ����ϴ�.");
			  this.okinjung=1;
		  }else{
			  this.injuenglb.setForeground(new Color(255, 0, 0));
			  this.injuenglb.setText("������ȣ ����ġ.");
			  this.okinjung=0;	
		  }
	   }
	 
	 
	   else if(e.getActionCommand().equals("back")){
		   this.stayRoom.setVisible(true);
		   this.dispose();
	   }
	 
	 
	 
	 
	   
	   else if(e.getActionCommand().equals("go")){
		   
		   if( id.getText().equals("���̵� �Է��ϼ���") || id.getText().equals("") ){
			   idlb.setText("���̵� �Է��ϼ���.");
			   return;
		   }
		   
		   
		   if(pw.getText().equals("")  ||  pw.getText().equals("��й�ȣ�� �Է��ϼ���")){
			   pw.setText("��й�ȣ�� �Է��ϼ���");
			   pwlb.setText("��й�ȣ�� �Է��ϼ���.");
			   return;
		   }
		   if(email.getText().equals("")  ||  email.getText().equals("�̸����� �Է��ϼ���")){
			   email.setText("�̸����� �Է��ϼ���");
			   emaillb.setText("�̸����� �Է��ϼ���.");
			   return;
		   }
		   
		   if(okinjung!=1){
			   injuenglb.setText("�������� �ʾҽ��ϴ�.");
			   return;
		   }
		   if(this.okinjung==1 && this.okemail==1){
			   int rr=-1;
			   boolean r=false;
			   //������ �� �޾Ұ� ���� �� �Ǿ��ִٸ�, ���� ���������� DB�� �˻��ϱ�.
			   String arr[] = {this.id.getText(),this.pw.getText()};
			   try {
				r = this.dataDao.deleteidSerch(arr);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			   
			   if(r==true){ //���̵�� ��й�ȣ�� ��ġ�Ѵٸ�. 
				   try {
					rr = this.dataDao.delete(arr);
					if(rr!=-1){
						new GetId(10,this.stayRoom,0);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			   }else{
				   this.idlb.setText("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				   this.pwlb.setText("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			   }
			   
		   }
		  
		
	   }
	   
	   
	   
      
   }@Override
   public void mouseClicked(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }@Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }@Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }@Override
   public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }@Override
   public void mouseReleased(MouseEvent e) {
      if(e.getSource().equals(id))      
      {
            id.setText("");
      }
      else if(e.getSource().equals(pw)){
            pw.setText("");
            this.pw.setEchoChar('*');
       }
      else if(e.getSource().equals(email))
      {
            email.setText("");
      }
      else if(e.getSource().equals(email_in))
      {
            email_in.setText("");
            
      }
      
   }
   

}
