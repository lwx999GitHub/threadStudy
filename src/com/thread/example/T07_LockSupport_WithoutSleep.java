package com.thread.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

public class T07_LockSupport_WithoutSleep {

    volatile List list = new ArrayList();

    public void add(Object obj) {
        list.add(obj);
    }

    public int size() {
        return list.size();
    }

    static Thread t1=null;

    static Thread t2=null;



    public static void main(String[] args) {

        T07_LockSupport_WithoutSleep c = new T07_LockSupport_WithoutSleep();
       // CountDownLatch latch = new CountDownLatch(1);

       t1= new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add" + i);
                if(c.size()==5){
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
            System.out.println("t1结束");
        }, "t1");


        t2=new Thread(() -> {
            System.out.println("t2启动");
                 LockSupport.park();
            System.out.println("t2结束");
            LockSupport.unpark(t1);
        }, "t2");

        t1.start();
        t2.start();







    }


}
