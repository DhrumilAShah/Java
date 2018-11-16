import db.resultdb;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Result extends HttpServlet {     
    private String set=null;
    private String user=null;
    private int result;
    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        user=request.getParameter("user");
        set=request.getParameter("x");
        result=Integer.parseInt(request.getParameter("result"));
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
        response.setContentType("text/html;charset=UTF-8");      
        response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
        response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance  
        response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility 
        doPost(request,response);   
        try (PrintWriter out = response.getWriter()){          
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Result</title>");         
            out.println("<link rel=\"stylesheet\" href=\"newcss.css\" type=\"text/css\" >");
            out.println("<style>");
            out.println(".submit{border:solid 3px #6F4242;}");           
            out.println("</style>");
            out.println("</head>");
            out.println("<body bgcolor='#E4DBBF' text='41AAC4'>");       
            float p=(float)(result*100)/10;              
            out.println("<br><br><br><br><br><br><br><br>");       
            out.println("<fieldset style=\"border-radius:20px; padding:25px;border: 3px solid #6F4242;\">");       
            out.println("<legend><h2>-Result Page-</h2></legend>");       
            out.println("<h3>"+result+"/10</h3>"+"<br>"+p+"%"); 
            out.println("</fieldset><br><br>"); 
            char sets=set.charAt(4);
             try{
                resultdb rdb=new resultdb();
                rdb.add(user,result,sets);
            }catch(Exception e){System.out.println("Exception in result "+e);}           
            out.println("<form action='last' method='POST'>");           
            out.println("<input type='submit' name='Logout' value='Logout' class='submit'>");           
            out.println("<input type='hidden' name='user' value='"+user+"'>");           
            out.println("</form>");           
            out.println("</body>");           
            out.println("</html>");
        }   
    }
    @Override
    public void destroy(){
        
    }
}

