
import java.net.*;
public class room {
  
    public static void main(String[] args) throws Exception{  
        
        room r1=new room();
        r1.connect();
        room r2=new room();
        r2.connect();
    }
    
        void connect()throws Exception{
             ServerSocket ss=new ServerSocket(2191);
             System.out.println(ss.getLocalPort());
             System.out.println("Connection Established");
               for(int i=0;i<2;i++){
                    Socket s=ss.accept();
                    Conv cc=new Conv(s);
                    cc.start();
               }
        }
}


