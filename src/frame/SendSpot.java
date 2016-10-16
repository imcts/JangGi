package frame;

import java.io.Serializable;
import java.util.*;

class SendSpot implements Serializable {
	String name = "", nickname = "", chat = "", gen = "", gpsu = "", id = "",
			targetid = "",roomChat="",pass="";
	String[] ids, nicknames, gens, gpsus, nos, creates, inwons, players, stats,
			cgpsus, cgens, pgpsu, pgen;
	int index, roomnum1, nextRoonNum, userPosition, myRoomPosition,ready,roomMaker,
		choiceP, movingP, kind, turn,janggun,ChoiceJP;

	public SendSpot() {

	}

	public SendSpot(int index, String id, String name, String nickname,
			String gpsu, String gen) {
		// 0���϶�. ���ǿ� ���Ӹ޼����� �����.
		this.index = index;
		this.name = name;
		this.nickname = nickname;
		this.gpsu = gpsu;
		this.gen = gen;
		this.id = id;
	}

	public SendSpot(int index, String name, String nickname, String chat) {
		// 1���϶�. ���ǿ� ä�� ����.
		this.name = name;
		this.nickname = nickname;
		this.chat = chat;
		this.index = index;
	}

	public SendSpot(int index, String[] id, String[] nickname, String[] gen,
			String[] gpsu) {
		// 2���϶� ���� ������ ����Ʈ�� ����.
		this.index = index;
		this.ids = id;
		this.nicknames = nickname;
		this.gens = gen;
		this.gpsus = gpsu;
	}

	public SendSpot(int index) {
		// 3���϶�, �α׾ƿ��̹Ƿ� �������� �ͼ��� �߻�. //9���̶��, ������ �������� ���� �ϳ��� ������ ������Ʈ �϶�� ���
		this.index = index;
	}

	public SendSpot(int index, String chat, String name, String targetid,
			String id) {
		// 4���϶�, �ӼӸ��̹Ƿ� ä�ó���� ���̵� ������.
		this.index = index;
		this.chat = chat;
		this.name = name;
		this.targetid = targetid;
		this.id = id;

	}

	public SendSpot(int index, int roomnum, String nickname, String gpsu,
			String gen, String name) {
		// 5���϶�, �������. �ε��� ��ȣ�� ���ȣ ������ ������ �޼� ������ ��Ƽ� ������ ������.
		this.index = index;
		this.roomnum1 = roomnum;
		this.nickname = nickname;
		this.gpsu = gpsu;
		this.gen = gen;
		this.name = name;
	}

	public SendSpot(int index, int nextRoomNum, String[] no, String[] create,
			String[] cgpsu, String[] cgen, String[] stat, String[] inwon,
			String[] player, String[] pgpsu, String[] pgen) {
		// 6���϶�, ������ �����鿡�� ���� ������ ��Ƽ� ����������.
		this.index = index;
		this.nextRoonNum = nextRoomNum;
		this.nos = no;
		this.creates = create;
		this.cgpsus = cgpsu;
		this.cgens = cgen;
		this.stats = stat;
		this.inwons = inwon;
		this.players = player;
		this.pgpsu = pgpsu;
		this.pgen = pgen;
	}

	public SendSpot(int index, int myRoomPosition, String nickname,
			String gpsu, String gen) {
		// 7���϶�, ������ �濡 ���������Ƿ�, �������� �ڽ��� �� ���ȣ��, �г���, �޼�, ������ ����.
		this.index = index;
		this.myRoomPosition = myRoomPosition;
		this.nickname = nickname;
		this.gpsu = gpsu;
		this.gen = gen;
	}

	public SendSpot(int index, int myRoomPosition) {
		//8���϶�, ���� �����̰� ���� ������ �ƹ��� ������. 
		this.index=index;
		this.myRoomPosition=myRoomPosition;
	}
	
	//9���̶��, ������ �������� ���� �ϳ��� ������ ������Ʈ �϶�� ���

	//10���̶��, ���� �����̰�, ������ ������ �������� �ش� ����� �����϶�� ����.
	
	//11���̶��, ���� ���� �����̰�, �濡�� �������� �������� �ش� ����� �����϶�� ����.
	
	public SendSpot(int index, int myRoomPosition, String nickname, String roomChat) {
		//12���̶�� �濡�� �ϴ� �Ϲ� ä�õ�.  //13���̶��, ������ �濡 ���ٴ� �޽����� ������. 
		this.index=index;
		this.myRoomPosition=myRoomPosition;
		this.nickname=nickname;
		this.roomChat=roomChat;
	}
	
	//13���̶�� ������ �濡 ���Դٴ� �޼��� ����. 
	
	public SendSpot(int index, int myRoomPosition, int ready) {
		//14���̶��, ������ ����Ǵ� ��ŸƮ�� ������.
		this.index=index;
		this.myRoomPosition=myRoomPosition;
		this.ready=ready;
	}
	
	//15���̶��, ���� �����̰� ������ ���� ��ư�� ������ ���� ��ŸƮ ��ư�� ������. 
	
	//16���̶��, ���� ���⼱���� �Ͽ���. 

	public SendSpot(int index, int myRoomPosition, int roomMaker, int choiceP, int movingP, int kind, int turn,String nickname, String pass) {
	      //17���̶��, ���ӹ濡�� ������ ����ڿ��� 4���� ����Ʈ �� ����. 
	      this.index=index;
	      this.myRoomPosition=myRoomPosition;
	      this.choiceP=choiceP;
	      this.movingP=movingP;
	      this.kind=kind;
	      this.roomMaker=roomMaker;
	      this.turn=turn;
	      this.nickname=nickname;
	      this.pass=pass;
	   }
	
   //18,this.user.myRoomPosition,this.user.roomMaker,this.user.nickname,this.user.gpsu
   public SendSpot(int index,int myRoomPosition, int roomMaker,String nickname, String gpsu) {
      //18���̶��, ������ ������ ������� ������ ������Ʈ ������. 
      this.index=index;
      this.myRoomPosition=myRoomPosition;
      this.roomMaker=roomMaker;
      this.nickname=nickname;
      this.gpsu=gpsu;

   }
	
   public SendSpot(int index, int myRoomposition, int roomMaker, int janggun,int ChoiceJP) {
       //19���̶�� �屺�Ͻ�. 
       this.index=index;
       this.myRoomPosition=myRoomposition;
       this.roomMaker=roomMaker;
       this.janggun=janggun;
       this.ChoiceJP=ChoiceJP;
    }
	
	
}
