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
		// 0번일때. 대기실에 접속메세지를 띄워줌.
		this.index = index;
		this.name = name;
		this.nickname = nickname;
		this.gpsu = gpsu;
		this.gen = gen;
		this.id = id;
	}

	public SendSpot(int index, String name, String nickname, String chat) {
		// 1번일때. 대기실에 채팅 전송.
		this.name = name;
		this.nickname = nickname;
		this.chat = chat;
		this.index = index;
	}

	public SendSpot(int index, String[] id, String[] nickname, String[] gen,
			String[] gpsu) {
		// 2번일때 대기실 접속자 리스트를 전송.
		this.index = index;
		this.ids = id;
		this.nicknames = nickname;
		this.gens = gen;
		this.gpsus = gpsu;
	}

	public SendSpot(int index) {
		// 3번일때, 로그아웃이므로 서버에서 익셉션 발생. //9번이라면, 서버가 유저에게 방이 하나도 없으니 리페인트 하라고 명령
		this.index = index;
	}

	public SendSpot(int index, String chat, String name, String targetid,
			String id) {
		// 4번일때, 귓속말이므로 채팅내용과 아이디를 보낸다.
		this.index = index;
		this.chat = chat;
		this.name = name;
		this.targetid = targetid;
		this.id = id;

	}

	public SendSpot(int index, int roomnum, String nickname, String gpsu,
			String gen, String name) {
		// 5번일때, 방생성시. 인덱스 번호와 방번호 유저의 정보와 급수 성별을 담아서 서버로 보낸다.
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
		// 6번일때, 서버가 유저들에게 방의 정보를 담아서 보내버린다.
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
		// 7번일때, 유저가 방에 참가했으므로, 서버에게 자신이 들어간 방번호와, 닉네임, 급수, 성별을 전송.
		this.index = index;
		this.myRoomPosition = myRoomPosition;
		this.nickname = nickname;
		this.gpsu = gpsu;
		this.gen = gen;
	}

	public SendSpot(int index, int myRoomPosition) {
		//8번일때, 내가 방장이고 방의 유저가 아무도 없을떄. 
		this.index=index;
		this.myRoomPosition=myRoomPosition;
	}
	
	//9번이라면, 서버가 유저에게 방이 하나도 없으니 리페인트 하라고 명령

	//10번이라면, 내가 방장이고, 유저가 있으니 서버에게 해당 명령을 수행하라고 전송.
	
	//11번이라면, 내가 참가 유저이고, 방에서 나왔으니 서버에게 해당 명령을 수행하라고 전송.
	
	public SendSpot(int index, int myRoomPosition, String nickname, String roomChat) {
		//12번이라면 방에서 하는 일반 채팅들.  //13번이라면, 유저가 방에 들어갔다는 메시지를 보내줌. 
		this.index=index;
		this.myRoomPosition=myRoomPosition;
		this.nickname=nickname;
		this.roomChat=roomChat;
	}
	
	//13번이라면 유저가 방에 들어왔다는 메세지 전송. 
	
	public SendSpot(int index, int myRoomPosition, int ready) {
		//14번이라면, 유저가 레디또는 스타트를 눌렀음.
		this.index=index;
		this.myRoomPosition=myRoomPosition;
		this.ready=ready;
	}
	
	//15번이라면, 내가 방장이고 유저가 레디 버튼을 눌러서 내가 스타트 버튼을 눌렀음. 
	
	//16번이라면, 내가 포기선언을 하였음. 

	public SendSpot(int index, int myRoomPosition, int roomMaker, int choiceP, int movingP, int kind, int turn,String nickname, String pass) {
	      //17번이라면, 게임방에서 상대게임 사용자에게 4가지 포인트 값 전송. 
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
      //18번이라면, 게임이 끝나고 사용자의 정보를 업데이트 시켜줌. 
      this.index=index;
      this.myRoomPosition=myRoomPosition;
      this.roomMaker=roomMaker;
      this.nickname=nickname;
      this.gpsu=gpsu;

   }
	
   public SendSpot(int index, int myRoomposition, int roomMaker, int janggun,int ChoiceJP) {
       //19번이라면 장군일시. 
       this.index=index;
       this.myRoomPosition=myRoomposition;
       this.roomMaker=roomMaker;
       this.janggun=janggun;
       this.ChoiceJP=ChoiceJP;
    }
	
	
}
