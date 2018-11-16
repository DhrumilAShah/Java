import db.databaseconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class quest extends HttpServlet {
private String user,subject;
int counter;   
@Override
public void doPost(HttpServletRequest request,HttpServletResponse response){
    user=request.getParameter("user");
    subject=request.getParameter("subject");
    if(request.getParameter("counter")!=null)
    counter=Integer.parseInt(request.getParameter("counter"));
}
@Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        doPost(request,response);
        HttpSession ses=request.getSession(false);
        String u=(String)ses.getAttribute(user);
            try{                       
            System.out.println(counter);
            ArrayList<String> al=new ArrayList<>();
            ArrayList<String> ans=new ArrayList<>();
            ArrayList<String> uans=new ArrayList<>();
            databaseconnection db=new databaseconnection(al,ans);
            String set=null; 
                if((counter%4)==1){
                    set="Set A";  
                }
                else if((counter%4)==2){
                    set="Set B";   
                }
                else if((counter%4)==3){
                    set="Set C"; 
                }
                else if((counter%4)==0){
                    set="Set D"; 
                }          
            db.start(set,subject);
            ses.setAttribute(user+"question",al);
            ses.setAttribute(user+"answer",ans);
            ses.setAttribute(user+"uanswer",uans);
            ses.setAttribute(user+"set",set);
            ses.setAttribute(user+"counter",counter);
            ses.setAttribute(user+"i",1);
            ses.setAttribute(user+"j",0);        
            System.out.println("loaded "+set +"  "+user);            
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");        
            out.println("</head>");
            out.println("<body bgcolor='DDDDDD' text='41AAC4'>");
            out.println("<form action='QuestionViewer' method='POST'>");
            out.println("<input type='hidden' name='user' value='"+user+"'/>");                                   
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");              
            RequestDispatcher rd=request.getRequestDispatcher("QuestionViewer");
            rd.forward(request,response);
            }catch(Exception e){System.out.println("Exception in quest "+e);}
    }
}
