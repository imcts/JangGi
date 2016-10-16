package frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.*;

class GameRoomFrame extends JFrame implements Runnable, ActionListener {
	Container mainCon;
	TextField txt;
	JTextArea ta;
	JScrollPane scroll;
	Choice choice;

	JLabel cgen, pgen, ctimer, ptimer, cnickname, pnickname, cgpsu, pgpsu,
			backR, backL, totMinute, totSecond, gameLlb, gameRlb;

	String chat = "";

	JButton start, giveup, nextturn, exit;

	JPanel rightP = new JPanel();
	JPanel centerP = new JPanel();
	JPanel gameLP = new JPanel();
	JPanel gameRP = new JPanel();
	StayR stayRoom;
	ArrayList<JanggiPanel> listPanel = null;
	ArrayList<JanggiPanel> savePanel = new ArrayList<>();
	PanelListener plistener = new PanelListener();
	SoundPlay s = new SoundPlay();
	int choiceP, movingP, kind, turn, TotTimerBreaker = 0,
			AloneTimerBreaker = 0, index2 = 1;

	// 1000,700기준
	// 화면 설정
	int x = 1000;
	int y = 700;
	int cx = 700;
	int rp_x_size = x - cx - 15;// 오른쪽 패널의 x크기
	int e_up_y = 200;
	User user;
	int time = 0;
	int utime = 0;
	// int go=0;

	int myjinyoung = 0; // 진영 : 1= 마상상마 2= 상마마상 3= 상마상마 4= 마상마상
	int youjinyoung = 0;

	public GameRoomFrame(User user, StayR stayRoom) {
		// 1000,700기준
		super("Jangi-");
		setSize(1000, 700);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sx = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int sy = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		setLocation(sx, sy);

		makePanel();
		makePanelListener();
		leftF();

		this.user = user;

		this.mainCon = getContentPane();
		this.mainCon.setLayout(null);

		this.rightP.setBounds(700, 0, 300, 700);
		this.rightP.setLayout(null);

		this.backR = new JLabel(new ImageIcon("photo/backR.jpg"));
		this.backR.setBounds(0, 0, 300, 700);

		this.ta = new JTextArea();
		this.ta.setLineWrap(true);

		this.scroll = new JScrollPane(ta);
		this.scroll.setBounds(5, 470, 265, 145);

		this.txt = new TextField();
		this.txt.setBounds(5, 620, 265, 20);
		this.txt.addActionListener(this);

		this.choice = new Choice();
		this.choice.add("일 반");

		this.cgen = new JLabel();
		this.cgen.setBounds(5, 5, 130, 100);
		this.pgen = new JLabel();
		this.pgen.setBounds(140, 5, 130, 100);

		this.cnickname = new JLabel();
		this.cnickname.setBounds(5, 110, 110, 30);
		this.cnickname.setForeground(new Color(157, 23, 23));
		this.cnickname.setFont(new Font(null, Font.BOLD, 15));

		this.cgpsu = new JLabel();
		this.cgpsu.setForeground(new Color(157, 23, 23));
		this.cgpsu.setBounds(110, 110, 50, 30);
		this.cgpsu.setFont(new Font(null, Font.BOLD, 15));

		this.pnickname = new JLabel();
		this.pnickname.setForeground(new Color(157, 23, 23));
		this.pnickname.setFont(new Font(null, Font.BOLD, 15));
		this.pnickname.setBounds(140, 110, 110, 30);

		this.pgpsu = new JLabel();
		this.pgpsu.setForeground(new Color(157, 23, 23));
		this.pgpsu.setBounds(245, 110, 70, 30);
		this.pgpsu.setFont(new Font(null, Font.BOLD, 15));
		this.start = new JButton("go"); // 레디버튼이미지와, 스타트 버튼 이미지 병행할것.
		this.start.setBounds(5, 409, 265, 30);
		this.start.addActionListener(this);

		this.giveup = new JButton("giveup", new ImageIcon("photo/giveup.jpg")); // 포기시
																				// 행동.
		this.giveup.setBounds(95, 440, 88, 30);
		this.giveup.addActionListener(this);

		this.nextturn = new JButton("nextturn", new ImageIcon(
				"photo/nextTurn.jpg")); // 내 턴을 종료 시킴.
		this.nextturn.setBounds(5, 440, 88, 30);
		this.nextturn.addActionListener(this);

		this.exit = new JButton("exit", new ImageIcon("photo/Exit.jpg"));// 방을
																			// 나갈것.
																			// 모든
																			// 것
																			// 초기화
																			// 후
																			// 이동.
		this.exit.setBounds(185, 440, 85, 30);
		this.exit.addActionListener(this);

		this.totMinute = new JLabel("ToTal play Time : 30분");
		this.totMinute.setBounds(5, 135, 180, 30);
		this.totMinute.setBackground(new Color(255, 255, 255));
		this.totMinute.setFont(new Font(null, Font.BOLD, 15));
		this.totMinute.setForeground(new Color(157, 23, 23));

		this.totSecond = new JLabel("00초");
		this.totSecond.setBounds(170, 135, 280, 30);
		this.totSecond.setBackground(new Color(255, 255, 255));
		this.totSecond.setFont(new Font(null, Font.BOLD, 15));
		this.totSecond.setForeground(new Color(157, 23, 23));

		this.ptimer = new JLabel("My Turn Time : 30초");
		this.ptimer.setBounds(20, 160, 280, 30);
		this.ptimer.setBackground(new Color(255, 255, 255));
		this.ptimer.setFont(new Font(null, Font.BOLD, 15));
		this.ptimer.setForeground(new Color(157, 23, 23));

		this.gameLP.setBounds(5, 190, 130, 215);
		this.gameLP.setBackground(new Color(255, 242, 233));

		this.gameRP.setBounds(140, 190, 130, 215);
		this.gameRP.setBackground(new Color(255, 242, 233));

		this.rightP.add(totSecond);
		this.rightP.add(gameRP);
		this.rightP.add(gameLP);
		this.rightP.add(nextturn);
		this.rightP.add(exit);
		this.rightP.add(giveup);
		this.rightP.add(start);
		this.rightP.add(cnickname);
		this.rightP.add(cgpsu);
		this.rightP.add(pnickname);
		this.rightP.add(pgpsu);

		this.rightP.add(ptimer);
		this.rightP.add(totMinute);
		this.rightP.add(pgen);
		this.rightP.add(cgen);
		this.rightP.add(scroll);
		this.rightP.add(txt);
		this.rightP.add(backR);

		this.mainCon.add(rightP);

		leftF();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(false);
	}

