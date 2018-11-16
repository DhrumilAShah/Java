package papersetter;

import db.Examinerdb;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class papertester extends HttpServlet {
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            PrintWriter out = response.getWriter();                         
            String s=request.getParameter("quest");
            if(s.length()>=30){
                int comma=0,quotes=0;               
                for(int i=0;i<s.length();i++){                   
                    if(s.charAt(i)==',')
                        comma++;                    
                    if(s.charAt(i)==39)
                        quotes++;
                }
                String proff=request.getParameter("username");
                String qid=s.substring(s.indexOf("-")+1,s.length());
                if(comma==9  && s.contains("-") && quotes==20){ 
                    Examinerdb db=new Examinerdb(request.getParameter("subject"));
                    String g=db.createtable(qid,s.substring(0, s.indexOf("-")),proff);
                    if(g.equals("true"))
                      out.println(""+s.substring(0, s.indexOf("-"))+ " was successfully created");
                    
                    else
                        out.println("Error Occured: "+g+"");
                }
                else if(comma!=9)
                   out.println("Invalid Entry:Insert Commas(,) properly"); 
                
                else if(quotes!=20)
                   out.println("Invalid Entry:Insert single Quotes(') properly"); 
                
                else if(!s.contains("-"))
                   out.println("Invalid Entry:Check hyphen(-) properly"); 
                
                else
                    out.println("Invalid Entry: Try Again");                          
            } 
            else
                out.println("Invalid Entry: Try Again");
            
             if(request.getParameter("logout")!=null){ 
                 String username=request.getParameter("username");
                HttpSession ses=request.getSession(false);
                ses.removeAttribute(username);
                ses.invalidate(); 
            }
            
        }catch(Exception e){System.out.println("Exception in doPost of papertest "+e);}       
    }


    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {      
        response.setContentType("text/html;charset=UTF-8");
        try  {
            PrintWriter out =response.getWriter();          
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>papertester</title>");
            out.println("<link rel=\"stylesheet\" href=\"newcss.css\" type=\"text/css\" >");
            out.println("<style>");
            out.println(".submit{border : solid 3px #E9E9E9}");
            out.println(".submit:hover{background-color:#ADD8E6}");                   
            out.println("</style>");
            out.println("</head>");
            out.println("<body bgcolor='#222930' text='#E9E9E9' class='noselect'>");
            out.println("<br><br><br><center>");     
            doPost(request,response);
            out.println("<br><br><br><br><br>");
            
            out.println("<div class='boxed'>");          
            out.println("<button onclick=\"goBack()\" class='submit'>Go Back</button>");
            out.println("</center>");
            out.println("</div>");
            
            out.println("<script>");
            out.println("function goBack() {");
            out.println("window.history.back();}");
            out.println("</script>");           
            out.println("</body>");
            out.println("</html>");
            
        }catch(Exception e){System.out.println("Exception in papertester "+e);}
    }


   
}
