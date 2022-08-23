package com.thread.example.fromVectorQueue;

import java.util.ArrayList;
import java.util.List;

public class TicketSeller1 {
    static List<String> tickets=new ArrayList<String>();

    static {
        for(int i=0;i<1000;i++){
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                //synchronized (tickets){
                    while(tickets.size()>0){
                        System.out.println("销售了--"+tickets.remove(0));
                    }
               // }
            }).start();
        }
    }//多线程操作List,没加锁会出现线程安全问题,正确应该加锁（看注释）
}
