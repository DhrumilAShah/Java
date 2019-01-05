import java.net.*;
import javax.swing.*;
import java.io.*;
public class Clientt {
    public static void main(String[] args) throws Exception{
        Socket soc;
        
        String k=JOptionPane.showInputDialog("Choose one group or private chat?");     
          soc=new Socket("127.0.0.1",2190);
            PrintWriter nos = new PrintWriter(new BufferedWriter
                        (new OutputStreamWriter(soc.getOutputStream())), true);
            nos.println(k);              
        
        System.out.println("Connection Established");
        System.out.println("Connected to"+soc.getInetAddress());
        System.out.println("Local port number: "+soc.getLocalPort());
        System.out.println("Remote port number: "+soc.getPort());
        Login l=new Login(soc);
        l.process();       
    }
    
}


