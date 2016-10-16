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

	// 1000,700����
	// ȭ�� ����
	int x = 1000;
	int y = 700;
	int cx = 700;
	int rp_x_size = x - cx - 15;// ������ �г��� xũ��
	int e_up_y = 200;
	User user;
	int time = 0;
	int utime = 0;
	// int go=0;

	int myjinyoung = 0; // ���� : 1= ����� 2= �󸶸��� 3= �󸶻� 4= ���󸶻�
	int youjinyoung = 0;

	public GameRoomFrame(User user, StayR stayRoom) {
		// 1000,700����
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
		this.choice.add("�� ��");

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
		this.start = new JButton("go"); // �����ư�̹�����, ��ŸƮ ��ư �̹��� �����Ұ�.
		this.start.setBounds(5, 409, 265, 30);
		this.start.addActionListener(this);

		this.giveup = new JButton("giveup", new ImageIcon("photo/giveup.jpg")); // �����
																				// �ൿ.
		this.giveup.setBounds(95, 440, 88, 30);
		this.giveup.addActionListener(this);

		this.nextturn = new JButton("nextturn", new ImageIcon(
				"photo/nextTurn.jpg")); // �� ���� ���� ��Ŵ.
		this.nextturn.setBounds(5, 440, 88, 30);
		this.nextturn.addActionListener(this);

		this.exit = new JButton("exit", new ImageIcon("photo/Exit.jpg"));// ����
																			// ������.
																			// ���
																			// ��
																			// �ʱ�ȭ
																			// ��
																			// �̵�.
		this.exit.setBounds(185, 440, 85, 30);
		this.exit.addActionListener(this);

		this.totMinute = new JLabel("ToTal play Time : 30��");
		this.totMinute.setBounds(5, 135, 180, 30);
		this.totMinute.setBackground(new Color(255, 255, 255));
		this.totMinute.setFont(new Font(null, Font.BOLD, 15));
		this.totMinute.setForeground(new Color(157, 23, 23));

		this.totSecond = new JLabel("00��");
		this.totSecond.setBounds(170, 135, 280, 30);
		this.totSecond.setBackground(new Color(255, 255, 255));
		this.totSecond.setFont(new Font(null, Font.BOLD, 15));
		this.totSecond.setForeground(new Color(157, 23, 23));

		this.ptimer = new JLabel("My Turn Time : 30��");
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
		// ��-����� �г�

		centerP.setBounds(0, 0, cx, cx);
		centerP.setBackground(new Color(204, 166, 61));
		centerP.setLayout(null);
		this.backL = new JLabel(new ImageIcon("photo/backL.jpg"));
		this.backL.setBounds(0, 0, cx, cx);

		ImageIcon img = new ImageIcon("photo/janggiPan.jpg");
		JLabel pan = new JLabel(img);

		// ����� �̹���
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
		String[] no = sendSpot.nos; // ���ȣ�� ����ִ� �迭 .
		String[] create = sendSpot.creates; // ������ �г���
		String[] cgpsu = sendSpot.cgpsus; // ������ �޼�.
		String[] cgen = sendSpot.cgens;// ������ ����.
		String[] stat = sendSpot.stats;// ���� ������.
		String[] inwon = sendSpot.inwons;// �ο����� �迭.
		String[] player = sendSpot.players;// �÷��̾��� �г���
		String[] pgpsu = sendSpot.pgpsu; // �÷��̾��� �޼�.
		String[] pgen = sendSpot.pgen;// �÷��̾��� ����.

		int index = -1;
		for (int i = 0; i < no.length; i++) { // 0��° �濡 �ִٸ� �ε����� 0��.
			index++;
			if (myRoomPosition.equals(no[i])) { // ���� �ִ� ���� ��ġ�� 1�̶��, no[0]����
				// ���װ�. �׷� �ε��� ���� 0�̸����.
				break;
			}
		}// �迭�� ������� ������ ���ٰ� �ھƹ��� ������ ����.

		if (create[index].equals(this.user.nickname)) { // ���� ��� �濡 �ִµ�, �� ����
														// ������ ���µɶ�,
			// �� ���� ���� ���� �� �г��Ӱ� �����ϴٸ�,
			this.user.roomMaker = 1; // ���� �����̴�!
			this.start.setIcon(new ImageIcon("photo/startGame.jpg"));
		}

		this.user.roomInwon = Integer.parseInt(inwon[index]);

		this.rightP.repaint();
		this.cnickname.setText(create[index]);
		this.cgpsu.setText(cgpsu[index]);

		this.pnickname.setText(player[index]);
		this.pgpsu.setText(pgpsu[index]);

		if (cgen[index].equals("����")) {// ������ ������ �����̰�
			if (create[index].equals(this.user.nickname)) {// �� ������ �г��Ӱ� ���� �г�����
															// ���ٸ�
				this.cgen.setIcon(new ImageIcon(gen[2]));
			} else {
				this.cgen.setIcon(new ImageIcon(gen[0])); // �ƴ϶�� �׳� ����.
			}
		} else if (cgen[index].endsWith(" ")) {
			this.cgen.setIcon(new ImageIcon("photo/white.jpg"));
		} else {
			if (create[index].equals(this.user.nickname)) {// �� ������ �г��Ӱ� ���� �г�����
															// ���ٸ�
				this.cgen.setIcon(new ImageIcon(gen[3]));
			} else {
				this.cgen.setIcon(new ImageIcon(gen[1])); // �ƴ϶�� �׳� ����.
			}
		}

		if (pgen[index].equals("����")) {
			if (player[index].equals(this.user.nickname)) {// ���� �÷��̾��� ������ �����̰�,
															// ���� �г����� ���ٸ�.
				this.pgen.setIcon(new ImageIcon(gen[2]));

			} else {
				this.pgen.setIcon(new ImageIcon(gen[0]));
			}
		} else if (pgen[index].endsWith(" ")) {
			this.pgen.setIcon(new ImageIcon("photo/white.jpg"));
		} else {
			if (player[index].equals(this.user.nickname)) {// ���� �÷��̾��� ������ �����̰�,
															// ���� �г����� ���ٸ�.
				this.pgen.setIcon(new ImageIcon(gen[3]));

			} else {
				this.pgen.setIcon(new ImageIcon(gen[1]));
			}
		}

		if (pgen[index].equals(" ") && this.user.roomMaker == 1) {// ������ ���� ����
																	// �����̶��,
			this.start.setEnabled(false);
			this.user.ready = 0;
		}

		this.rightP.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.user.userPosition == 2 && !(this.txt.getText().equals(""))
				&& this.choice.getSelectedItem().equals("�� ��")) {
			this.user.roomchat = this.txt.getText();
			this.user.sendindex = 9;
			this.txt.setText("");
		}

		if (e.getActionCommand().equals("giveup")) {

			System.out.println(this.user.roomMaker + "�����Ŀ ��ȣ");
			this.user.winner = 2; // ���� �й� ������, 2������ ����.
			this.user.sendindex = 13;
			if (this.user.roomMaker == 1) { // ���� �����̶��.
				this.exit.setEnabled(true);
				this.nextturn.setEnabled(false);
				this.giveup.setEnabled(false);
				this.lose();

			} else if (this.user.roomMaker == 2) {// ���� �������.
				this.start.setEnabled(true);
				this.exit.setEnabled(true);
				this.nextturn.setEnabled(false);
				this.giveup.setEnabled(false);
				this.lose();

			}

		}

		if (e.getActionCommand().equals("go")) {
			if (this.user.roomMaker == 1 && this.user.ready == 2) {// ���� �����̶��.
				this.user.sendindex = 12;
				goStart();
				choiceJinyoung();
			} else if (this.user.roomMaker == 2) {// ���� �������
				this.user.sendindex = 11;
			}
		}

		if (e.getActionCommand().equals("exit")) {
			this.ta.setText("");
			this.chat = "";
			if (this.user.roomMaker == 1) {// ���� �����̾����ٸ�.
				if (this.user.roomInwon == 1) {// ���� �����̰� �濡 �ο��� ������.
					this.user.roomMaker = 0; // ���� ����ų� �������� ���� ���·� ����.
					this.user.userPosition = 1; // ������ �������� ���Ƿ� ����.
					this.user.sendindex = 6;
					this.user.ready = 0;
					this.setVisible(false);
					this.stayRoom.setVisible(true);
				} else { // ���� �����̰� �濡 �ο��� 1���̻��������� ����.
					this.user.roomMaker = 0;
					this.user.userPosition = 1;
					this.user.sendindex = 7;
					this.setVisible(false);
					this.stayRoom.setVisible(true);
					this.user.userPosition = 1;
					this.user.ready = 0;
				}
			} else if (this.user.roomMaker == 2) {// ���� �����ڿ��ٸ�.
				this.ta.setText("");
				this.chat = "";
				this.user.roomMaker = 0; // ���Ƿ� �̵�.
				this.user.userPosition = 1;// ���Ƿ� �̵�.
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
				this.user.pass = " ���� ���� �ѱ�̽��ϴ�.";
				this.user.kind = 3;
				user.sendindex = 14;
			} else if (plistener.myTurn == true && user.turn % 2 == 0) {
				this.AloneTimerBreaker = 1;
				this.user.turn = +user.turn + 1;
				this.user.kind = 3;
				this.user.pass = " ���� ���� �ѱ�̽��ϴ�.";
				user.sendindex = 14;
			}
		}
	}

	void clear() {// ȭ�� ����

		for (int i = 0; i < listPanel.size(); i++) {
			listPanel.get(i).p.removeAll();

		}
		// totMinute.setText(" ");
		totMinute.setText("ToTal play Time : " + "29��");
		totSecond.setText("60��");
		user.turn = 0;
		new PanelListener("�ʱ�ȭ");
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

		JanggiPanel tmp = new JanggiPanel(2);// allJPanel �ʱ�ȭ
		new PanelListener(1);// savaPanel �ʱ�ȭ

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
			 * �г� �Ⱥ��̰� �ϴ�ȿ�� :setOpaque(false)
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
		// ������ ���� �Ǵ� �޼ҵ带 �����Ұ�.
		s.sound(0);
		this.user.winner = 0;
		this.start.setEnabled(false); // �����ư ��Ȱ��ȭ.
		this.exit.setEnabled(false); // ������ ��ư ��Ȱ��ȭ.
		this.giveup.setEnabled(true);
		this.nextturn.setEnabled(true);

		new TimerThread(this).start();
	}

	public void goEnd() {
		// ������ ���� �Ǵ� �޼ҵ带 �����Ұ�.
		s.sound(1);
		this.user.winner = 0;
		this.giveup.setEnabled(false);
		this.nextturn.setEnabled(false);
		if (user.roomMaker == 1)
			this.start.setEnabled(false); // �����ư ��Ȱ��ȭ.
		else
			this.start.setEnabled(true);
		this.exit.setEnabled(true); // ������ ��ư ��Ȱ��ȭ.
	}

	void change_savePanel_nowPanel(int choiceP2, int movingP, int kind, int turn) {

		this.user.turn = turn;
		if (kind == 1) {// �̵�
			s.sound(5);
			plistener.nowPanel.allJPanel.get(movingP).jr = listPanel
					.get(choiceP2).jr;
			plistener.nowPanel.allJPanel.get(movingP).p.add(listPanel
					.get(choiceP2).jr.lb);
			plistener.nowPanel.allJPanel.get(choiceP2).jr = new JanggiR();
			plistener.nowPanel.allJPanel.get(movingP).on = true;
			plistener.nowPanel.allJPanel.get(choiceP2).on = false;

			new AloneTimer(this).start();

		} else if (kind == 2) {// ����
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

		} else if (kind == 3) {// �ϳѱ�

			new AloneTimer(this).start();

		} else if (kind == 4) {// �������� ���� �̰ܼ�
			lose();
		} else if (kind == 5) {// ���������޴�
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

				if (user.gpsu.equals("18��")) {
					user.jum = 0;
					user.gpsu = String.valueOf("18��");
					user.sendindex = 15;
					stayRoom.setMyInfo();
				} else if (user.gpsu.equals("1��")) {
					// ��޶�����,����100�����θ����
					/*
					 * String[] temp2 = user.gpsu.split("��");
					 * System.out.println("temp2temp2temp2"+temp2[0]); int g =
					 * Integer.parseInt(temp2[0]);
					 */
					int g2 = 2;
					// ���� ������
					user.jum = 100;
					user.gpsu = String.valueOf(g2 + "��");
					user.sendindex = 15;
					stayRoom.setMyInfo();
					// ��� ������
					new DataDAO().dataEdit2(user.id, "jum", "100", "gpsu",
							String.valueOf(g2) + "��");
				} else {
					// ��޶�����,����100�����θ����
					String[] temp2 = user.gpsu.split("��");

					int g = Integer.parseInt(temp2[0]);
					int g2 = g - 1;
					// ���� ������
					user.jum = 100;
					user.gpsu = String.valueOf(g2 + "��");
					user.sendindex = 15;
					stayRoom.setMyInfo();
					// ��� ������
					new DataDAO().dataEdit2(user.id, "jum", "100", "gpsu",
							String.valueOf(g2) + "��");
				}
			} else {

				// ����-10;
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
				if (user.gpsu.equals("1��")) {
					user.jum = 100;
					user.gpsu = String.valueOf("1��");
					user.sendindex = 15;
					stayRoom.setMyInfo();
				} else {

					String[] temp2 = user.gpsu.split("��");
					int g = Integer.parseInt(temp2[0]);
					int g2 = g - 1;
					// ����������
					user.jum = 0;
					user.gpsu = String.valueOf(g2 + "��");
					user.sendindex = 15;
					stayRoom.setMyInfo();
					// ��񰡱�
					new DataDAO().dataEdit2(user.id, "jum", "0", "gpsu",
							String.valueOf(g2) + "��");
				}
			} else {

				if (user.gpsu.equals("1��")) {
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

		String[] temp = cgpsu.getText().split("��");

		int cg = Integer.parseInt(temp[0]);
		temp = pgpsu.getText().split("��");
		int pg = Integer.parseInt(temp[0]);
		if (cg == pg) {

			cg--;
		}
		checkGrand(cg, pg);

	}

	void checkGrand(int c, int p) {// ��������� ����Ѵ�
		// ����� ũ�� ������,�̰� ���̾ƴϴ�
		// ����� ������ �Ķ���,�̰� ���̴�
		// plistener.jrList = new ArrayList<>();
		// new HanPojin(this); �Ǵ� new ChoPojin(this)
		if (c < p) {
			if (user.roomMaker == 1) {
				plistener.color = true; // �� Ȧ����
				plistener.myTurn = false;
				new HanPojin(this);
				user.color = 0;
			} else {
				plistener.color = false;// �� ¦����
				plistener.myTurn = true;
				new ChoPojin(this);
				user.color = 1;
			}
		} else {
			if (user.roomMaker == 1) {
				plistener.color = false;// ��
				plistener.myTurn = true;
				new ChoPojin(this);
				user.color = 1;
			} else {
				plistener.color = true; // ��
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