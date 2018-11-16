import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Welcomepage extends HttpServlet {
    private String user=null;
    private int counter;
    @Override   
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        user=request.getParameter("username");
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
        HttpSession ses=request.getSession(false);
        String u=(String)ses.getAttribute(user);
        try {   
            counter++;
            ServletContext sc=request.getServletContext();
            sc.setAttribute("count",0);
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Welcomepage</title>"); 
            out.println("<link rel=\"stylesheet\" href=\"newcss.css\" type=\"text/css\" >");
            out.println("</head>");
            out.println("<body bgcolor='#E4DBBF'>");
            out.println("<form action='quest' method='POST'>");                                 
            out.println("<center><h1>Welcome "+u+"....</h1></center><br>");
            out.println("<br><center><input type='submit' value='Java' class='submit' name='subject'/>");
            out.println("<br><br><input type='submit' value='DesignPatterns' class='submit' name='subject'/>");
            out.println("<br><br><input type='submit' value='DataStructuresandalgorithm' class='submit' name='subject'/></center>");
            out.println("<input type='hidden' name='user' value='"+u+"'/>");
            out.println("<input type='hidden' name='counter' value='"+counter+"'/>");                   
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            }catch(Exception e){System.out.println("Exception in welcome "+e);} 
    }  
    @Override
    public void destroy(){
        
    }
    
}
