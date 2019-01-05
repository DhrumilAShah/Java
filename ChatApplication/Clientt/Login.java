import javax.swing.*;
import java.net.*;
import java.io.*;
public class Login{
    //private String a,b;
    //private boolean flag=false;
    Socket soc;
    Login(Socket soc){
        this.soc=soc;
    }
    void process() throws IOException{
        
    PrintWriter nos = new PrintWriter(new BufferedWriter
                        (new OutputStreamWriter(soc.getOutputStream())), true);
     BufferedReader nis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
     
    // for(int i=0;i<3;i++){
        String username=JOptionPane.showInputDialog("Enter user name: ");
        nos.println(username);  
        String pass=JOptionPane.showInputDialog("Enter password: ");
        nos.println(pass);     
        String a=nis.readLine();
        if(a.equalsIgnoreCase("wrong")){
            System.out.println("Wrong username or password,please try again");
        }
        else{
                Reader r=new Reader(soc,username);
                r.start();
            //break;          
        }
    // }
    
     
       
   }
}
