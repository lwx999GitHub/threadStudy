package com.threadPoll.example;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TicketSeller {
    static Queue<String > tickets=new ConcurrentLinkedDeque<String>();

    static {
        for(int i=0;i<100;i++){
            tickets.add("票 编号"+i);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                while(true){
                    String  s=tickets.poll();
                    if(s==null){
                        break;
                    }else{
                        System.out.println("销售了--"+s);
                    }
                }
            }).start();;
        }
    }
}
