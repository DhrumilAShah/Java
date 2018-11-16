package db;
import java.sql.*;
import java.util.ArrayList;
public class admindb {
    private String query,type;
    private boolean flag=false;
    private ArrayList<String> al=new ArrayList<>();
    
    public admindb(String query,String type) {
        this.query = query;
        this.type = type;
    }
    
    public ArrayList<String> connect() {
        try{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/setdata",
                                                "root","root");
        Statement st=conn.createStatement(); 
         ResultSet rs=null;
         if(type.equals("allpaper")){
            rs=st.executeQuery(query);
           while(rs.next()){
                String q=rs.getString("PROFFNAME");
                String a=rs.getString("SUBJECT");
                String b=rs.getString("TABLENAME");
                String c=rs.getString("DATEOFCREATION");
                String d=rs.getString("TIMEOFCREATION"); 
                al.add(q);
                al.add(a);
                al.add(b);
                al.add(c);
                al.add(d);
            }
         }
        if(type.equals("display")){
           rs=st.executeQuery(query);
           while(rs.next()){
                String q=rs.getString("QUESTIONS");
                String a=rs.getString("A");
                String b=rs.getString("B");
                String c=rs.getString("C");
                String d=rs.getString("D");
                al.add(q);
                al.add(a);
                al.add(b);
                al.add(c);
                al.add(d);
            }
        }
        else{
        st.executeUpdate(query);
        al.add("done");
        return al;
        }              
            
        conn.close();       
        }catch(Exception e){
            System.out.println("Exception in admin connect"+e);
            al.add("fail");
            return al;
        }
        return al;
    }
    
}