	void leftF() {
		// 좌-장기판 패널

		centerP.setBounds(0, 0, cx, cx);
		centerP.setBackground(new Color(204, 166, 61));
		centerP.setLayout(null);
		this.backL = new JLabel(new ImageIcon("photo/backL.jpg"));
		this.backL.setBounds(0, 0, cx, cx);

		ImageIcon img = new ImageIcon("photo/janggiPan.jpg");
		JLabel pan = new JLabel(img);

		// 장기판 이미지
		pan.setBounds(0, 5, cx, img.getIconHeight());
		centerP.add(pan);
		centerP.add(backL);
		add(BorderLayout.CENTER, centerP);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public void infoReset() {
		this.cnickname.setText("");
		this.cgpsu.setText("");

		this.pnickname.setText("");
		this.pgpsu.setText("");

		this.cgen.setIcon(new ImageIcon("photo/white.jpg"));

		this.pgen.setIcon(new ImageIcon("photo/white.jpg"));
		this.rightP.repaint();

	}

	public void infoSet(SendSpot sendSpot) {

		String myRoomPosition = "" + this.user.myRoomPosition;
		String[] gen = { "photo/GameMan.jpg", "photo/GameGirl.jpg",
				"photo/GameMyMan.jpg", "photo/GameMyGirl.jpg" };
		String[] no = sendSpot.nos; // 방번호가 담겨있는 배열 .
		String[] create = sendSpot.creates; // 방장의 닉네임
		String[] cgpsu = sendSpot.cgpsus; // 방장의 급수.
		String[] cgen = sendSpot.cgens;// 방장의 성별.
		String[] stat = sendSpot.stats;// 방의 대기상태.
		String[] inwon = sendSpot.inwons;// 인원수의 배열.
		String[] player = sendSpot.players;// 플레이어의 닉네임
		String[] pgpsu = sendSpot.pgpsu; // 플레이어의 급수.
		String[] pgen = sendSpot.pgen;// 플레이어의 성별.

		int index = -1;
		for (int i = 0; i < no.length; i++) { // 0번째 방에 있다면 인덱스는 0번.
			index++;
			if (myRoomPosition.equals(no[i])) { // 내가 있는 방의 위치가 1이라면, no[0]방이
				// 될테고. 그럼 인덱스 값은 0이면되지.
				break;
			}
		}// 배열의 몇번방의 정보를 빼다가 박아버릴 건지를 결정.

		if (create[index].equals(this.user.nickname)) { // 내가 어느 방에 있는데, 그 방의
														// 인포가 리셋될때,
			// 그 방의 방장 값이 내 닉네임과 동일하다면,
			this.user.roomMaker = 1; // 내가 방장이다!
			this.start.setIcon(new ImageIcon("photo/startGame.jpg"));
		}

		this.user.roomInwon = Integer.parseInt(inwon[index]);

		this.rightP.repaint();
		this.cnickname.setText(create[index]);
		this.cgpsu.setText(cgpsu[index]);

		this.pnickname.setText(player[index]);
		this.pgpsu.setText(pgpsu[index]);

		if (cgen[index].equals("남자")) {// 방장의 성별이 남자이고
			if (create[index].equals(this.user.nickname)) {// 그 방장의 닉네임과 나의 닉네임이
															// 같다면
				this.cgen.setIcon(new ImageIcon(gen[2]));
			} else {
				this.cgen.setIcon(new ImageIcon(gen[0])); // 아니라면 그냥 남자.
			}
		} else if (cgen[index].endsWith(" ")) {
			this.cgen.setIcon(new ImageIcon("photo/white.jpg"));
		} else {
			if (create[index].equals(this.user.nickname)) {// 그 방장의 닉네임과 나의 닉네임이
															// 같다면
				this.cgen.setIcon(new ImageIcon(gen[3]));
			} else {
				this.cgen.setIcon(new ImageIcon(gen[1])); // 아니라면 그냥 여자.
			}
		}

		if (pgen[index].equals("남자")) {
			if (player[index].equals(this.user.nickname)) {// 만약 플레이어의 성별이 남자이고,
															// 나와 닉네임이 같다면.
				this.pgen.setIcon(new ImageIcon(gen[2]));

			} else {
				this.pgen.setIcon(new ImageIcon(gen[0]));
			}
		} else if (pgen[index].endsWith(" ")) {
			this.pgen.setIcon(new ImageIcon("photo/white.jpg"));
		} else {
			if (player[index].equals(this.user.nickname)) {// 만약 플레이어의 성별이 남자이고,
															// 나와 닉네임이 같다면.
				this.pgen.setIcon(new ImageIcon(gen[3]));

			} else {
				this.pgen.setIcon(new ImageIcon(gen[1]));
			}
		}

		if (pgen[index].equals(" ") && this.user.roomMaker == 1) {// 상대방이 없고 내가
																	// 방장이라면,
			this.start.setEnabled(false);
			this.user.ready = 0;
		}

		this.rightP.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.user.userPosition == 2 && !(this.txt.getText().equals(""))
				&& this.choice.getSelectedItem().equals("일 반")) {
			this.user.roomchat = this.txt.getText();
			this.user.sendindex = 9;
			this.txt.setText("");
		}

		if (e.getActionCommand().equals("giveup")) {

			System.out.println(this.user.roomMaker + "룸메이커 번호");
			this.user.winner = 2; // 내가 패배 했으니, 2번으로 변경.
			this.user.sendindex = 13;
			if (this.user.roomMaker == 1) { // 내가 방장이라면.
				this.exit.setEnabled(true);
				this.nextturn.setEnabled(false);
				this.giveup.setEnabled(false);
				this.lose();

			} else if (this.user.roomMaker == 2) {// 내가 유저라면.
				this.start.setEnabled(true);
				this.exit.setEnabled(true);
				this.nextturn.setEnabled(false);
				this.giveup.setEnabled(false);
				this.lose();

			}

		}

		if (e.getActionCommand().equals("go")) {
			if (this.user.roomMaker == 1 && this.user.ready == 2) {// 내가 방장이라면.
				this.user.sendindex = 12;
				goStart();
				choiceJinyoung();
			} else if (this.user.roomMaker == 2) {// 내가 유저라면
				this.user.sendindex = 11;
			}
		}

		if (e.getActionCommand().equals("exit")) {
			this.ta.setText("");
			this.chat = "";
			if (this.user.roomMaker == 1) {// 내가 방장이었었다면.
				if (this.user.roomInwon == 1) {// 내가 방장이고 방에 인원이 없을때.
					this.user.roomMaker = 0; // 방을 만들거나 참가하지 않은 상태로 변경.
					this.user.userPosition = 1; // 유저의 포지션은 대기실로 변경.
					this.user.sendindex = 6;
					this.user.ready = 0;
					this.setVisible(false);
					this.stayRoom.setVisible(true);
				} else { // 내가 방장이고 방에 인원이 1명이상이있을때 종료.
					this.user.roomMaker = 0;
					this.user.userPosition = 1;
					this.user.sendindex = 7;
					this.setVisible(false);
					this.stayRoom.setVisible(true);
					this.user.userPosition = 1;
					this.user.ready = 0;
				}
			} else if (this.user.roomMaker == 2) {// 내가 참가자였다면.
				this.ta.setText("");
				this.chat = "";
				this.user.roomMaker = 0; // 대기실로 이동.
				this.user.userPosition = 1;// 대기실로 이동.
				this.user.sendindex = 8;
				this.user.ready = 0;
				this.setVisible(false);
				this.stayRoom.setVisible(true);
			}

		}

		if (e.getActionCommand().equals("nextturn")) {
			if (user.turn % 2 != 0 && plistener.myTurn == false) {
				this.AloneTimerBreaker = 1;
				this.user.turn = +user.turn + 1;
				this.user.pass = " 님이 턴을 넘기셨습니다.";
				this.user.kind = 3;
				user.sendindex = 14;
			} else if (plistener.myTurn == true && user.turn % 2 == 0) {
				this.AloneTimerBreaker = 1;
				this.user.turn = +user.turn + 1;
				this.user.kind = 3;
				this.user.pass = " 님이 턴을 넘기셨습니다.";
				user.sendindex = 14;
			}
		}
	}

