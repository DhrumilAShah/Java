
public class ll {

    
    public static void main(String[] args) {
        SimpleLink s1=new SimpleLink();
        s1.insert(50);
        s1.insert(20);
        s1.insert(10);
        s1.insert(45);
        s1.insert(55);
        s1.insert(15);
        s1.insert(95);
        s1.delete();
        s1.delete();
        s1.insert(950);
        s1.insert(905);
        s1.shortt();
        
        //s1.delete();
        //s1.delete();
      s1.displayLink();
    }
    
}
class link{
    public link next;
    public int data;

    public link(int dat) {
        data=dat;
    }
    public void display(){
        System.out.println(data+" ");
    } 
     
}

class SimpleLink{
    link head;
    private int c=0;
    
     SimpleLink(){
        head=null;
    }
   
     boolean emptycheck(){
          if(head==null){
          System.err.println("It is empty");
          return true;}
          else
          return false;
     }
     
     void insert(int dat){
       link l=new link(dat); 
       c++;
       if(head==null){
           head=l;
           head.next=null;
           System.out.println("null inserted to:"+dat);
       }
       else{
       l.next=head;
       head=l;
       System.out.println("inserted");
       }
         
     }
  void  delete(){
       if(emptycheck()){}
        else{
           c--;
      int temp=head.data;//autounboxing
      head=head.next;
      System.out.println("deleted:"+temp);
       }
  }  
  
  void displayLink(){
      link l=head;
      System.out.println("Displaying:");
      if(emptycheck()){}
       
      while(l!=null){
          l.display();
          l=l.next;
      }
  }
  void shortt(){
      link tem=head;
      int arr[]=new int[c];
      
       if(tem==null)
          System.err.println("It is empty"); 
       
        System.out.println("Sorting:");
       int k=0,in,out;
        while(tem!=null)
            {
                arr[k]=tem.data;
                k++;
                tem=tem.next; 
            }
            //int in,out;
                for( out=c-1; out>=0; out--)
                {
                    for(in=0; in<out; in++)
                    { 
                        if( arr[in] < arr[in+1] ) 
                        {  
                            int temp = arr[in];
                            arr[in] = arr[in+1];
                            arr[in+1] = temp;   
                        }
                    }
                   System.out.println(arr[in]); 
                }  
                
                
    }    
        
}  

  
    
    
