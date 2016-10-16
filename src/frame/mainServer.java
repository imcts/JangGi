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
   
   //���� ��������� ���� �ϴ� ����� �濡 ������ �����ϴ� ����� �и��� �޶�� �Ѵ�.  

   
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
         int index=sendSpot.index; //sendSpot�� index��.
         
         if(index==0){
            this.name=sendSpot.name;
            this.id=sendSpot.id;
            this.nickname=sendSpot.nickname;
            this.gpsu=sendSpot.gpsu;
            this.gen=sendSpot.gen;
            
            server.user.put(sendSpot.id, this.oos); //ó�� ���� �ߴٸ� ������ �ʿ� �ش� �̸��� oos���� �־��ְ� 
            
            server.userList.put(this.id,this.gen );
            server.userList2.put(this.nickname, this.gpsu);
            
            server.SendAll(sendSpot); //�ٷ� ��� �������� ������. �׷� 0������ �ް�, ���� �����ߴٰ� �˷���.
            server.SendList();
            
            if(server.nextroomNum!=0){ //���� ���� ��������ִٸ�. 
               server.SendMakeRoom(); //��..��������!!!! - _-..
            }
            
         }
         else if (index==1){
            server.SendAll(sendSpot); //�ƴ϶�� �׳� ������������ ������.
         }
         else if(index==3){//3���̶�� �ͼ��� �߻�.
            throw new Exception();
         }
         else if(index==4){
            server.targetSend(sendSpot);
         }
         else if(index==5){ //���� �����ߴٸ�.
         
            if(this.server.LoseRoomNum.size()!=0){
               Iterator it = this.server.LoseRoomNum.keySet().iterator();
         
            while(it.hasNext()){
               int l = (int)it.next(); 
               if(l==sendSpot.roomnum1){//���� ���� ���� ���ȣ�߿� Lose���� �ִٸ� �� ���ȣ�� �����ְ�.
                  this.server.LoseRoomNum.remove(l); //�� ���ȣ�� ������
                  break;
               }
            }
         }
            
            
            this.server.noGpsu.put(""+sendSpot.roomnum1, sendSpot.gpsu); //������ �޼�
            this.server.noGen.put(""+sendSpot.roomnum1, sendSpot.gen);  //������ ����
            this.server.noCreate.put(""+sendSpot.roomnum1, sendSpot.nickname); //������ �г���.
            this.server.noStat.put(""+sendSpot.roomnum1,"�����"); //���� ����.
            this.server.noInwon.put(""+sendSpot.roomnum1, "1"); //���� �ο���.
            this.server.noPlayer.put(""+sendSpot.roomnum1, " "); //���� �÷��̾�. 
            this.server.noPgpsu.put(""+sendSpot.roomnum1, " "); // ���� �÷��̾��� �޼�.
            this.server.noPgen.put(""+sendSpot.roomnum1, " "); //���� �÷��̾��� ����.
            //�� ���. ���࿡ 1���� ���������, 2���� ���������, 3���� ���������, 1���� ��������. ���� ���� 1��. 
            // �״����� ����������� ����? �����ѹ��� ���� �����Ƿ�, 1+1 2������ �Ź�����?! 
            

            
            this.server.SendMakeRoom();
            }
         
         else if(index==7){
            this.server.noPlayer.put(""+sendSpot.myRoomPosition, sendSpot.nickname);
            this.server.noPgpsu.put(""+sendSpot.myRoomPosition, sendSpot.gpsu);
            this.server.noPgen.put(""+sendSpot.myRoomPosition, sendSpot.gen);
            this.server.noStat.put(""+sendSpot.myRoomPosition, "������");
            this.server.noInwon.put(""+sendSpot.myRoomPosition, "2");
            
            
            this.server.SendMakeRoom();
         }
         else if(index==8){//���� �����̰� �濡 �ƹ��� ������. �ش� �� ��ȣ�� �ش� �ϴ� ���� ���� ����.
            this.server.noGpsu.remove(""+sendSpot.myRoomPosition);
            this.server.noGen.remove(""+sendSpot.myRoomPosition);  //������ ����
            this.server.noCreate.remove(""+sendSpot.myRoomPosition); //������ �г���.
            this.server.noStat.remove(""+sendSpot.myRoomPosition); //���� ����.
            this.server.noInwon.remove(""+sendSpot.myRoomPosition); //���� �ο���.
            this.server.noPlayer.remove(""+sendSpot.myRoomPosition); //���� �÷��̾�. 
            this.server.noPgpsu.remove(""+sendSpot.myRoomPosition); // ���� �÷��̾��� �޼�.
            this.server.noPgen.remove(""+sendSpot.myRoomPosition); //���� �÷��̾��� ����.
            
            this.server.LoseRoomNum.put(sendSpot.myRoomPosition, ""); //���� �����Ǹ�, �׹�ȣ�� �־��ְ�.
            
            
            this.server.SendMakeRoom();
            
         }
         else if(index==10){//���� �����̰� �濡 1���� ������. 
            //�ϴ�  �ش� ���� ���忡 �÷��̾��� ���� �־��ְ�. 
            this.server.noCreate.put(""+sendSpot.myRoomPosition, ((String)server.noPlayer.get(""+sendSpot.myRoomPosition)));
            //�ش� ���� ���� ������ �÷��̾� ���� �־��ְ�.
            this.server.noGen.put(""+sendSpot.myRoomPosition, ((String)server.noPgen.get(""+sendSpot.myRoomPosition)));
            //�ش� ���� ���� �޼��� �÷��̾� �޼� �־��ְ�.
            this.server.noGpsu.put(""+sendSpot.myRoomPosition, ((String)server.noPgpsu.get(""+sendSpot.myRoomPosition)));
            //�ش� ���� ���¸� ��������� �ٲ��ְ�.
            this.server.noStat.put(""+sendSpot.myRoomPosition, "�����");
            //�ش� ���� �ο��� 1������ �������ְ�.
            this.server.noInwon.put(""+sendSpot.myRoomPosition, "1");
            //�ش� ���� �÷��̾�� �������� ���� ��Ű��.
            this.server.noPlayer.put(""+sendSpot.myRoomPosition, " ");
            this.server.noPgpsu.put(""+sendSpot.myRoomPosition, " " );
            this.server.noPgen.put(""+sendSpot.myRoomPosition, " ");
         
            this.server.SendMakeRoom();
            
         }
         else if(index==11){//������ �����ߴٰ� ���� �������ÿ�.
            this.server.noPlayer.put(""+sendSpot.myRoomPosition, " ");
            this.server.noPgpsu.put(""+sendSpot.myRoomPosition, " ");
            this.server.noPgen.put(""+sendSpot.myRoomPosition, " ");
            this.server.noStat.put(""+sendSpot.myRoomPosition, "�����");
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
            this.server.noStat.put(""+sendSpot.myRoomPosition, "������");
            this.server.SendAll(sendSpot);
            this.server.SendMakeRoom();
         }
         else if(index==16){
            this.server.noStat.put(""+sendSpot.myRoomPosition, "������"); //������ ���⼱�������Ƿ�, ������.
            this.server.SendAll(sendSpot);
            this.server.SendMakeRoom();
         }
         else if(index==17){
            this.server.SendAll(sendSpot);
         }
         else if(index==18){
            this.server.userList2.put(sendSpot.nickname, sendSpot.gpsu); //���� ����Ʈ�� �޼� ����. 
            
            if(sendSpot.roomMaker==1){
               this.server.noGpsu.put(""+sendSpot.myRoomPosition, sendSpot.gpsu); //���� ����� �����̶�� ������ �޼�.
            }else{
               this.server.noPgpsu.put(""+sendSpot.myRoomPosition, sendSpot.gpsu); //���� ����� ������� ������ �޼���. 
            }
            
            this.server.SendList(); //������ ����Ʈ�� ����.
            this.server.SendMakeRoom(); //������ ���� ����. 
         }
         else if(index==19){
            this.server.SendAll(sendSpot); 
         }
      }
      
      } catch (Exception e) {
            this.server.user.remove(this.name); //����ڰ� ������ �����ϰ� �Ǹ�, �ش� ���� ����ڸ� ����.
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
   int nextroomNum=0; //������ ������� ���� ��ȣ. 
   
   public Totserver() throws Exception {
      this.user = new HashMap();//�������� ����
      this.LoseRoomNum=new TreeMap();//�Ҿ���� ���ȣ
      this.userList = new LinkedHashMap();//������ ����Ʈ
      this.userList2 = new LinkedHashMap();      //�濡 ������ ���� ����Ʈ.
      this.noGpsu=new LinkedHashMap(); //������ �޼�.
      this.noGen=new LinkedHashMap(); //������ ����.
      this.noCreate=new LinkedHashMap(); //������ �г���
      this.noStat=new LinkedHashMap(); //���� ����.
      this.noInwon=new LinkedHashMap(); //���� ���� �ο�. 
      this.noPlayer=new LinkedHashMap(); // ���� �÷��̾��� �г���.
      this.noPgpsu= new LinkedHashMap(); //���� �÷��̾��Ǳ޼�.
      this.noPgen= new LinkedHashMap(); //���� �÷��̾��� ����.
      
      
      
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
   
   
   public void SendMakeRoom() throws Exception { //������ ��� �������� ���ȣ�� �� ������ ��������Ѵ�. 
      //�ϴ� ������ ���� ���� �Ǹ�, ��������Ʈ�� �Է�,   �����Ǹ�, ��������Ʈ ��ȸ�� ������ ��ȣ ����. 
      // �׸��� ������ ����� ���Ե���. 
      //�׷��ٴ°�, ���࿡ ������ �����̵� ���� ����, LoseRoomNum�� ���� �ִٴ°� ������ ���� �ִٴ� �����. 
      if(this.LoseRoomNum.size()!=0){//���࿡ ������ �� ��ȣ����Ʈ�� �ִٸ�, 
         Iterator it1 =this.LoseRoomNum.keySet().iterator();
         this.nextroomNum=(int)it1.next(); //�������� ������ �渮��Ʈ�� ù��° ���� ��. 
      }
      else {
         Iterator it = this.noCreate.keySet().iterator();
         this.nextroomNum=0;
         while(it.hasNext()){
            int res = Integer.parseInt((String)it.next()); //���� �ִ� ���ȣ�� ����. 
            if(this.nextroomNum <= res){//���ȣ�� ���� ���ų� ũ�ٸ�, 
               this.nextroomNum=res+1; //���� �ִ� ���� ������ ���� ū ���� +1 �� ���� �ؽ�Ʈ �뿡 �־��ش�. 
            }
         }
      }
      
      
      //������ �� ��ȣ�� ���ٴ� �̾߱��, �׳� �� ��ȣ �� �Ǹ� �Ǵ°ǵ�, �װ� ���� ���⼭ ���ص� �ǰ�, 
      //���� �����Ǹ� ������ ������ �� +1�� �Ǹ� �ȴٴ� ���. �׷��Ƿ� ���࿡ ������ ���� ���� ���ٸ�, �׳� ������� ���� +1
      
   
      
      
      
      
      if(this.noGpsu.size()==0){//���� ���� �ϳ��� ���ٸ�? ������ ���� â�� ������Ʈ ��Ű�� ����϶�� ���.
         Iterator it = this.user.keySet().iterator();

         while(it.hasNext()){
            SendSpot sendSpot=new SendSpot(9,nextroomNum);
            ObjectOutputStream oos = (ObjectOutputStream) this.user.get(it.next());
            oos.writeObject(sendSpot);
         }
         return;
      }
   
      
      Iterator noit=this.noGpsu.keySet().iterator();
   
      String [] no=new String[this.noGpsu.size()]; //���ȣ�� ����ִ� �迭 . 
      String [] create=new String[this.noGpsu.size()]; //������ �г���
      String [] cgpsu=new String[this.noGpsu.size()]; //������ �޼�.
      String [] cgen = new String[this.noGpsu.size()]; //������ ����.
      String [] stat=new String[this.noGpsu.size()]; //���� ������.
      String [] inwon=new String[this.noGpsu.size()]; //�ο����� �迭.
      String [] player=new String[this.noGpsu.size()]; //�÷��̾��� �г���
      String [] pgpsu = new String [this.noGpsu.size()] ; //�÷��̾��� �޼�.
      String [] pgen = new String [this.noGpsu.size()] ; //�÷��̾��� ����.
      

      
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
      //Ÿ�ٿ��� �����ϴ� �κ�.
      String targetid = sendSpot.targetid; //���� ����� �����Ŵ.
      Iterator it = this.user.keySet().iterator(); //����Ǿ��ִ� ������ ���̵� ����.
      
      while(it.hasNext()){
         String target=(String)it.next();
         if(targetid.equals(target)){ //���� ����� ���̵�� ����Ǿ��ִ� ���̵� �����ҽÿ�. �� ����� oos�� ����. 
            ObjectOutputStream oos = (ObjectOutputStream)this.user.get(target);
            oos.writeObject(sendSpot);
            break;
         }
      }
   }
   
   
   //////////////////////////////////////////////////////////////////////////////////////////////////
   
   public void SendList() throws Exception{
   // userList ���� ���̵�� ����, userList2 ���� �г��Ӱ� �޼�. 
      
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