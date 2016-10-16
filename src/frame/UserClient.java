package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Iterator;


class Reciver extends Thread{
	ObjectInputStream ois;
	Socket socket;
	
	StayR stayRoom;
	User user;
	GameRoomFrame game;
	
	public Reciver(Socket socket,StayR stayRoom,GameRoomFrame game,User user) throws Exception {
		this.user=user;
		this.socket=socket;
		
		this.stayRoom=stayRoom;
		this.game=game;
		this.ois=new ObjectInputStream(this.socket.getInputStream());
	}
	
	@Override
	public void run() {
		try {
			while(ois!=null){	
				if(this.user.threadStop==1){
					break;
				}
			
				
				 new Result(((SendSpot)ois.readObject()),this.stayRoom,this.user,this.game);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////

class Send extends Thread{
	Socket socket;
	ObjectOutputStream oos;
	User user;
	SendSpot sendSpot;

	
	
	public Send(Socket socket,User user) throws Exception {
		this.user=user;
		this.socket = socket;
		this.oos = new ObjectOutputStream(this.socket.getOutputStream()); 
		SendSpot sendSpot = new SendSpot(0,this.user.id,this.user.name,this.user.nickname,this.user.gpsu,this.user.gen); //이름을 담아서 쏴버려.
		this.oos.writeObject(sendSpot);
	}
	
	@Override
	public void run() {
		
		try {
		while(oos!=null){
			if(this.user.threadStop==1){
				break;
			}
			
			if(this.user.sendindex==1){
				SendSpot sendSpot = new SendSpot(1,this.user.name,this.user.nickname,this.user.chat);
				this.oos.writeObject(sendSpot);
				this.user.sendindex=0;
			}
			else if(this.user.sendindex==2){
				SendSpot sendSpot = new SendSpot(3);
				this.oos.writeObject(sendSpot);
				this.user.sendindex=0;
			}
			else if(this.user.sendindex==3){
				SendSpot sendSpot = new SendSpot(4,this.user.chat,this.user.name,this.user.targetid,this.user.id);
				this.oos.writeObject(sendSpot);
				this.user.sendindex=0;
			}
			else if(this.user.sendindex==4){
				SendSpot sendSpot = new SendSpot(5,this.user.roomNum,this.user.nickname, this.user.gpsu, this.user.gen,this.user.name);
				this.oos.writeObject(sendSpot);
				this.user.sendindex=0;
			}
			else if(this.user.sendindex==5){
				SendSpot sendSpot = new SendSpot(7, this.user.myRoomPosition, this.user.nickname,this.user.gpsu,this.user.gen);
				this.oos.writeObject(sendSpot);
				this.user.sendindex=0;
			}
			else if(this.user.sendindex==6){ 
				SendSpot sendSpot = new SendSpot(8, this.user.myRoomPosition);
				System.out.println("유저 클라이언트의 유저 방 포지션 : "+user.myRoomPosition);
				this.oos.writeObject(sendSpot);
				this.user.sendindex=0;
				this.user.myRoomPosition=0; 
			}
			else if(this.user.sendindex==7){//내가 방장이고, 방에 인원이 있을시.
				SendSpot sendSpot = new SendSpot(10, this.user.myRoomPosition);
				this.oos.writeObject(sendSpot);
				this.user.sendindex=0;
				this.user.myRoomPosition=0; 
			}
			else if(this.user.sendindex==8){//내가 참가자이고, 방을 나갔을시.
				SendSpot sendSpot = new SendSpot(11, this.user.myRoomPosition);
				this.oos.writeObject(sendSpot);
				this.user.sendindex=0;
				this.user.myRoomPosition=0; 
			}
			else if(this.user.sendindex==9){
				SendSpot sendSpot = new SendSpot(12,this.user.myRoomPosition,this.user.nickname,this.user.roomchat);
				this.oos.writeObject(sendSpot);
				this.user.sendindex=0;
			}
			else if(this.user.sendindex==10){
				SendSpot sendSpot = new SendSpot(13,this.user.myRoomPosition,this.user.nickname,this.user.roomchat);
				this.oos.writeObject(sendSpot);
				this.user.sendindex=0;
			}
			else if(this.user.sendindex==11){
				SendSpot sendSpot = new SendSpot(14,this.user.myRoomPosition,this.user.ready);
				this.oos.writeObject(sendSpot);
				this.user.sendindex=0;
			}
			else if(this.user.sendindex==12){
				SendSpot sendSpot = new SendSpot(15,this.user.myRoomPosition,this.user.ready);
				this.oos.writeObject(sendSpot);
				this.user.sendindex=0;
			}
			else if(this.user.sendindex==13){
				SendSpot sendSpot = new SendSpot(16,this.user.myRoomPosition,this.user.winner);
				this.oos.writeObject(sendSpot);
				this.user.sendindex=0;
			}
			else if(this.user.sendindex==14){
	            SendSpot sendSpot = new SendSpot(17,this.user.myRoomPosition,this.user.roomMaker,this.user.choiceP,this.user.movingP, this.user.kind,this.user.turn,this.user.nickname,this.user.pass);
	            this.oos.writeObject(sendSpot);
	            this.user.sendindex=0;
	         }
			else if(this.user.sendindex==15){
	            SendSpot sendSpot = new SendSpot(18,this.user.myRoomPosition,this.user.roomMaker,this.user.nickname,this.user.gpsu);
	            this.oos.writeObject(sendSpot);
	            this.user.sendindex=0;
	         }
			else if(this.user.sendindex==16){
                SendSpot sendSpot = new SendSpot(19,this.user.myRoomPosition,this.user.roomMaker,this.user.janggun,this.user.ChoiceJP);
                     this.oos.writeObject(sendSpot);
                     this.user.sendindex=0;
                     this.user.janggun=0;
                     this.user.ChoiceJP=0;
               
            }
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}

////////////////////////////////////////////////////////////////////////////////////////////////
class User{
	String name,gen,gpsu,id,nickname,chat,targetid,roomchat,pass; 
	Socket socket;
	Send send;
	Reciver reciver;
	
	LoginPage loginpage;
	GameRoomFrame game;
	
	StayR stayRoom;
	int ready=0;//0이면 암것도 아녀., 1이면 준비완료. 2면 시작. 
	int roomMaker=0,roomInwon=0; //기본값은 0, 내가 방장이라면 1, 내가 입장이라면 2 
	int roomNum=1,myRoomPosition=0; 
	int winner=0;
	int jum,sendindex=0,threadStop=0,userPosition=1; 
	int choiceP=0, movingP=0, kind=0, turn=0 , color=0 ,janggun=0,ChoiceJP=0;
	//sendindex : 0=정지. 1=일반 채팅 전송. 2=에러메세지 전송. 3= 귓속말 전송.
					//	4=방번호 + 닉넴 + 급수  전송.  5=유저가 방에 참가하는 버튼을 눌렀을때, 참가한 유저의 이름과 급수 전송.
		//6=내가 방장이고, 방의 유저가 아무도 없을시. 7=내가 방장이고 방의 유저가 1명이상일시, 8=내가 유저일시.  9=방에서채팅.
	 //10=방 입장 메세지 전송.  11=내가 유저이고, 방에 참가후 레디버튼을 눌렀다면.  12= 내가 방장이고, 스타트 버튼을 눌렀다면.
	//13= 게임 포기선언. //14=게임방에서 좌표값을 전송시킴.
	
	
	//유저 포지션 0=대기실 , 1=게임방.
	
	public User(String id, String name, String nickname,String gen,int jum,String gpsu,LoginPage loginpage) throws Exception {
		this.loginpage=loginpage;
		this.id=id;
		this.nickname=nickname;
		this.gen=gen;
		this.jum=jum;
		this.gpsu=gpsu;
		this.name=name;
		
		this.socket=new Socket("localhost",7777);
		
		
		
		this.send=new Send(this.socket, this);
		this.send.start();
		this.game=new GameRoomFrame(this,stayRoom);
		this.stayRoom= new StayR(this, this.send, this.loginpage,this.game);
		this.reciver=new Reciver(this.socket,this.stayRoom,game,this);
		this.reciver.start();
	}
	
	
}