	void clear() {// 화면 없앰

		for (int i = 0; i < listPanel.size(); i++) {
			listPanel.get(i).p.removeAll();

		}
		// totMinute.setText(" ");
		totMinute.setText("ToTal play Time : " + "29분");
		totSecond.setText("60초");
		user.turn = 0;
		new PanelListener("초기화");
		centerP.removeAll();
		makePanel();
		makePanelListener();
		leftF();
		goEnd();
		gameLP.removeAll();
		gameRP.removeAll();
		centerP.updateUI();
		rightP.updateUI();
		time = 1;
		index2 = 1;
		this.TotTimerBreaker = 0;
		this.AloneTimerBreaker = 0;
	}

	void makePanel() {

		JanggiPanel tmp = new JanggiPanel(2);// allJPanel 초기화
		new PanelListener(1);// savaPanel 초기화

		listPanel = new ArrayList<>();
		int a = 63;
		int j = 0, k = 0;
		int p_size = 60;

		for (int i = 0; i < 90; i++) {
			listPanel.add(new JanggiPanel());
			listPanel.get(i).dx = 65 + (a * j);
			listPanel.get(i).dy = 18 + (a * k);

			listPanel.get(i).p.setBounds(65 + (a * j), 18 + (a * k), p_size,
					p_size);
			// listPanel.get(i).p.add(new JLabel(""+i+"//"+(j+1)+","+(k+1)));
			// listPanel.get(i).allJPanel.put(i,listPanel.get(i));
			listPanel.get(i).allJPanel.add(i, listPanel.get(i));
			j++;
			k++;
			listPanel.get(i).x = j;
			listPanel.get(i).y = k;
			j--;
			k--;
			j++;
			if (j == 9) {
				j = 0;
				k++;
			}
			/*
			 * 
			 * 패널 안보이게 하는효과 :setOpaque(false)
			 */
			listPanel.get(i).p.setOpaque(false);
			centerP.add(listPanel.get(i).p);
		}
		new PanelListener(this);
		new PanelListener(centerP);

	}

