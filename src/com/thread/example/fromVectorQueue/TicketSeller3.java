package com.thread.example.fromVectorQueue;

import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class TicketSeller3 {
    //static Vector<String> tickets=new Vector<String>();
    static Queue<String> tickets = new ConcurrentLinkedQueue<String>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();
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
