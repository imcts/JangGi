package frame;

import java.io.*;
import java.net.*;
import java.util.*;



class UserAccept extends Thread implements Serializable{
   
   ObjectOutputStream oos;
   ObjectInputStream ois;
   Totserver server;
   Socket socket;
   String name,id,nickname,gen,gpsu;
   
   //방을 만들었을때 삭제 하는 방법과 방에 들어갔을때 삭제하는 방법은 분명히 달라야 한다.  

   
   public UserAccept(Totserver server, Socket socket) throws Exception {
      this.server=server;
      this.socket=socket;
      this.oos=new ObjectOutputStream(this.socket.getOutputStream());
      this.ois=new ObjectInputStream(this.socket.getInputStream());
   }
   
   @Override
   public void run() {
      try {
      while(ois!=null){
         SendSpot sendSpot = ((SendSpot)ois.readObject());
         int index=sendSpot.index; //sendSpot의 index값.
         
         if(index==0){
            this.name=sendSpot.name;
            this.id=sendSpot.id;
            this.nickname=sendSpot.nickname;
            this.gpsu=sendSpot.gpsu;
            this.gen=sendSpot.gen;
            
            server.user.put(sendSpot.id, this.oos); //처음 접속 했다면 서버의 맵에 해당 이름과 oos값을 넣어주고 
            
            server.userList.put(this.id,this.gen );
            server.userList2.put(this.nickname, this.gpsu);
            
            server.SendAll(sendSpot); //바로 모든 유저에게 쏴버림. 그럼 0번으로 받고, 누가 접속했다고 알려줌.
            server.SendList();
            
            if(server.nextroomNum!=0){ //만약 방이 만들어져있다면. 
               server.SendMakeRoom(); //쏴..쏴버려엇!!!! - _-..
            }
            
         }
         else if (index==1){
            server.SendAll(sendSpot); //아니라면 그냥 대기실유저에게 쏴버림.
         }
         else if(index==3){//3번이라면 익셉션 발생.
            throw new Exception();
         }
         else if(index==4){
            server.targetSend(sendSpot);
         }
         else if(index==5){ //방을 생성했다면.
         
            if(this.server.LoseRoomNum.size()!=0){
               Iterator it = this.server.LoseRoomNum.keySet().iterator();
         
            while(it.hasNext()){
               int l = (int)it.next(); 
               if(l==sendSpot.roomnum1){//만약 내가 만든 방번호중에 Lose값이 있다면 그 방번호를 지워주고.
                  this.server.LoseRoomNum.remove(l); //그 방번호를 지워주
                  break;
               }
            }
         }
            
            
            this.server.noGpsu.put(""+sendSpot.roomnum1, sendSpot.gpsu); //방장의 급수
            this.server.noGen.put(""+sendSpot.roomnum1, sendSpot.gen);  //방장의 성별
            this.server.noCreate.put(""+sendSpot.roomnum1, sendSpot.nickname); //방장의 닉네임.
            this.server.noStat.put(""+sendSpot.roomnum1,"대기중"); //방의 상태.
            this.server.noInwon.put(""+sendSpot.roomnum1, "1"); //방의 인원수.
            this.server.noPlayer.put(""+sendSpot.roomnum1, " "); //방의 플레이어. 
            this.server.noPgpsu.put(""+sendSpot.roomnum1, " "); // 방의 플레이어의 급수.
            this.server.noPgen.put(""+sendSpot.roomnum1, " "); //방의 플레이어의 성별.
            //야 잠깐만. 만약에 1번이 만들어지고, 2번이 만들어지고, 3번이 만들어지고, 1번이 지워지면. 다음 방은 1번. 
            // 그다음에 만들어져야할 방은? 로즈룸넘버에 값이 없으므로, 1+1 2번방이 돼버리네?! 
            

            
            this.server.SendMakeRoom();
            }
         
         else if(index==7){
            this.server.noPlayer.put(""+sendSpot.myRoomPosition, sendSpot.nickname);
            this.server.noPgpsu.put(""+sendSpot.myRoomPosition, sendSpot.gpsu);
            this.server.noPgen.put(""+sendSpot.myRoomPosition, sendSpot.gen);
            this.server.noStat.put(""+sendSpot.myRoomPosition, "설정중");
            this.server.noInwon.put(""+sendSpot.myRoomPosition, "2");
            
            
            this.server.SendMakeRoom();
         }
         else if(index==8){//내가 방장이고 방에 아무도 없을때. 해당 방 번호에 해당 하는 값을 전부 삭제.
            this.server.noGpsu.remove(""+sendSpot.myRoomPosition);
            this.server.noGen.remove(""+sendSpot.myRoomPosition);  //방장의 성별
            this.server.noCreate.remove(""+sendSpot.myRoomPosition); //방장의 닉네임.
            this.server.noStat.remove(""+sendSpot.myRoomPosition); //방의 상태.
            this.server.noInwon.remove(""+sendSpot.myRoomPosition); //방의 인원수.
            this.server.noPlayer.remove(""+sendSpot.myRoomPosition); //방의 플레이어. 
            this.server.noPgpsu.remove(""+sendSpot.myRoomPosition); // 방의 플레이어의 급수.
            this.server.noPgen.remove(""+sendSpot.myRoomPosition); //방의 플레이어의 성별.
            
            this.server.LoseRoomNum.put(sendSpot.myRoomPosition, ""); //방이 삭제되면, 그번호를 넣어주고.
            
            
            this.server.SendMakeRoom();
            
         }
         else if(index==10){//내가 방장이고 방에 1명이 있을시. 
            //일단  해당 방의 방장에 플레이어의 값을 넣어주고. 
            this.server.noCreate.put(""+sendSpot.myRoomPosition, ((String)server.noPlayer.get(""+sendSpot.myRoomPosition)));
            //해당 방의 방장 성별에 플레이어 성별 넣어주고.
            this.server.noGen.put(""+sendSpot.myRoomPosition, ((String)server.noPgen.get(""+sendSpot.myRoomPosition)));
            //해당 방의 방장 급수에 플레이어 급수 넣어주고.
            this.server.noGpsu.put(""+sendSpot.myRoomPosition, ((String)server.noPgpsu.get(""+sendSpot.myRoomPosition)));
            //해당 방의 상태를 대기중으로 바꿔주고.
            this.server.noStat.put(""+sendSpot.myRoomPosition, "대기중");
            //해당 방의 인원을 1명으로 변경해주고.
            this.server.noInwon.put(""+sendSpot.myRoomPosition, "1");
            //해당 방의 플레이어는 공백으로 변경 시키고.
            this.server.noPlayer.put(""+sendSpot.myRoomPosition, " ");
            this.server.noPgpsu.put(""+sendSpot.myRoomPosition, " " );
            this.server.noPgen.put(""+sendSpot.myRoomPosition, " ");
         
            this.server.SendMakeRoom();
            
         }
         else if(index==11){//유저로 참가했다가 방을 나왔을시에.
            this.server.noPlayer.put(""+sendSpot.myRoomPosition, " ");
            this.server.noPgpsu.put(""+sendSpot.myRoomPosition, " ");
            this.server.noPgen.put(""+sendSpot.myRoomPosition, " ");
            this.server.noStat.put(""+sendSpot.myRoomPosition, "대기중");
            this.server.noInwon.put(""+sendSpot.myRoomPosition, "1");
            
            this.server.SendMakeRoom();
         }
         else if(index==12){
            this.server.SendAll(sendSpot);
         }
         else if(index==13){
            this.server.SendAll(sendSpot);
         }
         else if(index==14){
            this.server.SendAll(sendSpot);
         }
         else if(index==15){
            this.server.noStat.put(""+sendSpot.myRoomPosition, "게임중");
            this.server.SendAll(sendSpot);
            this.server.SendMakeRoom();
         }
         else if(index==16){
            this.server.noStat.put(""+sendSpot.myRoomPosition, "설정중"); //게임을 포기선언했으므로, 설정중.
            this.server.SendAll(sendSpot);
            this.server.SendMakeRoom();
         }
         else if(index==17){
            this.server.SendAll(sendSpot);
         }
         else if(index==18){
            this.server.userList2.put(sendSpot.nickname, sendSpot.gpsu); //유저 리스트의 급수 변경. 
            
            if(sendSpot.roomMaker==1){
               this.server.noGpsu.put(""+sendSpot.myRoomPosition, sendSpot.gpsu); //보낸 사람이 방장이라면 방장의 급수.
            }else{
               this.server.noPgpsu.put(""+sendSpot.myRoomPosition, sendSpot.gpsu); //보낸 사람이 유저라면 유저의 급수를. 
            }
            
            this.server.SendList(); //접속자 리스트의 갱신.
            this.server.SendMakeRoom(); //방목록의 정보 갱신. 
         }
         else if(index==19){
            this.server.SendAll(sendSpot); 
         }
      }
      
      } catch (Exception e) {
            this.server.user.remove(this.name); //사용자가 접속을 종료하게 되면, 해당 맵의 사용자를 삭제.
            this.server.userList.remove(this.id);
            this.server.userList2.remove(this.nickname);
            try {
               this.server.SendList();
            } catch (Exception e2) {
               e2.printStackTrace();
            }
      } 
   }
   
}