	void makePanelListener() {

		for (int i = 0; i < 90; i++) {
			listPanel.get(i).p.addMouseListener(new PanelListener(listPanel
					.get(i)));
		}
	}

	public void goStart() {
		// 게임이 시작 되는 메소드를 구성할것.
		s.sound(0);
		this.user.winner = 0;
		this.start.setEnabled(false); // 레디버튼 비활성화.
		this.exit.setEnabled(false); // 나가기 버튼 비활성화.
		this.giveup.setEnabled(true);
		this.nextturn.setEnabled(true);

		new TimerThread(this).start();
	}

	public void goEnd() {
		// 게임이 시작 되는 메소드를 구성할것.
		s.sound(1);
		this.user.winner = 0;
		this.giveup.setEnabled(false);
		this.nextturn.setEnabled(false);
		if (user.roomMaker == 1)
			this.start.setEnabled(false); // 레디버튼 비활성화.
		else
			this.start.setEnabled(true);
		this.exit.setEnabled(true); // 나가기 버튼 비활성화.
	}

	void change_savePanel_nowPanel(int choiceP2, int movingP, int kind, int turn) {

		this.user.turn = turn;
		if (kind == 1) {// 이동
			s.sound(5);
			plistener.nowPanel.allJPanel.get(movingP).jr = listPanel
					.get(choiceP2).jr;
			plistener.nowPanel.allJPanel.get(movingP).p.add(listPanel
					.get(choiceP2).jr.lb);
			plistener.nowPanel.allJPanel.get(choiceP2).jr = new JanggiR();
			plistener.nowPanel.allJPanel.get(movingP).on = true;
			plistener.nowPanel.allJPanel.get(choiceP2).on = false;

			new AloneTimer(this).start();

		} else if (kind == 2) {// 죽임
			s.sound(5);
			plistener.addJLB2(plistener.nowPanel.allJPanel.get(movingP));

			plistener.nowPanel.allJPanel.get(movingP).p.remove(listPanel
					.get(movingP).jr.lb);
			plistener.nowPanel.allJPanel.get(movingP).jr = listPanel
					.get(choiceP2).jr;
			plistener.nowPanel.allJPanel.get(movingP).p.add(listPanel
					.get(choiceP2).jr.lb);
			plistener.nowPanel.allJPanel.get(choiceP2).jr = new JanggiR();
			plistener.nowPanel.allJPanel.get(movingP).on = true;
			plistener.nowPanel.allJPanel.get(choiceP2).on = false;

			new AloneTimer(this).start();

		} else if (kind == 3) {// 턴넘김

			new AloneTimer(this).start();

		} else if (kind == 4) {// 겜종료댓다 누가 이겨서
			lose();
		} else if (kind == 5) {// 진영선택햇다
			makeJin(choiceP2);
		}
		centerP.updateUI();
	}

