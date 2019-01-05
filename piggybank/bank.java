package piggy;

public class bank {

   static int balance=0;
   static int wcnt,dcnt,i,l,undo,checkbalcount;
static int[] ltran=new int[10];

    public static void main(String[] args) 
     {
      System.out.println("Welcome to Procedural PiggyBank");
     deposit(100);
      deposit(200);
      deposit(300);
      deposit(400);
      deposit(500);
      deposit(600);
      withdraw(500);
      //statement();
      deposit(800);
      deposit(900);
      withdraw(1100);
      deposit(1200);
      deposit(1000);
      deposit(1000);
      undo();
     //withdraw(1500);
     //transaction();
      statement();
      //count();
     }

    
    static void deposit(int num)
    {   
        
        balance=num+balance;
        if(i>=10)
        {
         i=i-1;
         pop();
        }
        ltran[i]=num;
        i=i+1;
        System.out.println("Successfully deposited "+num+" rupees");
        dcnt++;
        if(num>=1000)
         checkbalcount++;
        if(checkbalcount==3)
        {
            System.out.println("alert");
            checkbalcount=0;
        }
        intrest();
    }
    static void withdraw(int num)
    {
       if(num>balance)
            System.out.println("Not enough balance");
       balance=balance-num;
       if(i>=10)
       {
           i=i-1;
           pop();
       }
       ltran[i]=-(num);
       i=i+1;
       wcnt++;
       System.out.println("Successfully withdrawn "+num+" rupees");
    }
    static void transaction()
    {
        for(int k=0;k<10;k++)
        {
            if(ltran[k]<0)
            {
                undo=ltran[k];
                ltran[k]=-(ltran[k]);
                System.out.println("You have withdrawn "+ltran[k]+" rupees");
            }
            else
            {
             undo=ltran[k];   
            System.out.println("You have deposited "+ltran[k]+" rupees");
            }
        }
    }
    static void statement()
    {
        System.out.println("Balance is "+balance+" rupees.");
    }
    public static void pop()
    {
        for(int m=0;m<9;m++)
        ltran[m]=ltran[m+1];
    }
   static void count()
   {
       System.out.println("You have deposited money "+dcnt+" times.");
       System.out.println("You have withdrawn money "+wcnt+" times.");
   }
   static void undo()
   {
       if(ltran[i-1]<0)
       {
            for(int u=9;u>1;u--)
            {
            if(ltran[u]==0)
               u--;
            balance=balance+undo;
            }
           System.out.println("Undo Successfully,credited "+ (-ltran[i-1])+" rupees");    
       }   
       else
       {
           balance=balance-undo;
       System.out.println("Undo Successfully,debited "+ltran[i-1]+" rupees"); 
       }
   }
   static void intrest()
   {
       int sum=0;
      if(dcnt>=10)
      {
          for(int d=0;d<10;d++)
          {
              sum+=ltran[d];
          }
      
       System.out.println("You have deposited 10 times."+sum);
   } 
   }

}