package frame;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JanggiPanel {
	
	static ArrayList<JanggiPanel> allJPanel ;
	int x;
	int y;
	boolean on=false;// 패널위에 장기알 그림 확인변수
	int dx;	//자신이 그리기 시작하는  x 좌표
	int dy;	//자신이 그리기 시작하는  y 좌표
	boolean possibleMove=false;
	
	JPanel p = new JPanel(); 
	JLabel lb ;
	JLabel lb_p ;	//이동 가능한 패널 표시용
	JanggiR jr=new JanggiR();
	
	
	public JanggiPanel(int not) {
		//listP 생성용 
		allJPanel = new ArrayList<>();
	}	
	public JanggiPanel() {;	}
	
	public JLabel pickimg() {
		lb_p = new JLabel();
		lb_p.setVisible(false);
		return lb_p;
	}
	
	JLabel pickimg(int rk,boolean team)
	{	
		if(team){
			if(rk==1){
				lb_p= new JLabel(new ImageIcon("photo/r/r/r_1_p.png"));
			}
			else if(rk==2){
				lb_p = new JLabel(new ImageIcon("photo/r/r/r_2_p.png"));
			}
			else if(rk==3){
				lb_p = new JLabel(new ImageIcon("photo/r/r/r_3_p.png"));
			}
			else if(rk==4){
				lb_p = new JLabel(new ImageIcon("photo/r/r/r_4_p.png"));
			}
			else if(rk==5){
				lb_p = new JLabel(new ImageIcon("photo/r/r/r_5_p.png"));
			}
			else if(rk==6){
				lb_p = new JLabel(new ImageIcon("photo/r/r/r_6_p.png"));
			}
			else if(rk==7){
				lb_p = new JLabel(new ImageIcon("photo/r/r/r_7_p.png"));
			}
		}else{
			if(rk==1){
				lb_p= new JLabel(new ImageIcon("photo/r/b/b_1_p.png"));
			}
			else if(rk==2){
				lb_p = new JLabel(new ImageIcon("photo/r/b/b_2_p.png"));
			}
			else if(rk==3){
				lb_p = new JLabel(new ImageIcon("photo/r/b/b_3_p.png"));
			}
			else if(rk==4){
				lb_p = new JLabel(new ImageIcon("photo/r/b/b_4_p.png"));
			}
			else if(rk==5){
				lb_p = new JLabel(new ImageIcon("photo/r/b/b_5_p.png"));
			}
			else if(rk==6){
				lb_p = new JLabel(new ImageIcon("photo/r/b/b_6_p.png"));
			}
			else if(rk==7){
				lb_p = new JLabel(new ImageIcon("photo/r/b/b_7_p.png"));
			}
		}
			
		return lb_p;
		
	}



}