class Totserver implements Serializable{
   Map user,userList,userList2,
        noGen, noGpsu,noCreate,noStat, noInwon,noPlayer,noPgpsu,noPgen,LoseRoomNum;
   ServerSocket server;
   Socket socket;
   int nextroomNum=0; //앞으로 만들어질 방의 번호. 
   
   public Totserver() throws Exception {
      this.user = new HashMap();//접속중인 유저
      this.LoseRoomNum=new TreeMap();//잃어버린 방번호
      this.userList = new LinkedHashMap();//방장의 리스트
      this.userList2 = new LinkedHashMap();      //방에 접속한 유저 리스트.
      this.noGpsu=new LinkedHashMap(); //방장의 급수.
      this.noGen=new LinkedHashMap(); //방장의 성별.
      this.noCreate=new LinkedHashMap(); //방장의 닉네임
      this.noStat=new LinkedHashMap(); //방의 상태.
      this.noInwon=new LinkedHashMap(); //방의 접속 인원. 
      this.noPlayer=new LinkedHashMap(); // 방의 플레이어의 닉네임.
      this.noPgpsu= new LinkedHashMap(); //방의 플레이어의급수.
      this.noPgen= new LinkedHashMap(); //방의 플레이어의 성별.
      
      
      
      this.server=new ServerSocket(7777);
      Collections.synchronizedMap(user);
      
      this.start();
   }

   
   //////////////////////////////////////////////////////////////////////////////////////////////////
   
   
   private void start() throws Exception {
      
      while(true){
         this.socket=this.server.accept();
         UserAccept user = new UserAccept(this,socket);
         user.start();
         
      }
   }
   
   
   //////////////////////////////////////////////////////////////////////////////////////////////////
   
   
   public void SendMakeRoom() throws Exception { //대기실의 모든 유저에게 방번호와 방 정보를 보내줘야한다. 
      //일단 무조건 방이 삭제 되면, 삭제리스트에 입력,   생성되면, 삭제리스트 조회후 생성된 번호 삭제. 
      // 그리고 무조건 여기로 오게되지. 
      //그렇다는건, 만약에 순차적 생성이든 뭐건 간에, LoseRoomNum에 값이 있다는건 삭제된 방이 있다는 얘기지. 
      if(this.LoseRoomNum.size()!=0){//만약에 삭제된 방 번호리스트가 있다면, 
         Iterator it1 =this.LoseRoomNum.keySet().iterator();
         this.nextroomNum=(int)it1.next(); //다음방은 삭제된 방리스트의 첫번째 방이 됨. 
      }
      else {
         Iterator it = this.noCreate.keySet().iterator();
         this.nextroomNum=0;
         while(it.hasNext()){
            int res = Integer.parseInt((String)it.next()); //현재 있는 방번호의 값들. 
            if(this.nextroomNum <= res){//방번호의 값이 같거나 크다면, 
               this.nextroomNum=res+1; //현재 있는 방의 값들중 가장 큰 값에 +1 한 값을 넥스트 룸에 넣어준다. 
            }
         }
      }
      
      
      //삭제된 방 번호가 없다는 이야기는, 그냥 방 번호 가 되면 되는건데, 그건 굳이 여기서 안해도 되고, 
      //방이 생성되면 무조건 생성된 방 +1이 되면 된다는 얘기. 그러므로 만약에 삭제된 방의 값이 없다면, 그냥 만들어진 방의 +1
      
   
      
      
      
      
      if(this.noGpsu.size()==0){//만약 방이 하나도 없다면? 유저의 대기실 창을 리페인트 시키고 대기하라고 명령.
         Iterator it = this.user.keySet().iterator();

         while(it.hasNext()){
            SendSpot sendSpot=new SendSpot(9,nextroomNum);
            ObjectOutputStream oos = (ObjectOutputStream) this.user.get(it.next());
            oos.writeObject(sendSpot);
         }
         return;
      }
   
      
      Iterator noit=this.noGpsu.keySet().iterator();
   
      String [] no=new String[this.noGpsu.size()]; //방번호가 담겨있는 배열 . 
      String [] create=new String[this.noGpsu.size()]; //방장의 닉네임
      String [] cgpsu=new String[this.noGpsu.size()]; //방장의 급수.
      String [] cgen = new String[this.noGpsu.size()]; //방장의 성별.
      String [] stat=new String[this.noGpsu.size()]; //방의 대기상태.
      String [] inwon=new String[this.noGpsu.size()]; //인원수의 배열.
      String [] player=new String[this.noGpsu.size()]; //플레이어의 닉네임
      String [] pgpsu = new String [this.noGpsu.size()] ; //플레이어의 급수.
      String [] pgen = new String [this.noGpsu.size()] ; //플레이어의 성별.
      

      
      for(int i=0; i<no.length; i++){
         no[i]=(String)noit.next();
         create[i]=(String)noCreate.get(no[i]);
         cgpsu[i]=(String)noGpsu.get(no[i]);
         cgen[i]=(String)noGen.get(no[i]);
         stat[i]=(String)noStat.get(no[i]);
         inwon[i]=(String)noInwon.get(no[i]);
         player[i]=(String)noPlayer.get(no[i]);
         pgpsu[i]=(String)noPgpsu.get(no[i]);
         pgen[i]=(String)noPgen.get(no[i]);
      }
      
      Iterator it = this.user.keySet().iterator();

      while(it.hasNext()){
         SendSpot sendSpot=new SendSpot(6,this.nextroomNum,no,create,cgpsu,cgen,stat,inwon,player,pgpsu,pgen);
         ObjectOutputStream oos = (ObjectOutputStream) this.user.get(it.next());
         oos.writeObject(sendSpot);
      }
      
      
   }
   
   
   
   
   //////////////////////////////////////////////////////////////////////////////////////////////////
   
   
   
   
   public void SendAll(SendSpot sendSpot) throws Exception{
      Iterator it = this.user.keySet().iterator();
      
      while(it.hasNext()){
      ObjectOutputStream oos = (ObjectOutputStream) this.user.get(it.next());
      oos.writeObject(sendSpot);
      
      }
   }
   
   
   
//////////////////////////////////////////////////////////////////////////////////////////////////
   
   
   
