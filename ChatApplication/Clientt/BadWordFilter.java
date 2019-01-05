
public class BadWordFilter {
    private String str;
    
    BadWordFilter(String str){
        this.str=str;
    }
    public String check(){
        String strl=str.toLowerCase();        
         if(strl.contains("party")){
             String s=strl.replace("party", "****");
            return s;         
        }
         else if(strl.contains("fun")){
             String s=strl.replace("fun", "****");
            return s;         
        }
        else if(strl.contains("music")){
             String s=strl.replace("music", "****");
            return s;         
        }
        else if(strl.contains("stupid")){
             String s=strl.replace("stupid", "****");
            return s;               
        }
         else if(strl.contains("insane")){
             String s=strl.replace("insane", "****");
            return s;              
        }
         else if(strl.contains("idiot")){
             String s=strl.replace("idiot", "****");
            return s;               
        }
          else if(strl.contains("mad")){
             String s=strl.replace("mad", "****");
            return s;              
        }
           else if(strl.contains("enjoy")){
             String s=strl.replace("enjoy", "****");
            return s;             
        }
           else if(strl.contains("cheers")){
             String s=strl.replace("cheers", "****");
            return s;             
        }
           else if(strl.contains("drunk")){
             String s=strl.replace("drunk", "****");
            return s;             
        }
           else if(strl.contains("drugs")){
             String s=strl.replace("drugs", "****");
            return s;             
        }
           else if(strl.contains("game")){
             String s=strl.replace("game", "****");
            return s;             
        }
           else if(strl.contains("ssup")){
             String s=strl.replace("ssup", "****");
            return s;             
        }
        return str;
    }
}
