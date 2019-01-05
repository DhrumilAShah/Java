package Serverx;
import java.io.PrintWriter;
import java.net.*;
public class MessageDispatcher extends Thread{
    Socket soc;
    @Override
    public void run(){
        try{
        while(true){     
            String str=server.q.dequeue();
           for(PrintWriter pw:server.noslist){
               pw.println(str);
           } 
        }
        }catch(Exception e){}
    }
    public void particular(String s,Socket soc){
        this.soc=soc;
        
    }
}
