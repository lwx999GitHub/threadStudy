package com.thread.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class T05_countDownLatch {

    volatile List list = new ArrayList();

    public void add(Object obj) {
        list.add(obj);
    }

    public int size() {
        return list.size();
    }


    public static void main(String[] args) {

        T05_countDownLatch c = new T05_countDownLatch();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("t2启动");
            if (c.size() != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 结束");
            latch.countDown();


        }, "t2").start();

/*        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add" + i);
                if (c.size() == 5) {
                    latch.countDown();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
  /*              try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

            }
            System.out.println("t1结束");
        }, "t1").start();
    }


}
