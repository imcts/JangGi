package frame;

public class TimerThread extends Thread {
   GameRoomFrame game;
   
   public TimerThread(GameRoomFrame game) {
      this.game=game;
   }
   
   
   @Override
   public void run() {
      
      int totMinute=29;
      this.game.totMinute.setText("ToTal play Time : "+totMinute+"분");
      for(int i=60;; i--){
         
         try {
            sleep(1000);
            this.game.totSecond.setText(i+"초");
            if(totMinute==0 && i==0){//전체 시간이 다 됐다면. 
               this.game.TotTimerBreaker=1;
               
               //게임 종료 시킬것. 
               if(game.user.color==0){//빨
             	  game.lose();
               }else{
             	  game.win();
               }
            }
            if(i==0){
               i=59;
               totMinute--;
               this.game.totMinute.setText("ToTal play Time : "+totMinute+"분");
            }
            if(this.game.TotTimerBreaker!=0){
               break;
            }
            
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         
      }
   
   	

   
   }
}
class AloneTimer extends Thread{
	   GameRoomFrame game;
	   
	   public AloneTimer(GameRoomFrame game) {
	      this.game=game;
	   }
	   
	   @Override
	   public void run() {
		   
		   boolean index=false;
		   
	      for(int i=30;i>=0 ; i--){
	         try {
	        	 if(game.index2==1&& game.user.color==1){
	        	 i=60;
	        	 game.index2--;
	        	}
	            if(this.game.AloneTimerBreaker!=0){
	               break;
	            }
	            sleep(1000);
	            
	            this.game.ptimer.setText("My Turn Time : "+i+"초");
	            index=true;
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         }
	      }
	      
	      
	      if(index &&game.AloneTimerBreaker ==0 ){
		     game.user.turn++;
		     game.user.pass=" 님이 시간초과로 턴을 넘기셨습니다.\n";
		     game.user.kind=3;
		     game.user.sendindex=14;
	      }
	      this.game.ptimer.setText("My Turn Time : 30초");
	      this.game.AloneTimerBreaker=0;
	   }
	}
