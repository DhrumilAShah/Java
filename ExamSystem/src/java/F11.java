import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class F11 implements Filter {
    private boolean allowed=false;
    private FilterConfig filterConfig = null;
    
    public F11() {
    }    
    
    private void doBeforeProcessing(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession ses=request.getSession();
        String user=request.getParameter("user");
        if(user==null){
            RequestDispatcher rd=request.getRequestDispatcher("Loginn");
            rd.forward(request, response);
        }

    }       
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {

    }
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException { 
         HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        doBeforeProcessing(req, res);
        chain.doFilter(request, response);      
       // doAfterProcessing(request, response);

    }
     @Override
    public void init(FilterConfig filterConfig) {
          this.filterConfig = filterConfig;
    }
   
    @Override
    public void destroy(){        
    }



    
}
