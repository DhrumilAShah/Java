
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class Conv extends Thread{
    Socket soc;
    Conv(Socket soc){
        this.soc=soc;
    }
    @Override
    public void run(){
        try{
        BufferedReader nis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        PrintWriter nos = new PrintWriter(new BufferedWriter(new 
                        OutputStreamWriter(soc.getOutputStream())), true);
        String str=nis.readLine();
        while(!str.equalsIgnoreCase("End")){
            System.out.println(str);
            nos.println(str);
            str=nis.readLine();
        }
        nos.println("End");
        
            
    }catch(Exception ae){}
    }
}
