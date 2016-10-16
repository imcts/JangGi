package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;


class StayR extends JFrame implements ActionListener{
   User user;
   Container con;
   
   TextField txt;
   
   JTextArea ta,userinfo;
   
   GameRoomFrame game;
   
   JScrollPane sp,userinfosp;
   
   JPanel mainP,myInfoP,userInfoP,backMenuP,roomP;
   
   JLabel usernfolb,mychar,gpsu,id,nickname,jum,backimg;
   
   JLabel menu, menu1, menu2, nomenu, joinmenu, grademenu, createmenu,
         playermenu, statemenu;

   String res="",myinfores="";
   
   Send send;
   
   JButton LogOut, CreateRoom,myInfoEdit,exit;
   
   Choice choice;
   
   String [] gen={"photo/characterMan.jpg","photo/characterGirl.jpg"};
   
  
   LoginPage loginpage;
   
   
   int sendIndex=1;
   int lby=0;
   
   
   public StayR(User user,Send send,LoginPage loginpage,GameRoomFrame game) {
      super("StayRoom-");
      this.loginpage=loginpage;
      this.game=game;
      this.send=send;
      
      this.con=getContentPane();
      this.game.stayRoomin(this);
      setSize(1000,690);
      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (int)(screen.getWidth()/2 - super.getWidth()/2);
      int y = (int)(screen.getHeight()/2 - super.getHeight()/2);
      setLocation(x,y);
      
      this.user=user;
      
      this.mainP=new JPanel();
      this.mainP.setBounds(0, 0, 1000, 700);
      this.mainP.setLayout(null);
      
      
      this.txt=new TextField();
      this.txt.setBounds(110, 600, 600, 30);
      this.txt.addActionListener(this);
      this.txt.setFont(new Font(null,Font.PLAIN,17));
      
      
      this.ta=new JTextArea();
      this.ta.setLineWrap(true);
      this.sp=new JScrollPane(ta);
      this.sp.setBounds(10,450,700,150);
      
      this.choice=new Choice();
      this.choice.add("일 반");
      this.choice.setBounds(10, 603, 95, 30);
      
      
      
      this.userInfoP=new JPanel();
      this.userInfoP.setLayout(null);
      this.userInfoP.setBackground(new Color(255,255,255));
      this.userinfosp= new JScrollPane(this.userInfoP);
      this.userinfosp.setBounds(730, 70, 250, 300);
      
      
      this.roomP=new JPanel();
      this.roomP.setLayout(null);
      this.roomP.setBounds(12, 70, 695, 365);
      this.roomP.setBackground(new Color(255,255,255));
      
      
      
      
      
      
      
      this.usernfolb=new JLabel(new ImageIcon("photo/userinfomenu.jpg"));
      this.usernfolb.setBounds(730, 10, 250, 40);
      
      
      this.menu=new JLabel(new ImageIcon("photo/smallmenu.jpg"));   
      this.menu.setBounds(705,10, 15, 40);

      
      this.backimg=new JLabel(new ImageIcon("photo/stayRoomBack.jpg"));
      this.backimg.setBounds(0, 0, 1000, 700);
      

      this.myInfoP=new JPanel();
      this.myInfoP.setLayout(null);
      this.myInfoP.setBackground(new Color(255,255,255));
      this.myInfoP.setBounds(730, 450, 250, 180);
      
      
      
      if(this.user.gen.equals("남자")){
         this.mychar=new JLabel(new ImageIcon(this.gen[0]));
         this.mychar.setBounds(130, 0, 120, 180);
      }else{
         this.mychar=new JLabel(new ImageIcon(this.gen[1]));
         this.mychar.setBounds(130, 0, 120, 180);
      }
      
      
      
      this.id=new JLabel("Name : ");
      this.id.setBounds(3, 20, 100, 30);
      this.id.setFont(new Font(null,Font.BOLD,15));
      
      
      this.nickname=new JLabel("Id : ");
      this.nickname.setBounds(3, 55, 100, 30);
      this.nickname.setFont(new Font(null,Font.BOLD,15));
      
      this.gpsu=new JLabel("Grade : ");
      this.gpsu.setBounds(3, 90, 100, 30);
      this.gpsu.setFont(new Font(null,Font.BOLD,15));
      
      
      this.jum=new JLabel("Point : ");
      this.jum.setBounds(3, 125, 100, 30);
      this.jum.setFont(new Font(null,Font.BOLD,15));
      
      
      
      this.CreateRoom=new JButton("createRoom",new ImageIcon("photo/createRoom.jpg"));
      this.CreateRoom.setBounds(730, 378, 248, 30);
      this.CreateRoom.addActionListener(this);
      
      this.LogOut=new JButton("logout",new ImageIcon("photo/logout.jpg"));
      this.LogOut.setBounds(900, 413, 80, 30);
      this.LogOut.addActionListener(this);
      
      this.myInfoEdit=new JButton("infoedit",new ImageIcon("photo/edit.jpg"));
      this.myInfoEdit.setBounds(730,413,80,30);
      this.myInfoEdit.addActionListener(this);
      
      this.exit = new JButton("exit",new ImageIcon("photo/stayexit.jpg"));
      this.exit.setBounds(813, 413, 83, 30);
      this.exit.addActionListener(this);
   
      this.menu1=new JLabel(new ImageIcon("photo/menu1.jpg"));   
      this.menu1.setBounds(10,10, 50, 40);
      
      this.nomenu=new JLabel(new ImageIcon("photo/nomenu.jpg"));
      this.nomenu.setBounds(65, 10, 60, 40);
      
      this.createmenu=new JLabel(new ImageIcon("photo/createmenu.jpg"));
      this.createmenu.setBounds(130, 10, 90, 40);
      
      this.grademenu=new JLabel(new ImageIcon("photo/grademenu.jpg"));
      this.grademenu.setBounds(225, 10, 90, 40);
   
      this.playermenu=new JLabel(new ImageIcon("photo/playermenu.jpg"));
      this.playermenu.setBounds(320, 10, 90, 40);
      
      this.joinmenu=new JLabel(new ImageIcon("photo/joinmenu.jpg"));
      this.joinmenu.setBounds(400, 10, 90, 40);
      
      this.statemenu=new JLabel(new ImageIcon("photo/statemenu.jpg"));
      this.statemenu.setBounds(465, 10, 90, 40);
      
      this.menu2=new JLabel(new ImageIcon("photo/menu2.jpg"));
      this.menu2.setBounds(548, 10, 150, 40);
      
 
      
      this.myInfoP.add(jum);
      this.myInfoP.add(gpsu);
      this.myInfoP.add(nickname);
      this.myInfoP.add(mychar);
      this.myInfoP.add(id);
   
      
      this.mainP.add(exit);
      this.mainP.add(choice);
      this.mainP.add(myInfoEdit);
      this.mainP.add(menu);
      this.mainP.add(menu2);
      this.mainP.add(statemenu);
      this.mainP.add(joinmenu);
      this.mainP.add(playermenu);
      this.mainP.add(grademenu);
      this.mainP.add(createmenu);
      this.mainP.add(nomenu);
      this.mainP.add(menu1);
      this.mainP.add(CreateRoom);
      this.mainP.add(LogOut);
      this.mainP.add(myInfoP);
      this.mainP.add(usernfolb);
      this.mainP.add(userinfosp);
      this.mainP.add(txt);
      this.mainP.add(sp);
      this.mainP.add(backimg);
      
      
      con.add(roomP);
      con.add(mainP);
      
      
      
      this.setMyInfo();
      
      setVisible(true);
      setResizable(false);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   
   //////////////////////////////////////////////////////////////////////////////////////////////////
   
   
   
   public void addUserInfoLabel(SendSpot sendSpot){

      this.lby=0;
      this.userInfoP.removeAll();
      this.userInfoP.repaint();
      this.choice.removeAll();
      this.choice.add("일 반");
      
      String [] ids= sendSpot.ids;
      String [] nicknames =sendSpot.nicknames;
      String [] gens =sendSpot.gens;
      String [] gpsus =sendSpot.gpsus;
      
      
      for(int i=0; i<ids.length;i++){      
         JLabel id = new JLabel(ids[i]);
         id.setBounds(5, lby, 80, 20);
         if(!(this.user.id.equals(ids[i]))){
         this.choice.add(ids[i]);
         }
      
         JLabel nickname = new JLabel(nicknames[i]);
         nickname.setBounds(80, lby, 80, 20);
      
         JLabel gen = new JLabel(gens[i]);
         gen.setBounds(160, lby, 80, 20);
      
         JLabel gpsu = new JLabel(gpsus[i]);
         gpsu.setBounds(200, lby, 80, 20);
      
         
         this.lby+=20;
      
         this.userInfoP.add(id);
         this.userInfoP.add(gen);
         this.userInfoP.add(gpsu);
         this.userInfoP.add(nickname);
      }
      this.userInfoP.repaint();
      
   }
   
   //////////////////////////////////////////////////////////////////////////////////////////////////
   

   public void setMyInfo() {
      this.id.setText("Name : "+this.user.name);
      this.nickname.setText("Id : "+this.user.id);
      this.gpsu.setText("Grade : "+this.user.gpsu);
      this.jum.setText("Point : "+this.user.jum+"점");
   }

   //////////////////////////////////////////////////////////////////////////////////////////////////

   
   @Override
   public void actionPerformed(ActionEvent e) {
      
       if(e.getActionCommand().equals("1")){
            this.game.start.setIcon(new ImageIcon("photo/ready.jpg"));
            this.user.myRoomPosition=Integer.parseInt(e.getActionCommand());
            this.user.roomMaker=2;
            this.user.userPosition=2;
            this.user.sendindex=5;
            this.game.nextturn.setEnabled(false);
            this.game.giveup.setEnabled(false);
            this.game.start.setEnabled(true);
            this.setVisible(false);
            this.game.setVisible(true);
            this.user.sendindex=10;
            
         }
      else if(e.getActionCommand().equals("2")){
         this.game.start.setIcon(new ImageIcon("photo/ready.jpg"));
         this.user.myRoomPosition=Integer.parseInt(e.getActionCommand());
         this.user.roomMaker=2;
         this.user.userPosition=2;
         this.user.sendindex=5;
         this.game.nextturn.setEnabled(false);
         this.game.giveup.setEnabled(false);
         this.game.start.setEnabled(true);
         this.setVisible(false);
         this.game.setVisible(true);
         this.user.sendindex=10;
      }
      else if(e.getActionCommand().equals("3")){
         this.game.start.setIcon(new ImageIcon("photo/ready.jpg"));
         this.user.myRoomPosition=Integer.parseInt(e.getActionCommand());
         this.user.roomMaker=2;
         this.user.userPosition=2;
         this.user.sendindex=5;
         this.game.nextturn.setEnabled(false);
         this.game.giveup.setEnabled(false);
         this.game.start.setEnabled(true);
         this.setVisible(false);
         this.game.setVisible(true);
         this.user.sendindex=10;
      }
      else if(e.getActionCommand().equals("4")){
         this.game.start.setIcon(new ImageIcon("photo/ready.jpg"));
         this.user.myRoomPosition=Integer.parseInt(e.getActionCommand());
         this.user.roomMaker=2;
         this.user.userPosition=2;
         this.user.sendindex=5;
         this.game.nextturn.setEnabled(false);
         this.game.giveup.setEnabled(false);
         this.game.start.setEnabled(true);
         this.setVisible(false);
         this.game.setVisible(true);
         this.user.sendindex=10;
      }
      else if(e.getActionCommand().equals("5")){
         this.game.start.setIcon(new ImageIcon("photo/ready.jpg"));
         this.user.myRoomPosition=Integer.parseInt(e.getActionCommand());
         this.user.roomMaker=2;
         this.user.userPosition=2;
         this.user.sendindex=5;
         this.game.nextturn.setEnabled(false);
         this.game.giveup.setEnabled(false);
         this.game.start.setEnabled(true);
         this.setVisible(false);
         this.game.setVisible(true);
         this.user.sendindex=10;
      }
      else if(e.getActionCommand().equals("6")){
         this.game.start.setIcon(new ImageIcon("photo/ready.jpg"));
         this.user.myRoomPosition=Integer.parseInt(e.getActionCommand());
         this.user.roomMaker=2;
         this.user.userPosition=2;
         this.user.sendindex=5;
         this.game.nextturn.setEnabled(false);
         this.game.giveup.setEnabled(false);
         this.game.start.setEnabled(true);
         this.setVisible(false);
         this.game.setVisible(true);
         this.user.sendindex=10;
      }
      else if(e.getActionCommand().equals("7")){
         this.game.start.setIcon(new ImageIcon("photo/ready.jpg"));
         this.user.myRoomPosition=Integer.parseInt(e.getActionCommand());
         this.user.roomMaker=2;
         this.user.userPosition=2;
         this.user.sendindex=5;
         this.game.nextturn.setEnabled(false);
         this.game.giveup.setEnabled(false);
         this.game.start.setEnabled(true);
         this.setVisible(false);
         this.game.setVisible(true);
         this.user.sendindex=10;
      }
      else if(e.getActionCommand().equals("8")){
         this.game.start.setIcon(new ImageIcon("photo/ready.jpg"));
         this.user.myRoomPosition=Integer.parseInt(e.getActionCommand());
         this.user.roomMaker=2;
         this.user.userPosition=2;
         this.user.sendindex=5;
         this.game.nextturn.setEnabled(false);
         this.game.giveup.setEnabled(false);
         this.game.start.setEnabled(true);
         this.setVisible(false);
         this.game.setVisible(true);
         this.user.sendindex=10;
      }
      else if(e.getActionCommand().equals("9")){
         this.game.start.setIcon(new ImageIcon("photo/ready.jpg"));
         this.user.myRoomPosition=Integer.parseInt(e.getActionCommand());
         this.user.roomMaker=2;
         this.user.userPosition=2;
         this.user.sendindex=5;
         this.game.nextturn.setEnabled(false);
         this.game.giveup.setEnabled(false);
         this.game.start.setEnabled(true);
         this.setVisible(false);
         this.game.setVisible(true);
         this.user.sendindex=10;
      }
      else if(e.getActionCommand().equals("10")){
         this.game.start.setIcon(new ImageIcon("photo/ready.jpg"));
         this.user.myRoomPosition=Integer.parseInt(e.getActionCommand());
         this.user.roomMaker=2;
         this.user.userPosition=2;
         this.user.sendindex=5;
         this.game.nextturn.setEnabled(false);
         this.game.giveup.setEnabled(false);
         this.game.start.setEnabled(true);
         this.setVisible(false);
         this.game.setVisible(true);
         this.user.sendindex=10;
      }
      
      if(e.getActionCommand().equals("infoedit")){
         this.setVisible(false);
         try {
            new Su(this.user,this);
         } catch (Exception e1) {
            e1.printStackTrace();
         }
      }
      else if(e.getActionCommand().equals("createRoom")){
         this.game.start.setIcon(new ImageIcon("photo/startGame.jpg"));//이미지 버튼값을 변경 시켜주고.
         this.game.start.setEnabled(false);
         this.user.myRoomPosition=user.roomNum; //유저가 지금 있는 위치가 방을 만든 위치. 내가 있는 방num.
         this.user.roomMaker=1; //내가 방장일시.
         this.user.sendindex=4;
         this.user.userPosition=2;
         this.game.nextturn.setEnabled(false);
         this.game.giveup.setEnabled(false);
         this.setVisible(false);
         this.game.setVisible(true);
         this.user.sendindex=10;
         
      }
      else if(e.getActionCommand().equals("exit")){
         this.setVisible(false);
         new Tal(this);
      }
      else  if(e.getActionCommand().equals("logout")){
         this.logout();
      }
      else if(this.txt.getText().equals("")){
         return;
      }
      else if(this.choice.getSelectedItem().equals("일 반") && this.user.userPosition==1){ //내가 대기실에있다면.
         this.user.chat=txt.getText();
         this.user.sendindex=1;
         this.txt.setText("");
      }
      
      else if(!(this.choice.getSelectedItem().equals("일 반"))){//귓속말일때       
         //사용자가 상대의 이름을 클릭하고, 엔터를 치면, 
         this.res+= this.choice.getSelectedItem()+"님께 은밀한속삭임 시전 : "+this.txt.getText()+"\n";
         
         this.ta.setText(res); //일단 내가 보낸것을 채팅창에 셋팅해주고~
         
         this.user.chat=txt.getText(); //채팅정보에 담아서 넘겨주고
         this.user.targetid=this.choice.getSelectedItem(); //받는 사람 아이디 넘겨주고 
         this.user.sendindex=3; //3번 방식으로 전송.
         this.txt.setText("");
      }
      
      


      
      
      
      
   }
   
   
   public void logout() {
      this.user.sendindex=2;
      this.user.send.interrupt();
      this.user.reciver.interrupt();
      this.loginpage.setVisible(true);
      this.dispose();
   }


   //////////////////////////////////////////////////////////////////////////////////////////////////
   
   
   


   public void roomListSet(SendSpot sendSpot) {

      
      String [] no= sendSpot.nos; //방번호가 담겨있는 배열 . 
      String [] create=sendSpot.creates; //방장의 닉네임
      String [] cgpsu= sendSpot.cgpsus; //방장의 급수.
      String [] cgen = sendSpot.cgens;//방장의 성별.
      String [] stat= sendSpot.stats;//방의 대기상태.
      String [] inwon= sendSpot.inwons;//인원수의 배열.
      String [] player= sendSpot.players;//플레이어의 닉네임
      String [] pgpsu = sendSpot.pgpsu; //플레이어의 급수.
      String [] pgen =  sendSpot.pgen;//플레이어의 성별.
      

      this.roomP.removeAll();
      this.roomP.repaint();
      int y=10;
     
      for(int i=0; i<no.length; i++){
         JLabel st;
         if(stat[i].equals("대기중")){
            st = new JLabel(new ImageIcon("photo/stayRoomOwn.jpg"));
            st.setFont(new Font(null,Font.BOLD,18));
            st.setBounds(3, y-7, 40, 40);
            
         }
         else if(stat[i].equals("설정중")){
            st = new JLabel(new ImageIcon("photo/stayRoomstay.jpg"));
            st.setFont(new Font(null,Font.BOLD,18));
            st.setBounds(3, y-7, 40, 40);
         }
         else{
            st = new JLabel(new ImageIcon("photo/stayRoomSam.jpg"));
            st.setFont(new Font(null,Font.BOLD,18));
            st.setBounds(3, y-7, 40, 40);
         }
         
         JLabel no1 = new JLabel("no."+no[i]);
         no1.setFont(new Font(null,Font.BOLD,18));
         no1.setBounds(65, y, 100, 30);
         
         JLabel nick = new JLabel(create[i]);
         nick.setFont(new Font(null,Font.BOLD,18));
         nick.setBounds(120, y, 100, 30);
         
         JLabel g = new JLabel(cgpsu[i]);
         g.setFont(new Font(null,Font.BOLD,18));
         g.setBounds(230, y, 100, 30);
         
         JLabel player1 = new JLabel(player[i]);
         player1.setFont(new Font(null,Font.BOLD,18));
         player1.setBounds(300, y, 100, 30);
         
         JLabel join = new JLabel(inwon[i]+"명");
         join.setFont(new Font(null,Font.BOLD,18));
         join.setBounds(415, y, 100, 30);
         
         JLabel stat1 = new JLabel(stat[i]);
         stat1.setFont(new Font(null,Font.BOLD,18));
         stat1.setBounds(465, y, 100, 30);
         
         if(inwon[i].equals("2")){
            JButton go=new JButton(no[i],new ImageIcon("photo/stayRoomIn.jpg"));
            go.setBounds(540, y, 130, 30);
            go.setEnabled(false);
            this.roomP.add(go);
         }else{
            JButton go=new JButton(no[i],new ImageIcon("photo/stayRoomIn.jpg"));
            go.setBounds(540, y, 130, 30);
            go.addActionListener(this);
            this.roomP.add(go);
         }
         
        
      
         
         this.roomP.add(st);
         this.roomP.add(no1);
         this.roomP.add(nick);
         this.roomP.add(g);
         this.roomP.add(player1);
         this.roomP.add(join);
         this.roomP.add(stat1);
        
      
         y+=40;
         
         
      }
      this.roomP.repaint();
   }


}