	void jangSound(int kind, int num) {
		if (num != 0) {
			plistener.jangturn = true;
		}

		if (kind == 1)
			s.sound(3);
		else if (kind == 2)
			s.sound(4);
	}

	public void lose() {
		this.TotTimerBreaker = 1;
		this.AloneTimerBreaker = 1;
		// s.sound(1);
		new Looser(this);

		try {

			if (user.jum == 0) {

				if (user.gpsu.equals("18급")) {
					user.jum = 0;
					user.gpsu = String.valueOf("18급");
					user.sendindex = 15;
					stayRoom.setMyInfo();
				} else if (user.gpsu.equals("1급")) {
					// 등급떨구고,점수100점으로만들고
					/*
					 * String[] temp2 = user.gpsu.split("급");
					 * System.out.println("temp2temp2temp2"+temp2[0]); int g =
					 * Integer.parseInt(temp2[0]);
					 */
					int g2 = 2;
					// 서버 보내기
					user.jum = 100;
					user.gpsu = String.valueOf(g2 + "급");
					user.sendindex = 15;
					stayRoom.setMyInfo();
					// 디비 보내기
					new DataDAO().dataEdit2(user.id, "jum", "100", "gpsu",
							String.valueOf(g2) + "급");
				} else {
					// 등급떨구고,점수100점으로만들고
					String[] temp2 = user.gpsu.split("급");

					int g = Integer.parseInt(temp2[0]);
					int g2 = g - 1;
					// 서버 보내기
					user.jum = 100;
					user.gpsu = String.valueOf(g2 + "급");
					user.sendindex = 15;
					stayRoom.setMyInfo();
					// 디비 보내기
					new DataDAO().dataEdit2(user.id, "jum", "100", "gpsu",
							String.valueOf(g2) + "급");
				}
			} else {

				// 점수-10;
				int jum2 = user.jum - 10;
				user.jum = jum2;
				user.sendindex = 15;
				stayRoom.setMyInfo();
				new DataDAO().dataEdit(user.id, "jum", String.valueOf(jum2));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void win() {
		this.TotTimerBreaker = 1;
		this.AloneTimerBreaker = 1;
		// s.sound(1);
		new Winner(this);
		int jum;
		try {
			// jum = new DataDAO().getUserJum(user.id);
			if (user.jum == 100) {
				if (user.gpsu.equals("1급")) {
					user.jum = 100;
					user.gpsu = String.valueOf("1급");
					user.sendindex = 15;
					stayRoom.setMyInfo();
				} else {

					String[] temp2 = user.gpsu.split("급");
					int g = Integer.parseInt(temp2[0]);
					int g2 = g - 1;
					// 서버보내기
					user.jum = 0;
					user.gpsu = String.valueOf(g2 + "급");
					user.sendindex = 15;
					stayRoom.setMyInfo();
					// 디비가기
					new DataDAO().dataEdit2(user.id, "jum", "0", "gpsu",
							String.valueOf(g2) + "급");
				}
			} else {

				if (user.gpsu.equals("1급")) {
					int jum2 = user.jum + 10;
					user.jum = jum2;
					user.sendindex = 15;
					stayRoom.setMyInfo();
					new DataDAO()
							.dataEdit(user.id, "jum", String.valueOf(jum2));

				} else {
					int jum2 = user.jum + 10;
					user.jum = jum2;
					user.sendindex = 15;
					stayRoom.setMyInfo();
					new DataDAO()
							.dataEdit(user.id, "jum", String.valueOf(jum2));

				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void stayRoomin(StayR stayRoom) {
		this.stayRoom = stayRoom;
	}

	public void choiceJinyoung() {
		s.sound(2);

		String[] temp = cgpsu.getText().split("급");

		int cg = Integer.parseInt(temp[0]);
		temp = pgpsu.getText().split("급");
		int pg = Integer.parseInt(temp[0]);
		if (cg == pg) {

			cg--;
		}
		checkGrand(cg, pg);

	}

	void checkGrand(int c, int p) {// 유저등급을 계산한다
		// 계급이 크면 빨강색,이고 선이아니다
		// 계급이 작으면 파랑색,이고 선이다
		// plistener.jrList = new ArrayList<>();
		// new HanPojin(this); 또는 new ChoPojin(this)
		if (c < p) {
			if (user.roomMaker == 1) {
				plistener.color = true; // 빨 홀수턴
				plistener.myTurn = false;
				new HanPojin(this);
				user.color = 0;
			} else {
				plistener.color = false;// 파 짝수턴
				plistener.myTurn = true;
				new ChoPojin(this);
				user.color = 1;
			}
		} else {
			if (user.roomMaker == 1) {
				plistener.color = false;// 파
				plistener.myTurn = true;
				new ChoPojin(this);
				user.color = 1;
			} else {
				plistener.color = true; // 빨
				plistener.myTurn = false;
				new HanPojin(this);
				user.color = 0;
			}
		}

		user.choiceP = myjinyoung;

	}

	void makeJin(int choiceP) {

		plistener.setUp(choiceP, !plistener.color);

		centerP.updateUI();
		;
	}

	public void timestop() {
		this.AloneTimerBreaker = 1;
	}

}