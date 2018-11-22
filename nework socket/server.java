package network;
//import network.client.*;
import java.net.*;
import java.io.*;
import java.util.*;
public class server {

    public static ArrayList<PrintWriter> al=new ArrayList<>();
    public static void main(String[] args) throws Exception{
        System.out.println("Server Signing ON");
        ServerSocket ss = new ServerSocket(9081);
        for(int i=0;i<10;i++){
            Socket soc = ss.accept();
            Conversation c = new Conversation(soc);
            c.start();
        }
        System.out.println("Server Singing OFF");
    }

}

class Conversation extends Thread {

    Socket soc;

    Conversation(Socket soc) {
        this.soc = soc;
    }

    @Override
    public void run() {
        try {
            BufferedReader nis = new BufferedReader(
                    new InputStreamReader(
                            soc.getInputStream()
                    )
            );
            PrintWriter nos = new PrintWriter(
                                 new BufferedWriter(
                                    new OutputStreamWriter(
                                         soc.getOutputStream()
                                    )
                                 ),true
            );
            server.al.add(nos);
            String str = nis.readLine();
            while (!str.equals("End")) {
                System.out.println("Server Recieved " + str);
                for(PrintWriter o:server.al){
                    o.println(str);
                }
                str = nis.readLine();
            }
            nos.println("End");
            }
            catch(Exception e){
            
            System.out.println("Client Seems to have abruptly closed the connection");
        }
     System.out.println("Conversation thread  "+
                                     Thread.currentThread().getName() +
                                      "   signing Off");
    }
}


