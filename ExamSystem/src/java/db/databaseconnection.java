package db;
import java.sql.*;
import java.util.ArrayList;
public class databaseconnection {
private ArrayList<String> al;
private ArrayList<String> ans;
    public databaseconnection(ArrayList<String> al,ArrayList<String> ans) {
        this.al=al;
        this.ans=ans;
    }
    public void start(String k,String subject) throws Exception{       
       Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/setdata",
                                                "root","root");
        Statement st=conn.createStatement();
        String g=subject.replaceAll("\\s","");//fow white spaces...
        System.out.println(g);
        ResultSet rss=st.executeQuery("SELECT Datarepresentation From "+g+" WHERE SETNAME='"+k+"'");
        String set=null; 
        while(rss.next()){
           set=rss.getString("Datarepresentation");
         }
        ResultSet rs=st.executeQuery("SELECT * FROM "+set);
        
       while(rs.next()){   
           String q=rs.getString("QUESTIONS");
           String a=rs.getString("A");
           String b=rs.getString("B");
           String c=rs.getString("C");
           String d=rs.getString("D");
           String an=rs.getString("Answer");                        
                al.add(q);
                al.add(a);
                al.add(b);
                al.add(c);
                al.add(d);
                ans.add(an);        
       }    
       
       conn.close();        
    }   
} 
  /*  public static void main(String[] args) throws Exception{
         Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/setdata",
                                                "root","root");
        Statement st=conn.createStatement();
        String k="Set C";
        ResultSet rss=st.executeQuery("SELECT Datarepresentation From MAIN WHERE SETNAME='"+k+"'");
        String set=null; 
        while(rss.next()){
           set=rss.getString("Datarepresentation");
         }
        ResultSet rs=st.executeQuery("SELECT * FROM "+set);        
       while(rs.next()){   
           String q=rs.getString("QUESTIONS");
           String a=rs.getString("A");
           String b=rs.getString("B");
           String c=rs.getString("C");
           String d=rs.getString("D");
           String an=rs.getString("Answer"); 
                al.add(q);
                al.add(a);
                al.add(b);
                al.add(c);
                al.add(d);
                ans.add(an); 
     
           /*     al1.add(q);
                al1.add(a);
                al1.add(b);
                al1.add(c);
                al1.add(d);
                ans.add(an); */        
  //     }
  //     conn.close();        
  //  }         
//}
