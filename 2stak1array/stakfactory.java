public class stakfactory {

    public static void main(String[] args) {
        
      stack k=stack.getstack();
      stack j=stack.getstack();
      k.push(10);
      k.push(20);
      k.push(30);
      k.push(40);
      k.push(50);
      j.push(60);
      j.push(70);
      j.push(80);
      k.push(90);
      j.push(100);
      k.push(110);
      k.push(120);
      k.push(130);
     k.pop();
     k.pop();
     k.pop();
     k.pop();
     k.pop();
      k.pop();
      k.pop();
      k.pop();
      j.pop();
      j.pop();
      j.pop();
      j.pop();
      j.pop();
    }
    
}
class stack{
    static int tos1=-1,tos2=10,count=0,overflowcnt=0;
    int delta,s;
    static int[] a=new int[10];
    private stack(int delt){
        delta=delt;
    }
    public static stack getstack(){
       if(count==0){
           count++;
           System.out.println("Stack 1 created "+count+" length:"+a.length);
           stack p=new stack(1);
           return p;
       }
       else if(count==1){
           count++;
           System.out.println("Stack 2 creted "+count+" length: "+a.length);
           stack q=new stack(-1);
           return q;
       }
       else{
           System.out.println("Cannot create 3 stack");
           return null;
       }
    }
    public void push(int num){
        overflowcnt++;
        if(overflowcnt>10)
            System.out.println("Stack overflow");
        else{
            if(delta==1) {
                tos1++;
                a[tos1]=num;
                System.out.println("Successfully entered in stack 1 "+num+tos1);
            }
            else{
                tos2--;
                a[tos2]=num;
                System.out.println("Successfully entered in stack 2 "+num+tos2);
            }
        }
    }
    public int pop(){
            if(delta==1){
                if(tos1<0){
                    System.out.println("underflow");
                    return 0;
                }
                else{
                s=a[tos1];
                tos1--;
                System.out.println("Successfully poped from stack 1 "+ s);
                return s;
                }
            }
            else{
                if(tos2>=10){
                    System.out.println("underflow");
                    return 0;
                }    
                else{
                s=a[tos2];
                tos2++;
                System.out.println("Successfully poped from stack 2 "+tos2+s);
                return s;
                }
            }
     }
}
