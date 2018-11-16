package db;
import java.sql.*;
public class resultdb {
   //public static void main(String[] args) throws Exception{  
   public void add(String name,int marks,char set) throws Exception{
         Class.forName("org.apache.derby.jdbc.ClientDriver");
         Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/setdata",
                                                "root","root");
         Statement st=conn.createStatement();
        st.executeUpdate("INSERT INTO RESULTSET VALUES"
                        + "('"+name+"',"+marks+",'"+set+"')");
        System.out.println("Successfully inserted..!");
       conn.close(); 
    }
         
}
    

