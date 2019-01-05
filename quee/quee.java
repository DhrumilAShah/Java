import java.util.*;
public class quee {
    public static void main(String[] args) {
        
        System.out.println("Welcome to String Queue.");
        Queue q=new Queue(5);
       q.enqueue(13);
       q.enqueue(23);
       q.enqueue(33);
       q.enqueue(43);
       q.enqueue(53);
       //q.enqueue(63);
      
      // q.print();
        System.out.println("deeeeequeueeeeee..........");
            int str1= q.dequeue();
            //int str2= q.dequeue();
           // int str3= q.dequeue();
           // int str4= q.dequeue();
           // int str5= q.dequeue();
           // int str6= q.dequeue();
            int str7= q.dequeue();
            q.print();
    }   
}
class Queue {
    
    int arr[],size;
     int tos=-1,head=0;
    
    Queue(int size){
        this.size=size;
        arr=new int[size];
    }
    void enqueue(int num) {
        if(tos>=size-1)
            System.err.println("Overflow");
        else
            arr[++tos]=num;
    }
    
    
    
    boolean isEmpty(){
        if (tos<=-1) {
            System.err.println("Underflow");
            return true; }
        else
            return false;
    }
    
    
    int dequeue() {
        if (tos<=-1) {
            System.err.println("Underflow");
            return 0;
        }
        int s=0;
        for(int h=0;h<arr.length-1;h++)
            {
                arr[h]=arr[h+1];
                arr[h+1]=0;
            }
           tos--;
          return arr[s++]; 
    }
    
    public void print() {
       
        if(isEmpty())
            System.err.println("Cannot print it is empty");
        else{
             System.out.println("Printing String Queue");
             for(int l=0;l<arr.length;l++) 
                    System.out.println(arr[l]);
           
            
        }
    }
}
