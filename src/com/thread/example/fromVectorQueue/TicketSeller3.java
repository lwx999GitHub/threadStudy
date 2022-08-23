package com.thread.example.fromVectorQueue;

import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class TicketSeller3 {
    //static Vector<String> tickets=new Vector<String>();
    static Queue<String> tickets = new ConcurrentLinkedQueue<String>();
    //static String s = null;
    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                while (true) {
                 String  s= tickets.poll();//如果把s定义到上面去，就会有同步问题（当一个线程更新栈内存,
                    // 还没来得及更新堆内存，另一个更新栈内存就会有问题，就会出现同步问题）
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (s == null) {
                        break;
                    } else {
                        System.out.println("销售了--" + s);
                    }
                }
            }).start();
        }
    }
}
