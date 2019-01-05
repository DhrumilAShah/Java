package Serverx;
import java.io.*;
import java.util.*;
import java.net.*;
public class server {

    public static MessageQueue<String> q=new MessageQueue<>();
    public static ArrayList<PrintWriter> noslist=new ArrayList<>();
    
    public static void main(String[] args) throws Exception{         
        ServerSocket ss=new ServerSocket(2190);          
        MessageDispatcher md=new MessageDispatcher(); 
        md.setDaemon(true);               
        System.out.println("Connection Established");        
            for(int i=0;i<10;i++){
            Socket s=ss.accept();  
            Authenticatorr a=new Authenticatorr(s);
            a.process();
            }  
           /* room r1=new room();     
            r1.start();
            room r2=new room();
            r2.start();*/
    }
   
}
/*class room extends Thread{
    @Override
    public void run(){
        try{
    ServerSocket ss=new ServerSocket(2190);     
        
        MessageDispatcher md=new MessageDispatcher(); 
        md.setDaemon(true);           
        System.out.println("Connection Established");        
            for(int i=0;i<2;i++){
            Socket s=ss.accept();  
            Authenticatorr a=new Authenticatorr(s);
            a.process();
            }
        }catch(Exception e){}
}
}*/

