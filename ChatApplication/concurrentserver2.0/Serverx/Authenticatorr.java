package Serverx;
import java.io.*;
import java.net.Socket;
import java.util.Properties;
public class Authenticatorr {
    Socket soc;  
    Authenticatorr(Socket soc){
        this.soc=soc;
    }  
    void process() throws Exception{
        BufferedReader nis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        PrintWriter nos = new PrintWriter(new BufferedWriter
                          (new OutputStreamWriter(soc.getOutputStream())), true);      
        Properties p=new Properties();
        FileReader fr=new FileReader("D:\\Documents\\NetBeansProjects\\jdbc\\loginn.properties");
        p.load(fr);      
        
            String username=nis.readLine();
            String s=p.getProperty(username); 
            if(s==null){
                System.out.println("User Name doesnt exist,please connect again");   
                nos.println("Wrong");
                System.out.println("Send to client");
            }
            else{
                 String pass=nis.readLine();
                if(pass.equals(s)) {        
                    System.out.println("Welcome "+username); 
                    nos.println("Right");
                    Conversation c=new Conversation(soc,username);
                    statuscheck sc=new statuscheck(soc,username);
                    sc.start();
                    c.start();           
                }                                    
                else{
                    System.out.println("Wrong password,please connect again"); 
                    nos.println("Wrong");
                }
            }
    }
}
    
