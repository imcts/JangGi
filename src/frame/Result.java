package frame;

import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//totdata적극 활용 할 것. 


public class Result {
    SendSpot sendSpot;
   
    StayR stayRoom;
    User user;
    GameRoomFrame game;
    int index;
    
   public Result(SendSpot sendSpot,StayR stayRoom,User user,GameRoomFrame game) { //이놈은 항상 데이터의값을 알고 있어야 함. 
      this.sendSpot = sendSpot;
    
      this.stayRoom=stayRoom;
      this.index=sendSpot.index; //결과 클래스에서 인덱스값에 따라서 결과를 결정 tot데이터의 값을 바꾸어줌. 
      this.user=user;
      this.game=game;
      this.start();
   }

   
   private void start() {
      if(this.index==0 && this.user.userPosition==1){ //첫번째 접속 메세지 수신.
        
         this.stayRoom.res+="["+this.sendSpot.nickname+"] 님이 접속 하셨습니다.\n";
         this.stayRoom.ta.setText(this.stayRoom.res);
         this.stayRoom.ta.setLineWrap(true);
         this.stayRoom.sp.getVerticalScrollBar().setValue(this.stayRoom.sp.getVerticalScrollBar().getMaximum());
      }
      
      else if(this.index==1){ //1번의 경우라면 일반 대기실 채팅 메세지.
         if(this.user.userPosition==1){
          
            this.stayRoom.res+=this.sendSpot.nickname +" : "+this.sendSpot.chat+"\n";
            this.stayRoom.ta.setText(this.stayRoom.res);
            this.stayRoom.sp.getVerticalScrollBar().setValue(this.stayRoom.sp.getVerticalScrollBar().getMaximum());
         }
      }
      else if(this.index==2){ //대기실 유저의 리스트.
         this.stayRoom.addUserInfoLabel(this.sendSpot);
      }
      
      //index==3일때엔 서버에게 익셉션 발생.
      
      
      else if(this.index==4){//4번일땐 귓속말, 차후에 게임방에도 보내줄것.   
         if(this.user.id.equals(this.sendSpot.targetid)){
            this.stayRoom.res+=this.sendSpot.id + "님의 은밀한 속삭임 : "+this.sendSpot.chat+"\n";
            this.stayRoom.ta.setText(this.stayRoom.res);
            this.stayRoom.sp.getVerticalScrollBar().setValue(this.stayRoom.sp.getVerticalScrollBar().getMaximum());
         }
      }
      
      //index==5일때엔 서버에게 방장의 정보를 담아서 전송시킴. 
      
      else if(this.index==6){//다음번 방번호를 유저에게 알려주고 유저에게 현재 방들의 정보값을 전송 시켜준다.
         this.stayRoom.roomListSet(this.sendSpot);
         this.game.infoSet(sendSpot);
         this.user.roomNum=sendSpot.nextRoonNum; //현재 방번호에서 +1 시킨값을 넣어준다. 
      }
      
      // 7번일때, 유저가 방에 참가했으므로, 서버에게 자신이 들어간 방번호와, 닉네임, 급수, 성별을 전송.
      
      
      //8번일때, 내가 방장이고 방의 유저가 아무도 없을떄. 
      
      else if(this.index==9){
         //9번이라면, 서버가 유저에게 방이 하나도 없으니 리페인트 하라고 명령
         this.user.roomNum=sendSpot.myRoomPosition; //다음에 만들어져야 할 값을 전송시켜줌.
         this.stayRoom.roomP.removeAll();
         this.stayRoom.repaint();
         this.game.infoReset();
      }
      
      //10번이라면, 내가 방장이고, 유저가 있으니 서버에게 해당 명령을 수행하라고 전송.
      
      //11번이라면, 내가 참가 유저이고, 방에서 나왔으니 서버에게 해당 명령을 수행하라고 전송.
      else if(this.index==12){
         //12번이라면 방에서 하는 일반 채팅들.  //13번이라면, 유저가 방에 들어갔다는 메시지를 보내줌. 
         if(this.user.myRoomPosition==sendSpot.myRoomPosition){
            this.game.chat+=sendSpot.nickname+" : "+sendSpot.roomChat+"\n";
            this.game.ta.setText(this.game.chat);
            this.game.scroll.getVerticalScrollBar().setValue(this.game.scroll.getVerticalScrollBar().getMaximum());
         }
      }
      
      else if(this.index==13){   //13번이라면 유저가 방에 들어왔다는 메세지 전송. 
         if(this.user.myRoomPosition==sendSpot.myRoomPosition){
            this.game.chat+=sendSpot.nickname+"님이 입장 하셨습니다.\n";
            this.game.ta.setText(this.game.chat);
            this.game.scroll.getVerticalScrollBar().setValue(this.game.scroll.getVerticalScrollBar().getMaximum());
         }
      }
      else if(this.index==14){      //14번이라면, 유저가 레디를 눌렀음. 내가 방장이고. 
         if(this.user.myRoomPosition==sendSpot.myRoomPosition && this.user.roomMaker==1){//내가 있는 방번호와 같고, 내가 방장이라면, 
            this.game.start.setEnabled(true);
            this.user.ready=2;
         }
      }
      else if(this.index==15){ //15번이라면 내가 유저이고, 방장이 게임 시작을 눌렀음.
         if(this.user.myRoomPosition==sendSpot.myRoomPosition && this.user.roomMaker==2){
            this.user.ready=2;
            game.goStart();
            this.game.choiceJinyoung();
            
         }
      }
      else if(this.index==16){//16번이라면 게임하던 누군가가 포기버튼을 눌렀고, 포기한자가 자신의 위너번호를 2번으로변경했음. 
         if(this.user.myRoomPosition==sendSpot.myRoomPosition && this.user.winner==0){//방번호가 같고, 내가 패배자가아니라면. 
            if(this.user.roomMaker==1){//내가 방장인데 포기선언 받았다면. 
               this.game.win();
               this.game.exit.setEnabled(true);
               this.game.nextturn.setEnabled(false);
               this.game.giveup.setEnabled(false);
            }
            else if(this.user.roomMaker==2 ){//내가 유저인데 포기선언 받았다면, 레디 버튼 활성화. 나가기 활성화.
               this.game.win();
               this.game.start.setEnabled(true);
               this.game.exit.setEnabled(true);
               this.game.nextturn.setEnabled(false);
               this.game.giveup.setEnabled(false);
            }
         }
      }
      else if(this.index==17){
         if(this.user.myRoomPosition==sendSpot.myRoomPosition ){
            if(sendSpot.kind==3){
                  this.game.chat+=sendSpot.nickname+sendSpot.pass+"\n";
                  this.game.ta.setLineWrap(true);
                  this.game.ta.setText(this.game.chat);
                  this.game.scroll.getVerticalScrollBar().setValue(this.game.scroll.getVerticalScrollBar().getMaximum());
                  
            }
         }
         
         if(this.user.myRoomPosition==sendSpot.myRoomPosition && this.user.roomMaker != sendSpot.roomMaker){

            this.game.change_savePanel_nowPanel(sendSpot.choiceP,sendSpot.movingP,sendSpot.kind,sendSpot.turn);
         }
      }
      else if(this.index==19){
         if(this.user.myRoomPosition==sendSpot.myRoomPosition && this.user.roomMaker != sendSpot.roomMaker){
            
            this.game.jangSound(sendSpot.janggun,sendSpot.ChoiceJP);
            
         }
         }
      
   }
   

}