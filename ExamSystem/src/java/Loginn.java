import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import Authenticate.Authenticator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Loginn extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
        response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance  
        response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility 
        try{          
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>");  
            out.println("<link rel=\"stylesheet\" href=\"newcss.css\" type=\"text/css\" >");
            out.println("<style>");
            out.println(".submit{border : solid 3px #E9E9E9}");
            out.println(".submit:hover{background-color:#ADD8E6}");                   
            out.println("</style>");
            out.println("</head>");
            out.println("<body background=\"loginimg.jpg\" text='white'>"); 
            out.println("<form method='POST' >");
            out.println("<div class=\"boxed\">");
            out.println("<h2><center>Login page</center></h2>");
            out.println("<h3><center><input type='text' name='username' class=\"inputs\" placeholder=\"User name\" /></center></h3>");
            out.println("<h3><center><input type='password' name='pass'class=\"inputs\" placeholder=\"Password\" /></center></h3>");
            out.println("<h3><center><input type='submit' value='Submit' class='submit'/></center></h3>"); 
            out.println("</div>");
            String uname=request.getParameter("username");
            String p=request.getParameter("pass");
            if(uname!=null||p!=null){
            Authenticator a=new Authenticator(uname,p);
            try{
            String s=a.check();
            if(s.equals("admin")){              
                HttpSession ses=request.getSession(true);
                ses.setAttribute("admin","admin");
                RequestDispatcher rd=request.getRequestDispatcher("Adminpage");
                rd.forward(request,response);
            }
            else if(s.equals("examiner")){              
                HttpSession ses=request.getSession(true);
                ses.setAttribute(uname,uname);
                RequestDispatcher rd=request.getRequestDispatcher("Examiner");
                rd.forward(request,response);
            }
            else if(s.equals("user")){
                out.println("Login successful");              
                HttpSession ses=request.getSession(true);  
                ses.setAttribute(uname,uname);
                System.out.println("created new session: "+uname+" "+ses.getId());
                RequestDispatcher rd=request.getRequestDispatcher("Welcomepage");
                rd.forward(request,response);
                
            }   
            else{
                out.println("<br><br><br><br><br><br><br><br>Failed try again....");
            }        
            }catch(Exception e){System.out.println("exception in login "+e);}                               
            }                    
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){System.out.println("exception in last login "+e);}
    }
    @Override
    public void destroy(){
        
    }
}
