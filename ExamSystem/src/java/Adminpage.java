import db.admindb;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Adminpage extends HttpServlet {
private boolean flag=false;
private boolean displayflag=false;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ses=request.getSession();
        if((String)ses.getAttribute("admin")==null){
             RequestDispatcher rd=request.getRequestDispatcher("Loginn");
            rd.forward(request,response);   
       }
        else{
        try {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Adminpage</title>");  
            out.println("<link rel=\"stylesheet\" href=\"newcss.css\" type=\"text/css\" >");
            out.println("</head>");
            out.println("<body bgcolor='#70AB8F' text='#000000'>");
            out.println("<style>");                       
            out.println("#submit:hover{background-color: #D9853B;}");
            out.println("</style>");
            
            out.println("<h1><center class='noselect'>Welcome admin...!</center></h1>"); 
            
            out.println("<form method='POST' class='noselect' action='Adminpage'>"); 
            out.println("<br><h5>Insert ,Update or Delete records....</h5>"); 
            out.println("<center><textarea  class='textarea' name='query' onFocus=this.value='' spellcheck='false'>"//
                    + "</textarea></center><br><br>");                
            out.println("<input type='submit' name='submit' value='Submit' class='submit' />");
            out.println("<input type='reset' name='clear' value='Clear All' class='submit'/>");
            out.println("<input type='submit' onclick=window.open('"+logout(request,response)+"') value='Logout' "
                    + "name='logout' class='submit'/>");   
            out.println("</form>");        
           
            out.println("<script>");
          
            String query;
            if(request.getParameter("query")!=null && request.getParameter("logout")==null){
                query=request.getParameter("query");  
                System.out.println(query);
                admindb adb;
                String l= query.toLowerCase();
                String type=gettype(l);
                if(l.equalsIgnoreCase("select * from allpapers")){
                    adb=new admindb(query,"allpaper");
                    displayflag=true;
                }
                else if(l.contains("select") && l.contains("*")){
                    adb=new admindb(query,"display");
                    displayflag=true;
                }
                else
                    adb=new admindb(query,"normal");
                
                ArrayList<String> al=adb.connect();
                if(al.get(0).equals("fail")){
                    System.out.println("fail...");
                    out.println("window.alert('"+type+" execution Failed...try again')");
                }
                else{
                    System.out.println("done...");
                    out.println("window.alert('"+type+" execution Successfully done..')");
                    flag=true;
                }    
                if(flag && displayflag){
                    out.println("window.alert('"+al+"')");
                    al.clear();
                    flag=false;
                    displayflag=false;
                }           
            }                    
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){System.out.println("Exception in adminPage"+e);   
       }
     }       
    }
    public String logout(HttpServletRequest request,HttpServletResponse response) 
                                            throws ServletException, IOException{
        if(request.getParameter("logout")!=null){
           System.out.println("l");
            HttpSession ses=request.getSession(false);
            ses.removeAttribute("admin");
            ses.invalidate();    
        }   
        return "http://localhost:8080/ExamSyatem/Loginn";
    }
    
    String gettype(String q){
        String g=q.toLowerCase();
        if(g.contains("select"))
            return "Select query";
        else if(g.contains("insert"))
            return "Insert query";
        else if(g.contains("update"))
            return "Update query";
        else if(g.contains("delete"))
            return "Delete query";
        else
            return "Query";
    }
    @Override
    public void destroy(){
        
    }
}
