package Serverx;
//import java.io.*;
import java.util.*;

public class  MessageQueue<T>{
    private ArrayList<T> al=new ArrayList<>();
    
    synchronized public void enqueue(T i){
      al.add(i);
      //notify();
    }
    synchronized public T dequeue(){
        if(al.isEmpty()){
            try{
                //al.wait();
            }catch(Exception e){System.out.println("Exception in message queue");}
        }
        return al.remove(0); 
    }
    synchronized public void print(){
        for(T i:al){
            System.out.println(i);
        }
    }
    synchronized public String toString(){
        String str=null;
        for(T i:al)
            str += "::"+i;
        return str;
        
    }
}
