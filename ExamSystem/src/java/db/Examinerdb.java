package db;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Examinerdb {
private String subject;
private ArrayList<String> al=new ArrayList<>();
    public Examinerdb(String subject) {
        this.subject = subject;
    }

   // public Examinerdb() {
   // }
    
   public ArrayList<String> connect() throws Exception{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/setdata",
                                                "root","root");
        Statement st=conn.createStatement();
        ResultSet rss=st.executeQuery("Select QID,QUESTIONS from ALLQUESTIONS WHERE "
                + "SUBJECT='"+subject+"'");
         
        while(rss.next()){
          String qid=rss.getString("QID"); 
          al.add(qid+"\n");
          String a=rss.getString("QUESTIONS");
          al.add(a+"\n");    
        }
        conn.close();
        return al;
   }
   public String createtable(String qid,String tablename,String proffname)throws Exception{
       try{
        if(this.subject.equals("null")){
            return "Enter Subject....";
        }   
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/setdata",
                                                "root","root");
        Statement st=conn.createStatement();
        
        st.executeUpdate("CREATE TABLE "+tablename+"(Questions char(220),A char(220),B char(220)"
                + ",C char(220),D char(220),Answer char(200))");
        
   
        
        st.executeUpdate("INSERT INTO "+tablename+"" +
                            "(QUESTIONS,A,B,C,D,ANSWER) SELECT QUESTIONS,A,B,C,D,ANSWER " +
                         "FROM ALLQUESTIONS WHERE QID IN("+qid+")");
            LocalDateTime now = LocalDateTime.now();
            String d=now.format(DateTimeFormatter.ISO_DATE);
            String t=now.format(DateTimeFormatter.ISO_TIME);
            String g=t.substring(0,8);
       st.executeUpdate("Insert Into AllPapers Values('"+proffname+"','"+this.subject+"','"+tablename+"'"
               + ",'"+d+"','"+g+"')");
       conn.close();
        return "true";
       }catch(Exception e){
           System.out.println("Exception in create table "+e);
           return e.getMessage();
       }
   }
}
