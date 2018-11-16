
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class last extends HttpServlet {
    private String user;
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String l=(String)request.getAttribute("logout");
        user=(String)request.getAttribute("user");
    }
     @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        doPost(request,response);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>last</title>");            
            out.println("</head>");
            out.println("<body bgcolor='#E4DBBF' text='41AAC4'>");
            out.println("<h3><center>Successfully logged out...</center></h3>");
            out.println("<h3><center>Thank You for Visiting...</center></h3>");
            out.println("</body>");
            out.println("</html>");
            ServletContext sc=request.getServletContext();
            sc.removeAttribute(user);
            HttpSession ses=request.getSession(false);
            ses.removeAttribute(user);
        }
        
    }
     @Override
    public void destroy(){
       
    }
    
}
