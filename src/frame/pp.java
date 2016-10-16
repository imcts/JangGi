package frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



class ChoPojin extends JFrame implements ActionListener {

	Container con;
	JPanel main;
	JLabel msg, i,back;
	GameRoomFrame game;

	JButton btn1;
	JButton btn2;

	int su = 1; //1= 마상상마 2= 상마마상 3= 상마상마 4= 마상마상==> //1마상마상2.상마상마3마상상마4상마마상

	public ChoPojin(GameRoomFrame game) {
		super();
		this.con = getContentPane();
		this.setSize(400, 400);
		this.game=game;
		
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sx = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int sy = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		setLocation(sx, sy);
		setLayout(null);

		this.main = new JPanel();
		this.main.setBounds(0, 0, 400, 400);
		this.main.setBackground(new Color(5, 255, 255));
		this.main.setLayout(null);

		
		this.back=new JLabel(new ImageIcon("photo/pojinBack.jpg"));
		this.back.setBounds(0, 0, 400, 400);
		

		this.i = new JLabel(new ImageIcon("photo/pos5.png"));
		this.i.setBounds(55, 90, 266, 57);

		this.btn1 = new JButton("pojin",new ImageIcon("photo/pojinBt.jpg"));
		this.btn1.setBounds(47, 200, 280, 45);
		this.btn1.setFont(new Font(null, Font.BOLD, 20));
		this.btn1.addActionListener(this);

		this.btn2 = new JButton("choice",new ImageIcon("photo/pojinOK.jpg"));
		this.btn2.setBounds(90, 275, 180, 40);
		this.btn2.setFont(new Font(null, Font.BOLD, 20));
		this.btn2.addActionListener(this);

	
		this.main.add(i);
		this.main.add(btn1);
		this.main.add(btn2);
		this.main.add(back);

		this.con.add(main);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("choice")){
			//System.out.println("pp클래스-초이스전"+game.myjinyoung+",su:"+su);
			game.plistener.setDown(this.su,game.plistener.color);
			game.centerP.updateUI();
			game.user.choiceP=su;
			game.user.kind=5;
			game.user.sendindex=14;
			
			//this.game.goStart();
			//System.out.println("pp클래스-초이스후"+game.myjinyoung+",su:"+su);
			this.dispose();
		}
		if(e.getActionCommand().equals("pojin")){
			this.su++;
			if(this.su>4){
				su=1;
			}
			//1마상마상2.상마상마3마상상마4상마마상
			if(this.su==1){
				
				this.i.setIcon(new ImageIcon("photo/pos5.png")); //마상마상
			}
			else if(this.su==2){
				this.i.setIcon(new ImageIcon("photo/pos8.png")); //상마상마
				
			}
			else if(this.su==3){
				this.i.setIcon(new ImageIcon("photo/pos6.png")); //마상상마
				
			}
			else if(su==4){
				this.i.setIcon(new ImageIcon("photo/pos7.png")); //상마마상
				
			}
		
		}
		
	}

}

class HanPojin extends JFrame implements ActionListener {

	Container con;
	JPanel main;
	JLabel msg, i,back;
	GameRoomFrame game;

	JButton btn1;
	JButton btn2;

	int su = 1; //1= 마상상마 2= 상마마상 3= 상마상마 4= 마상마상 

	public HanPojin(GameRoomFrame game) {
		super();
		this.con = getContentPane();
		this.setSize(400, 400);
		this.game=game;
		
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sx = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int sy = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		setLocation(sx, sy);
		setLayout(null);

		this.main = new JPanel();
		this.main.setBounds(0, 0, 400, 400);
		this.main.setBackground(new Color(5, 255, 255));
		this.main.setLayout(null);

		
		this.back=new JLabel(new ImageIcon("photo/pojinBack.jpg"));
		this.back.setBounds(0, 0, 400, 400);
		

		this.i = new JLabel(new ImageIcon("photo/pos1.png"));
		this.i.setBounds(55, 90, 266, 57);

		this.btn1 = new JButton("pojin",new ImageIcon("photo/pojinBt.jpg"));
		this.btn1.setBounds(47, 200, 280, 45);
		this.btn1.setFont(new Font(null, Font.BOLD, 20));
		this.btn1.addActionListener(this);

		this.btn2 = new JButton("choice",new ImageIcon("photo/pojinOK.jpg"));
		this.btn2.setBounds(90, 275, 180, 40);
		this.btn2.setFont(new Font(null, Font.BOLD, 20));
		this.btn2.addActionListener(this);

	
		this.main.add(i);
		this.main.add(btn1);
		this.main.add(btn2);
		this.main.add(back);

		this.con.add(main);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("choice")){
			//System.out.println("pp클래스-초이스전"+game.myjinyoung+",su:"+su);
			
			game.plistener.setDown(this.su,game.plistener.color);
			game.centerP.updateUI();
			game.user.choiceP=su;
			game.user.kind=5;
			game.user.sendindex=14;
			
			this.dispose();
		}
		if(e.getActionCommand().equals("pojin")){
			this.su++;
			if(this.su>4){
				su=1;
			}
			//1마상마상2.상마상마3마상상마4상마마상
			if(this.su==1){
				this.i.setIcon(new ImageIcon("photo/pos1.png")); //마상마상
				
			}
			else if(this.su==2){
				this.i.setIcon(new ImageIcon("photo/pos2.png")); //상마상마
				
			}
			else if(this.su==3){
				
				this.i.setIcon(new ImageIcon("photo/pos3.png")); //마상상마
			}
			else if(su==4){
				this.i.setIcon(new ImageIcon("photo/pos4.png")); //상마마상
				
			}
		
		}
		
	}

}


class Winner extends JFrame implements ActionListener{
	Container con;
	JButton bt;
	JPanel main;
	GameRoomFrame f;
	public Winner(GameRoomFrame f) {
		super();
		this.f =f;
		f.setEnabled(false);
		this.con = getContentPane();
		this.setSize(600, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sx = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int sy = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		setLocation(sx, sy);
		setLayout(null);
		this.main=new JPanel();
		this.main.setBounds(0, 0, 600, 300);
		this.main.setLayout(null);
		
		this.bt=new JButton(new ImageIcon("photo/Winnergame.jpg"));
		this.bt.setBounds(0, 0, 600, 300);
		this.bt.addActionListener(this);
		
		this.main.add(bt);
		
		
		con.add(main);
		
		
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		f.clear();
		f.setEnabled(true);
		this.dispose();
	}
}
class Looser extends JFrame implements ActionListener{
	Container con;
	JButton bt;
	JPanel main;
	GameRoomFrame f;
	public Looser(GameRoomFrame f) {
		super();
		this.f=f;
		f.setEnabled(false);
		this.con = getContentPane();
		this.setSize(600, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sx = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int sy = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		setLocation(sx, sy);
		setLayout(null);
		this.main=new JPanel();
		this.main.setBounds(0, 0, 600, 300);
		this.main.setLayout(null);
		
		this.bt=new JButton(new ImageIcon("photo/Losegame.jpg"));
		this.bt.setBounds(0, 0, 600, 300);
		this.bt.addActionListener(this);
		
		this.main.add(bt);
		
		
		con.add(main);
		
		
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		f.clear();
		f.setEnabled(true);
		this.dispose();
	}
}
