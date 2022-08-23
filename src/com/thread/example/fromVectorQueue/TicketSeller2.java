package com.thread.example.fromVectorQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller2 {
    static Vector<String> tickets=new Vector<String>();

    static {
        for(int i=0;i<50;i++){
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(()->{
               // synchronized (tickets){
                    while(tickets.size()>0){
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("销售了--"+tickets.remove(0));
                    }
               // }
            }).start();
        }
    }//虽然vector是线程安全的，但是从本程序看它只对remove和size是线程安全的,主要问题是它中间有睡眠。
    //例如当只有最后一张票时，多个线程看到也是一张票。A线程看到有一张票,然后睡眠1秒,A线程看到有一张票,然后睡眠1秒。
    // 当A线程卖完最后一张票后,B线程也会去卖，但此时B去卖的时候,没有票了，就会有问题。
    // 要解决问题，对run方法的整个代码加锁(看注释)
}
