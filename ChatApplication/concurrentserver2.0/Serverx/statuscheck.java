package Serverx;
import java.io.*;
import java.util.*;
import java.net.Socket;

public class statuscheck extends Thread{
    Socket soc;
    String username;   
    private boolean online=false;
   private static ArrayList<String> as=new ArrayList<>();
    public statuscheck(Socket soc,String username) {
        this.soc = soc;
        this.username=username;
    }
    @Override
    public void run(){
        try{
            if(soc.isClosed()){         
                server.q.enqueue(username+" left conversation"); 
                System.out.println(username+" left conversation");
                
                as.remove(username);
                MessageDispatcher md=new MessageDispatcher(); 
                md.start();
            }
            if(!soc.isClosed()){
                as.add(username);
                PrintWriter nos = new PrintWriter(new BufferedWriter
                                (new OutputStreamWriter(soc.getOutputStream())), true);
                
                nos.println("List of persons currently online.....");
                for(String a:as)
                        nos.println(a);
                server.q.enqueue(username+" joined successfully,now you can chat with ur team");
                MessageDispatcher md=new MessageDispatcher(); 
                md.start();
                
            }
           
         
            
        }catch(Exception e){System.out.println("Exception in status");}
    }

  
        
    
}

