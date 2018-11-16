package db;
import java.sql.*;
public class insertion {
    void insert(String subject,String Questions,String A,String B,String C,String D,String Answer,String QID) 
            throws Exception{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/setdata",
                                                "root","root");
        Statement st=conn.createStatement();
        st.executeUpdate("insert into AllQUESTIONS VALUES ('"+subject+"','"+Questions+"',"
                + " '"+A+"' "
                + ", '"+B+"' ,"
                + " '"+C+"' ,"
                + " '"+D+"' ,"
                + " '"+Answer+"' ,"
                + " '"+QID+"'     )");
        conn.close();
    }
}
