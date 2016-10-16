package frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelListener implements MouseListener {
	
	static int savePanelCnt=-1;
	static ArrayList<JanggiPanel> savePanel;	//세이브패널이 필요한이유는 ? 전에 선택한 패널을 저장하려고
	static ArrayList<JanggiR> jrListDown;	
	static ArrayList<JanggiR> jrListUp;
	static ArrayList<JanggiPanel> possiblejangp ;	
	static GameRoomFrame f;
	static boolean end=false;
	
	static JPanel centerP;
	static boolean selectR = false;				//장기알을 선택하고 클릭하는지 선택안하고 클릭하는지 알기위함
	static boolean myTurn = true;
	static boolean color = true;		//color변수가 true면 빨강색 //false면 파랑색
	static ArrayList<int[]> possiblePos;			// 이동가능한 좌표값 
	int x;
	int y;
	int [] sendxy = null;
	JanggiPanel nowPanel;
	int nowPanelNum,savePanelNum;
	JLabel jb1;
	static boolean jangturn=false;
	static int jangx=0;
	static int jangy=0;
	
	public PanelListener() {

	}
	
	
	public PanelListener(String str) {
		savePanelCnt=-1;
		possiblePos=null;
		centerP=null;
		f=null;
		jrListUp=null;
		jrListDown=null;
		possiblejangp=null;
		savePanel=null;
		end =false;
	}
	

	public PanelListener(int i) {//생성용
		savePanel = new ArrayList<>();
		jrListUp = new ArrayList<>();
		jrListDown = new ArrayList<>();
	}
	
	
	public PanelListener(JPanel centerP){//생성용
		this.centerP = centerP;
	}
	
	
	public PanelListener(JanggiPanel nowPanel) {
		
		this.nowPanel=nowPanel;
		x=nowPanel.x;
		y=nowPanel.y;
	}
	

	public PanelListener(GameRoomFrame gameRoomFrame) {
		f=gameRoomFrame;
	}
	
	
	@Override	
	public void mouseClicked(MouseEvent e) {
		searchAllPanel();
		//System.out.println("선택한 위치=("+x+","+y+")jr.rk:"+nowPanel.jr.rk+",jr.color:"+nowPanel.jr.color);
		//System.out.println("지금 턴은 누구지?"+f.user.turn+"지금차례는 빨f,파t"+myTurn);
		
		if(f.user.turn %2 !=0 && !myTurn)
			check();
		else if(myTurn&& f.user.turn %2 ==0)
			check();
	}
	
	
	void check() {
		
			if(nowPanel.on){
				if(!selectR){
					if(color== nowPanel.jr.color)//빨
						firstClickChoice();
					
				}else if(selectR){
					secondClickKillR();
					remove_possibelJP();
					remove_savePanel();
				}
			}else if(!nowPanel.on){
				if(selectR){
					secondClickMove();
					remove_possibelJP();
					remove_savePanel();
				}else if(!selectR){
					notChoice(false);
				}else{
					notChoice(true);
				}
			}
			f.centerP.updateUI();
		
	}
	

	void notChoice(boolean tmp) {
		
		if(tmp){
			selectR =false;
			savePanel.remove(savePanelCnt);
			savePanelCnt--;
			possiblejangp = new ArrayList<>();
		}else if(!tmp){
			
		}
	}
	
	
	void firstClickChoice() {
		
		possiblejangp= new ArrayList<>();	//이동가능한 장기알패널을 담을  리스트를 만든다
		savePanelCnt++;	//처음 선택한 패널정보를 꺼낼때 사용
		savePanel.add(nowPanel); //처음 선택한 패널정보를 저장한다
		possiblePos=new ArrayList<>();	//이동가능 한 좌표를 담을 리스트를 만든다
		possiblePos = jrListUp.get(0).cal(nowPanel);	//장기알클레스의 cal을 이용해 이동가능한 좌표값을 구한다
	
		for (JanggiPanel buf : nowPanel.allJPanel) {
			for(int[] buf2 : possiblePos){
				if( buf2[0]==buf.x && buf2[1]==buf.y ){
					buf.possibleMove=true;
					if(!buf.on){
						buf.p.add(buf.pickimg(nowPanel.jr.rk, nowPanel.jr.color)); 
					}else{
						buf.p.add(buf.pickimg()); 
					}
					possiblejangp.add(buf);
				}
			}
		}
		selectR =true;
	}
	
	
	void secondClickMove() {
		
		if(nowPanel.possibleMove && savePanel.get(savePanelCnt)!=nowPanel){	
			move();
			change_p_num(savePanel.get(savePanelCnt),nowPanel,1);//현재 좌표와 전에 좌표가 상대에의 어느지점에 보이는지 계산한다
		//	System.out.println("이동-장기알 턴값 확인:"+jangturn);
			if(jangturn){
				checkOutKing();
			}
			checkKing();
		}else {
			System.out.println("범위벗어남");
		}
	}
	
	
	void colorJlb(boolean color,JanggiPanel nowPanel){
		if(color)
		{
			if(nowPanel.jr.rk==1)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/r_1.png"));
			else if(nowPanel.jr.rk==2)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/r_2.png"));
			else if(nowPanel.jr.rk==3)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/r_3.png"));
			else if(nowPanel.jr.rk==4)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/r_4.png"));
			else if(nowPanel.jr.rk==5)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/r_5.png"));
			else if(nowPanel.jr.rk==6)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/r_6.png"));
			else if(nowPanel.jr.rk==7)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/r_7.png"));
		}else{
			if(nowPanel.jr.rk==1)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/b_1.png"));
			else if(nowPanel.jr.rk==2)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/b_2.png"));
			else if(nowPanel.jr.rk==3)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/b_3.png"));
			else if(nowPanel.jr.rk==4)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/b_4.png"));
			else if(nowPanel.jr.rk==5)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/b_5.png"));
			else if(nowPanel.jr.rk==6)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/b_6.png"));
			else if(nowPanel.jr.rk==7)
				jb1 =new JLabel(new ImageIcon("photo/rightp_r/b_7.png"));
		}
		
	}
	
	
	void addJLB1(JanggiPanel nowPanel){
		
		if(f.user.roomMaker==1){//내가 방장이면			
			colorJlb(!color, nowPanel);
			if(jb1!=null)
				f.gameLP.add(jb1);
		}else{//내가 참가자면
			colorJlb(!color, nowPanel);
			if(jb1!=null)
				f.gameRP.add(jb1);
		}
		
	}
	
	
	void addJLB2(JanggiPanel nowPanel){
		
		if(f.user.roomMaker==1){//내가 방장이면			
			colorJlb(color, nowPanel);
			if(jb1!=null)
				f.gameRP.add(jb1);
		}else{//내가 참가자면
			colorJlb(color, nowPanel);
			if(jb1!=null)
				f.gameLP.add(jb1);
		}
		
	}
	
	
	void secondClickKillR() {
		if(savePanel.get(savePanelCnt).jr.rk==4 && nowPanel.jr.rk==4 ){
				//System.out.println("		포는 포를 못죽임");
		}else if(savePanel.get(savePanelCnt)!=nowPanel 
					&& savePanel.get(savePanelCnt).jr.color != nowPanel.jr.color)
		{
				boolean gokill = false;	
				
				for(int i =0 ; i<possiblePos.size();i++){
					if(possiblePos.get(i)[0]==nowPanel.x && possiblePos.get(i)[1]==nowPanel.y )
						gokill=true;
				}
				
				if(gokill&&nowPanel.jr.rk==1){
					addJLB1(nowPanel);
					kill();
					
					change_p_num(savePanel.get(savePanelCnt),nowPanel,4);
					end=true;
				}else if(gokill){
					//System.out.println("죽임-장기알 턴값 확인:"+jangturn);
					addJLB1(nowPanel);
					kill();
					change_p_num(savePanel.get(savePanelCnt),nowPanel,2);
					if(jangturn){
						checkOutKing();
					}
					checkKing();
				}	
		}else{	
			System.out.println("		같은편 못죽임,자신못죽임");
		}
	}
	
	
	void kill(){
		f.s.sound(5);
		
		nowPanel.p.remove(nowPanel.jr.lb);						
		nowPanel.jr = savePanel.get(savePanelCnt).jr;
		nowPanel.p.add(savePanel.get(savePanelCnt).jr.lb);	
		
		savePanel.get(savePanelCnt).jr=new JanggiR();
		savePanel.get(savePanelCnt).on=false;					
		selectR=false;											
		nowPanel.on = true;	
		
	}
	

	void move(){
		f.s.sound(5);
	
		nowPanel.jr = savePanel.get(savePanelCnt).jr;
		nowPanel.p.add(nowPanel.jr.lb);
		savePanel.get(savePanelCnt).jr=new JanggiR();
		nowPanel.on=true;
		savePanel.get(savePanelCnt).on=false;
		selectR=false;
	
	}


	void remove_possibelJP() {
		for (int i = 0; i < possiblejangp.size(); i++) {
			possiblejangp.get(i).possibleMove=false;//모든 패널에 이동불가라고 해놓고
			possiblejangp.get(i).p.remove(possiblejangp.get(i).lb_p);//모든 패널에 이동 가능하단 라벨을 제거한다 
		}
	}

	
	void remove_savePanel() {
		
		savePanel.remove(savePanelCnt);
		savePanelCnt--;
		selectR=false;
		if(end)
			f.win();
	}
	
	
	int findPanelNum(int x,int y){//패널의 xy값이 패널리스트에서 몇번째에 위치한지 찾는 매소드
		int num=0;
		for (int i = 0; i < nowPanel.allJPanel.size(); i++) {
			if(nowPanel.allJPanel.get(i).x==x && nowPanel.allJPanel.get(i).y==y){
				num=i;
			}
		}
		return num;
	}
	
	
	void change_p_num(JanggiPanel firstP,JanggiPanel secondP,int kind ){
		
		int fx=0,fy=0,sx=0,sy=0;
		
		fx = 10-firstP.x;	//savepanel의 x좌표
		fy = 11-firstP.y;	//			y좌표
		
		sx = 10-secondP.x;	//nowpanel 의 x좌표
		sy = 11-secondP.y;	//			y좌표
		
		f.timestop();
		f.user.turn++;
		f.user.choiceP=findPanelNum(fx,fy);
		f.user.movingP =findPanelNum(sx,sy);
		f.user.kind =kind;
		f.user.sendindex=14;
	}
	
	
	void checkKing() {
		boolean cking =false;
		
		for(JanggiPanel buf : nowPanel.allJPanel)
		{
			if(buf.on && buf.jr.color!=myTurn)//내알
			{
					ArrayList<int[]> pos = new ArrayList<>();
					pos=jrListUp.get(0).cal(buf);
					
					for(int[]findKing : pos)
					{
						if(nowPanel.allJPanel.get( findPanelNum(findKing[0], findKing[1]) ).jr.rk==1 
								&&nowPanel.allJPanel.get( findPanelNum(findKing[0], findKing[1]) ).jr.color==myTurn )
						{
							cking=true;
						}
					}
			}
		}
		
		if(cking)
		{	
			//System.out.println("장군 말했다");
			f.s.sound(3);
			f.user.ChoiceJP=1;//장군이다,장군변수
			f.user.janggun=1;//장군싸운드 종류 1.장군 2.멍군
			f.user.sendindex=16;
		}
		
	}
	
	void checkOutKing() {
	
		boolean outking=false;
		int cnt=0;
		for(JanggiPanel buf : nowPanel.allJPanel)
		{
			if(buf.on && buf.jr.color==myTurn)//적알
			{
					ArrayList<int[]> pos = new ArrayList<>();
					pos=jrListUp.get(0).cal(buf);
					
					for(int[]findKing : pos)
					{
						if(nowPanel.allJPanel.get( findPanelNum(findKing[0], findKing[1]) ).jr.rk==1 )
						{//왕이라면
							if(nowPanel.allJPanel.get( findPanelNum(findKing[0], findKing[1]) ).jr.color!=myTurn)
							{//내 왕이라면 그대로 장군이지
								cnt++;
							}
						}
					}
			}
		}
		if(cnt==0){
			System.out.println("멍군햇군");
			jangturn=false;
			outking=true;
		}
		
		if(outking)
		{
			System.out.println("멍군 말했다");
			f.s.sound(4);
			f.user.janggun=2;//멍군
			f.user.sendindex=16;
		}
	}
	
	void searchAllPanel() {//내가 선택한 패널이 몇번째에 있는지 구함

		//현재클릭한 패널이 몇번째 패널인지
		for( int i=0 ; i< nowPanel.allJPanel.size() ; i++ )
		{	
			if(nowPanel.allJPanel.get(i).x==this.x && nowPanel.allJPanel.get(i).y==this.y )
				nowPanelNum = i;
		}
		//전에 클릭한 패널이 몇번째 패널인지 구한다
		if(savePanelCnt>=0){
			for( int i=0 ; i< nowPanel.allJPanel.size() ; i++ )
			{	
				if(nowPanel.allJPanel.get(i).x==savePanel.get(savePanelCnt).x && nowPanel.allJPanel.get(i).y==savePanel.get(savePanelCnt).y )
					savePanelNum = i;
			}
		}
	}
	
	
	void setDown(int choice, boolean color) {
		//boolean color =true;  빨강팀 //false이면 파랑팀 
		//한팀당 장기알수 16개 2팀이니 32개 0부터니 31개
		//choice ==0 디폴트값
		//1= 마상상마 2= 상마마상 3= 상마상마 4= 마상마상 
		//1,장2사,3차,4포,5,마,6상,7쫄
		if(choice==1 ){//1,장2사,3차,4포,5,마,6상,7쫄
			//마상 마상
/*맨왼쪽 차*/	jrListDown.add(new JanggiR( nowPanel.allJPanel.get(81).x,nowPanel.allJPanel.get(81).y,3,color,myTurn));
/*맨왼쪽 마*/	jrListDown.add(new JanggiR( nowPanel.allJPanel.get(82).x,nowPanel.allJPanel.get(82).y,5,color,myTurn));
/*맨왼쪽 상*/	jrListDown.add(new JanggiR( nowPanel.allJPanel.get(83).x,nowPanel.allJPanel.get(83).y,6,color,myTurn));
/*맨왼쪽 사*/	jrListDown.add(new JanggiR( nowPanel.allJPanel.get(84).x,nowPanel.allJPanel.get(84).y,2,color,myTurn));
/*맨왼쪽 사*/	jrListDown.add(new JanggiR( nowPanel.allJPanel.get(86).x,nowPanel.allJPanel.get(86).y,2,color,myTurn));
/*맨왼쪽 마*/	jrListDown.add(new JanggiR( nowPanel.allJPanel.get(87).x,nowPanel.allJPanel.get(87).y,5,color,myTurn));
/*맨왼쪽 상*/	jrListDown.add(new JanggiR( nowPanel.allJPanel.get(88).x,nowPanel.allJPanel.get(88).y,6,color,myTurn));
/*맨왼쪽 차*/	jrListDown.add(new JanggiR( nowPanel.allJPanel.get(89).x,nowPanel.allJPanel.get(89).y,3,color,myTurn));
			
		}else if(choice==2){
			//상마 상마										//상마 상마
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(81).x,nowPanel.allJPanel.get(81).y,3,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(82).x,nowPanel.allJPanel.get(82).y,6,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(83).x,nowPanel.allJPanel.get(83).y,5,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(84).x,nowPanel.allJPanel.get(84).y,2,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(86).x,nowPanel.allJPanel.get(86).y,2,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(87).x,nowPanel.allJPanel.get(87).y,6,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(88).x,nowPanel.allJPanel.get(88).y,5,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(89).x,nowPanel.allJPanel.get(89).y,3,color,myTurn));	
		}else if(choice==3){
			//마상상마
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(81).x,nowPanel.allJPanel.get(81).y,3,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(82).x,nowPanel.allJPanel.get(82).y,5,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(83).x,nowPanel.allJPanel.get(83).y,6,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(84).x,nowPanel.allJPanel.get(84).y,2,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(86).x,nowPanel.allJPanel.get(86).y,2,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(87).x,nowPanel.allJPanel.get(87).y,6,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(88).x,nowPanel.allJPanel.get(88).y,5,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(89).x,nowPanel.allJPanel.get(89).y,3,color,myTurn));			
		}else if(choice==4){
			//상마마상
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(81).x,nowPanel.allJPanel.get(81).y,3,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(82).x,nowPanel.allJPanel.get(82).y,6,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(83).x,nowPanel.allJPanel.get(83).y,5,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(84).x,nowPanel.allJPanel.get(84).y,2,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(86).x,nowPanel.allJPanel.get(86).y,2,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(87).x,nowPanel.allJPanel.get(87).y,5,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(88).x,nowPanel.allJPanel.get(88).y,6,color,myTurn));
			jrListDown.add(new JanggiR( nowPanel.allJPanel.get(89).x,nowPanel.allJPanel.get(89).y,3,color,myTurn));					
		}else{
			System.out.println("포진선택==>초이스값 확인");
		}
		
		
		nowPanel.allJPanel.get(81).p.add(jrListDown.get(0).lb);
		nowPanel.allJPanel.get(81).on=true;
		nowPanel.allJPanel.get(81).jr = jrListDown.get(0);
		
		nowPanel.allJPanel.get(82).p.add(jrListDown.get(1).lb);
		nowPanel.allJPanel.get(82).on=true;
		nowPanel.allJPanel.get(82).jr = jrListDown.get(1);
		
		nowPanel.allJPanel.get(83).p.add(jrListDown.get(2).lb);
		nowPanel.allJPanel.get(83).on=true;
		nowPanel.allJPanel.get(83).jr = jrListDown.get(2);
		
		nowPanel.allJPanel.get(84).p.add(jrListDown.get(3).lb);
		nowPanel.allJPanel.get(84).on=true;
		nowPanel.allJPanel.get(84).jr = jrListDown.get(3);
		
		nowPanel.allJPanel.get(86).p.add(jrListDown.get(4).lb);
		nowPanel.allJPanel.get(86).on=true;
		nowPanel.allJPanel.get(86).jr = jrListDown.get(4);
		
		nowPanel.allJPanel.get(87).p.add(jrListDown.get(5).lb);
		nowPanel.allJPanel.get(87).on=true;
		nowPanel.allJPanel.get(87).jr = jrListDown.get(5);
		
		nowPanel.allJPanel.get(88).p.add(jrListDown.get(6).lb);
		nowPanel.allJPanel.get(88).on=true;
		nowPanel.allJPanel.get(88).jr = jrListDown.get(6);
		
		nowPanel.allJPanel.get(89).p.add(jrListDown.get(7).lb);
		nowPanel.allJPanel.get(89).on=true;
		nowPanel.allJPanel.get(89).jr = jrListDown.get(7);
		//왕
		jrListDown.add(new JanggiR( nowPanel.allJPanel.get(13).x,nowPanel.allJPanel.get(13).y,1,color,myTurn));	//왕
		nowPanel.allJPanel.get(76).p.add(jrListDown.get(8).lb);
		nowPanel.allJPanel.get(76).on=true;
		nowPanel.allJPanel.get(76).jr = jrListDown.get(8);	
		//좌포
		jrListDown.add(new JanggiR( nowPanel.allJPanel.get(19).x,nowPanel.allJPanel.get(19).y,4,color,myTurn));	//왕
		nowPanel.allJPanel.get(64).p.add(jrListDown.get(9).lb);
		nowPanel.allJPanel.get(64).on=true;
		nowPanel.allJPanel.get(64).jr = jrListDown.get(9);			
		//우포
		jrListDown.add(new JanggiR( nowPanel.allJPanel.get(25).x,nowPanel.allJPanel.get(25).y,4,color,myTurn));	//왕
		nowPanel.allJPanel.get(70).p.add(jrListDown.get(10).lb);
		nowPanel.allJPanel.get(70).on=true;
		nowPanel.allJPanel.get(70).jr = jrListDown.get(10);		
		//좌쫄1
		jrListDown.add(new JanggiR( nowPanel.allJPanel.get(27).x,nowPanel.allJPanel.get(27).y,7,color,myTurn));	//왕
		nowPanel.allJPanel.get(54).p.add(jrListDown.get(11).lb);
		nowPanel.allJPanel.get(54).on=true;
		nowPanel.allJPanel.get(54).jr = jrListDown.get(11);		
		//좌쫄2
		jrListDown.add(new JanggiR( nowPanel.allJPanel.get(29).x,nowPanel.allJPanel.get(29).y,7,color,myTurn));	//왕
		nowPanel.allJPanel.get(56).p.add(jrListDown.get(12).lb);
		nowPanel.allJPanel.get(56).on=true;
		nowPanel.allJPanel.get(56).jr = jrListDown.get(12);	
		//좌쫄3
		jrListDown.add(new JanggiR( nowPanel.allJPanel.get(31).x,nowPanel.allJPanel.get(31).y,7,color,myTurn));	//왕
		nowPanel.allJPanel.get(58).p.add(jrListDown.get(13).lb);
		nowPanel.allJPanel.get(58).on=true;
		nowPanel.allJPanel.get(58).jr = jrListDown.get(13);	
		//좌쫄4
		jrListDown.add(new JanggiR( nowPanel.allJPanel.get(33).x,nowPanel.allJPanel.get(33).y,7,color,myTurn));	//왕
		nowPanel.allJPanel.get(60).p.add(jrListDown.get(14).lb);
		nowPanel.allJPanel.get(60).on=true;
		nowPanel.allJPanel.get(60).jr = jrListDown.get(14);	
		//좌쫄5
		jrListDown.add(new JanggiR( nowPanel.allJPanel.get(35).x,nowPanel.allJPanel.get(35).y,7,color,myTurn));	//왕
		nowPanel.allJPanel.get(62).p.add(jrListDown.get(15).lb);
		nowPanel.allJPanel.get(62).on=true;
		nowPanel.allJPanel.get(62).jr = jrListDown.get(15);	
	
		
		if(f.user.color==1)
			new AloneTimer(f).start();
		
	}

	
	void setUp(int choice,boolean color){
		
		//boolean color =true;  빨강팀 //false이면 파랑팀 
		//한팀당 장기알수 16개 2팀이니 32개 0부터니 31개
		//choice ==0 디폴트값
		//1,장2사,3차,4포,5,마,6상,7쫄
		if(choice==1 ){//1,장2사,3차,4포,5,마,6상,7쫄
			//		상마 상마	
/*맨왼쪽 차*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(0).x,nowPanel.allJPanel.get(0).y,3,color,myTurn));
/*맨왼쪽 마*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(1).x,nowPanel.allJPanel.get(1).y,6,color,myTurn));
/*맨왼쪽 상*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(2).x,nowPanel.allJPanel.get(2).y,5,color,myTurn));
/*맨왼쪽 사*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(3).x,nowPanel.allJPanel.get(3).y,2,color,myTurn));
/*맨왼쪽 사*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(5).x,nowPanel.allJPanel.get(5).y,2,color,myTurn));
/*맨왼쪽 마*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(6).x,nowPanel.allJPanel.get(6).y,6,color,myTurn));
/*맨왼쪽 상*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(7).x,nowPanel.allJPanel.get(7).y,5,color,myTurn));
/*맨왼쪽 차*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(8).x,nowPanel.allJPanel.get(8).y,3,color,myTurn));	
		}else if(choice==2){
			//	마상 마상
			/*맨왼쪽 차*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(0).x,nowPanel.allJPanel.get(0).y,3,color,myTurn));
			/*맨왼쪽 마*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(1).x,nowPanel.allJPanel.get(1).y,5,color,myTurn));
			/*맨왼쪽 상*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(2).x,nowPanel.allJPanel.get(2).y,6,color,myTurn));
			/*맨왼쪽 사*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(3).x,nowPanel.allJPanel.get(3).y,2,color,myTurn));
			/*맨왼쪽 사*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(5).x,nowPanel.allJPanel.get(5).y,2,color,myTurn));
			/*맨왼쪽 마*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(6).x,nowPanel.allJPanel.get(6).y,5,color,myTurn));
			/*맨왼쪽 상*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(7).x,nowPanel.allJPanel.get(7).y,6,color,myTurn));
			/*맨왼쪽 차*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(8).x,nowPanel.allJPanel.get(8).y,3,color,myTurn));
			
		}else if(choice==3){
			//마상상마
/*맨왼쪽 차*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(0).x,nowPanel.allJPanel.get(0).y,3,color,myTurn));
/*맨왼쪽 마*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(1).x,nowPanel.allJPanel.get(1).y,5,color,myTurn));
/*맨왼쪽 상*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(2).x,nowPanel.allJPanel.get(2).y,6,color,myTurn));
/*맨왼쪽 사*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(3).x,nowPanel.allJPanel.get(3).y,2,color,myTurn));
/*맨왼쪽 사*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(5).x,nowPanel.allJPanel.get(5).y,2,color,myTurn));
/*맨왼쪽 마*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(6).x,nowPanel.allJPanel.get(6).y,6,color,myTurn));
/*맨왼쪽 상*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(7).x,nowPanel.allJPanel.get(7).y,5,color,myTurn));
/*맨왼쪽 차*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(8).x,nowPanel.allJPanel.get(8).y,3,color,myTurn));				
		}else if(choice==4){
			//상마마상
/*맨왼쪽 차*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(0).x,nowPanel.allJPanel.get(0).y,3,color,myTurn));
/*맨왼쪽 마*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(1).x,nowPanel.allJPanel.get(1).y,6,color,myTurn));
/*맨왼쪽 상*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(2).x,nowPanel.allJPanel.get(2).y,5,color,myTurn));
/*맨왼쪽 사*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(3).x,nowPanel.allJPanel.get(84).y,2,color,myTurn));
/*맨왼쪽 사*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(5).x,nowPanel.allJPanel.get(86).y,2,color,myTurn));
/*맨왼쪽 마*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(6).x,nowPanel.allJPanel.get(6).y,5,color,myTurn));
/*맨왼쪽 상*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(7).x,nowPanel.allJPanel.get(7).y,6,color,myTurn));
/*맨왼쪽 차*/	jrListUp.add(new JanggiR( nowPanel.allJPanel.get(8).x,nowPanel.allJPanel.get(8).y,3,color,myTurn));				
		}else{
			System.out.println("포진선택==>초이스값 확인");
		}
		nowPanel.allJPanel.get(0).p.add(jrListUp.get(0).lb);
		nowPanel.allJPanel.get(0).on=true;
		nowPanel.allJPanel.get(0).jr = jrListUp.get(0);
		
		nowPanel.allJPanel.get(1).p.add(jrListUp.get(1).lb);
		nowPanel.allJPanel.get(1).on=true;
		nowPanel.allJPanel.get(1).jr = jrListUp.get(1);
		
		nowPanel.allJPanel.get(2).p.add(jrListUp.get(2).lb);
		nowPanel.allJPanel.get(2).on=true;
		nowPanel.allJPanel.get(2).jr = jrListUp.get(2);
		
		nowPanel.allJPanel.get(3).p.add(jrListUp.get(3).lb);
		nowPanel.allJPanel.get(3).on=true;
		nowPanel.allJPanel.get(3).jr = jrListUp.get(3);
		
		nowPanel.allJPanel.get(5).p.add(jrListUp.get(4).lb);
		nowPanel.allJPanel.get(5).on=true;
		nowPanel.allJPanel.get(5).jr = jrListUp.get(4);
		
		nowPanel.allJPanel.get(6).p.add(jrListUp.get(5).lb);
		nowPanel.allJPanel.get(6).on=true;
		nowPanel.allJPanel.get(6).jr = jrListUp.get(5);
		
		nowPanel.allJPanel.get(7).p.add(jrListUp.get(6).lb);
		nowPanel.allJPanel.get(7).on=true;
		nowPanel.allJPanel.get(7).jr = jrListUp.get(6);
		
		nowPanel.allJPanel.get(8).p.add(jrListUp.get(7).lb);
		nowPanel.allJPanel.get(8).on=true;
		nowPanel.allJPanel.get(8).jr = jrListUp.get(7);
		//왕
		jrListUp.add(new JanggiR( nowPanel.allJPanel.get(13).x,nowPanel.allJPanel.get(13).y,1,color,myTurn));	//왕
		nowPanel.allJPanel.get(13).p.add(jrListUp.get(8).lb);
		nowPanel.allJPanel.get(13).on=true;
		nowPanel.allJPanel.get(13).jr = jrListUp.get(8);	
		//좌포
		jrListUp.add(new JanggiR( nowPanel.allJPanel.get(19).x,nowPanel.allJPanel.get(19).y,4,color,myTurn));	//왕
		nowPanel.allJPanel.get(19).p.add(jrListUp.get(9).lb);
		nowPanel.allJPanel.get(19).on=true;
		nowPanel.allJPanel.get(19).jr = jrListUp.get(9);			
		//우포
		jrListUp.add(new JanggiR( nowPanel.allJPanel.get(25).x,nowPanel.allJPanel.get(25).y,4,color, myTurn));	//왕
		nowPanel.allJPanel.get(25).p.add(jrListUp.get(10).lb);
		nowPanel.allJPanel.get(25).on=true;
		nowPanel.allJPanel.get(25).jr = jrListUp.get(10);		
		//좌쫄1
		jrListUp.add(new JanggiR( nowPanel.allJPanel.get(27).x,nowPanel.allJPanel.get(27).y,7,color,myTurn));	//왕
		nowPanel.allJPanel.get(27).p.add(jrListUp.get(11).lb);
		nowPanel.allJPanel.get(27).on=true;
		nowPanel.allJPanel.get(27).jr = jrListUp.get(11);		
		//좌쫄2
		jrListUp.add(new JanggiR( nowPanel.allJPanel.get(29).x,nowPanel.allJPanel.get(29).y,7,color,myTurn));	//왕
		nowPanel.allJPanel.get(29).p.add(jrListUp.get(12).lb);
		nowPanel.allJPanel.get(29).on=true;
		nowPanel.allJPanel.get(29).jr = jrListUp.get(12);	
		//좌쫄3
		jrListUp.add(new JanggiR( nowPanel.allJPanel.get(31).x,nowPanel.allJPanel.get(31).y,7,color,myTurn));	//왕
		nowPanel.allJPanel.get(31).p.add(jrListUp.get(13).lb);
		nowPanel.allJPanel.get(31).on=true;
		nowPanel.allJPanel.get(31).jr = jrListUp.get(13);	
		//좌쫄4
		jrListUp.add(new JanggiR( nowPanel.allJPanel.get(33).x,nowPanel.allJPanel.get(33).y,7,color,myTurn));	//왕
		nowPanel.allJPanel.get(33).p.add(jrListUp.get(14).lb);
		nowPanel.allJPanel.get(33).on=true;
		nowPanel.allJPanel.get(33).jr = jrListUp.get(14);	
		//좌쫄5
		jrListUp.add(new JanggiR( nowPanel.allJPanel.get(35).x,nowPanel.allJPanel.get(35).y,7,color,myTurn));	//왕
		nowPanel.allJPanel.get(35).p.add(jrListUp.get(15).lb);
		nowPanel.allJPanel.get(35).on=true;
		nowPanel.allJPanel.get(35).jr = jrListUp.get(15);	
	

	
	}
	
	
	public void mouseExited(MouseEvent e) {
		
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}


