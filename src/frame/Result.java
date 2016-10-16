package frame;

import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//totdata���� Ȱ�� �� ��. 


public class Result {
    SendSpot sendSpot;
   
    StayR stayRoom;
    User user;
    GameRoomFrame game;
    int index;
    
   public Result(SendSpot sendSpot,StayR stayRoom,User user,GameRoomFrame game) { //�̳��� �׻� �������ǰ��� �˰� �־�� ��. 
      this.sendSpot = sendSpot;
    
      this.stayRoom=stayRoom;
      this.index=sendSpot.index; //��� Ŭ�������� �ε������� ���� ����� ���� tot�������� ���� �ٲپ���. 
      this.user=user;
      this.game=game;
      this.start();
   }

   
   private void start() {
      if(this.index==0 && this.user.userPosition==1){ //ù��° ���� �޼��� ����.
        
         this.stayRoom.res+="["+this.sendSpot.nickname+"] ���� ���� �ϼ̽��ϴ�.\n";
         this.stayRoom.ta.setText(this.stayRoom.res);
         this.stayRoom.ta.setLineWrap(true);
         this.stayRoom.sp.getVerticalScrollBar().setValue(this.stayRoom.sp.getVerticalScrollBar().getMaximum());
      }
      
      else if(this.index==1){ //1���� ����� �Ϲ� ���� ä�� �޼���.
         if(this.user.userPosition==1){
          
            this.stayRoom.res+=this.sendSpot.nickname +" : "+this.sendSpot.chat+"\n";
            this.stayRoom.ta.setText(this.stayRoom.res);
            this.stayRoom.sp.getVerticalScrollBar().setValue(this.stayRoom.sp.getVerticalScrollBar().getMaximum());
         }
      }
      else if(this.index==2){ //���� ������ ����Ʈ.
         this.stayRoom.addUserInfoLabel(this.sendSpot);
      }
      
      //index==3�϶��� �������� �ͼ��� �߻�.
      
      
      else if(this.index==4){//4���϶� �ӼӸ�, ���Ŀ� ���ӹ濡�� �����ٰ�.   
         if(this.user.id.equals(this.sendSpot.targetid)){
            this.stayRoom.res+=this.sendSpot.id + "���� ������ �ӻ��� : "+this.sendSpot.chat+"\n";
            this.stayRoom.ta.setText(this.stayRoom.res);
            this.stayRoom.sp.getVerticalScrollBar().setValue(this.stayRoom.sp.getVerticalScrollBar().getMaximum());
         }
      }
      
      //index==5�϶��� �������� ������ ������ ��Ƽ� ���۽�Ŵ. 
      
      else if(this.index==6){//������ ���ȣ�� �������� �˷��ְ� �������� ���� ����� �������� ���� �����ش�.
         this.stayRoom.roomListSet(this.sendSpot);
         this.game.infoSet(sendSpot);
         this.user.roomNum=sendSpot.nextRoonNum; //���� ���ȣ���� +1 ��Ų���� �־��ش�. 
      }
      
      // 7���϶�, ������ �濡 ���������Ƿ�, �������� �ڽ��� �� ���ȣ��, �г���, �޼�, ������ ����.
      
      
      //8���϶�, ���� �����̰� ���� ������ �ƹ��� ������. 
      
      else if(this.index==9){
         //9���̶��, ������ �������� ���� �ϳ��� ������ ������Ʈ �϶�� ���
         this.user.roomNum=sendSpot.myRoomPosition; //������ ��������� �� ���� ���۽�����.
         this.stayRoom.roomP.removeAll();
         this.stayRoom.repaint();
         this.game.infoReset();
      }
      
      //10���̶��, ���� �����̰�, ������ ������ �������� �ش� ����� �����϶�� ����.
      
      //11���̶��, ���� ���� �����̰�, �濡�� �������� �������� �ش� ����� �����϶�� ����.
      else if(this.index==12){
         //12���̶�� �濡�� �ϴ� �Ϲ� ä�õ�.  //13���̶��, ������ �濡 ���ٴ� �޽����� ������. 
         if(this.user.myRoomPosition==sendSpot.myRoomPosition){
            this.game.chat+=sendSpot.nickname+" : "+sendSpot.roomChat+"\n";
            this.game.ta.setText(this.game.chat);
            this.game.scroll.getVerticalScrollBar().setValue(this.game.scroll.getVerticalScrollBar().getMaximum());
         }
      }
      
      else if(this.index==13){   //13���̶�� ������ �濡 ���Դٴ� �޼��� ����. 
         if(this.user.myRoomPosition==sendSpot.myRoomPosition){
            this.game.chat+=sendSpot.nickname+"���� ���� �ϼ̽��ϴ�.\n";
            this.game.ta.setText(this.game.chat);
            this.game.scroll.getVerticalScrollBar().setValue(this.game.scroll.getVerticalScrollBar().getMaximum());
         }
      }
      else if(this.index==14){      //14���̶��, ������ ���� ������. ���� �����̰�. 
         if(this.user.myRoomPosition==sendSpot.myRoomPosition && this.user.roomMaker==1){//���� �ִ� ���ȣ�� ����, ���� �����̶��, 
            this.game.start.setEnabled(true);
            this.user.ready=2;
         }
      }
      else if(this.index==15){ //15���̶�� ���� �����̰�, ������ ���� ������ ������.
         if(this.user.myRoomPosition==sendSpot.myRoomPosition && this.user.roomMaker==2){
            this.user.ready=2;
            game.goStart();
            this.game.choiceJinyoung();
            
         }
      }
      else if(this.index==16){//16���̶�� �����ϴ� �������� �����ư�� ������, �������ڰ� �ڽ��� ���ʹ�ȣ�� 2�����κ�������. 
         if(this.user.myRoomPosition==sendSpot.myRoomPosition && this.user.winner==0){//���ȣ�� ����, ���� �й��ڰ��ƴ϶��. 
            if(this.user.roomMaker==1){//���� �����ε� ���⼱�� �޾Ҵٸ�. 
               this.game.win();
               this.game.exit.setEnabled(true);
               this.game.nextturn.setEnabled(false);
               this.game.giveup.setEnabled(false);
            }
            else if(this.user.roomMaker==2 ){//���� �����ε� ���⼱�� �޾Ҵٸ�, ���� ��ư Ȱ��ȭ. ������ Ȱ��ȭ.
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