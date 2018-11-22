import java.awt.Font;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.io.*;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class notepadd {
    
    public static void main(String[] args) {
       
        JFrame f1=new JFrame("Notepad");
        JTextArea ta=new JTextArea();
        JScrollPane sp=new JScrollPane(ta);
        
        JMenu j1=new JMenu("File");
        JMenu j2=new JMenu("Edit"); 
        JMenu j4=new JMenu("Fonts..");
        JMenu j5=new JMenu("Help");  
        JMenuBar jm=new JMenuBar();
        jm.add(j1);
        jm.add(j2);
        jm.add(j4);
        jm.add(j5);        
        f1.setJMenuBar(jm);
        //**********For File menu*****************
        JMenuItem clearall=new JMenuItem("Clear All");
        clearall.addActionListener(new Notepad("clear all",ta));
        
        JMenuItem open=new JMenuItem("Open");
        open.addActionListener(new Notepad("open",ta));
        
        JMenuItem save=new JMenuItem("Save");
        save.addActionListener(new Notepad("save",ta));
        
        JMenuItem saveas=new JMenuItem("Save As");
        saveas.addActionListener(new Notepad("saveas",ta));
        
        JMenuItem exit=new JMenuItem("Exit");
        exit.addActionListener(new Notepad("exit",ta));
        
        j1.add(clearall);
        j1.add(open);
        j1.add(save);
        j1.add(saveas);
        j1.add(exit);        
        //**********For Edit menu*****************;
        JMenuItem cut=new JMenuItem("Cut");
        cut.addActionListener(new Notepad("cut",ta));
        
        JMenuItem copy=new JMenuItem("Copy");
        copy.addActionListener(new Notepad("copy",ta));
        
        JMenuItem paste=new JMenuItem("Paste");
        paste.addActionListener(new Notepad("paste",ta));   
        
        JMenuItem selectall=new JMenuItem("Select all");
        selectall.addActionListener(new Notepad("selectall",ta));
        
        JMenuItem timedate=new JMenuItem("Time/Date");   
        timedate.addActionListener(new Notepad("timedate",ta));
        
        j2.add(cut);
        j2.add(copy);
        j2.add(paste);
        j2.add(selectall);
        j2.add(timedate);
        //**********For View menu*****************
        JMenuItem bold=new JMenuItem("Bold");   
        bold.addActionListener(new Notepad("bold",ta));
        
        JMenuItem ita=new JMenuItem("Italics");   
        ita.addActionListener(new Notepad("ita",ta));
        
        JMenuItem pl=new JMenuItem("Plain");   
        pl.addActionListener(new Notepad("pl",ta));
        
        j4.add(bold);
        j4.add(ita);
        j4.add(pl);
        //**********For Help menu*****************
        JMenuItem about=new JMenuItem("About Notepad");
        j5.add(about);

        //Adding  Text area************************  
        f1.add(sp); 
        f1.setVisible(true);
        f1.setSize(600,600);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
    }   
}
class Notepad implements ActionListener{
    String h;
    JTextArea ta;
    static String op;
    static boolean savee=false;
    Notepad(String h,JTextArea ta){
        this.ta=ta;
        this.h=h;
    }    
    @Override
     public void actionPerformed(ActionEvent e){
        
        if(h.equalsIgnoreCase("copy")){
            ta.copy();
        }
        else if(h.equalsIgnoreCase("cut")){
           ta.cut();
        }
        else if(h.equalsIgnoreCase("selectall")){
            ta.selectAll();
        }
         else if(h.equalsIgnoreCase("paste")){
            ta.paste();         
        }
         else if(h.equalsIgnoreCase("timedate")){
            LocalDateTime now = LocalDateTime.now();
            String d=now.format(DateTimeFormatter.ISO_DATE);
            String t=now.format(DateTimeFormatter.ISO_TIME);
            ta.append("  time:"+t+" date:"+d);
        }
        else if(h.equalsIgnoreCase("clear all")){
            ta.setText("");
        }
        else if(h.equalsIgnoreCase("exit")){
            exit();
        }   
        else if(h.equalsIgnoreCase("bold")){
            ta.setFont(new Font(ta.getText(),Font.BOLD,12));
        }    
        else if(h.equalsIgnoreCase("ita")){
            ta.setFont(new Font(ta.getText(),Font.ITALIC,12));
        }  
        else if(h.equalsIgnoreCase("pl")){
            ta.setFont(new Font(ta.getText(),Font.PLAIN,12));
        }        
        else if(h.equalsIgnoreCase("saveas")){
           saveas();
        }
        else if(h.equalsIgnoreCase("open")){
           open();
        }
        else if(h.equalsIgnoreCase("save")){
           save();
        }
    }
     //*********code for save as option*********************
     void saveas(){
          try{
            String name=JOptionPane.showInputDialog("Enter File name");
            String dir=JOptionPane.showInputDialog("Directory In which file is to be saved..");
            File ff=new File(dir);
            File f=new File(dir+"\\"+name+".txt");
            PrintWriter fos = new PrintWriter(new BufferedWriter
                                  ((new FileWriter(f))), true);
            if((!ff.isDirectory()) || name==null)//validate
                JOptionPane.showMessageDialog(null,"Directory or file name is improper");
            else{
            if(f.exists()){
                String g="This file name "
                            + "already exist,do you want to replace it?";
                int a=JOptionPane.showOptionDialog(null,g,dir,0,YES_NO_OPTION,null,null,null);
                if(a==1){
                    JOptionPane.showMessageDialog(null,"Save File with other name");
                }
                else{
                    fos.write(ta.getText());
                    fos.close();
                    savee=true;
                }           
            }
            else{
                savee=true;
                f.createNewFile();  
                fos.write(ta.getText());
                fos.close();
            }
            }
            }catch(IOException ie){
                System.out.println("Exception while saving........");}
     }
       //*********code for open option*********************
     void open(){
           try{
          String dir=JOptionPane.showInputDialog("Directory in which file is stored");
          String name=JOptionPane.showInputDialog("Enter File name");
          File ff=new File(dir);
           if((!ff.isDirectory()) || name==null)
                JOptionPane.showMessageDialog(null,"Directory or file name is improper");
           else{
               String path=dir+"\\"+name+".txt";        
               File f=new File(path);
               if((!f.canRead()) || (!f.exists()))
                   JOptionPane.showMessageDialog(null,"Check for file existance or "
                           + "for read permissions.......");
               else{
                op  = dir+"\\"+name+".txt";   
                BufferedReader fr = new BufferedReader(new FileReader(path));
                String g1=fr.readLine();
                while(g1!=null){
                    ta.append(g1+"\n");
                    g1=fr.readLine();          
                }
                fr.close();  
               }
            }
            }catch(IOException ae){
                JOptionPane.showMessageDialog(null,"Only .txt file supported,"
                        + "make sure that it is a text file....");
                System.out.println("Exception while opening");}
     }
     //*********code for save option*********************
     void save(){
            if(op==null)
                saveas();
            else{
              try{              
              PrintWriter fos = new PrintWriter(new BufferedWriter
                                ((new FileWriter(op))), true);
              fos.write("\n"+ta.getText()+"\n");
              fos.close();
              savee=true;
              JOptionPane.showMessageDialog(null,"Saved Successfully");
              }catch(IOException ei){
                  JOptionPane.showMessageDialog(null,"problem in saving file,try again");
                  System.out.println("problem in saving file");}
            }
     }
     //*********code for exit option*********************
     void exit(){
         if(!savee){
             String g="Do you want to save your file?";
              int a=JOptionPane.showOptionDialog(null,g,null,0,YES_NO_OPTION,null,null,null);
              if(a==0)
                  save();
              else
                  System.exit(0);        
         }
     }      
}
