package Authenticate;
import java.io.FileReader;
import java.util.Properties;

public class Authenticator {
    private String username,pass;

    public Authenticator(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }
    public String check() throws Exception{
        FileReader fr=new FileReader("D:\\Documents\\NetBeansProjects\\ExamSyatem\\authenticate.properties"); 
        Properties p=new Properties();
        p.load(fr);
        String upass=p.getProperty(username);
        if(upass==null)
            return "fake";
        if(!upass.equals(pass))
            return "fake";
        if(username.equals("admin_boss_sir") && upass.equals(pass))
            return "admin";   
        if(username.contains("examiner") && upass.equals(pass))
            return "examiner";  
        return "user";
    }
}
