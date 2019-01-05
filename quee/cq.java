
public class cq {

    
    public static void main(String[] args) {
        
    cqe theQueue = new cqe(5); // queue holds 5 items
    theQueue.insert(10); // insert 4 items
    theQueue.insert(20);
    theQueue.insert(30);
    theQueue.insert(40);
    theQueue.insert(10); // insert 4 items
  // theQueue.insert(20);
    //theQueue.insert(30);
   // theQueue.insert(40);
    theQueue.display();
    
        System.out.println(theQueue.remove()); // remove 3 items
   System.out.println(theQueue.remove()); // (10, 20, 30)
   // theQueue.remove();
    //theQueue.remove(); // remove 3 items
   System.out.println(theQueue.remove());
    System.out.println(theQueue.remove());
    System.out.println("---------------------");
    theQueue.display();
    // System.out.println("---------------------");
   //theQueue.insert(50); // insert 4 more items
 // theQueue.insert(60); // (wraps around)
  //theQueue.insert(70);
 //  theQueue.insert(80);
  //theQueue.display();
} // end main()
    
}
class cqe{
private int[] queArray;
private int rear,front,maxSize,count=0;
//-------------------------------------------------------------

public cqe(int s) // constructor
{
maxSize = s; // array is 1 cell larger
queArray = new int[s]; // than requested
front = 0;
rear = -1;
count=0;
}
//-------------------------------------------------------------
public void insert(int j) // put item at rear of queue
{
    if(count>=maxSize){
        System.out.println("Fulllllllllllllll");
    }
    else{
if(rear >= (maxSize-1)||count==maxSize){
rear = -1;
//count=0;
    System.out.println("-------------reset");
}        
queArray[++rear] = j;
count++;
System.out.println("-------"+rear);
System.out.println( count+"---------");
    }
}
//-------------------------------------------------------------
public int remove() // take item from front of queue
{
    if(count==0){
        System.out.println("Emptyyyyyyyyyyyyy");
        return 0;
    }
    else{
        int t=queArray[front];
    queArray[front]=0;
    //int temp = queArray[front++];
    front++;
    count--;
    if(front == maxSize){
        front = 0;
        //count=0;
    }   


        System.out.println("--------"+front+"---0"+count);
        
return t;
    }
}
//-------------------------------------------------------------
public int peek() // peek at front of queue
{
return queArray[front];

}

public int size() // (assumes queue not empty)
{
if(rear >= front) // contiguous sequence
return rear-front+1;
else // broken sequence
return (maxSize-front) + (rear+1);
}
public void display(){
    for(int i=0;i<queArray.length;i++)
        System.out.println(queArray[i]);
}
//-------------------------------------------------------------
} // end class Queue

    
    