   public void targetSend(SendSpot sendSpot) throws Exception{
      //타겟에게 전송하는 부분.
      String targetid = sendSpot.targetid; //받을 사람을 저장시킴.
      Iterator it = this.user.keySet().iterator(); //저장되어있는 유저의 아이디값 저장.
      
      while(it.hasNext()){
         String target=(String)it.next();
         if(targetid.equals(target)){ //받을 사람의 아이디와 저장되어있는 아이디가 동일할시에. 그 사람의 oos로 전송. 
            ObjectOutputStream oos = (ObjectOutputStream)this.user.get(target);
            oos.writeObject(sendSpot);
            break;
         }
      }
   }
   
   
   //////////////////////////////////////////////////////////////////////////////////////////////////
   
   public void SendList() throws Exception{
   // userList 에는 아이디와 성별, userList2 에는 닉네임과 급수. 
      
      Iterator it = this.user.keySet().iterator();
      
      Iterator idGen = this.userList.keySet().iterator();
      Iterator nickGpsu = this.userList2.keySet().iterator();
      
      String [] id= new String[this.userList.size()];
      String [] nickname = new String[this.userList2.size()];
      String [] gen = new String[this.userList.size()];
      String [] gpsu = new String[this.userList2.size()];
      
      for(int i=0; i<id.length; i++){
         id[i]=(String)idGen.next();
         nickname[i]=(String)nickGpsu.next();
         gen[i]=(String)userList.get(id[i]);
         gpsu[i]=(String)userList2.get(nickname[i]);
         }
      while(it.hasNext()){
         SendSpot sendSpot=new SendSpot(2,id,nickname,gen,gpsu);
         ObjectOutputStream oos = (ObjectOutputStream) this.user.get(it.next());
         oos.writeObject(sendSpot);
      }
   
   }
}
//////////////////////////////////////////////////////////////////////////////////////////////////

public class mainServer {
   public static void main(String [] args) throws Exception{
      new Totserver();
   }
}