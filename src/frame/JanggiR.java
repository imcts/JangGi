package frame;


import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JanggiR {
	
	JLabel lb;
	JLabel lb_p;
	boolean color=false;
	boolean myTurn=false;
	int KillCnt=0;
	int rk;
	int x;
	int y;
	
	ImageIcon b_1; 
	ImageIcon b_2; 
	ImageIcon b_3; 
	ImageIcon b_4; 
	
	ImageIcon r_1; 
	ImageIcon r_2; 
	ImageIcon r_3; 
	ImageIcon r_4; 
	ArrayList<int[]> pp;
	PanelListener p = new PanelListener();
	
	public JanggiR(int x,int y,int kind,boolean color,boolean myTurn) {
		this.color = color;
		this.rk = kind;
		this.myTurn = myTurn;
		this.x = x;
		this.y = y;
		makeLB();
	}
	
	private void makeLB() {
		//1,장2사,3차,4포,5,마,6상,7쫄
		if(color){
			if(rk==1){
				lb = new JLabel(new ImageIcon("photo/r/r/r_1.png"));
			}
			else if(rk==2){
				lb = new JLabel(new ImageIcon("photo/r/r/r_2.png"));
			}
			else if(rk==3){
				lb = new JLabel(new ImageIcon("photo/r/r/r_3.png"));
			}
			else if(rk==4){
				lb = new JLabel(new ImageIcon("photo/r/r/r_4.png"));
			}
			else if(rk==5){
				lb = new JLabel(new ImageIcon("photo/r/r/r_5.png"));
			}
			else if(rk==6){
				lb = new JLabel(new ImageIcon("photo/r/r/r_6.png"));
			}
			else if(rk==7){
				lb = new JLabel(new ImageIcon("photo/r/r/r_7.png"));
			}
			else{
				System.out.println("rk확인바람");
				lb = new JLabel(new ImageIcon("photo/hh.jpg"));
				}
			
		}else if(!color) {
			if(rk==1){
				lb = new JLabel(new ImageIcon("photo/r/b/b_1.png"));
			}
			else if(rk==2){
				lb = new JLabel(new ImageIcon("photo/r/b/b_2.png"));
			}
			else if(rk==3){
				lb = new JLabel(new ImageIcon("photo/r/b/b_3.png"));
			}
			else if(rk==4){
				lb = new JLabel(new ImageIcon("photo/r/b/b_4.png"));
			}
			else if(rk==5){
				lb = new JLabel(new ImageIcon("photo/r/b/b_5.png"));
			}
			else if(rk==6){
				lb = new JLabel(new ImageIcon("photo/r/b/b_6.png"));
			}
			else if(rk==7){
				lb = new JLabel(new ImageIcon("photo/r/b/b_7.png"));
			}
			else{System.out.println("rk확인바람");}
			
		}else{	System.out.println("팀 스펠링 확인");	}
	}

	ArrayList<int[]> cal(JanggiPanel nowPanel) {
		
		int now_x = nowPanel.x;
		int now_y = nowPanel.y;
		int rk = nowPanel.jr.rk;
		boolean color = nowPanel.jr.color;
		
		ArrayList<int[]> pos = new ArrayList<>();
		//1,장2사,3차,4포,5,마,6상,7쫄
		
		if(rk==1 || rk==2){//장=왕 ,사
			check12(pos,rk,now_x,now_y);
		}/*else if(rk==2){//사}*/
		else if(rk==3 ){//차
			check3(nowPanel, now_x, now_y, pos, rk);
		}else if(rk==4){//포
			check4(nowPanel, now_x, now_y, pos, rk);
		}else if(rk==5){//마
			check5(nowPanel, now_x, now_y, pos, rk);
		}else if(rk==6){//상
			check6(nowPanel, now_x, now_y, pos, rk);
		}else if(rk==7){//쫄
				pos.add(new int[]{now_x+1,now_y});
				pos.add(new int[]{now_x,now_y-1});
				pos.add(new int[]{now_x-1,now_y});
				if(now_x==4 &&now_y==3 ){
					pos.add(new int[]{5,2});
				}else if(now_x==6 &&now_y==3 ){
					pos.add(new int[]{5,2});
				}else if(now_x==5 &&now_y==2 ){
					pos.add(new int[]{4,1});
					pos.add(new int[]{6,1});
				}
		}else{
			System.out.println("장기알 카운트 값을 확인하세요:"+rk);
		}
		return pos;
	}
	
	void check12(ArrayList<int[]> pos, int rk2,int now_x,int now_y) {
		
			if(now_x==4 && now_y ==8){
				pos.add(new int[]{4,9});
				pos.add(new int[]{5,8});
				pos.add(new int[]{5,9});
			}else if(now_x==4 && now_y ==9){
				pos.add(new int[]{4,8});
				pos.add(new int[]{4,10});
				pos.add(new int[]{5,9});
			}else if(now_x==4 && now_y ==10){
				pos.add(new int[]{4,9});
				pos.add(new int[]{5,10});
				pos.add(new int[]{5,9});
			}else if(now_x==5 && now_y ==8){
				pos.add(new int[]{4,8});
				pos.add(new int[]{6,8});
				pos.add(new int[]{5,9});
			}else if(now_x==5 && now_y ==9){
				pos.add(new int[]{4,8});
				pos.add(new int[]{5,8});
				pos.add(new int[]{6,8});
				pos.add(new int[]{4,9});
				pos.add(new int[]{6,9});
				pos.add(new int[]{4,10});
				pos.add(new int[]{5,10});
				pos.add(new int[]{6,10});
			}else if(now_x==5 && now_y ==10){
				pos.add(new int[]{4,10});
				pos.add(new int[]{5,9});
				pos.add(new int[]{6,10});
			}else if(now_x==6 && now_y ==8){
				pos.add(new int[]{5,8});
				pos.add(new int[]{5,9});
				pos.add(new int[]{6,9});
			}else if(now_x==6 && now_y ==9){
				pos.add(new int[]{6,8});
				pos.add(new int[]{5,9});
				pos.add(new int[]{6,10});
			}else if(now_x==6 && now_y ==10){
				pos.add(new int[]{5,10});
				pos.add(new int[]{5,9});
				pos.add(new int[]{6,9});
			}else if(now_x==4 && now_y ==1)
			{	
				pos.add(new int[]{4,2});
				pos.add(new int[]{5,2});
				pos.add(new int[]{5,1});
			}else if(now_x==4 && now_y ==2){
				pos.add(new int[]{4,1});
				pos.add(new int[]{4,3});
				pos.add(new int[]{5,2});
			}else if(now_x==4 && now_y ==3){
				pos.add(new int[]{4,2});
				pos.add(new int[]{5,2});
				pos.add(new int[]{5,3});
			}else if(now_x==5 && now_y ==1){
				pos.add(new int[]{4,1});
				pos.add(new int[]{5,2});
				pos.add(new int[]{6,1});
			}else if(now_x==5 && now_y ==2){
				pos.add(new int[]{4,1});
				pos.add(new int[]{4,2});
				pos.add(new int[]{4,3});
				pos.add(new int[]{5,1});
				pos.add(new int[]{5,3});
				pos.add(new int[]{6,1});
				pos.add(new int[]{6,2});
				pos.add(new int[]{6,3});
			}else if(now_x==5 && now_y ==3){
				pos.add(new int[]{4,3});
				pos.add(new int[]{5,2});
				pos.add(new int[]{6,3});
			}else if(now_x==6 && now_y ==1){
				pos.add(new int[]{5,1});
				pos.add(new int[]{5,2});
				pos.add(new int[]{6,2});
			}else if(now_x==6 && now_y ==2){
				pos.add(new int[]{5,2});
				pos.add(new int[]{6,1});
				pos.add(new int[]{6,3});
			}else if(now_x==6 && now_y ==3){
				pos.add(new int[]{5,2});
				pos.add(new int[]{6,2});
				pos.add(new int[]{5,3});
			}else{
				System.out.println("왕 좌표값 확인바람"+now_x+","+now_y);
			}
	}
	
	void check3(JanggiPanel nowPanel,int now_x,int now_y,ArrayList<int[]> pos,int rk) {
	/*
	 * 차    가 이동할수 있는 위치 를 체크한다 
	 * 1.자신의 위에 패널이 on인지 체크한다 false면 +2위치의 패널을 체크한다 false면 +3의 위치를 체크한다
	 * */
		
		boolean stopR =false;
		boolean stopDown =false;
		ArrayList<int[]> up = new ArrayList<>();
		ArrayList<int[]> down = new ArrayList<>();
		ArrayList<int[]> left = new ArrayList<>();
		ArrayList<int[]> right = new ArrayList<>();
		for(int i =0; i<nowPanel.allJPanel.size() ; i++)	
		{	
			
			if(now_x==nowPanel.allJPanel.get(i).x && now_y > nowPanel.allJPanel.get(i).y)//위로 가는 경우를 체크한다
			{
				if(nowPanel.allJPanel.get(i).on==true){	
					up = new ArrayList<>();
				}else{}
				up.add(new int[]{nowPanel.allJPanel.get(i).x ,nowPanel.allJPanel.get(i).y});
			}
			if(now_x==nowPanel.allJPanel.get(i).x && now_y < nowPanel.allJPanel.get(i).y)//아래로 가는 경우를 체크한다
			{
				if(!stopDown){
					down.add(new int[]{nowPanel.allJPanel.get(i).x ,nowPanel.allJPanel.get(i).y});
				}
				if(nowPanel.allJPanel.get(i).on==true){	
					stopDown=true;
				}
			}
			if(now_x > nowPanel.allJPanel.get(i).x && now_y == nowPanel.allJPanel.get(i).y)//위로 가는 경우를 체크한다
			{
				if(nowPanel.allJPanel.get(i).on==true)
				{	
					left= new ArrayList<>();
				}else{}
				left.add(new int[]{nowPanel.allJPanel.get(i).x ,nowPanel.allJPanel.get(i).y});
			}
			if(now_x < nowPanel.allJPanel.get(i).x && now_y == nowPanel.allJPanel.get(i).y)//우로 가는 경우를 체크한다
			{
				if(!stopR){
					right.add(new int[]{nowPanel.allJPanel.get(i).x ,nowPanel.allJPanel.get(i).y});
				}
				if(nowPanel.allJPanel.get(i).on==true){	
					stopR=true;
				}
			}else{}
			
		}
		for (int j = 0; j < up.size(); j++) {
			pos.add(up.get(j));
		}
		for (int j = 0; j < down.size(); j++) {
			pos.add(down.get(j));
		}
		for (int j = 0; j < left.size(); j++) {
			pos.add(left.get(j));
		}
		for (int j = 0; j < right.size(); j++) {
			pos.add(right.get(j));
		}
		
		if(now_x ==4 && now_y == 1 )
			check(nowPanel,1, pos);
		else if(now_x ==4 && now_y == 3 )
			check(nowPanel,2, pos);
		else if(now_x ==5 && now_y == 2 )
			check(nowPanel,3, pos);
		else if(now_x ==6 && now_y == 1 )
			check(nowPanel,4, pos);
		else if(now_x ==6 && now_y == 3 )
			check(nowPanel,5, pos);
		else if(now_x ==4 && now_y == 8 )
			check(nowPanel,6, pos);
		else if(now_x ==4 && now_y == 10 )
			check(nowPanel,7, pos);
		else if(now_x ==5 && now_y == 9 )
			check(nowPanel,8, pos);
		else if(now_x ==6 && now_y == 8 )
			check(nowPanel,9, pos);
		else if(now_x ==6 && now_y == 10 )
			check(nowPanel,10, pos);
		
	}
	
	void check(JanggiPanel nowPanel,int kind,ArrayList<int[]> pos){
		pp = new ArrayList<>();
		pp.add(new int[]{4,1});//1
		pp.add(new int[]{4,3});//2
		pp.add(new int[]{5,2});//3
		pp.add(new int[]{6,1});//4
		pp.add(new int[]{6,3});//5
		
		pp.add(new int[]{4,8});//6
		pp.add(new int[]{4,10});//7
		pp.add(new int[]{5,9});//8
		pp.add(new int[]{6,8});//9
		pp.add(new int[]{6,10});//10
		
		if(kind!=3||kind!=8){
			if(kind==1||kind==2||kind==4||kind==5){
				pos.add(new int[]{nowPanel.allJPanel.get(13).x,nowPanel.allJPanel.get(13).y});
				if( ! nowPanel.allJPanel.get(13).on){
					if(kind==1)
						pos.add(new int[]{nowPanel.allJPanel.get(23).x,nowPanel.allJPanel.get(23).y});
					if(kind==2)
						pos.add(new int[]{nowPanel.allJPanel.get(5).x,nowPanel.allJPanel.get(5).y});
					if(kind==4)
						pos.add(new int[]{nowPanel.allJPanel.get(21).x,nowPanel.allJPanel.get(21).y});
					if(kind==5)
						pos.add(new int[]{nowPanel.allJPanel.get(3).x,nowPanel.allJPanel.get(3).y});
				}
			}else{
				pos.add(new int[]{nowPanel.allJPanel.get(76).x,nowPanel.allJPanel.get(76).y});
				if( ! nowPanel.allJPanel.get(76).on){
					if(kind==6)
						pos.add(new int[]{nowPanel.allJPanel.get(86).x,nowPanel.allJPanel.get(86).y});
					if(kind==7)//pp.add(new int[]{4,10});//7
						pos.add(new int[]{nowPanel.allJPanel.get(68).x,nowPanel.allJPanel.get(68).y});
					if(kind==9)//pp.add(new int[]{6,8});//9
						pos.add(new int[]{nowPanel.allJPanel.get(84).x,nowPanel.allJPanel.get(84).y});
					if(kind==10)//pp.add(new int[]{6,10});//10
						pos.add(new int[]{nowPanel.allJPanel.get(66).x,nowPanel.allJPanel.get(66).y});
				}
			}
		}
		if(kind==3||kind==8){
			if(kind==3){
					pos.add(new int[]{nowPanel.allJPanel.get(23).x,nowPanel.allJPanel.get(23).y});
					pos.add(new int[]{nowPanel.allJPanel.get(5).x,nowPanel.allJPanel.get(5).y});
					pos.add(new int[]{nowPanel.allJPanel.get(21).x,nowPanel.allJPanel.get(21).y});
					pos.add(new int[]{nowPanel.allJPanel.get(3).x,nowPanel.allJPanel.get(3).y});
			}else if(kind==8){
					pos.add(new int[]{nowPanel.allJPanel.get(86).x,nowPanel.allJPanel.get(86).y});
					pos.add(new int[]{nowPanel.allJPanel.get(68).x,nowPanel.allJPanel.get(68).y});
					pos.add(new int[]{nowPanel.allJPanel.get(84).x,nowPanel.allJPanel.get(84).y});
					pos.add(new int[]{nowPanel.allJPanel.get(66).x,nowPanel.allJPanel.get(66).y});
			}
		}
	}
	
	void check4(JanggiPanel nowPanel, int now_x, int now_y,ArrayList<int[]> pos, int rk) {
		//////포
		if(rk==4){
			
			ArrayList<int[]> up = new ArrayList<>();
			ArrayList<int[]> down = new ArrayList<>();
			ArrayList<int[]> left = new ArrayList<>();
			ArrayList<int[]> right = new ArrayList<>();
			
			boolean upgoing = false;
			boolean upend = false;
			boolean leftgoing = false;
			boolean leftend = false;
			
			boolean downgoing = false;
			boolean downend = false;
			boolean rightgoing = false;
			boolean rightend = false;
			
			for(int i =nowPanel.allJPanel.size()-1 ; i>-1 ; i--)	
			{
				if(nowPanel.allJPanel.get(i).x ==now_x &&  nowPanel.allJPanel.get(i).y < now_y && upend==false)
				{	
					if(nowPanel.allJPanel.get(i).jr.rk==4){
						upend=true;
					}
					if(nowPanel.allJPanel.get(i).on && upgoing==false && !upgoing && nowPanel.allJPanel.get(i).jr.rk!=4 ){
						upgoing=true;
					}else if(upgoing){
						if(!nowPanel.allJPanel.get(i).on){
							up.add(new int[]{nowPanel.allJPanel.get(i).x,nowPanel.allJPanel.get(i).y});
						}else if(nowPanel.allJPanel.get(i).on ){
							up.add(new int[]{nowPanel.allJPanel.get(i).x,nowPanel.allJPanel.get(i).y});
							upend=true;
						}
					}
						
				}
				
				if(nowPanel.allJPanel.get(i).x <now_x &&  nowPanel.allJPanel.get(i).y == now_y && !leftend)
				{	
					if(nowPanel.allJPanel.get(i).jr.rk==4){
						leftend=true;
					}
					if(nowPanel.allJPanel.get(i).on && !leftgoing && nowPanel.allJPanel.get(i).jr.rk!=4){
						leftgoing=true;
					}else if(leftgoing){
						if(!nowPanel.allJPanel.get(i).on){
							left.add(new int[]{nowPanel.allJPanel.get(i).x,nowPanel.allJPanel.get(i).y});
						}else if(nowPanel.allJPanel.get(i).on){
							left.add(new int[]{nowPanel.allJPanel.get(i).x,nowPanel.allJPanel.get(i).y});
							leftend=true;
						}
					}
						
				}
			}
			
			for(int i =0 ; i<nowPanel.allJPanel.size() ; i++)	
			{
				if(nowPanel.allJPanel.get(i).x ==now_x &&  nowPanel.allJPanel.get(i).y > now_y && !downend)
				{	
					if(nowPanel.allJPanel.get(i).jr.rk==4){
						downend=true;
					}
					if(nowPanel.allJPanel.get(i).on && !downgoing && nowPanel.allJPanel.get(i).jr.rk!=4){
						downgoing=true;
					}else if(downgoing){
						
						
						
						if(!nowPanel.allJPanel.get(i).on){
							down.add(new int[]{nowPanel.allJPanel.get(i).x,nowPanel.allJPanel.get(i).y});
						}else if(nowPanel.allJPanel.get(i).on ){
							down.add(new int[]{nowPanel.allJPanel.get(i).x,nowPanel.allJPanel.get(i).y});
							downend=true;
						}
					}
						
				}
				
				if(nowPanel.allJPanel.get(i).x >now_x &&  nowPanel.allJPanel.get(i).y == now_y && !rightend)
				{	
					//System.out.println("nowPanel.allJPanel.get(i).jr.rk-=>"+nowPanel.allJPanel.get(i).jr.rk);
					if(nowPanel.allJPanel.get(i).jr.rk==4){
						rightend=true;
					}
					if(nowPanel.allJPanel.get(i).on && !rightgoing && nowPanel.allJPanel.get(i).jr.rk!=4){
						rightgoing=true;
					}else if(rightgoing){
						
						
						
						if(!nowPanel.allJPanel.get(i).on){
							right.add(new int[]{nowPanel.allJPanel.get(i).x,nowPanel.allJPanel.get(i).y});
						}else if(nowPanel.allJPanel.get(i).on){
							right.add(new int[]{nowPanel.allJPanel.get(i).x,nowPanel.allJPanel.get(i).y});
							rightend=true;
						}
					}
						
				}
			}
			
			if(now_x==4&&now_y==8){
				if(nowPanel.allJPanel.get(p.findPanelNum(5, 9)).on ){
					up.add(new int[]{6,10});
				}
			}else if(now_x==6&&now_y==8){
				if(nowPanel.allJPanel.get(p.findPanelNum(5, 9)).on ){
					up.add(new int[]{4,10});
				}
			}else if(now_x==4&&now_y==10){
				if(nowPanel.allJPanel.get(p.findPanelNum(5, 9)).on ){
					up.add(new int[]{6,8});
				}
			}else if(now_x==6&&now_y==10){
				if(nowPanel.allJPanel.get(p.findPanelNum(5, 9)).on ){
					up.add(new int[]{4,8});
				}
			}
			
			if(now_x==4&&now_y==1){
				if(nowPanel.allJPanel.get(p.findPanelNum(5, 9)).on ){
					up.add(new int[]{6,3});
				}
			}else if(now_x==6&&now_y==1){
				if(nowPanel.allJPanel.get(p.findPanelNum(5, 9)).on ){
					up.add(new int[]{4,3});
				}
			}else if(now_x==4&&now_y==3){
				if(nowPanel.allJPanel.get(p.findPanelNum(5, 9)).on ){
					up.add(new int[]{6,1});
				}
			}else if(now_x==6&&now_y==3){
				if(nowPanel.allJPanel.get(p.findPanelNum(5, 9)).on ){
					up.add(new int[]{4,1});
				}
			}		
			
			
			for (int j = 0; j < up.size(); j++) {
				pos.add(up.get(j));
			}
			for (int j = 0; j < down.size(); j++) {
				pos.add(down.get(j));
			}
			for (int j = 0; j < left.size(); j++) {
				pos.add(left.get(j));
			}
			for (int j = 0; j < right.size(); j++) {
				pos.add(right.get(j));
			}
		}
	}
	
	void check5(JanggiPanel nowPanel,int now_x,int now_y,ArrayList<int[]> pos,int rk){
		/* 마          이동 가능 위치 체크
		 * 1.자신을 기준으로 한칸 차이나는 십자가 형태의 4곳의 on을 체크함
		 * */
		
		for(int i =0; i<nowPanel.allJPanel.size() ;i++)
		{
			if(nowPanel.allJPanel.get(i).x ==now_x+1 && nowPanel.allJPanel.get(i).y ==now_y && nowPanel.allJPanel.get(i).on==false )
			{	
				if(rk==5){
					pos.add(new int[]{now_x+2,now_y-1});
					pos.add(new int[]{now_x+2,now_y+1});
				}else if(rk==6){
					if(nowPanel.allJPanel.get(i).x ==now_x+2 && nowPanel.allJPanel.get(i).y ==now_y-1 && nowPanel.allJPanel.get(i).on==false )
						pos.add(new int[]{now_x+3,now_y-2});
					if(nowPanel.allJPanel.get(i).x ==now_x+2 && nowPanel.allJPanel.get(i).y ==now_y+1 && nowPanel.allJPanel.get(i).on==false )
						pos.add(new int[]{now_x+3,now_y+2});
				}else{	System.out.println("janggiR 오류==>check");	}
			}
			
			if(nowPanel.allJPanel.get(i).x ==now_x && nowPanel.allJPanel.get(i).y ==now_y+1 && nowPanel.allJPanel.get(i).on==false )
			{
				if(rk==5){
					pos.add(new int[]{now_x-1,now_y+2});
					pos.add(new int[]{now_x+1,now_y+2});
				}else if(rk==6){
					if(nowPanel.allJPanel.get(i).x ==now_x-1 && nowPanel.allJPanel.get(i).y ==now_y+2 && nowPanel.allJPanel.get(i).on==false )
						pos.add(new int[]{now_x-2,now_y+3});
					if(nowPanel.allJPanel.get(i).x ==now_x+1 && nowPanel.allJPanel.get(i).y ==now_y+2 && nowPanel.allJPanel.get(i).on==false )
						pos.add(new int[]{now_x+2,now_y+3});
				}else{	System.out.println("janggiR 오류==>check");	}
			}
			
			if(nowPanel.allJPanel.get(i).x ==now_x-1 && nowPanel.allJPanel.get(i).y ==now_y && nowPanel.allJPanel.get(i).on==false )
			{
				if(rk==5){
					pos.add(new int[]{now_x-2,now_y-1});
					pos.add(new int[]{now_x-2,now_y+1});
				}else if(rk==6){
					if(nowPanel.allJPanel.get(i).x ==now_x-2 && nowPanel.allJPanel.get(i).y ==now_y-1 && nowPanel.allJPanel.get(i).on==false )
						pos.add(new int[]{now_x-3,now_y-2});
					if(nowPanel.allJPanel.get(i).x ==now_x-2 && nowPanel.allJPanel.get(i).y ==now_y+1 && nowPanel.allJPanel.get(i).on==false )
						pos.add(new int[]{now_x-3,now_y+2});
				}else{	System.out.println("janggiR 오류==>check");	}
			}
			
			if(nowPanel.allJPanel.get(i).x ==now_x && nowPanel.allJPanel.get(i).y ==now_y-1 && nowPanel.allJPanel.get(i).on==false )
			{
				if(rk==5){
					pos.add(new int[]{now_x+1,now_y-2});
					pos.add(new int[]{now_x-1,now_y-2});
				}else{	System.out.println("janggiR 오류==>check");	}
			}
			
		}
	}

	void check6(JanggiPanel nowPanel, int now_x, int now_y,ArrayList<int[]> pos, int rk) {
/* 상 		*/
		for(int i =0; i<nowPanel.allJPanel.size() ;i++)
		{
			if(nowPanel.allJPanel.get(i).x ==now_x+1 && nowPanel.allJPanel.get(i).y ==now_y && nowPanel.allJPanel.get(i).on==false )
			{	
				for(int i1 =0; i1<nowPanel.allJPanel.size() ;i1++){
					if(nowPanel.allJPanel.get(i1).x ==now_x+2 && nowPanel.allJPanel.get(i1).y ==now_y-1 && nowPanel.allJPanel.get(i1).on==false )
						pos.add(new int[]{now_x+3,now_y-2});
				}
				for(int i1 =0; i1<nowPanel.allJPanel.size() ;i1++){
					if(nowPanel.allJPanel.get(i1).x ==now_x+2 && nowPanel.allJPanel.get(i1).y ==now_y+1 && nowPanel.allJPanel.get(i1).on==false )
						pos.add(new int[]{now_x+3,now_y+2});
				}
			}
			
			if(nowPanel.allJPanel.get(i).x ==now_x && nowPanel.allJPanel.get(i).y ==now_y+1 && nowPanel.allJPanel.get(i).on==false )
			{
				for(int i1 =0; i1<nowPanel.allJPanel.size() ;i1++){
					if(nowPanel.allJPanel.get(i1).x ==now_x-1 && nowPanel.allJPanel.get(i1).y ==now_y+2 && nowPanel.allJPanel.get(i1).on==false )
						pos.add(new int[]{now_x-2,now_y+3});
				}
				for(int i1 =0; i1<nowPanel.allJPanel.size() ;i1++){
					if(nowPanel.allJPanel.get(i1).x ==now_x+1 && nowPanel.allJPanel.get(i1).y ==now_y+2 && nowPanel.allJPanel.get(i1).on==false )
						pos.add(new int[]{now_x+2,now_y+3});
				}
			}
			
			if(nowPanel.allJPanel.get(i).x ==now_x-1 && nowPanel.allJPanel.get(i).y ==now_y && nowPanel.allJPanel.get(i).on==false )
			{
				for(int i1 =0; i1<nowPanel.allJPanel.size() ;i1++){
					if(nowPanel.allJPanel.get(i1).x ==now_x-2 && nowPanel.allJPanel.get(i1).y ==now_y-1 && nowPanel.allJPanel.get(i1).on==false )
						pos.add(new int[]{now_x-3,now_y-2});
				}
				for(int i1 =0; i1<nowPanel.allJPanel.size() ;i1++){
					if(nowPanel.allJPanel.get(i1).x ==now_x-2 && nowPanel.allJPanel.get(i1).y ==now_y+1 && nowPanel.allJPanel.get(i1).on==false )
						pos.add(new int[]{now_x-3,now_y+2});
				}
			}
			
			if(nowPanel.allJPanel.get(i).x ==now_x && nowPanel.allJPanel.get(i).y ==now_y-1 && nowPanel.allJPanel.get(i).on==false )
			{
				for(int i1 =0; i1<nowPanel.allJPanel.size() ;i1++){
					if(nowPanel.allJPanel.get(i1).x ==now_x+1 && nowPanel.allJPanel.get(i1).y ==now_y-2 && nowPanel.allJPanel.get(i1).on==false )
						pos.add(new int[]{now_x+2,now_y-3});
				}
				for(int i1 =0; i1<nowPanel.allJPanel.size() ;i1++){
					if(nowPanel.allJPanel.get(i1).x ==now_x-1 && nowPanel.allJPanel.get(i1).y ==now_y-2 && nowPanel.allJPanel.get(i1).on==false )
						pos.add(new int[]{now_x-2,now_y-3});
				}
			}
			
		}
		
	}
	
	public JanggiR() {;}
	
}
