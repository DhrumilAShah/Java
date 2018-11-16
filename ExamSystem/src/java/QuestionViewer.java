import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuestionViewer extends HttpServlet {
    
   private ArrayList<String> uans;//stores user ans one by one
    private int i;//counter for name
    private int j;//counter for questionload
    private String user;
    private int counter;
    @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response){
       user=request.getParameter("user");
       HttpSession ses=request.getSession(false);
       uans=(ArrayList<String>)ses.getAttribute(user+"uanswer");
            uans.add(request.getParameter("u"));            
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
        response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance  
        response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility 
        doPost(request,response);
        
        HttpSession ses=request.getSession(false);
        try {     
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>QuestionViewer</title>"); 
            out.println("<link rel=\"stylesheet\" href=\"newcss.css\" type=\"text/css\" >");           
            out.println("<style>");
            out.println(".submit{border:solid 3px #6F4242;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body bgcolor='#E4DBBF' class='noselect'>");
            i=(int)ses.getAttribute(user+"i");
            j=(int)ses.getAttribute(user+"j");
            ArrayList<String> qList=new ArrayList<>();//question list
            ArrayList<String> canswer=new ArrayList<>();//stores correct answer
            qList =(ArrayList<String>)ses.getAttribute(user+"question");   
            canswer=(ArrayList<String>)ses.getAttribute(user+"answer");  
             out.println("<h1>Question Page</h1>");
             out.println("<h3>"+ses.getAttribute(user+"set")+"</h3>");
               if(i==11){
                    int score=0;
                    for(int l=1;l<11;l++){
                        String userans=uans.get(l);
                        if(userans==null)
                            userans="x";
                        if(userans.equals(canswer.get(l-1))){
                           score++;
                            System.out.println(score);
                        }                        
                    }
                    out.println("<form action='Result' method='POST'>");
                    out.println("<input type='hidden' value='"+ses.getAttribute(user+"set")+"' name='x'/>");
                    out.println("<input type='hidden' name='user' value='"+user+"'/>");        
                    out.println("<input type='hidden' name='result' value='"+score+"'/>");  
                    out.println("<br><br><br><br><br>"
                        + "<input type='submit' value='Submit all Answers' class='submit' />");                
                    out.println("</form>");                
                }
               else{
                    out.println("<form action='QuestionViewer' method='POST'>");
                    out.println("<fieldset style=\"border-radius:20px; padding:25px;border: 3px solid #6F4242;\">");
                    out.println("<legend>Q)"+qList.get(j)+"</legend>"); 
                    out.println("<input type='radio' value='A' name='u'/>" + qList.get(j+1) +"<br>");
                    out.println("<input type='radio' value='B' name='u'/>" + qList.get(j+2) +"<br>");
                    out.println("<input type='radio' value='C' name='u'/>" + qList.get(j+3) +"<br>");
                    out.println("<input type='radio' value='D' name='u'/>" + qList.get(j+4) +"<br><br>");              
                    out.println("</fieldset>");
                    j=(i*4)+i;
                    i++;
                    ses.setAttribute(user+"i",i);
                    ses.setAttribute(user+"j",j);
                    out.println("<br><br><input type='submit' value='Next'class='submit'/>");
                    out.println("<input type='hidden' name='user' value='"+user+"'/>");
                    out.println("</form>");
               }
                out.println("</body>");
                out.println("</html>");
            }catch(Exception e){System.out.println("Exception in questionViewer: "+e);}         
    } 
    @Override
    public void destroy(){       
    }
  
}
