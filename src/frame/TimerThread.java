package frame;

public class TimerThread extends Thread {
   GameRoomFrame game;
   
   public TimerThread(GameRoomFrame game) {
      this.game=game;
   }
   
   
   @Override
   public void run() {
      
      int totMinute=29;
      this.game.totMinute.setText("ToTal play Time : "+totMinute+"��");
      for(int i=60;; i--){
         
         try {
            sleep(1000);
            this.game.totSecond.setText(i+"��");
            if(totMinute==0 && i==0){//��ü �ð��� �� �ƴٸ�. 
               this.game.TotTimerBreaker=1;
               
               //���� ���� ��ų��. 
               if(game.user.color==0){//��
             	  game.lose();
               }else{
             	  game.win();
               }
            }
            if(i==0){
               i=59;
               totMinute--;
               this.game.totMinute.setText("ToTal play Time : "+totMinute+"��");
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
	            
	            this.game.ptimer.setText("My Turn Time : "+i+"��");
	            index=true;
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         }
	      }
	      
	      
	      if(index &&game.AloneTimerBreaker ==0 ){
		     game.user.turn++;
		     game.user.pass=" ���� �ð��ʰ��� ���� �ѱ�̽��ϴ�.\n";
		     game.user.kind=3;
		     game.user.sendindex=14;
	      }
	      this.game.ptimer.setText("My Turn Time : 30��");
	      this.game.AloneTimerBreaker=0;
	   }
	}
