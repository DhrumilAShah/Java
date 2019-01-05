package Serverx;
import java.io.*;
import java.net.*;
public class Conversation extends Thread{
 Socket soc;    
 private String username;
 Conversation(Socket soc,String username){
     this.soc=soc;
     this.username=username;
 }
 @Override
 public void run(){
     try{
     BufferedReader nis=new BufferedReader(new InputStreamReader(soc.getInputStream()));
     PrintWriter nos=new PrintWriter(new BufferedWriter(new OutputStreamWriter
                                        (soc.getOutputStream())),true);    
     server.noslist.add(nos);
     String str=nis.readLine();
     while(!str.equalsIgnoreCase("End")){
        MessageDispatcher md=new MessageDispatcher(); 
        md.start();
         server.q.enqueue(username+": "+str);//reading from client and storing in queue
         System.out.println(username+": "+str);
         str=nis.readLine();         
     }  
     nos.println("End"); 
     statuscheck sc=new statuscheck(soc,username);
     soc.close();
     
     sc.start();
     
     server.noslist.remove(nos); 
     }catch(Exception e){}
 }
}
