
public class circqueue {

    
    public static void main(String[] args) {
        
        cqueue ac=new cqueue(5);
	ac.insert(new Integer(1));
	ac.insert(new Integer(2));
	ac.insert(new Integer(3));
	ac.insert(new Integer(4));
	ac.insert(new Integer(5));
	System.out.println(ac.remove());
	System.out.println(ac.remove());
	System.out.println(ac.remove());
	System.out.println(ac.remove());
	ac.insert(new Integer(6));
	System.out.println(ac.remove());

    }
    
 }

class cqueue{
    private int size,rear,front=0,count=-1,dcnt=-1;
    Object q[];
    
    public cqueue(int siz){
        rear=size;
        q=new Object[size];
    }
    
    public void insert(Object o){
        System.out.println("value of count:"+count);
        if(count>=q.length)
            System.err.println("Overfow");
        else{
            if(count>=size && q[dcnt]==null){
                q[dcnt]=o;
            }
            else
            q[++count]=o;
            
            System.out.println("inertd:"+o);
        }
    }
    
    public Object remove(){
        if(count==-1){
            System.err.println("Underflow");
            return 0;
        }
        else{
            dcnt++;
            q[dcnt]=null;
            return q[--count];
        }
    }
    
}