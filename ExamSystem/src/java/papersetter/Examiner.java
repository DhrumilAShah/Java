package papersetter;
import db.Examinerdb;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Examiner extends HttpServlet {
    private ArrayList<String> al=new ArrayList<>();
    private String username;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
        if(request.getParameter("username")!=null)
        username=request.getParameter("username");
        if(username==null){
            RequestDispatcher rd=request.getRequestDispatcher("Loginn");
            rd.forward(request, response);
        }
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        doPost(request,response);
        HttpSession ses=request.getSession();
        String uname=(String)ses.getAttribute(username);
        if(uname==null){
            RequestDispatcher rd=request.getRequestDispatcher("Loginn");
            rd.forward(request,response);
        }
        else{
        try{
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Examiner</title>");     
            out.println("<link rel=\"stylesheet\" href=\"newcss.css\" type=\"text/css\" >");      
            out.println("<style>");
            out.println(".submit{border : solid 3px #E9E9E9}");
            out.println(".submit:hover{background-color:#ADD8E6}");          
            out.println(".textarea{background-color: #E9E9E9;border: 2px solid #ADD8E6;"
                    + "width:98%;height:200px;}");          
            out.println("</style>");
            out.println("</head>");
            out.println("<body bgcolor='#222930' text='#E9E9E9' class='noselect'>");         
            out.println("<h2><center>Welcome Professor "+uname+"</cener></h2>");
            out.println("<div class='hr'>");
            out.println("</div>");
            out.println("Create a test Of 10 Questions From given list...!");
            out.println("<form method='POST' action='Examiner'>");               
            out.println("<input type='text' name='sub' class=\"inputs\" placeholder=\"Subject\" />");
            out.println("<input type='submit' class='submit' value='Display'/>");
            out.println("<input type='hidden' name='username' value='"+uname+"'/>");
                 
            if(request.getParameter("sub")!=null){           
            Examinerdb db=new Examinerdb(request.getParameter("sub"));
            al=db.connect();                         
            }
            out.print("<br><textarea readonly class='textarea' name='list' spellcheck='false'> "
                    +al+"</textarea>");    
            out.println("</form>");  
            out.println("<form method='POST' action='papertester'>");          
            out.print("<textarea class='textarea' name='quest' spellcheck='false' "
             +"placeholder=\"First Enter Table name then hyphen(-) and then question id"
             + " for example:mytable-'dp1','dp2',.....\"></textarea>");
            out.println("<input type='submit' class='submit' />");
            out.println("<input type='reset' class='submit' value='Clear'/>");
            out.println("<input type='submit' onclick=window.open('"+logout(request,response)+"') value='Logout' "
                    + "name='logout' class='submit'/>"); 
            out.println("<input type='hidden' name='username' value='"+uname+"'/>");
            out.println("<input type='hidden' name='subject' value='"+request.getParameter("sub")+"'/>");
            out.println("</form>");  
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){System.out.println("Exception in examiner "+ e);}
        
        }
    }
    public String logout(HttpServletRequest request,HttpServletResponse response) 
                                            throws ServletException, IOException{
        return "http://localhost:8080/ExamSyatem/Loginn";
    }
    @Override
    public void destroy(){
        
    }
}

    


