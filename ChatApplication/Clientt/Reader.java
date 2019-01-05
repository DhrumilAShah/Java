import java.awt.BorderLayout;
import java.awt.event.*;
import java.net.Socket;
import java.io.*;
import javax.swing.*;

public class Reader extends Thread{
    Socket soc;
    String username;
    Reader(Socket s,String username){
    soc=s;
    this.username=username;
    }
    @Override
    public void run(){
        try{          
          BufferedReader nis=new BufferedReader(new InputStreamReader(soc.getInputStream()));  
          PrintWriter nos=new PrintWriter(new BufferedWriter(new 
                            OutputStreamWriter(soc.getOutputStream())),true);    
        JFrame f1 = new JFrame(username);       
        JButton b1 = new JButton("Send");
        
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        JTextField tf = new JTextField(20);
        JPanel p1 = new JPanel();
        p1.add(tf);
        p1.add(b1);
        f1.add(ta);
        f1.add(BorderLayout.SOUTH,p1);
        ChatListener l1 = new ChatListener(tf,nos);
        b1.addActionListener(l1);
        tf.addActionListener(l1);
        f1.setSize(400,400);
        f1.setVisible(true);
        
        f1.addWindowListener(new WindowAdapter(){
                 @Override
                 public void windowClosing(WindowEvent e) {
                    try{
                        //nos.println(" left conversation");
                        //ta.append(" left conversation");
                        nos.println("End");
                        soc.close();
                        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }catch(Exception ei){System.out.println("Exception in window closing");}
            }
        });
        
        String str = nis.readLine();
        while(!str.equals("End")){
            ta.append(str + "\n" );
            str = nis.readLine();
        }
         //nos.println(username+" left conversation");  
         
         ta.append(username+" left conversation");
         nos.println("End");
        sleep(1000);
        //soc.close();
        System.exit(0);                                                                      
       } catch(Exception e){}       
    }
}
class ChatListener implements ActionListener{
   JTextField tf ;
   PrintWriter nos;
    public ChatListener(JTextField tf,PrintWriter nos){
        this.tf = tf;
        this.nos = nos;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String str  = tf.getText();
        BadWordFilter bwf=new BadWordFilter(str);
        nos.println(bwf.check());
        tf.setText("");
    }
    
    
}
   


