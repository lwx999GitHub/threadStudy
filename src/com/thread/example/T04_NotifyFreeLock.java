package com.thread.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T04_NotifyFreeLock {
List list =new ArrayList();

public void add(Object o){list.add(o);}
public int size(){return list.size();}

    public static void main(String[] args) throws InterruptedException {
        T04_NotifyFreeLock c=new T04_NotifyFreeLock();
        final Object lock=new Object();



        new Thread(()->{
            synchronized (lock){
                System.out.println("t2启动");
                if(c.size()!=5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
                System.out.println("t2结束");
            }

        }).start();

   TimeUnit.SECONDS.sleep(1);
      Thread  t1= new Thread(()->{
            synchronized (lock){
                System.out.println("t1启动");
                for (int i=0;i<10;i++){
                    System.out.println(i);
                    c.add(new Object());
                    if (c.size()==5){
                        lock.notify();
                        try {
                            lock.wait();
                           // System.out.println(Thread.currentThread().getState());
                            //System.out.println(t1.getState());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t1结束");
        });

      t1.start();

    }


}
